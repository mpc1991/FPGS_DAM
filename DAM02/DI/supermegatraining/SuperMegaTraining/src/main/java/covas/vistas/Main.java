package covas.vistas;

import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import covas.model.Usuari;
import covas.dataaccess.DataAccess;
import covas.model.Workout;
import covas.utils.Utilitats;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Toni Covas
 */
public class Main extends javax.swing.JFrame {

    

    private Usuari usuariActiu = null;
    private DataAccess da = new DataAccess();
    private Usuari usuariSeleccionat = null;
    private String usuariSeleccionatTexte = "";
    private MainJPanel pnlMain;
    private VistaUsuaris pnlVistaUsuaris;
    private VistaWorkouts pnlVistaWorkouts;
    private LogIn pnlLogIn;
    private AddChangeWorkouts pnlAddChangeWorkouts;
    private int darrerWorkoutAfegit;

    public Main() {

        initComponents();

        SwingUtilities.updateComponentTreeUI(this);

        // definim el logo pel frame principal
        this.setIconImage(Utilitats.obtenirImage("logocompany.jpg"));

        // panell amb el nom d'usuari actiu visible y botó de Log Out
        jPanelLogin.setVisible(false);

        setResizable(true);

        // Definim MigLayout pel frame principal
        setLayout(new MigLayout("fill"));

        // Definim MigLayout pel panell de Login
        jPanelLogin.setLayout(new MigLayout("fill"));
        getContentPane().add(jPanelLogin, "pos (100%-250px) 5px, wrap 5");

        pnlMain = new MainJPanel(this);
        getContentPane().add(pnlMain, "span, grow, align center, gap top 20px, wrap");

        setLocationRelativeTo(null); // Centrar el JFrame en la pantalla       
        pack();
        repaint();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelLogin = new javax.swing.JPanel();
        jButtonLogOut = new javax.swing.JButton();
        jLabelUsuariRegistrat = new javax.swing.JLabel();
        jTableWorkOutsperborrar = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(200, 200));
        setPreferredSize(new java.awt.Dimension(900, 625));
        setResizable(false);
        setSize(new java.awt.Dimension(900, 625));
        getContentPane().setLayout(null);

        jPanelLogin.setVisible(false);
        jPanelLogin.setLayout(null);

        jButtonLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cerrar-sesion.png"))); // NOI18N
        jButtonLogOut.setText("Log Out");
        jButtonLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogOutActionPerformed(evt);
            }
        });
        jPanelLogin.add(jButtonLogOut);
        jButtonLogOut.setBounds(155, 15, 90, 23);

        jLabelUsuariRegistrat.setText("Usuari:");
        jLabelUsuariRegistrat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelLogin.add(jLabelUsuariRegistrat);
        jLabelUsuariRegistrat.setBounds(6, 14, 143, 18);

        getContentPane().add(jPanelLogin);
        jPanelLogin.setBounds(20, 0, 270, 50);

        jTableWorkOutsperborrar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "blanc", "ForDate", "IdWorkout"
            }
        ));
        getContentPane().add(jTableWorkOutsperborrar);
        jTableWorkOutsperborrar.setBounds(0, 0, 150, 0);
        if (jTableWorkOutsperborrar.getColumnModel().getColumnCount() > 0) {
            jTableWorkOutsperborrar.getColumnModel().getColumn(0).setMinWidth(0);
            jTableWorkOutsperborrar.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableWorkOutsperborrar.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jMenu1.setMnemonic('F');
        jMenu1.setText("File");
        jMenu1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jMenu1KeyPressed(evt);
            }
        });

        jMenuItem3.setMnemonic('V');
        jMenuItem3.setText("Veure per Consola usuaris");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem2.setMnemonic('A');
        jMenuItem2.setText("Acerca de...");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setMnemonic('S');
        jMenuItem1.setText("Sortir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setUsuariActiu(Usuari usuariActiu) {
        this.usuariActiu = usuariActiu;
    }

    public Usuari getUsuariActiu() {
        return usuariActiu;
    }

    public Usuari getUsuariSeleccionat() {
        return usuariSeleccionat;
    }

    public String getUsuariSeleccionatTexte() {
        return usuariSeleccionatTexte;
    }

    public void setUsuariSeleccionatTexte(String usuariSeleccionatTexte) {
        this.usuariSeleccionatTexte = usuariSeleccionatTexte;
    }

    public void setUsuariSeleccionat(Usuari usuari) {
        this.usuariSeleccionat = usuari;
    }

    public void abrirLogin() {
        //obrim el jDialog per fer el login
        pnlLogIn = new LogIn(this, true);
        pnlLogIn.setLocationRelativeTo(this);
        pnlLogIn.setVisible(true);

    }

    public void addChangeWorkouts(String modo, Workout workoutModificar) {
        //obrim el jDialog per afegir/ modificar workouts
        pnlAddChangeWorkouts = new AddChangeWorkouts(this, true, modo, workoutModificar);
        pnlAddChangeWorkouts.setLocationRelativeTo(this); // Centrar el JDialog respecte el JFrame principal
        pnlAddChangeWorkouts.setVisible(true);
    }


    private void jButtonLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogOutActionPerformed

        JOptionPane.showMessageDialog(this, "Hasta Luego " + jLabelUsuariRegistrat.getText() + " !.  Su sesión ha sido desconectada", "Logout", JOptionPane.INFORMATION_MESSAGE, Utilitats.obtenirIcon("adios.png"));

        jLabelUsuariRegistrat.setText("");
        jPanelLogin.setVisible(false);        // TODO add your handling code here:
        setUsuariActiu(null);
        getContentPane().remove(getPnlVistaUsuaris());
        if (getPnlVistaWorkouts() != null) {
            getContentPane().remove(getPnlVistaWorkouts());
        }
        pnlMain.getJLabelSignIn().setVisible(true);
        repaint();


    }//GEN-LAST:event_jButtonLogOutActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // botó del menú about
        final JOptionPane pane = new JOptionPane("Aplicació creada per Toni Covas Nicolau\nVersión 1.0\n\nGrau Superior DAM - Desenvolupament aplicaciones multiplataforma\nMòdul: Desenvolupament d'interfícies \nCIFP Pau Casesnoves");
        final JDialog d = pane.createDialog((JFrame) null, "About...");
        d.setLocation(jMenuItem2.getLocation().x + 5, jMenuItem2.getLocation().y + 2);
        d.setLocationRelativeTo(this);
        d.setVisible(true);

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenu1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMenu1KeyPressed

    }//GEN-LAST:event_jMenu1KeyPressed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // enseñar lista de usuarios por consola
        ArrayList<Usuari> usuaris = DataAccess.getAllUsers();

        for (Usuari u : usuaris) {
            System.out.println(u.getId() + " " + u.getNom() + "\n");

        }

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public void registrarUsuariActiu(Usuari usuari) {
        pnlMain.getJLabelSignIn().setVisible(false);
        this.usuariActiu = usuari;
        jLabelUsuariRegistrat.setText("Usuari: " + this.usuariActiu.getNom());
        jLabelUsuariRegistrat.setVisible(true);
        jPanelLogin.setVisible(true);
    }

    public void carregarLlistaUsuarisInstructor() {

        if (pnlVistaWorkouts != null) {
            getContentPane().remove(getPnlVistaWorkouts());
        }

        if (pnlVistaUsuaris != null) {
            getContentPane().remove(getPnlVistaUsuaris());
        }

        setPnlVistaUsuaris(new VistaUsuaris(this));        
        getContentPane().add(pnlVistaUsuaris, "span, grow, align center, wrap");
        revalidate();
        repaint();
        
        

    }

    public void carregarLlistaWorkOutsUsuari(String nomUsuari) {
        getContentPane().remove(getPnlVistaUsuaris());
        setUsuariSeleccionatTexte(nomUsuari);
        setPnlVistaWorkouts(new VistaWorkouts(this));

        getContentPane().remove(getPnlVistaUsuaris());
        getContentPane().add(getPnlVistaWorkouts(), "span, grow, align center, wrap");

        pack();
        repaint();

    }

    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        try {
            // Crear propiedades para personalizar el look and feel
            Properties props = new Properties();
            props.put("logoString", ""); // Eliminar el texto del logo

            //establim les propietats al lookAndFeel Bernstein    
            BernsteinLookAndFeel.setCurrentTheme(props);

            //establim fonts a distints objectes visuals. Jugam amb dues fonts distintes
            Font customFont1 = new Font("Open Sans Semibold", Font.PLAIN, 14);
            Font customFont2 = new Font("Consolas", Font.PLAIN, 14);
            UIManager.put("Label.font", customFont1);
            UIManager.put("TableHeader.font", customFont1);
            UIManager.put("Table.font", customFont2);
            UIManager.put("List.font", customFont2);
            UIManager.put("Button.font", customFont1);
            UIManager.put("TextField.font", customFont1);
            UIManager.put("Spinner.font", customFont1);
            UIManager.put("Datepicker.font", customFont1);
            UIManager.put("TextArea.font", customFont1);
            UIManager.put("ComboBox.font", customFont1);
            UIManager.put("Menu.font", customFont1);
            UIManager.put("MenuItem.font", customFont1);
            UIManager.put("TitledBorder.font", customFont1);
            UIManager.put("ToolTip.font", customFont1);

            //establim un color blau molt dèbil a distints elements
            UIManager.put("Button.background", new Color(230, 240, 255));
            UIManager.put("Label.background", new Color(230, 240, 255));
            UIManager.put("TextField.background", new Color(230, 240, 255));
            UIManager.put("TextArea.background", new Color(230, 240, 255));
            UIManager.put("ComboBox.background", new Color(230, 240, 255));
            UIManager.put("Menu.background", new Color(230, 240, 255));
            UIManager.put("MenuItem.background", new Color(230, 240, 255));
            UIManager.put("TitledBorder.background", new Color(230, 240, 255));
            UIManager.put("ToolTip.background", new Color(230, 240, 255));

            // Establecer el "look and fiel jTatoo bernstein"
            UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");

            UIManager.put("logoString", ""); // Eliminar el texto del logo que ve per defecte amb el jTatoo
            UIManager.put("logo", ""); // Eliminar el logo que ve per defecte amb el jTatoo
            UIManager.put("backgroundColor", "230 240 255"); // Color azul muy débil

            Main mainInstance = new Main();
            
            //definim el logo de l'aplicació 
            UIManager.put("logo", Utilitats.obtenirImage("logocompany.jpg")); 
          

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });

    }

    public VistaUsuaris getPnlVistaUsuaris() {
        return pnlVistaUsuaris;
    }

    public void setPnlVistaUsuaris(VistaUsuaris pnlVistaUsuaris) {
        this.pnlVistaUsuaris = pnlVistaUsuaris;
    }

    public VistaWorkouts getPnlVistaWorkouts() {
        return pnlVistaWorkouts;
    }

    public void setPnlVistaWorkouts(VistaWorkouts pnlVistaWorkouts) {
        this.pnlVistaWorkouts = pnlVistaWorkouts;
    }
    
    
    
    public int getDarrerWorkoutAfegit() {
        return darrerWorkoutAfegit;
    }

    
    public void setDarrerWorkoutAfegit(int darrerWorkoutAfegit) {
        this.darrerWorkoutAfegit = darrerWorkoutAfegit;
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogOut;
    private javax.swing.JLabel jLabelUsuariRegistrat;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanelLogin;
    private javax.swing.JTable jTableWorkOutsperborrar;
    // End of variables declaration//GEN-END:variables
}
