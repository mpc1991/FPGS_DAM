package porcel.workout2success.views;

import javax.swing.JTextField;
import porcel.workout2success.Main;

public class JPanelHome extends javax.swing.JPanel {

    private Main jFrameMain;
    private JPanelHomeUsers jPanelUsers;

    String sessionUsername = Main.getUsername();

    public JPanelHome(Main jFrameMain) {
        initComponents();
        this.jFrameMain = jFrameMain;
        jPanelUsers = new JPanelHomeUsers(this, jFrameMain);
        setSize(1280, 684); //No es necesario al indicarselo desde main?

        InicializejPanels();
    }

    public void InicializejPanels() {
        jPanelOptions.setBounds(1, 1, 300, 600);
        jLabelQuestion.setBounds(40, 50, 200, 30);
        jButtonShowMyUsers.setBounds(40, 90, 210, 30);
        jButtonHideMyUsers.setBounds(40, 130, 210, 30);
        jLabelUserSession.setBounds(10, 570, 270, 30);
        jLabelUserSession.setHorizontalAlignment(JTextField.CENTER);
        jPanelShowInfo.setBounds(350, 0, 900, 600);

        this.add(jPanelOptions);
        jPanelOptions.add(jLabelQuestion);
        jPanelOptions.add(jButtonShowMyUsers);
        jPanelOptions.add(jButtonHideMyUsers);
        jPanelOptions.add(jLabelUserSession);
        this.add(jPanelShowInfo);
        jPanelShowInfo.add(jPanelUsers);

        jPanelUsers.setVisible(false);
        jPanelShowInfo.setVisible(true);
        jLabelUserSession.setVisible(true);

        jLabelUserSession.setText("Logged in session: " + sessionUsername);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelOptions = new javax.swing.JPanel();
        jLabelQuestion = new javax.swing.JLabel();
        jButtonHideMyUsers = new javax.swing.JButton();
        jLabelUserSession = new javax.swing.JLabel();
        jButtonShowMyUsers = new javax.swing.JButton();
        jPanelShowInfo = new javax.swing.JPanel();

        setLayout(null);

        jPanelOptions.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelOptions.setLayout(null);

        jLabelQuestion.setText("What are we gonna do today?");
        jPanelOptions.add(jLabelQuestion);
        jLabelQuestion.setBounds(20, 40, 162, 16);

        jButtonHideMyUsers.setText("Hide users list");
        jButtonHideMyUsers.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonHideMyUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHideMyUsersActionPerformed(evt);
            }
        });
        jPanelOptions.add(jButtonHideMyUsers);
        jButtonHideMyUsers.setBounds(20, 100, 210, 30);
        jPanelOptions.add(jLabelUserSession);
        jLabelUserSession.setBounds(20, 470, 220, 30);

        jButtonShowMyUsers.setText("Show users list");
        jButtonShowMyUsers.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonShowMyUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowMyUsersActionPerformed(evt);
            }
        });
        jPanelOptions.add(jButtonShowMyUsers);
        jButtonShowMyUsers.setBounds(20, 70, 210, 30);

        add(jPanelOptions);
        jPanelOptions.setBounds(0, 0, 260, 540);

        jPanelShowInfo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelShowInfo.setLayout(null);
        add(jPanelShowInfo);
        jPanelShowInfo.setBounds(300, 0, 660, 540);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonHideMyUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHideMyUsersActionPerformed
        jPanelUsers.setVisible(false);
    }//GEN-LAST:event_jButtonHideMyUsersActionPerformed

    private void jButtonShowMyUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowMyUsersActionPerformed
        jPanelUsers.setVisible(true);

    }//GEN-LAST:event_jButtonShowMyUsersActionPerformed

    private javax.swing.JPanel jMenuBar1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonHideMyUsers;
    private javax.swing.JButton jButtonShowMyUsers;
    private javax.swing.JLabel jLabelQuestion;
    private javax.swing.JLabel jLabelUserSession;
    private javax.swing.JPanel jPanelOptions;
    private javax.swing.JPanel jPanelShowInfo;
    // End of variables declaration//GEN-END:variables
}
