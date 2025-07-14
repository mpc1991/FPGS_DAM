package porcel.workout2success.views;

import porcel.workout2success.Main;

public class JDialogAbout extends javax.swing.JDialog {

    Main mainFrame;

    public JDialogAbout(Main parent, boolean modal) {
        super(parent, modal);
        this.mainFrame = parent;
        initComponents();

        this.setSize(1200, 600);
        jScrollPaneAbout.setBounds(0, 0, 1200, 600);
        jTextAreaAbout.setBounds(10, 10, 1180, 580);

        jTextAreaAbout.setEditable(false);
        jTextAreaAbout.setText(
                "Developed by: Macia Porcel\n\n"
                + "Course: DAM02 - SI01\n"
                + "Instructor: Miguel Oscar García Jodar\n\n"
                + "Resources Used:\n"
                + "- Logo by https://looka.com/\n"
                + "- \n"
                + "- Libraries: \n"
                + "-- BCrypt for Java by Password4j\n\n"
                + "-- at.favre.li "
                + "This application demonstrates basic functionalities of Java Swing, including panels, dialogs, and menus.\n"
        );

        this.add(jScrollPaneAbout);
        jScrollPaneAbout.add(jTextAreaAbout);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneAbout = new javax.swing.JScrollPane();
        jTextAreaAbout = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jScrollPaneAbout.setBorder(null);

        jTextAreaAbout.setColumns(20);
        jTextAreaAbout.setRows(5);
        jTextAreaAbout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPaneAbout.setViewportView(jTextAreaAbout);

        getContentPane().add(jScrollPaneAbout);
        jScrollPaneAbout.setBounds(30, 20, 224, 84);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPaneAbout;
    private javax.swing.JTextArea jTextAreaAbout;
    // End of variables declaration//GEN-END:variables
}
