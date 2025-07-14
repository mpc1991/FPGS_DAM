package covas.vistas;

import covas.dataaccess.DataAccess;
import covas.model.Usuari;
import covas.utils.Utilitats;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Toni Covas
 */
public class VistaUsuaris extends javax.swing.JPanel {

    private Main main = null;
       private JTextField jTextFieldTexteRecerca = new JTextField(20);
       private JDialog dialog;

    public VistaUsuaris(Main mainJframe) {
        initComponents();
        main = mainJframe;
        
        SwingUtilities.invokeLater(() -> jTextFieldTexteRecerca.requestFocus());
        SwingUtilities.invokeLater(() -> jTextFieldTexteRecerca.grabFocus());

        // definim el layout per les instàncies de l'objecte VistaUsuaris
        setLayout(new MigLayout("fill, insets 1", "[grow, center]", "[grow, center]"));

        //definim un DefaultListModel que serà la base d'informació per el JList
        DefaultListModel llistaBase = new DefaultListModel();
        ArrayList<Usuari> usuaris = DataAccess.getAllUsersByInstructor(main.getUsuariActiu().getId());
        System.out.println("l'usuari es: " + main.getUsuariActiu().getId());
        System.out.println("\n" + usuaris.size());
        for (int i = 0; i < usuaris.size(); i++) {
            llistaBase.add(i, usuaris.get(i).getNom());

        }
        
        //definim el DefaultListModel al Jlist
        getListUsuarisInstructor().setModel(llistaBase);
        
        Color borderColor = new Color(255, 200, 0); // Color entre groc i taronja
        Border border = BorderFactory.createLineBorder(borderColor, 5);

        
        // cream un jPanel amb MigLayout que durà tant el Jlist i el jLabel que presenta el Jlist
        JPanel panellJListUsuaris = new JPanel();
        panellJListUsuaris.setLayout(new MigLayout("fill, insets 1", "[grow, center]", "[]1[]"));

        jLabelListaTitulo.setText("Llista d'usuaris que entrenes actualment");
        
        JPanel jPanelRecerca = new JPanel(new MigLayout("fill, insets 1", "[grow, center]", "[]1[]"));
        jPanelRecerca.add(jTextFieldTexteRecerca, "align center");
        jTextFieldTexteRecerca.setToolTipText("introduzca el texto a buscar");
        JLabel jLabelImageRecerca = new JLabel();
        jLabelImageRecerca.setIcon(Utilitats.obtenirIcon("buscar.png"));
        jPanelRecerca.add(jLabelImageRecerca);
        jPanelRecerca.setBorder(border);
        
        
        
        
        panellJListUsuaris.add(jPanelRecerca, "grow, align center, wrap");
        panellJListUsuaris.add(jLabelListaTitulo, " align center,  wrap, gaptop 15");
        panellJListUsuaris.add(jScrollPane2, "span, grow,  align center, wrap");

        // definim un marco per el jList        
        jListUsuarisInstructor.setBorder(border);
        
        
            jListUsuarisInstructor.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = jListUsuarisInstructor.locationToIndex(e.getPoint());
                if (index != -1) {
                    String usuariMarcat = jListUsuarisInstructor.getModel().getElementAt(index);
                    
                } else {
                    if (dialog != null) {
                        dialog.setVisible(false);
                    }
                }
            }
        });
        
         
        
        
        //incorporam el panell del jList dins l'objecte principal, definint les propietats de MigLayout
        add(panellJListUsuaris, "span,  align center, wrap");
        
           //listener perquè el camp de text actualitzi el jList cada vegada que s'afegeix, elimina o canvia un caracter
           // per això, sobreescrivim els 3 mètodes.
         jTextFieldTexteRecerca.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarJList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarJList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarJList();
            }

            // és el mètode que s'executa cada vegada que hi ha un canvi al camp de text de recerca
            private void filtrarJList() {
                String texteRecerca = jTextFieldTexteRecerca.getText();
                if (texteRecerca.isEmpty()) {
                    jListUsuarisInstructor.setModel(llistaBase);
                } else {
                    DefaultListModel<String> filteredModel = new DefaultListModel<>();
                    for (int i = 0; i < llistaBase.size(); i++) {
                        String item = llistaBase.getElementAt(i).toString();
                        if (item.toLowerCase().contains(texteRecerca.toLowerCase())) {
                            filteredModel.addElement(item);
                        }
                    }
                    jListUsuarisInstructor.setModel(filteredModel);
                }
            }
        });
        
     
        
    }
    
    
    

    
    
    

    public JList<String> getListUsuarisInstructor() {
        return this.jListUsuarisInstructor;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jListUsuarisInstructor = new javax.swing.JList<>();
        jLabelListaTitulo = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(900, 400));
        setLayout(null);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(200, 300));

        jListUsuarisInstructor.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListUsuarisInstructor.setMaximumSize(new java.awt.Dimension(500, 500));
        jListUsuarisInstructor.setMinimumSize(new java.awt.Dimension(1, 1));
        jListUsuarisInstructor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListUsuarisInstructorMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jListUsuarisInstructor);

        add(jScrollPane2);
        jScrollPane2.setBounds(30, 10, 190, 210);

        jLabelListaTitulo.setText("TituloLista");
        add(jLabelListaTitulo);
        jLabelListaTitulo.setBounds(20, 230, 520, 16);
    }// </editor-fold>//GEN-END:initComponents

    // definim que en fer click al jList se mos obri la pantalla dels workouts
    private void jListUsuarisInstructorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListUsuarisInstructorMouseClicked
        main.getContentPane().remove(this);
        main.revalidate();
        main.repaint();
        main.carregarLlistaWorkOutsUsuari(jListUsuarisInstructor.getSelectedValue());    

    }//GEN-LAST:event_jListUsuarisInstructorMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelListaTitulo;
    private javax.swing.JList<String> jListUsuarisInstructor;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
