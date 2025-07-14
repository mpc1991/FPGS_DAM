package covas.vistas;

import covas.model.Usuari;
import at.favre.lib.crypto.bcrypt.BCrypt;
import covas.dataaccess.DataAccess;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Toni Covas
 */
public class LogIn extends javax.swing.JDialog {

    private Main main = null;

    /**
     * Creates new form LogIn
     */
    public LogIn(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        main = (Main) parent;
        repaint();
        btnLogin.setEnabled(false);
        txtEmailLogin.setBackground(Color.YELLOW);

        jButtonTancar.setToolTipText("Pitji aquí per tancar la finestre");

        // Añadir efecto al hacer hover
        jButtonTancar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jButtonTancar.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jButtonTancar.setBackground(UIManager.getColor("control"));
            }
        });

        
        
        btnLogin.setToolTipText("Pitji aquí per fer el login");

        // Añadir efecto al hacer hover
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(UIManager.getColor("control"));
            }
        });
        
        
        
        //aplicam el mètode validar email ja d'un principi, per validar l'email que hi ha per defecte
        validateEmail();

        //listener pel camp email, per anar validant l'email cada vegada que se pitja una tecla
        txtEmailLogin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateEmail();
            }

        });

        // per fer click a login automàticament cada vegada que pitjem return desde el camp del password
        txtPasswordLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLogin.doClick(); // Ejecutar l' acció del botó
            }
        });

        // per fer click a login automàticament cada vegada que pitjem return desde el camp del email
        txtEmailLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLogin.doClick(); // Ejecutar l' acción del botó
            }
        });

    }

    private void validateEmail() {
        String email = txtEmailLogin.getText();
        if (isValidEmail(email)) {
            txtEmailLogin.setBackground(Color.WHITE); // Canviar a blanco si el email es vàlid
            btnLogin.setToolTipText("Pitji aquí per fer el login");
            btnLogin.setEnabled(true);
        } else {
            txtEmailLogin.setBackground(Color.YELLOW); // Canviar a groc si el email no es vàlid
            btnLogin.setEnabled(false);
            btnLogin.setToolTipText("Fins que no hagi posat una adreça d'email correcta, no s'habilita aquest botó");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{1,6}$";  // patró de la regular expression per revisar l'email
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    @SuppressWarnings("unchecked")


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelLogearse = new javax.swing.JPanel();
        txtEmailLogin = new javax.swing.JTextField();
        txtPasswordLogin = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        jLabelEmail = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jButtonTancar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Intrudueixi el seu usuari i contrasenya");
        setMinimumSize(new java.awt.Dimension(350, 300));
        getContentPane().setLayout(null);

        jPanelLogearse.setBorder(javax.swing.BorderFactory.createTitledBorder("Login"));
        jPanelLogearse.setLayout(null);

        txtEmailLogin.setText("a@b.c");
        jPanelLogearse.add(txtEmailLogin);
        txtEmailLogin.setBounds(120, 30, 143, 22);

        txtPasswordLogin.setText("string");
        jPanelLogearse.add(txtPasswordLogin);
        txtPasswordLogin.setBounds(120, 60, 143, 22);

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanelLogearse.add(btnLogin);
        btnLogin.setBounds(30, 100, 72, 23);

        jLabelEmail.setText("Email");
        jPanelLogearse.add(jLabelEmail);
        jLabelEmail.setBounds(18, 37, 50, 16);

        jLabelPassword.setText("Password");
        jPanelLogearse.add(jLabelPassword);
        jLabelPassword.setBounds(18, 65, 90, 16);

        jButtonTancar.setText("Tancar");
        jButtonTancar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTancarActionPerformed(evt);
            }
        });
        jPanelLogearse.add(jButtonTancar);
        jButtonTancar.setBounds(155, 100, 90, 23);

        getContentPane().add(jPanelLogearse);
        jPanelLogearse.setBounds(30, 30, 270, 150);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // definim el que passa quan pitja al botó per fer login
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // canviam el cursor del ratolí quan feim click al botó
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        // ficam el codi dins un Thread, per executar-ho amb un fil diferent i permetre que l'equip no quedi bloquejat durant el procés
        Thread loginThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Usuari usuari = null;

                try {
                    usuari = DataAccess.getUser(txtEmailLogin.getText());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error quan intentam accedir a l'usuari, transmeti aquest missatge a l'administrador del sistema: \n Error al mètode getUser(). " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error quan intentam accedir a l'usuari, transmeti aquest missatge a l'administrador del sistema: \n Error al mètode getUser(). " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                if (usuari != null) {
                    char[] passwordToVerify = txtPasswordLogin.getPassword();
                    String userPasswordHashInDatabase = usuari.getPasswordHash();
                    var result = BCrypt.verifyer().verify(passwordToVerify, userPasswordHashInDatabase);

                    if (result.verified) {
                        if (usuari.isInstructor()) {
                            main.setUsuariActiu(usuari);
                            JOptionPane.showMessageDialog(null, "Login correcte. Benvingut " + usuari.getNom() + "!", "Proceso Login", JOptionPane.INFORMATION_MESSAGE);

                        } else {
                            JOptionPane.showMessageDialog(null, "Ho sentim. És necessari que l'usuari sigui instructor per accedir " + usuari.getNom() + "!", "Proceso Login", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Password incorrecte !", "Proceso Login", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error. Email " + txtEmailLogin.getText() + " no trobat a la base de dades!", "Proceso Login", JOptionPane.INFORMATION_MESSAGE);
                }

                setCursor(Cursor.getDefaultCursor());
                if (main.getUsuariActiu() != null) {
                    dispose();
                    main.registrarUsuariActiu(main.getUsuariActiu());
                    main.carregarLlistaUsuarisInstructor();
                }
            }
        });

        // iniciam el Thread que acabam de definir
        loginThread.start();


    }//GEN-LAST:event_btnLoginActionPerformed

    private void jButtonTancarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTancarActionPerformed
        dispose();        // tancam l'objecte MainJPanel
    }//GEN-LAST:event_jButtonTancarActionPerformed

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
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LogIn dialog = new LogIn(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton jButtonTancar;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JPanel jPanelLogearse;
    private javax.swing.JTextField txtEmailLogin;
    private javax.swing.JPasswordField txtPasswordLogin;
    // End of variables declaration//GEN-END:variables
}
