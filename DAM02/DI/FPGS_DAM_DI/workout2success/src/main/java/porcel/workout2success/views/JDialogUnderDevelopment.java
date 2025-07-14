// Panel comodin para las funciones que todavía no están implementadas.
package porcel.workout2success.views;

import javax.swing.JTextField;
import porcel.workout2success.Main;

public class JDialogUnderDevelopment extends javax.swing.JDialog {

    Main mainFrame;

    public JDialogUnderDevelopment(Main parent, boolean modal) {
        super(parent, modal);
        this.mainFrame = parent;
        initComponents();

        setSize(450, 300);
        jScrollPaneUnderDevelopment.setBounds(0, 0, 440, 290);
        jTextAreaUnderDevelopment.setBounds(10, 10, 440, 290);
        jButtonConfirm.setBounds(115, 182, 220, 30);
        jButtonConfirm.setHorizontalAlignment(JTextField.CENTER);

        jTextAreaUnderDevelopment.setEditable(false);
        jTextAreaUnderDevelopment.setText(
                "This section is under development\n\n"
                + "For look & feel purposes the buttons are\nvisible but don't have any action enabled\n\n"
                + "sorry for the inconvenience.\n\n"
        );

        this.add(jScrollPaneUnderDevelopment);
        jScrollPaneUnderDevelopment.add(jTextAreaUnderDevelopment);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneUnderDevelopment = new javax.swing.JScrollPane();
        jTextAreaUnderDevelopment = new javax.swing.JTextArea();
        jButtonConfirm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jScrollPaneUnderDevelopment.setBorder(null);

        jTextAreaUnderDevelopment.setColumns(20);
        jTextAreaUnderDevelopment.setRows(5);
        jTextAreaUnderDevelopment.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPaneUnderDevelopment.setViewportView(jTextAreaUnderDevelopment);

        getContentPane().add(jScrollPaneUnderDevelopment);
        jScrollPaneUnderDevelopment.setBounds(30, 20, 224, 84);

        jButtonConfirm.setText("jButton1");
        getContentPane().add(jButtonConfirm);
        jButtonConfirm.setBounds(110, 150, 75, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfirm;
    private javax.swing.JScrollPane jScrollPaneUnderDevelopment;
    private javax.swing.JTextArea jTextAreaUnderDevelopment;
    // End of variables declaration//GEN-END:variables
}
