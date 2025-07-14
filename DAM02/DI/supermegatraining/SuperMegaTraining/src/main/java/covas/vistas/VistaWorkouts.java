package covas.vistas;

import covas.model.Workout;
import covas.model.Usuari;
import covas.model.Exercici;
import java.awt.Cursor;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import covas.dataaccess.DataAccess;
import covas.utils.Utilitats;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Toni Covas
 */
public class VistaWorkouts extends javax.swing.JPanel {

    private Main main = null;
    private Usuari usuariSeleccionat = null;
    private DefaultTableModel model;
    private JTable jTableWorkOuts;
    private DefaultTableModel modelExercicis;
    private Workout workoutSeleccionat = null;
    private JTable jTableExercicis;

    public VistaWorkouts(Main mainJframe) {
        initComponents();
        main = mainJframe;

        //definim el MigLayout amb les seves propietats per les instàncies de l'bojecte VistaWorkouts
        setLayout(new MigLayout("fill, insets 1", "[grow, center]", "[]1[]"));

        // obtenim (a partir del nom) l'objecte Usuari de l'usuari seleccionat a VistaUsuaris
        ArrayList<Usuari> usuaris = DataAccess.getAllUsers();
        for (int i = 0; i < usuaris.size(); i++) {
            if (usuaris.get(i).getNom().equals(main.getUsuariSeleccionatTexte())) {
                usuariSeleccionat = usuaris.get(i);
            }
        }

        // amb aquests 3 mètodes s'omplirà tot el que hi ha dins el panell principal
        // s'ah dividit la càrrega inicial en els 3 apartats principal
        carregarFoto();
        carregarTaulaWorkouts();
        carregarTaulaExercicis();

    }

