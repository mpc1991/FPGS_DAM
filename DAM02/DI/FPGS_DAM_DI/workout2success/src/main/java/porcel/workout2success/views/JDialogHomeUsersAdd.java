package porcel.workout2success.views;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import porcel.workout2success.Main;
import porcel.workout2success.dto.Usuari;
import porcel.workout2success.dto.UsuariDAO;
import porcel.workout2success.dto.UsuariDAOImpl;

public class JDialogHomeUsersAdd extends javax.swing.JDialog {

    Main mainFrame;

    public JDialogHomeUsersAdd(Main parent, boolean modal) {
        super(parent, modal);
        this.mainFrame = parent;
        initComponents();

        inicialize();
    }

    private void inicialize() {
        getContentPane().add(jPanelAddUser);
        setSize(450, 320);
        jPanelAddUser.setBounds(0, 0, 435, 300);
        jLabelNom.setBounds(10, 10, 100, 40);
        jLabelEmail.setBounds(10, 60, 100, 40);
        jLabelPassword.setBounds(10, 110, 100, 40);
        jLabelIsInstructor.setBounds(10, 160, 100, 40);

        jTextFieldNom.setBounds(120, 10, 310, 40);
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
        jTextFieldPassword = new javax.swing.JTextField();
        jCheckBoxIsInstructor = new javax.swing.JCheckBox();
        jButtonCreate = new javax.swing.JButton();
        jLabelError = new javax.swing.JLabel();

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
        jPanelAddUser.add(jTextFieldNom);
        jTextFieldNom.setBounds(110, 30, 160, 20);

        jTextFieldEmail.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelAddUser.add(jTextFieldEmail);
        jTextFieldEmail.setBounds(110, 60, 160, 20);

        jTextFieldPassword.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelAddUser.add(jTextFieldPassword);
        jTextFieldPassword.setBounds(110, 90, 160, 20);

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

        getContentPane().add(jPanelAddUser);
        jPanelAddUser.setBounds(0, 0, 290, 230);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateActionPerformed
        String nom = jTextFieldNom.getText();
        String email = jTextFieldEmail.getText();
        String password = jTextFieldPassword.getText();
        boolean isInstructor = false;
        if (jCheckBoxIsInstructor.isSelected()) {
            isInstructor = true;
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
    private javax.swing.JTextField jTextFieldPassword;
    // End of variables declaration//GEN-END:variables
}
