package porcel.workout2success.views;

import javax.swing.JComponent;
import javax.swing.JPanel;
import porcel.workout2success.Main;

/**
 * Interfaz inicial de la aplicación
 * 
 * <p>Existen tres paneles fundamentales:</p>
 * <ul>
 *  <li><b>jPanelCredentials representa la interfaz de inicio de sesión</b></li>
 *  <li><b>jPanelBanner representa el banner de la aplicación</b></li>
 *  <li><b>jPanelWelcomeMessage representa el mensaje de bienvenida así como información adicional</b></li>
 * </ul>
 * 
 * @author Macià Porcel Cifre
 * @version 2.0
 */
public class JPanelLogin extends javax.swing.JPanel {    
    private Main jFrameMain;

    /**
     * <p>Constructor de la clase</p>
     * <p>Inicializa los componentes gráficos</p>
     * 
     * @param jFrameMain Referencia de la ventana principal de la aplicación
     */
    public JPanelLogin(Main jFrameMain) {
        initComponents();
        this.jFrameMain = jFrameMain;

        jPanelLogin.setBounds(0, 0, 1280, 720);
        jPanelBanner.setBounds(10, 10, 860, 300);
        centerComponents(jPanelBanner, jLabelBanner); // Centramos la imagen
        jPanelWelcomeMessage.setBounds(10, 350, 860, 300);
        jLabelLine1.setBounds(10, 40, 800, 40);
        jLabelLine2.setBounds(10, 70, 800, 40);
        jLabelLine3.setBounds(10, 100, 830, 40);
        jLabelLine4.setBounds(10, 230, 800, 40);
        jLabelLine5.setBounds(10, 250, 800, 40);
        jPanelCredentials.setBounds(890, 10, 360, 640);
        jLabelLogo.setBounds(10, 10, 340, 300);
        jLabelInsertUsername.setBounds(15, 350, 200, 40);
        jLabelInsertPassword.setBounds(15, 390, 200, 40);
        jTextFieldUsername.setBounds(130, 350, 220, 40);
        jPasswordFieldPassword.setBounds(130, 390, 220, 40);
        jButtonLogin.setBounds(10, 450, 170, 40);
        jButtonRegister.setBounds(180, 450, 170, 40);
        jLabelLoginInfo.setBounds(10, 530, 300, 40);
        jButtonResetPassword.setBounds(10, 490, 340, 20);

        jPanelLogin.add(jPanelBanner);
        jPanelBanner.add(jLabelBanner);
        jPanelLogin.add(jPanelWelcomeMessage);
        jPanelWelcomeMessage.add(jLabelLine1);
        jPanelWelcomeMessage.add(jLabelLine2);
        jPanelWelcomeMessage.add(jLabelLine3);
        jPanelWelcomeMessage.add(jLabelLine4);
        jPanelWelcomeMessage.add(jLabelLine5);
        jPanelLogin.add(jPanelCredentials);
        jPanelCredentials.add(jLabelInsertUsername);
        jPanelCredentials.add(jLabelInsertUsername);
        jPanelCredentials.add(jLabelLogo);
        jPanelCredentials.add(jTextFieldUsername);
        jPanelCredentials.add(jPasswordFieldPassword);
        jPanelCredentials.add(jButtonLogin);
        jPanelCredentials.add(jButtonRegister);
        jPanelCredentials.add(jLabelLoginInfo);
        jPanelCredentials.add(jButtonResetPassword);

    }