    // llegim la foto de la base de dades que té l'usuari i la mostram dins un JLabel
    public void carregarFoto() {
        JLabel jLabelFoto;
        byte[] fotoBytes = usuariSeleccionat.getFoto();

        // Verificar si fotoBytes es null
        if (fotoBytes != null) {
            // Convertir l' array de bytes en una imatge
            BufferedImage imagen = null;
            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(fotoBytes);
                imagen = ImageIO.read(bais);
            } catch (IOException e) {
                e.printStackTrace();
            }
            

            // Crear un JLabel per mostrar la imatge            
            jLabelFoto = new JLabel();
            if (imagen != null) {

                int anchoDeseado = 75; 
                int anchoOriginal = imagen.getWidth();
                int altoOriginal = imagen.getHeight();
                int altoDeseado = (anchoDeseado * altoOriginal) / anchoOriginal;  // per l'altura mantenim la proporció a partir de l'ample que li hem definit
                
                // disminuim la imatge en base a l'ample i altura que li acabam de definir
                Image imagenRedimensionada = imagen.getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);

                jLabelFoto.setIcon(new ImageIcon(imagenRedimensionada));
            } else {
                jLabelFoto.setText("No se pudo cargar la imagen");
            }
        } else {
            // Mostrar un mensaje si fotoBytes es null
            jLabelFoto= new JLabel("No hay foto disponible");
        }

        
        // posam un "marco" al jLabel de la foto
        Color borderColor = new Color(255, 200, 0); // Color entre groc i taronja
        Border border = BorderFactory.createLineBorder(borderColor, 5);
        jLabelFoto.setBorder(border);

        // introduim el jlabel de la foto dins l'objecte principal, amb els criteris de MigLayout
        add(jLabelFoto, "span, align center, wrap");

    }

    public void carregarTaulaWorkouts() {
        // model que farà de base a la taula workouts
        model = new DefaultTableModel();
        model.setColumnCount(3);
        model.setColumnIdentifiers(new Object[]{"ForDate", "Id", "Comments"});

        // inicialitzam la taula de workouts
        jTableWorkOuts = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // definim que totes les cel.les de la taula són no editables
            }
        };

        //cream un objecte de tipus tableHeader per l'encapçalat de la taula workouts
        JTableHeader th = jTableWorkOuts.getTableHeader();

        // determinam què s'ha de fer quan feim doble click damunt la taula workouts     
        jTableWorkOuts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Ejecutar el mètodo quan feim doble click
                    jButtonModificarWorkoutActionPerformed(null);
                }
            }
        });

        // omplim el model de la taula workouts amb informació que llegim de la base de dades
        actualizarTablaWorkouts(main.getUsuariSeleccionatTexte());

        
        jScrollPaneWorkouts.getViewport().add(jTableWorkOuts);

        jLabelListaWorkouts.setText("Estos es el listado de Workouts del usuario: " + main.getUsuariSeleccionat().getNom());
        jLabelListaWorkouts.setVisible(true);

        jLabelIrAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  // en fer click al jLabel, volem canviar el cursor del ratolí
        jLabelIrAtras.setVisible(true);

        jButtonAnadirWorkout.setText("Añadir Workout");        
        jButtonBorrarWorkout.setText("Borrar Workout");        
        jButtonModificarWorkout.setText("Modificar Workout");
        
        // definim i inicalitzam (amb propietats MigLayout) un panell on hi posam la taula de workouts, boton per gestionar workouts i un jlabel pel títol
        JPanel jPanelWorkouts = new JPanel(new MigLayout("fill, insets 1", "[grow, fill]", "[]1[]"));
        jPanelWorkouts.add(jLabelListaWorkouts, "wrap");
        jPanelWorkouts.add(jScrollPaneWorkouts, "wrap");
        jPanelWorkouts.add(jButtonAnadirWorkout, "split 3, sg btn");
        jPanelWorkouts.add(jButtonBorrarWorkout, "sg btn");
        jPanelWorkouts.add(jButtonModificarWorkout, "sg btn, wrap");

        // definim i inicialitzam amb base migLayout un panell per la taula d'exercicis
        JPanel jPanelExercicis = new JPanel(new MigLayout("fill, insets 1", "[grow, fill]", "[]1[]"));
        jPanelExercicis.add(jLabelListaExercicis, "wrap");
        jPanelExercicis.add(jScrollPaneExercicis, "wrap");

        // introduim els dos panells (workout i exercicis) i l'icono per tornar a la pàgina principal, dins el cos de l'bojecte principal VistaWorkouts
        add(jPanelWorkouts, "grow");
        add(jPanelExercicis, "grow, wrap");        
        add(jLabelIrAtras, "span, align center, wrap");

        // definim per la taula de workouts, el que volem que faci cada vegada que ens movem de fila = actualitzar la taula d'exercicis
        jTableWorkOuts.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Verificar si hay filas en la tabla y si hay una fila seleccionada
                    if (jTableWorkOuts.getRowCount() > 0 && jTableWorkOuts.getSelectedRow() != -1) {
                        // Limpiar la tabla de ejercicios
                        for (int i = modelExercicis.getRowCount() - 1; i >= 0; i--) {
                            modelExercicis.removeRow(i);
                        }
                        // Actualizar la tabla de ejercicios con la fila seleccionada
                        actualizarTablaEjercicios((int) jTableWorkOuts.getValueAt(jTableWorkOuts.getSelectedRow(), 1));
                    }
                }
            }
        });

        //ajustam la taula de col.lumnes de la taula Workouts a l'ample del texte que conté
        ajustarAnchoColumnas(jTableWorkOuts);

        repaint();

    }

    private void ajustarAnchoColumnas(JTable table) {
        TableModel model = table.getModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Component c = table.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);
            }
            tableColumn.setPreferredWidth(preferredWidth);
        }
    }

    public void carregarTaulaExercicis() {
        // cream un taula Exercicis i la col.locarem dins jScrollPaneExercicis
        // aquest scrollPane ja s'havia definit i insertat dins l'objecte principal
        // al mètode carregarWorkouts
        
        
        jTableExercicis = new JTable();
        JTableHeader thExercicis = jTableExercicis.getTableHeader();
        thExercicis.setFont(new Font("Serif", Font.BOLD, 15));

        
        // inicialitzam modelExercicis que serà la base d'informació per la taula d'exercicis
        modelExercicis = new DefaultTableModel();
        modelExercicis.setColumnCount(3);
        modelExercicis.setColumnIdentifiers(new Object[]{"IdExercici", "Exercici", "Descripcio"});
        
        jTableExercicis.setModel(modelExercicis);
        

        if (model.getRowCount() > 0) {
            actualizarTablaEjercicios((int) jTableWorkOuts.getValueAt(jTableWorkOuts.getSelectedRow(), 1));
        }

        
        jScrollPaneExercicis.getViewport().removeAll();
        jScrollPaneExercicis.getViewport().add(jTableExercicis);

        
        if (workoutSeleccionat != null) {
            jLabelListaExercicis.setText("Això és el llistat d'exercicis del workout: " + workoutSeleccionat.getId());
        } else {
            jLabelListaExercicis.setText("");
        }

        jLabelListaExercicis.setVisible(true);

        
        ajustarAnchoColumnas(jTableExercicis);
        repaint();

    }

    
    
    public void seleccionarFilaPorId(int workoutId) {
    for (int i = 0; i < jTableWorkOuts.getRowCount(); i++) {
        if ((int) jTableWorkOuts.getValueAt(i, 1) == workoutId) {
            jTableWorkOuts.setRowSelectionInterval(i, i);
            // Asegurarse de que la fila seleccionada sea visible
            jTableWorkOuts.scrollRectToVisible(jTableWorkOuts.getCellRect(i, 0, true));
            break;
        }
    }
}

    
    
    

    public void actualizarTablaWorkouts(String usuarioIdTexto) {
        // Eliminar todas las filas de la tabla
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        // Obtener el usuario seleccionado
        ArrayList<Usuari> usuaris = DataAccess.getAllUsers();
        for (int i = 0; i < usuaris.size(); i++) {
            if (usuaris.get(i).getNom().equals(usuarioIdTexto)) {
                usuariSeleccionat = usuaris.get(i);
            }
        }

        main.setUsuariSeleccionatTexte(null);

        // Obtener los workouts del usuario seleccionado , llegint a la base de dades
        ArrayList<Workout> workouts = DataAccess.getWorkoutsPerUser(usuariSeleccionat);

        // Ordenam les dades per la columna "ID" 
        Collections.sort(workouts, new Comparator<Workout>() {
            @Override
            public int compare(Workout w1, Workout w2) {
                return Integer.compare(w1.getId(), w2.getId());
            }
        });

        for (Workout w : workouts) {

            
            //recorrem tots els workouts de l'usuari i modificam el format de la data
            // el que després integram dins el DataDefaultModel del workout és la data amb format ajustat
            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = null;
            try {
                date = originalFormat.parse(w.getForDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dataFormatAjustat = targetFormat.format(date);

            model.addRow(new Object[]{dataFormatAjustat, w.getId(), w.getComments()});
        }

        // Verificar si la tabla tiene al menos una fila antes de seleccionar
        if (model.getRowCount() > 0) {
            jTableWorkOuts.changeSelection(0, 0, false, false);
        }

        main.setUsuariSeleccionat(usuariSeleccionat);
    }

    public void actualizarTablaEjercicios(int idWorkout) {
        //llegim de la base de dadesels exercicis de l'usuari seleccionat, 
        ArrayList<Workout> workouts = DataAccess.getWorkoutsPerUser(usuariSeleccionat);

        for (int i = 0; i < workouts.size(); i++) {
            if (workouts.get(i).getId() == idWorkout) {
                workoutSeleccionat = workouts.get(i);
            }
        }

        ArrayList<Exercici> exercicis = DataAccess.getExercicisPerWorkout(workoutSeleccionat);

        // Ordenam les dades per la col.lumna "ID" de l'exercici
        Collections.sort(exercicis, new Comparator<Exercici>() {
            @Override
            public int compare(Exercici w1, Exercici w2) {
                return Integer.compare(w1.getId(), w2.getId());
            }
        });

        // omplim la base de dades amb l'ordre que acabam de definir
        for (Exercici e : exercicis) {
            modelExercicis.addRow(new Object[]{e.getId(), e.getNomExercici(), e.getDescripcio()});
        }

        jLabelListaExercicis.setText("Això és el llistat d'exercicis del workout: " + workoutSeleccionat.getId());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneWorkouts = new javax.swing.JScrollPane();
        jLabelListaWorkouts = new javax.swing.JLabel();
        jLabelIrAtras = new javax.swing.JLabel();
        jScrollPaneExercicis = new javax.swing.JScrollPane();
        jLabelListaExercicis = new javax.swing.JLabel();
        jButtonAnadirWorkout = new javax.swing.JButton();
        jButtonBorrarWorkout = new javax.swing.JButton();
        jButtonModificarWorkout = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(900, 400));
        setLayout(null);

        jScrollPaneWorkouts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPaneWorkoutsMouseClicked(evt);
            }
        });
        add(jScrollPaneWorkouts);
        jScrollPaneWorkouts.setBounds(10, 50, 2, 2);

        jLabelListaWorkouts.setText("jLabel1");
        add(jLabelListaWorkouts);
        jLabelListaWorkouts.setBounds(50, 220, 330, 16);

        jLabelIrAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/flecha-atras.png"))); // NOI18N
        jLabelIrAtras.setText("Volver");
        jLabelIrAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelIrAtrasMouseClicked(evt);
            }
        });
        add(jLabelIrAtras);
        jLabelIrAtras.setBounds(260, 250, 118, 60);
        add(jScrollPaneExercicis);
        jScrollPaneExercicis.setBounds(330, 50, 2, 2);

        jLabelListaExercicis.setText("jLabel1");
        add(jLabelListaExercicis);
        jLabelListaExercicis.setBounds(10, 190, 710, 16);

        jButtonAnadirWorkout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/boton_anadir.png"))); // NOI18N
        jButtonAnadirWorkout.setMnemonic('A');
        jButtonAnadirWorkout.setText("jButton1");
        jButtonAnadirWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnadirWorkoutActionPerformed(evt);
            }
        });
        add(jButtonAnadirWorkout);
        jButtonAnadirWorkout.setBounds(40, 320, 140, 39);

        jButtonBorrarWorkout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/boton_eliminar.png"))); // NOI18N
        jButtonBorrarWorkout.setMnemonic('B');
        jButtonBorrarWorkout.setText("jButton1");
        jButtonBorrarWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarWorkoutActionPerformed(evt);
            }
        });
        add(jButtonBorrarWorkout);
        jButtonBorrarWorkout.setBounds(40, 360, 140, 39);

        jButtonModificarWorkout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/boton_modificar.png"))); // NOI18N
        jButtonModificarWorkout.setMnemonic('M');
        jButtonModificarWorkout.setText("jButton1");
        jButtonModificarWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarWorkoutActionPerformed(evt);
            }
        });
        add(jButtonModificarWorkout);
        jButtonModificarWorkout.setBounds(40, 400, 140, 39);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelIrAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelIrAtrasMouseClicked
        // aquí definim el que feim quan anam cap enrera (que és tornar a la pàgina on mostram la llista d'usuaris
        
        main.getContentPane().remove(this);
        main.revalidate();
        main.repaint();
        main.carregarLlistaUsuarisInstructor();
    }//GEN-LAST:event_jLabelIrAtrasMouseClicked

    private void jScrollPaneWorkoutsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPaneWorkoutsMouseClicked


    }//GEN-LAST:event_jScrollPaneWorkoutsMouseClicked

    private void jButtonAnadirWorkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnadirWorkoutActionPerformed
        // obrim jDialog amb el paràmetre "afegir", ja que aquest jDialog té dos paràmetres: "afegir" i " modificar"
        main.addChangeWorkouts("afegir", null);       
        
        // el següent ho fa quan tancam el jDialog, bàsicament actualitzar taula exercicis i workouts
        actualizarTablaWorkouts(main.getUsuariSeleccionat().getNom());

        for (int i = modelExercicis.getRowCount() - 1; i >= 0; i--) {
            modelExercicis.removeRow(i);
        }

        if (model.getRowCount() > 0) {
            actualizarTablaEjercicios((int) jTableWorkOuts.getValueAt(jTableWorkOuts.getSelectedRow(), 1));
        }

        seleccionarFilaPorId(main.getDarrerWorkoutAfegit());

    }//GEN-LAST:event_jButtonAnadirWorkoutActionPerformed

    private void jButtonBorrarWorkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarWorkoutActionPerformed

        if (modelExercicis.getRowCount()>0) {
            JOptionPane.showMessageDialog(null, "No s'ha pogut eliminar el workout perquè té exercicis. Per el.liminar el workout, primer haurà d'el.liminar els exercicis associats", "Error", JOptionPane.ERROR_MESSAGE);            
            return;
        }
        
        
        int result = JOptionPane.showConfirmDialog(null, "¿Seguro que vols el.liminar el workout seleccionat?", "Confirmació", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        // Verificar la selección del usuario
        if (result == JOptionPane.NO_OPTION) {
            // Si l'usuario selecciona "No", acabam l'execució del mètode
            return;
        }

        int numeroWorkoutAEliminar = (int) jTableWorkOuts.getValueAt(jTableWorkOuts.getSelectedRow(), 1);
        try {
            DataAccess.deleteFromWorkoutTable(numeroWorkoutAEliminar); // el.liminam el workout seleccionat a la base de dades
            model.removeRow(jTableWorkOuts.getSelectedRow());
            actualizarTablaWorkouts(main.getUsuariSeleccionat().getNom());
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Hi ha hagut errors i no s'ha pogut borrar el workout");
        }

        for (int i = modelExercicis.getRowCount() - 1; i >= 0; i--) {
            modelExercicis.removeRow(i);
        }

        if (model.getRowCount() > 0) {
            actualizarTablaEjercicios((int) jTableWorkOuts.getValueAt(jTableWorkOuts.getSelectedRow(), 1));
        } else {
            jLabelListaExercicis.setText("");
        }
        
        JOptionPane.showMessageDialog(null, "El workout amb numero " + numeroWorkoutAEliminar + " s'ha pogut el.liminar correctament.  ", "Eliminació de Workout", JOptionPane.INFORMATION_MESSAGE, Utilitats.obtenirIcon("checklist.png"));


    }//GEN-LAST:event_jButtonBorrarWorkoutActionPerformed

    private void jButtonModificarWorkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarWorkoutActionPerformed
        
        // inicialitzam un nou workout amb la  informació del workout seleccionat a la taula workouts
        Workout workoutModificar = new Workout();
        workoutModificar.setForDate((String) jTableWorkOuts.getValueAt(jTableWorkOuts.getSelectedRow(), 0));
        workoutModificar.setComments((String) jTableWorkOuts.getValueAt(jTableWorkOuts.getSelectedRow(), 2));
        workoutModificar.setId((int) jTableWorkOuts.getValueAt(jTableWorkOuts.getSelectedRow(), 1));
        workoutModificar.setIdUsuari(main.getUsuariSeleccionat().getId());

        // obrim el jDialog en mode "modificar", per editar el workout que acabam de inicialitzar
        main.addChangeWorkouts("modificar", workoutModificar);

        // això és el que fa quan tancam el jDialog
        actualizarTablaWorkouts(main.getUsuariSeleccionat().getNom());

        // col.locar el cursor de la taula workouts damunt el workout que acabam de modificar
        seleccionarFilaPorId(workoutModificar.getId());


    }//GEN-LAST:event_jButtonModificarWorkoutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnadirWorkout;
    private javax.swing.JButton jButtonBorrarWorkout;
    private javax.swing.JButton jButtonModificarWorkout;
    private javax.swing.JLabel jLabelIrAtras;
    private javax.swing.JLabel jLabelListaExercicis;
    private javax.swing.JLabel jLabelListaWorkouts;
    private javax.swing.JScrollPane jScrollPaneExercicis;
    private javax.swing.JScrollPane jScrollPaneWorkouts;
    // End of variables declaration//GEN-END:variables
}
