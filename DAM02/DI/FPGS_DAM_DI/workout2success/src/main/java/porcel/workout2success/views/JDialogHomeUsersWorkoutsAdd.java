package porcel.workout2success.views;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import porcel.workout2success.Main;
import porcel.workout2success.dto.Usuari;
import porcel.workout2success.dto.UsuariDAO;
import porcel.workout2success.dto.UsuariDAOImpl;
import porcel.workout2success.dto.Workout;
import porcel.workout2success.dto.WorkoutDAO;
import porcel.workout2success.dto.WorkoutDAOImpl;

public class JDialogHomeUsersWorkoutsAdd extends javax.swing.JDialog {

    Main mainFrame;
    private javax.swing.JComboBox<Usuari> jComboBoxSelectUser;
    String sessionUsername = Main.getUsername();

    public JDialogHomeUsersWorkoutsAdd(Main parent, boolean modal, String sessionUsername) {
        super(parent, modal);
        this.mainFrame = parent;

        initComponents();
        inicialize();
        getAllMyUsers();
    }

    private void inicialize() {
        setSize(450, 320);
        if (jComboBoxSelectUser == null) {
            jComboBoxSelectUser = new JComboBox<>();
        }

        jPanelAddWorkouts.setBounds(0, 0, 435, 300);
        jLabelSelectUser.setBounds(10, 10, 100, 40);
        jLabelForDate.setBounds(10, 60, 100, 40);
        jLabelComments.setBounds(10, 110, 100, 40);

        jComboBoxSelectUser.setBounds(120, 10, 310, 40);
        jSpinnerSelectdate.setBounds(120, 60, 310, 40);
        jTextFieldComments.setBounds(120, 110, 310, 40);

        jButtonCreate.setBounds(60, 210, 310, 40);
        jLabelError.setHorizontalAlignment(JTextField.CENTER);

        this.add(jPanelAddWorkouts);
        jPanelAddWorkouts.add(jLabelSelectUser);
        jPanelAddWorkouts.add(jLabelForDate);
        jPanelAddWorkouts.add(jLabelComments);
        jPanelAddWorkouts.add(jComboBoxSelectUser);
        jPanelAddWorkouts.add(jSpinnerSelectdate);
        jPanelAddWorkouts.add(jTextFieldComments);
        jPanelAddWorkouts.add(jButtonCreate);
        jPanelAddWorkouts.add(jLabelError);
    }

    private void getAllMyUsers() {
        UsuariDAO usuariDAO = new UsuariDAOImpl();
        if (jComboBoxSelectUser == null) {
            jComboBoxSelectUser = new JComboBox<>();
        }
        try {
            var usuaris = usuariDAO.getMyUsers(sessionUsername);
            DefaultComboBoxModel<Usuari> defaultComboBoxModel = new DefaultComboBoxModel<>();
            for (Usuari u : usuaris) {
                defaultComboBoxModel.addElement(u);
            }
            jComboBoxSelectUser.setModel(defaultComboBoxModel);

        } catch (SQLException ex) {
            System.out.println("Error al obtener la lista de usuarios");
            //ex.printStackTrace(); // Para obtener más detalles sobre la excepción.
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelAddWorkouts = new javax.swing.JPanel();
        jLabelSelectUser = new javax.swing.JLabel();
        jLabelForDate = new javax.swing.JLabel();
        jLabelComments = new javax.swing.JLabel();
        jSpinnerSelectdate = new javax.swing.JSpinner();
        jTextFieldComments = new javax.swing.JTextField();
        jButtonCreate = new javax.swing.JButton();
        jLabelError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanelAddWorkouts.setLayout(null);

        jLabelSelectUser.setText("Select User");
        jPanelAddWorkouts.add(jLabelSelectUser);
        jLabelSelectUser.setBounds(30, 20, 110, 16);

        jLabelForDate.setText("For Date");
        jPanelAddWorkouts.add(jLabelForDate);
        jLabelForDate.setBounds(30, 50, 45, 16);

        jLabelComments.setText("Comments");
        jPanelAddWorkouts.add(jLabelComments);
        jLabelComments.setBounds(30, 90, 58, 16);

        jSpinnerSelectdate.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.YEAR));
        jSpinnerSelectdate.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelAddWorkouts.add(jSpinnerSelectdate);
        jSpinnerSelectdate.setBounds(180, 50, 140, 24);
        jPanelAddWorkouts.add(jTextFieldComments);
        jTextFieldComments.setBounds(170, 80, 270, 90);

        jButtonCreate.setText("Create");
        jButtonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateActionPerformed(evt);
            }
        });
        jPanelAddWorkouts.add(jButtonCreate);
        jButtonCreate.setBounds(160, 190, 72, 23);
        jPanelAddWorkouts.add(jLabelError);
        jLabelError.setBounds(170, 240, 0, 0);

        getContentPane().add(jPanelAddWorkouts);
        jPanelAddWorkouts.setBounds(0, 0, 510, 370);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void jButtonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateActionPerformed
        Usuari selectedUser = (Usuari) jComboBoxSelectUser.getSelectedItem();
        Date dateValue = (Date) jSpinnerSelectdate.getValue();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fecha = dateFormat.format(dateValue);
        String comment = jTextFieldComments.getText();

        if (selectedUser == null || fecha == null) {
            jLabelError.setText("You have to select an user and a date.");
            return;
        }

        Workout workout = new Workout(fecha, selectedUser.getId(), comment);
        WorkoutDAO workoutDAO = new WorkoutDAOImpl();

        try {
            workoutDAO.insert(workout);
        } catch (SQLException ex) {
        }
        dispose();
    }//GEN-LAST:event_jButtonCreateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCreate;
    private javax.swing.JLabel jLabelComments;
    private javax.swing.JLabel jLabelError;
    private javax.swing.JLabel jLabelForDate;
    private javax.swing.JLabel jLabelSelectUser;
    private javax.swing.JPanel jPanelAddWorkouts;
    private javax.swing.JSpinner jSpinnerSelectdate;
    private javax.swing.JTextField jTextFieldComments;
    // End of variables declaration//GEN-END:variables
}