    /**
     * <p>Método para centra el componente hijo dentro de un panel padre</p>
     * 
     * @param parentPanel Panel padre
     * @param childComponent Panel hijo a centrar
     */
    public void centerComponents(JPanel parentPanel, JComponent childComponent) { //Método sacado con ayuda de chatGPT por la necesidad de querer centrar la imagen.
        int pWidth = parentPanel.getWidth();
        int pHeight = parentPanel.getHeight();
        int cWidth = childComponent.getWidth();
        int cHeight = childComponent.getHeight();

        int x = (pWidth - cWidth) / 2;
        int y = (pHeight - cHeight) / 2;

        childComponent.setBounds(x, y, cWidth, cHeight);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelLogin = new javax.swing.JPanel();
        jPanelCredentials = new javax.swing.JPanel();
        jLabelInsertUsername = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        jLabelInsertPassword = new javax.swing.JLabel();
        jLabelLogo = new javax.swing.JLabel();
        jButtonLogin = new javax.swing.JButton();
        jButtonRegister = new javax.swing.JButton();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jLabelLoginInfo = new javax.swing.JLabel();
        jButtonResetPassword = new javax.swing.JButton();
        jPanelBanner = new javax.swing.JPanel();
        jPanelWelcomeMessage = new javax.swing.JPanel();
        jLabelLine1 = new javax.swing.JLabel();
        jLabelLine3 = new javax.swing.JLabel();
        jLabelLine4 = new javax.swing.JLabel();
        jLabelLine5 = new javax.swing.JLabel();
        jLabelBanner = new javax.swing.JLabel();
        jLabelLine2 = new javax.swing.JLabel();

        setLayout(null);

        jPanelLogin.setLayout(null);

        jPanelCredentials.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelCredentials.setLayout(null);

        jLabelInsertUsername.setText("Instert username");
        jPanelCredentials.add(jLabelInsertUsername);
        jLabelInsertUsername.setBounds(10, 390, 100, 40);

        jTextFieldUsername.setText("a@b.c");
        jTextFieldUsername.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextFieldUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsernameActionPerformed(evt);
            }
        });
        jPanelCredentials.add(jTextFieldUsername);
        jTextFieldUsername.setBounds(120, 390, 240, 40);

        jLabelInsertPassword.setText("Insert password");
        jPanelCredentials.add(jLabelInsertPassword);
        jLabelInsertPassword.setBounds(10, 440, 97, 40);

        jLabelLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/Designer (6).png"))); // NOI18N
        jLabelLogo.setText("jLabel3");
        jLabelLogo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelCredentials.add(jLabelLogo);
        jLabelLogo.setBounds(10, 10, 346, 1028);

        jButtonLogin.setText("Login");
        jButtonLogin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });
        jPanelCredentials.add(jButtonLogin);
        jButtonLogin.setBounds(20, 490, 160, 32);

        jButtonRegister.setText("Register");
        jButtonRegister.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegisterActionPerformed(evt);
            }
        });
        jPanelCredentials.add(jButtonRegister);
        jButtonRegister.setBounds(180, 490, 170, 32);

        jPasswordFieldPassword.setText("string");
        jPasswordFieldPassword.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPasswordFieldPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldPasswordActionPerformed(evt);
            }
        });
        jPanelCredentials.add(jPasswordFieldPassword);
        jPasswordFieldPassword.setBounds(120, 440, 240, 40);
        jPanelCredentials.add(jLabelLoginInfo);
        jLabelLoginInfo.setBounds(350, 440, 0, 0);

        jButtonResetPassword.setText("Reset password");
        jPanelCredentials.add(jButtonResetPassword);
        jButtonResetPassword.setBounds(340, 320, 111, 23);

        jPanelLogin.add(jPanelCredentials);
        jPanelCredentials.setBounds(850, -20, 420, 520);

        jPanelBanner.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelBanner.setLayout(null);
        jPanelLogin.add(jPanelBanner);
        jPanelBanner.setBounds(13, 6, 760, 230);

        jPanelWelcomeMessage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelWelcomeMessage.setLayout(null);

        jLabelLine1.setText("Welcome to Workout 2 Success! We are thrilled to have you here.");
        jLabelLine1.setOpaque(true);
        jPanelWelcomeMessage.add(jLabelLine1);
        jLabelLine1.setBounds(10, 10, 650, 34);

        jLabelLine3.setText("Whether you're a beginner or an experienced athlete, our personalized training programs are designed to motivate and inspire you every step of the way.");
        jPanelWelcomeMessage.add(jLabelLine3);
        jLabelLine3.setBounds(10, 70, 835, 27);

        jLabelLine4.setText("Join us today and take the first step toward a healthier, more successful you! ");
        jPanelWelcomeMessage.add(jLabelLine4);
        jLabelLine4.setBounds(10, 130, 835, 27);

        jLabelLine5.setText("Let's embark on this journey together on https://workout2success.com!!!");
        jPanelWelcomeMessage.add(jLabelLine5);
        jLabelLine5.setBounds(10, 160, 383, 16);

        jLabelBanner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/banners/830x300.png"))); // NOI18N
        jPanelWelcomeMessage.add(jLabelBanner);
        jLabelBanner.setBounds(330, -120, 740, 230);

        jPanelLogin.add(jPanelWelcomeMessage);
        jPanelWelcomeMessage.setBounds(-10, 390, 840, 240);

        jLabelLine2.setText("Our dedicated trainers are ready to guide you on your fitness journey, helping you achieve your goals and unlock your full potential.");
        jPanelLogin.add(jLabelLine2);
        jLabelLine2.setBounds(300, 250, 721, 27);

        add(jPanelLogin);
        jPanelLogin.setBounds(6, 6, 1270, 690);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsernameActionPerformed

    }//GEN-LAST:event_jTextFieldUsernameActionPerformed

    /**
     * <p>Evento llamado al hacer click en el botón jButtonLogin</p>
     * <p>Obtiene el texto de los campos de usuario y contraseña y los pasa al método showConnectionDialog del jFrame</p>
     * 
     * @param evt 
     */
    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        String username = jTextFieldUsername.getText();
        String password = new String(jPasswordFieldPassword.getPassword()); // Código adaptado de Stackoverflow https://stackoverflow.com/questions/10443308/why-gettext-in-jpasswordfield-was-deprecated

        jFrameMain.showConnectionDialog(username, password);
    }//GEN-LAST:event_jButtonLoginActionPerformed

    private void jPasswordFieldPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldPasswordActionPerformed

    }//GEN-LAST:event_jPasswordFieldPasswordActionPerformed

    private void jButtonRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegisterActionPerformed

    }//GEN-LAST:event_jButtonRegisterActionPerformed
    
    /**
     * <p>Label que va a mostrar información sobre el inicio de sesión</p>
     * <p>Si se intenta iniciar sesión y el inicio falla este label mostrará la información del porqué ha fallado</p>
     * 
     * @param text texto a mostrar en el label
     */
    public void setJLabelLoginInfo(String text) {
        jLabelLoginInfo.setText(text);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JButton jButtonRegister;
    private javax.swing.JButton jButtonResetPassword;
    private javax.swing.JLabel jLabelBanner;
    private javax.swing.JLabel jLabelInsertPassword;
    private javax.swing.JLabel jLabelInsertUsername;
    private javax.swing.JLabel jLabelLine1;
    private javax.swing.JLabel jLabelLine2;
    private javax.swing.JLabel jLabelLine3;
    private javax.swing.JLabel jLabelLine4;
    private javax.swing.JLabel jLabelLine5;
    private javax.swing.JLabel jLabelLoginInfo;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JPanel jPanelBanner;
    private javax.swing.JPanel jPanelCredentials;
    private javax.swing.JPanel jPanelLogin;
    private javax.swing.JPanel jPanelWelcomeMessage;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration//GEN-END:variables
}
