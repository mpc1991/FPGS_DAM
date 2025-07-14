package porcel.workout2success.views;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;
import porcel.workout2success.Main;
import porcel.workout2success.dto.Usuari;
import porcel.workout2success.data.UsuariDAO;
import porcel.workout2success.data.UsuariDAOImpl;

/**
 * Dialogo con el formulario para añadir Usuarios a la BBDD
 * 
 * @author Macia Porcel Cifre
 * @version 1.0
 */
public class JDialogHomeUsersAdd extends javax.swing.JDialog {

    Main mainFrame;

    /**
     * Constructor
     * 
     * @param parent Referencia al Main
     * @param modal Define si el dialogo sera modal
     */
    public JDialogHomeUsersAdd(Main parent, boolean modal) {
        super(parent, modal);
        this.mainFrame = parent;
        initComponents();

        inicialize();

        getRootPane().setDefaultButton(jButtonCreate);
    }

    /**
     * Inicializa y ubica los componentes gráficos
     */
    private void inicialize() {
        getContentPane().add(jPanelAddUser);
        setSize(450, 320);
        jPanelAddUser.setBounds(0, 0, 435, 300);
        jLabelNom.setBounds(10, 10, 100, 40);
        jLabelEmail.setBounds(10, 60, 100, 40);
        jLabelPassword.setBounds(10, 110, 100, 40);
        jLabelIsInstructor.setBounds(10, 160, 100, 40);

        jTextFieldNom.setBounds(120, 10, 310, 40);
        jTextFieldNom.setEnabled(false);
        jTextFieldNom.addKeyListener(new java.awt.event.KeyAdapter() {
        
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                if (jTextFieldNom.getText().trim().isEmpty()) {
                    jButtonCreate.setEnabled(false);
                } else {
                    jButtonCreate.setEnabled(true);
                }
            }
        });
        jTextFieldEmail.setBounds(120, 60, 310, 40);
        jTextFieldPassword.setBounds(120, 110, 310, 40);
        jCheckBoxIsInstructor.setBounds(120, 160, 310, 40);

        jButtonCreate.setBounds(60, 210, 310, 40);
        jLabelError.setBounds(60, 250, 310, 40);
        jLabelError.setHorizontalAlignment(JTextField.CENTER);

        this.add(jPanelAddUser);
        jPanelAddUser.add(jLabelNom);
        jPanelAddUser.add(jLabelEmail);
        jPanelAddUser.add(jLabelPassword);
        jPanelAddUser.add(jLabelIsInstructor);
        jPanelAddUser.add(jTextFieldNom);
        jPanelAddUser.add(jTextFieldEmail);
        jPanelAddUser.add(jTextFieldPassword);
        jPanelAddUser.add(jCheckBoxIsInstructor);
        jPanelAddUser.add(jButtonCreate);
        jPanelAddUser.add(jLabelError);
        
        this.revalidate();
        this.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelAddUser = new javax.swing.JPanel();
        jLabelIsInstructor = new javax.swing.JLabel();
        jLabelNom = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jTextFieldNom = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jCheckBoxIsInstructor = new javax.swing.JCheckBox();
        jButtonCreate = new javax.swing.JButton();
        jLabelError = new javax.swing.JLabel();
        jTextFieldPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanelAddUser.setLayout(null);

        jLabelIsInstructor.setText("Is instructor?");
        jPanelAddUser.add(jLabelIsInstructor);
        jLabelIsInstructor.setBounds(10, 120, 80, 20);

        jLabelNom.setText("Nom");
        jPanelAddUser.add(jLabelNom);
        jLabelNom.setBounds(10, 30, 80, 16);

        jLabelEmail.setText("Email");
        jPanelAddUser.add(jLabelEmail);
        jLabelEmail.setBounds(10, 60, 80, 20);

        jLabelPassword.setText("Password");
        jPanelAddUser.add(jLabelPassword);
        jLabelPassword.setBounds(10, 90, 80, 20);

        jTextFieldNom.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextFieldNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomActionPerformed(evt);
            }
        });
        jPanelAddUser.add(jTextFieldNom);
        jTextFieldNom.setBounds(110, 30, 160, 20);

        jTextFieldEmail.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelAddUser.add(jTextFieldEmail);
        jTextFieldEmail.setBounds(110, 60, 160, 20);

        jCheckBoxIsInstructor.setText("Yes");
        jPanelAddUser.add(jCheckBoxIsInstructor);
        jCheckBoxIsInstructor.setBounds(110, 120, 50, 20);

        jButtonCreate.setText("Create");
        jButtonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateActionPerformed(evt);
            }
        });
        jPanelAddUser.add(jButtonCreate);
        jButtonCreate.setBounds(70, 170, 72, 23);
        jPanelAddUser.add(jLabelError);
        jLabelError.setBounds(90, 210, 0, 0);
        jPanelAddUser.add(jTextFieldPassword);
        jTextFieldPassword.setBounds(110, 90, 64, 22);

        getContentPane().add(jPanelAddUser);
        jPanelAddUser.setBounds(0, 0, 290, 230);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * <p>acción desencadenada al apretar el botón "jButtonCreate"</p>
     * <p>Obtiene los datos insertados en el formulario y crea un nuevo Usuario</p>
     * @param evt 
     */
    private void jButtonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateActionPerformed
        String nom = jTextFieldNom.getText();
        String email = jTextFieldEmail.getText();
        String password = jTextFieldPassword.getText();
        boolean isInstructor = false;
        if (jCheckBoxIsInstructor.isSelected()) {
            isInstructor = true;
        }
        
        // expresión regular para el correo
        String patternString = "[a-zA-Z0-9.-_]+@[a-zA-Z0-9]+.[a-zA-Z0-9.]*";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            jLabelError.setText("You have tu introduce a correct Email.");
            return;
        }

        if (nom.isEmpty() || email.isEmpty() || password.isEmpty()) {
            jLabelError.setText("You have tu introduce Name, Email and Password.");
            return;
        }

        String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        Usuari usuari = new Usuari(nom, email, hashedPassword, isInstructor);
        UsuariDAO usuariDAO = new UsuariDAOImpl();

        try {
            usuariDAO.insert(usuari);
        } catch (SQLException ex) {
            Logger.getLogger(JDialogHomeUsersAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_jButtonCreateActionPerformed

    private void jTextFieldNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCreate;
    private javax.swing.JCheckBox jCheckBoxIsInstructor;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelError;
    private javax.swing.JLabel jLabelIsInstructor;
    private javax.swing.JLabel jLabelNom;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JPanel jPanelAddUser;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldNom;
    private javax.swing.JPasswordField jTextFieldPassword;
    // End of variables declaration//GEN-END:variables
}
