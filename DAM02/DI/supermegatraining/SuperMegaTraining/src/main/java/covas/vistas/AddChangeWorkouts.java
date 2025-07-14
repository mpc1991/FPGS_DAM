package covas.vistas;

import covas.dataaccess.DataAccess;
import covas.model.Exercici;
import covas.model.Usuari;
import covas.model.Workout;
import covas.utils.DateLabelFormatter;
import covas.utils.Utilitats;
import static covas.vistas.Main.main;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Toni Covas
 */
public class AddChangeWorkouts extends javax.swing.JDialog {

    /**
     * @return the modo
     */
    public String getModo() {
        return modo;
    }

    /**
     * @param modo the modo to set
     */
    public void setModo(String modo) {
        this.modo = modo;
    }

    private Main main = null;
    private JDatePickerImpl datePicker;
    private JTextField jTextFieldComentaris;
    private JSpinner timeSpinner;
    private Workout workoutModificar;
    private String modo;
    private JTable jTableExercicis;
    private DefaultTableModel modelExercicis;
    private Workout workoutSeleccionat;
    private Workout workoutPerAfegir = null;
    private JButton jButtonAfegirModificar;
    private UtilDateModel model;
    private boolean pasAfegirAModificar = false;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * Creates new form AddChangeWorkouts
     */
    public AddChangeWorkouts(java.awt.Frame parent, boolean modal, String modo, Workout workoutModificar) {
        super(parent, modal);
        this.modo = modo;
        this.workoutModificar = workoutModificar;
        main = (Main) parent;
        initComponents();

        setLayout(new MigLayout("fill"));

        JPanel jPanelAfegirEditarWorkouts = new JPanel(new MigLayout("fill, insets 1", "[grow, fill]", "[]1[]"));

        model = new UtilDateModel();
        if (modo.equals("afegir")) {
            model.setSelected(false);
        }
        Properties p = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setPreferredSize(new Dimension(150, datePicker.getPreferredSize().height));

        SpinnerDateModel timeModel = new SpinnerDateModel();
        timeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setVisible(true);
        timeSpinner.setPreferredSize(new Dimension(100, timeSpinner.getPreferredSize().height));

        jTextFieldComentaris = new JTextField();
        jTextFieldComentaris.setPreferredSize(new Dimension(100, jTextFieldComentaris.getPreferredSize().height));

        jLabelTitol.setText("AFEGIR UN NOU WORKOUT");

        jPanelAfegirEditarWorkouts.add(jLabelTitol, "span, align center , wrap");
        jPanelAfegirEditarWorkouts.add(jLabelComentaris);
        jPanelAfegirEditarWorkouts.add(jTextFieldComentaris, "grow, wrap");
        jPanelAfegirEditarWorkouts.add(jLabelData);
        jPanelAfegirEditarWorkouts.add(datePicker, "wrap");
        jPanelAfegirEditarWorkouts.add(jLabelHora);
        jPanelAfegirEditarWorkouts.add(timeSpinner, "wrap");

        add(jPanelAfegirEditarWorkouts, "grow, wrap");

        JPanel buttonPanel = new JPanel(new MigLayout("fill, insets 0", "[grow, fill][grow, fill]", "[]"));

        jButtonAfegirModificar = new JButton();

        
        // configuración del jButton para el caso del modo=Afegir
        if (this.modo.equals("afegir")) {
            jButtonAfegirModificar.setText("Afegir");
            jButtonAfegirModificar.setIcon(Utilitats.obtenirIcon("pulgar-arriba.png"));
            jButtonAfegirModificar.setMnemonic('A');
            jButtonAfegirModificar.setVisible(true);

        }

        jButtonAfegirModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // el codi a executar del boto AfegirModificar depen de si estam en mode "afegir" o "modificar"

                if (getModo().equals("afegir")) {
                    guardarNouWorkout();
                }

                if (getModo().equals("modificar")) {

                    if (!pasAfegirAModificar) {
                        actualitzarWorkout();
                    } else {
                        pasAfegirAModificar = false;
                    }
                }

            }
        });

        buttonPanel.add(jButtonAfegirModificar, "grow");
        buttonPanel.add(jButtonSortir, "grow");
        add(buttonPanel, "grow, wrap");

        if (modo.equals("modificar")) {
            ajustosPantallaModeModificar();
        }

        pack();
        repaint();

    }

    public void ajustosPantallaModeModificar() {

        jLabelTitol.setText("MODIFICAR EL WORKOUT " + workoutModificar.getId());
        jTextFieldComentaris.setText(workoutModificar.getComments());

        model.setSelected(true);

        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            Date date = dateTimeFormat.parse(workoutModificar.getForDate());
            model.setValue(date); // Configurar la data en el datePicker
            timeSpinner.setValue(date); // Configurar l'hora en el timeSpinner
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // si estam en mode modificar, canviam el texte i l'aspecte del botó
        jButtonAfegirModificar.setText("Guardar canvis");
        jButtonAfegirModificar.setIcon(Utilitats.obtenirIcon("pulgar-arriba.png"));
        jButtonAfegirModificar.setMnemonic('G');

        crearTaulaExercicis();

        JPanel jPanelExercicis = new JPanel(new MigLayout("fill, insets 1", "[grow]", "[]20[]"));
        JButton botoAfegirExercici = new JButton("+ Exercici");
        JButton botoEliminarExercici = new JButton("- Exercici");

        botoEliminarExercici.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
                //com,provam que hi hagi algun fila seleccionada a la taula d'exercicis
                if (jTableExercicis.getSelectedRow()==-1) {
                    JOptionPane.showMessageDialog(null, "Primerament hi ha d'haver algun exercici seleccionat", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int idExerciciSeleccionat = (int) jTableExercicis.getValueAt(jTableExercicis.getSelectedRow(), 0);
              
                // confirmació per l'usuari que realment bol eliminar l'exercici                
                int result = JOptionPane.showConfirmDialog(null, "¿Segur que vol el.liminar l'exercici seleccionat?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                // Si l'usuari respon que "no" llavors s'acaba i no continiua amb l'execució del codi del mètode
                if (result == JOptionPane.NO_OPTION) {
                    return;
                }

                

                int affectedRows = 0;
                try {
                    affectedRows = DataAccess.deleteFromExercicisWorkoutsTable(idExerciciSeleccionat, workoutModificar.getId());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar l'exercici, transmeti aquest missatge a l'administrador del sistema: \n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                if (affectedRows > 0) {
                    actualizarTablaEjercicios(workoutModificar.getId());
                    JOptionPane.showMessageDialog(null, "Exercici eliminat correctament", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No s'ha pogut eliminar cap exercici", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botoAfegirExercici.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Exercici> totsExercicis = DataAccess.getAllExercicis();

                // Cream un JComboBox hi afegim els exercicis
                JComboBox<String> comboBox = new JComboBox<>();
                for (Exercici exercici : totsExercicis) {
                    comboBox.addItem(exercici.getId() + " - " + exercici.getDescripcio());
                }

                // Mostrar el JComboBox dins d'un JOptionPane
                int result = JOptionPane.showConfirmDialog(null, comboBox, "Seleccionar Exercici", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                int selectedId = 0;
                int numeroInsercions = 0;
                Exercici selectedExercici = null;
                // Verificar si l' usuario va fer clic en OK, si és així seguim endavant. Si no, s'acaba l'execució
                if (result == JOptionPane.OK_OPTION) {
                    // Obtener el exercici seleccionado
                    String selectedItem = (String) comboBox.getSelectedItem();
                    if (selectedItem != null) {
                        // Extraer el id del exercici seleccionado
                        String[] parts = selectedItem.split(" - ");
                        selectedId = Integer.parseInt(parts[0]);
                        System.out.println("ID del exercici seleccionado: " + selectedId);
                    }

                    for (Exercici exercici : totsExercicis) {
                        if (exercici.getId() == selectedId) {
                            selectedExercici = exercici;
                        }
                    }

                    boolean jaExisteix = false;

                    DefaultTableModel modelCopia = (DefaultTableModel) jTableExercicis.getModel();
                    int rowCount = modelCopia.getRowCount();

                    for (int i = 0; i < rowCount; i++) {
                        int id = (int) modelCopia.getValueAt(i, 0); // Obtener el valor de la primera columna (IdExercici)
                        if (id == selectedId) {
                            jaExisteix = true; // El id ja existia dins el workout
                        }
                    }

                    // només insertam l'exercici en cas que no existís ja dins el workout en qüestió.
                    if (!jaExisteix) {

                        numeroInsercions = DataAccess.insertExerciciPerWorkout(workoutModificar.getId(), selectedExercici);

                        if (numeroInsercions > 0) {
                            actualizarTablaEjercicios(workoutModificar.getId());
                            JOptionPane.showMessageDialog(null, "Exercici introduit correctament", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "No s'ha pgout insertar l'exercici", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "No s'ha pgout insertar l'exercici perquè aquest workout ja contenia aquest exercici", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }

        });

        Dimension buttonSize = new Dimension(10, botoAfegirExercici.getPreferredSize().height);

        botoAfegirExercici.setPreferredSize(buttonSize);
        botoAfegirExercici.setIcon(Utilitats.obtenirIcon("mas.png"));

        botoEliminarExercici.setPreferredSize(buttonSize);
        botoEliminarExercici.setIcon(Utilitats.obtenirIcon("menos.png"));

        jPanelExercicis.add(botoAfegirExercici, "split 2, gapright 5px, align center, gapy 20px");
        jPanelExercicis.add(botoEliminarExercici, "wrap");

        JScrollPane jScrollPaneTaulaExercicis = new JScrollPane();
        jScrollPaneTaulaExercicis.getViewport().add(jTableExercicis);
        jPanelExercicis.add(jScrollPaneTaulaExercicis, "grow, span");
        add(jPanelExercicis, "grow");

    }

    public void guardarNouWorkout() {
        workoutPerAfegir = new Workout();
        String formattedDate;

        Date selectedDate = (Date) datePicker.getModel().getValue();
        Date selectedTime = (Date) timeSpinner.getValue();

        if (selectedDate != null) {
            formattedDate = dateFormat.format(selectedDate) + " " + timeFormat.format(selectedTime);;
        } else {
            JOptionPane.showMessageDialog(null, "Ho sentim, la data no pot estar en blanc", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        workoutPerAfegir.setForDate(formattedDate);

        workoutPerAfegir.setIdUsuari(main.getUsuariSeleccionat().getId());

        String nouComments = jTextFieldComentaris.getText();
        workoutPerAfegir.setComments(nouComments);

        int numeroRegistreAfegit = 0;

        try {
            numeroRegistreAfegit = DataAccess.insertToWorkoutTable(workoutPerAfegir);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Hi ha hagut errors i no s'ha pogut donar d'alta el workout. ", "Error", JOptionPane.ERROR_MESSAGE);

        }

        workoutPerAfegir.setId(numeroRegistreAfegit);
        main.setDarrerWorkoutAfegit(numeroRegistreAfegit);

        JOptionPane.showMessageDialog(null, "El workout s'ha pogut guardar correctament. El número id és " + numeroRegistreAfegit + ". Ara pot editar les dades, així com associar-hi alguns exercicis." , "Introducció Workout", JOptionPane.INFORMATION_MESSAGE, Utilitats.obtenirIcon("checklist.png"));

        // aquí convertim la pantalla d'afegir a la pantalla de modificar... entrarem en mode edició de les dades del workout que acabam de crear
        workoutModificar = workoutPerAfegir;
        setModo("modificar");
        pasAfegirAModificar = true;
        ajustosPantallaModeModificar();        
        pack();
        this.setLocationRelativeTo(main);
        revalidate();
        repaint();
        

    }

    public void actualitzarWorkout() {
        try {

            String formattedDate = "";

            Date selectedDate = (Date) datePicker.getModel().getValue();
            Date selectedTime = (Date) timeSpinner.getValue();

            if (selectedDate != null) {               
                formattedDate = dateFormat.format(selectedDate) + " " + timeFormat.format(selectedTime);;
            } else {
                JOptionPane.showMessageDialog(null, "Lo sentimos, la fecha no puede estar en blanco", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nouForDate = formattedDate;
            String nouComments = jTextFieldComentaris.getText();

            int workoutId = workoutModificar.getId();

            DataAccess.updateFromWorkoutTable(nouForDate, nouComments, workoutId);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Hi ha hagut errors i no s'ha pogut modificar el workout", "Error", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(null, "El workout s'ha pogut actualitzar correctament", "Introducció Workout", JOptionPane.INFORMATION_MESSAGE, Utilitats.obtenirIcon("checklist.png"));

    }

    public void crearTaulaExercicis() {
        jTableExercicis = new JTable();
        JTableHeader thExercicis = jTableExercicis.getTableHeader();
        thExercicis.setFont(new Font("Serif", Font.BOLD, 15));

        modelExercicis = new DefaultTableModel();
        modelExercicis.setColumnCount(3);
        jTableExercicis.setModel(modelExercicis);
        modelExercicis.setColumnIdentifiers(new Object[]{"IdExercici", "Exercici", "Descripcio"});
        actualizarTablaEjercicios(workoutModificar.getId());
    }

    public void actualizarTablaEjercicios(int idWorkout) {

        Usuari usuariSeleccionat = null;

        ArrayList<Usuari> usuaris = DataAccess.getAllUsers();
        for (int i = 0; i < usuaris.size(); i++) {
            if (usuaris.get(i).getId() == (workoutModificar.getIdUsuari())) {
                usuariSeleccionat = usuaris.get(i);
            }
        }

        ArrayList<Workout> workouts = DataAccess.getWorkoutsPerUser(usuariSeleccionat);

        for (int i = 0; i < workouts.size(); i++) {
            if (workouts.get(i).getId() == idWorkout) {
                workoutSeleccionat = workouts.get(i);
            }
        }

        modelExercicis.setRowCount(0);

        ArrayList<Exercici> exercicis = DataAccess.getExercicisPerWorkout(workoutSeleccionat);
        for (Exercici e : exercicis) {
            modelExercicis.addRow(new Object[]{e.getId(), e.getNomExercici(), e.getDescripcio()});
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelComentaris = new javax.swing.JLabel();
        jLabelData = new javax.swing.JLabel();
        jButtonSortir = new javax.swing.JButton();
        jLabelHora = new javax.swing.JLabel();
        jLabelTitol = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabelComentaris.setText("Comentaris");
        getContentPane().add(jLabelComentaris);
        jLabelComentaris.setBounds(0, 40, 80, 16);

        jLabelData.setText("Data");
        getContentPane().add(jLabelData);
        jLabelData.setBounds(10, 100, 80, 16);

        jButtonSortir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cerrar-sesion.png"))); // NOI18N
        jButtonSortir.setMnemonic('S');
        jButtonSortir.setText("Sortir");
        jButtonSortir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSortirActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSortir);
        jButtonSortir.setBounds(130, 0, 78, 23);

        jLabelHora.setText("Hora");
        getContentPane().add(jLabelHora);
        jLabelHora.setBounds(10, 130, 80, 16);

        jLabelTitol.setText("titol");
        getContentPane().add(jLabelTitol);
        jLabelTitol.setBounds(10, 60, 80, 16);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSortirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSortirActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSortirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddChangeWorkouts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddChangeWorkouts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddChangeWorkouts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddChangeWorkouts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String modo = args[0];
                Workout workoutModificar = new Workout(args[1]);
                AddChangeWorkouts dialog = new AddChangeWorkouts(new javax.swing.JFrame(), true, modo, workoutModificar);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSortir;
    private javax.swing.JLabel jLabelComentaris;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JLabel jLabelHora;
    private javax.swing.JLabel jLabelTitol;
    // End of variables declaration//GEN-END:variables
}
