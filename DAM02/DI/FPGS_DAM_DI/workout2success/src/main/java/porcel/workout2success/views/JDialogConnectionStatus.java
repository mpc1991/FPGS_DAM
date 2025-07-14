package porcel.workout2success.views;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTextField;
import porcel.workout2success.Main;
import porcel.workout2success.data.DataAccess;
import porcel.workout2success.dto.UsuariDAOImpl;
import porcel.workout2success.dto.Usuari;
import porcel.workout2success.dto.UsuariDAO;

public class JDialogConnectionStatus extends javax.swing.JDialog {

    Main mainFrame;

    public JDialogConnectionStatus(Main parent, boolean modal, String username, String userPassword) {
        super(parent, modal);
        this.mainFrame = parent;
        initComponents();

        inicialize();
        verifyConnection(username, userPassword);
    }

    private void inicialize() {
        setSize(450, 300);

        jLabelLogo.setBounds(157, 30, 128, 95);
        jLabelConnectionResult.setBounds(41, 140, 350, 40);
        jButtonConfirm.setBounds(115, 182, 220, 30);
        jLabelConnectionResult.setHorizontalAlignment(JTextField.CENTER);

        this.add(jLabelLogo);
        this.add(jLabelConnectionResult);
        this.add(jButtonConfirm);
    }

    private void verifyConnection(String username, String userPassword) {
        try {
            Connection conn = DataAccess.getConnection();
            if (conn != null) {
                jLabelConnectionResult.setText("Connection successfully to the database");
                UsuariDAO employeeDAO = new UsuariDAOImpl();
                try {
                    Usuari usuari = employeeDAO.get(username);
                    String dbHashedPassword = usuari.getPasswordHash();
                    var result = BCrypt.verifyer().verify(userPassword.toCharArray(), dbHashedPassword);
                    if (result.verified) {
                        if (usuari.isInstructor()) {
                            jLabelConnectionResult.setText("Login verifyed: welcome " + usuari.getNom());
                            mainFrame.showHomePanel();
                        } else {
                            jLabelConnectionResult.setText("This function is only for Instructors");
                        }
                    } else {
                        jLabelConnectionResult.setText("Error02: Incorrect username or password.");
                    }
                } catch (Exception e) {
                    jLabelConnectionResult.setText("Error01: Incorrect username or password.");
                }
            } else {
                jLabelConnectionResult.setText("Error: Can not access to the database.");
            }
        } catch (SQLException ex) {
            jLabelConnectionResult.setText("Error: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelLogo = new javax.swing.JLabel();
        jButtonConfirm = new javax.swing.JButton();
        jLabelConnectionResult = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/w2s_ConnectionStatus.png"))); // NOI18N
        jLabelLogo.setText("jLabel1");
        jLabelLogo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonConfirm.setText("Confirm");
        jButtonConfirm.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 118, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(jLabelConnectionResult, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelLogo)
                .addGap(62, 62, 62)
                .addComponent(jButtonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabelConnectionResult)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jButtonConfirmActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfirm;
    private javax.swing.JLabel jLabelConnectionResult;
    private javax.swing.JLabel jLabelLogo;
    // End of variables declaration//GEN-END:variables
}
