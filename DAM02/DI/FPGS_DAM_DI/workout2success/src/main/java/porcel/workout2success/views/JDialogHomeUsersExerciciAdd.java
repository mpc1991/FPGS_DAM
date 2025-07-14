/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package porcel.workout2success.views;

import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import porcel.workout2success.Main;
import porcel.workout2success.dto.Exercici;
import porcel.workout2success.dto.ExerciciDAO;
import porcel.workout2success.dto.ExerciciDAOImpl;
import porcel.workout2success.dto.Usuari;
import porcel.workout2success.dto.UsuariDAO;
import porcel.workout2success.dto.UsuariDAOImpl;
import porcel.workout2success.dto.Workout;
import porcel.workout2success.dto.WorkoutDAO;
import porcel.workout2success.dto.WorkoutDAOImpl;

public class JDialogHomeUsersExerciciAdd extends javax.swing.JDialog {

    Main mainFrame;
    private javax.swing.JComboBox<Usuari> jComboBoxSelectUser;
    private javax.swing.JComboBox<Exercici> jComboBoxSelectExercici;
    private javax.swing.JComboBox<Workout> jComboBoxSelectWorkout;

    String sessionUsername = Main.getUsername();

    public JDialogHomeUsersExerciciAdd(Main parent, boolean modal, String sessionUsername) {
        super(parent, modal);
        this.mainFrame = parent;
        initComponents();

        inicialize();
        initializeJListeners();
        getAllMyUsers();
        getAllExercices();
    }

    private void inicialize() {
        setSize(450, 320);
        if (jComboBoxSelectUser == null) {
            jComboBoxSelectUser = new JComboBox<>();
        }
        if (jComboBoxSelectWorkout == null) {
            jComboBoxSelectWorkout = new JComboBox<>();
        }
        if (jComboBoxSelectExercici == null) {
            jComboBoxSelectExercici = new JComboBox<>();
        }

        jPanelAddWorkouts.setBounds(0, 0, 435, 300);
        jLabelSelectUser.setBounds(10, 10, 100, 40);
        jLabelSelectWorkout.setBounds(10, 60, 100, 40);
        jLabelDescripcio.setBounds(10, 110, 100, 40);
        //jLabelDescripcio.setBounds(10,160,100,40);

        jComboBoxSelectUser.setBounds(120, 10, 310, 40);
        jComboBoxSelectWorkout.setBounds(120, 60, 310, 40);
        jComboBoxSelectExercici.setBounds(120, 110, 310, 40);
        //jComboBoxSelectExercici.setBounds(120, 160, 310, 40);

        jButtonCreate.setBounds(60, 210, 310, 40);
        jLabelError.setHorizontalAlignment(JTextField.CENTER);

        this.add(jPanelAddWorkouts);
        jPanelAddWorkouts.add(jLabelSelectUser);
        jPanelAddWorkouts.add(jLabelSelectWorkout);
        //jPanelAddWorkouts.add(jLabelNomExercici);
        jLabelNomExercici.setVisible(false);
        jPanelAddWorkouts.add(jLabelDescripcio);
        jPanelAddWorkouts.add(jComboBoxSelectUser);
        jPanelAddWorkouts.add(jComboBoxSelectWorkout);
        //jPanelAddWorkouts.add(jTextFieldNomexercici);
        jTextFieldNomexercici.setVisible(false);
        jPanelAddWorkouts.add(jComboBoxSelectExercici);
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
            ex.printStackTrace(); // Para obtener más detalles sobre la excepción.
        }
    }

    private void getListMyUsersWorkouts(int id) {
        WorkoutDAO workoutDAO = new WorkoutDAOImpl();
        try {
            var workouts = workoutDAO.getWorkoutsPerUser(id);

            // Limpiar el contenido actual del combo box antes de agregar nuevos elementos
            jComboBoxSelectWorkout.removeAllItems();

            // Llenar el combo box con los objetos de tipo Workout
            for (Workout w : workouts) {
                jComboBoxSelectWorkout.addItem(w);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getAllExercices() {
        ExerciciDAO exerciciDAO = new ExerciciDAOImpl();
        try {
            var exercicis = exerciciDAO.getAll();
            for (Exercici exercici : exercicis) {
                jComboBoxSelectExercici.addItem(exercici);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initializeJListeners() {
        jComboBoxSelectUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSelectUserActionPerformed(evt);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void jComboBoxSelectUserActionPerformed(java.awt.event.ActionEvent evt) {
        Usuari selectedUser = (Usuari) jComboBoxSelectUser.getSelectedItem();
        if (selectedUser != null) {
            int userId = selectedUser.getId();
            getListMyUsersWorkouts(userId);
        }
    }

    // Las siguientes secciones deberán descomentarse en caso de querer aplicar acciones adicionales
    private void jComboBoxSelectWorkoutActionPerformed(java.awt.event.ActionEvent evt) {
        /*Workout selectedWorkout = (Workout) jComboBoxSelectWorkout.getSelectedItem();
        if (selectedWorkout != null) {
            int workoutId = (int)selectedWorkout.getId();
            getAllExercices(workoutId);
        
        /*if (!evt.getValueIsAdjusting()) {
            int selectedRow = jTableUsersWorkouts.getSelectedRow();

            if (selectedRow != -1) { // Verifica que haya una fila seleccionada
                int selectedWorkout = (int) jTableUsersWorkouts.getValueAt(selectedRow, 0);
                getAllExercices(selectedWorkout);*/
    }

    /*private void jComboBoxSelectExerciciActionPerformed(java.awt.event.ActionEvent evt) {
        Workout selectedWorkout = (Workout) jComboBoxSelectWorkout.getSelectedItem();
        if (selectedWorkout != null) {
            int workoutId = selectedWorkout.getId();
            getAllExercices(workoutId); // Aquí debes obtener los ejercicios asociados con el workout seleccionado.
        }
    }*/

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelAddWorkouts = new javax.swing.JPanel();
        jLabelSelectUser = new javax.swing.JLabel();
        jLabelSelectWorkout = new javax.swing.JLabel();
        jLabelNomExercici = new javax.swing.JLabel();
        jLabelDescripcio = new javax.swing.JLabel();
        jTextFieldNomexercici = new javax.swing.JTextField();
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

        jLabelSelectUser.setText("Select User to add");
        jPanelAddWorkouts.add(jLabelSelectUser);
        jLabelSelectUser.setBounds(30, 20, 110, 16);

        jLabelSelectWorkout.setText("Select Workout");
        jPanelAddWorkouts.add(jLabelSelectWorkout);
        jLabelSelectWorkout.setBounds(30, 50, 81, 16);

        jLabelNomExercici.setText("Exercice name");
        jPanelAddWorkouts.add(jLabelNomExercici);
        jLabelNomExercici.setBounds(40, 80, 90, 16);

        jLabelDescripcio.setText("Exercice");
        jPanelAddWorkouts.add(jLabelDescripcio);
        jLabelDescripcio.setBounds(30, 110, 41, 16);

        jTextFieldNomexercici.setText("ExerciciX");
        jPanelAddWorkouts.add(jTextFieldNomexercici);
        jTextFieldNomexercici.setBounds(210, 70, 64, 22);

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
        Workout selectedWorkout = (Workout) jComboBoxSelectWorkout.getSelectedItem();
        Exercici selectedExercici = (Exercici) jComboBoxSelectExercici.getSelectedItem();

        int idWorkout = selectedWorkout.getId();
        int idExercici = selectedExercici.getId();

        ExerciciDAO exerciciDAO = new ExerciciDAOImpl();

        try {
            exerciciDAO.insertExerciciPerWorkout(idWorkout, idExercici);
        } catch (SQLException ex) {
        }
        dispose();
    }//GEN-LAST:event_jButtonCreateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCreate;
    private javax.swing.JLabel jLabelDescripcio;
    private javax.swing.JLabel jLabelError;
    private javax.swing.JLabel jLabelNomExercici;
    private javax.swing.JLabel jLabelSelectUser;
    private javax.swing.JLabel jLabelSelectWorkout;
    private javax.swing.JPanel jPanelAddWorkouts;
    private javax.swing.JTextField jTextFieldNomexercici;
    // End of variables declaration//GEN-END:variables
}
