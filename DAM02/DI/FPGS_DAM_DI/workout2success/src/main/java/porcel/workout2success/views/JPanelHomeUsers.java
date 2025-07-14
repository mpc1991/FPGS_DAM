package porcel.workout2success.views;

import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import porcel.workout2success.Main;
import porcel.workout2success.dto.Exercici;
import porcel.workout2success.dto.ExerciciDAO;
import porcel.workout2success.dto.ExerciciDAOImpl;
import porcel.workout2success.dto.UsuariDAOImpl;
import porcel.workout2success.dto.Usuari;
import porcel.workout2success.dto.UsuariDAO;
import porcel.workout2success.dto.Workout;
import porcel.workout2success.dto.WorkoutDAO;
import porcel.workout2success.dto.WorkoutDAOImpl;

public class JPanelHomeUsers extends javax.swing.JPanel {

    private JPanelHome jPanelHome;
    private Main main;

    String sessionUsername = Main.getUsername();

    private javax.swing.JList<Usuari> jListUsuaris;

    public JPanelHomeUsers(JPanelHome jPanelHome, Main main) {
        initComponents();
        this.jPanelHome = jPanelHome;
        this.main = main;
        inicialize();

        setSize(900, 600);
        jPanelHomeUsers.setBounds(0, 0, 900, 600);
        jLabelUserList.setBounds(10, 10, 150, 30);
        jScrollPaneUsersList.setBounds(10, 40, 150, 510);
        jListUsuaris.setBounds(10, 10, 130, 495);
        jButtonAddUser.setBounds(10, 555, 50, 30);
        jButtonChangeUser.setBounds(60, 555, 50, 30);
        jButtonDeleteUser.setBounds(110, 555, 50, 30);

        jLabelUsersWorkouts.setBounds(170, 10, 370, 30);
        jScrollPaneUsersWorkout.setBounds(170, 40, 350, 210);
        jTableUsersWorkouts.setBounds(5, 5, 350, 195);//
        jButtonAddWorkout.setBounds(170, 256, 119, 30);
        jButtonChangeWorkout.setBounds(284, 256, 119, 30);
        jButtonDeleteWorkout.setBounds(404, 256, 117, 30);

        jLabelUsersExercicis.setBounds(525, 10, 370, 30);
        jScrollPaneUsersExercicis.setBounds(525, 40, 365, 210);
        jTableUsersExercicis.setBounds(5, 5, 355, 180);//
        jButtonAddExercice.setBounds(525, 256, 120, 30);
        jButtonChangeExercice.setBounds(645, 256, 120, 30);
        jButtonDeleteExercice.setBounds(765, 256, 125, 30);

        jLabelAllWorkouts.setBounds(170, 300, 350, 30);
        jScrollPaneAllWorkouts.setBounds(170, 330, 350, 220);
        jTableAllWorkouts.setBounds(5, 5, 340, 205);
        jButtonAddWorkoutToUser.setBounds(170, 555, 350, 30);

        jLabelAllExercices.setBounds(525, 300, 365, 30);
        jScrollPaneAllExercices.setBounds(525, 330, 365, 220);
        jTableAllExercices.setBounds(5, 5, 355, 210);
        jButtonAddExerciceToWorkout.setBounds(525, 555, 365, 30);

        jButtonRefresh.setBounds(828, 1, 70, 30);

        this.add(jPanelHomeUsers);
        jPanelHomeUsers.add(jLabelUserList);

        jPanelHomeUsers.add(jScrollPaneUsersList);
        jScrollPaneUsersList.setViewportView(jListUsuaris);
        jPanelHomeUsers.add(jButtonAddUser);
        jPanelHomeUsers.add(jButtonChangeUser);
        jPanelHomeUsers.add(jButtonDeleteUser);

        jPanelHomeUsers.add(jLabelUsersWorkouts);
        jPanelHomeUsers.add(jScrollPaneUsersWorkout);
        jScrollPaneUsersWorkout.setViewportView(jTableUsersWorkouts);
        jPanelHomeUsers.add(jButtonAddWorkout);
        jPanelHomeUsers.add(jButtonChangeWorkout);
        jPanelHomeUsers.add(jButtonDeleteWorkout);

        jPanelHomeUsers.add(jLabelUsersExercicis);
        jPanelHomeUsers.add(jScrollPaneUsersExercicis);
        jScrollPaneUsersExercicis.setViewportView(jTableUsersExercicis);
        jPanelHomeUsers.add(jButtonAddExercice);
        jPanelHomeUsers.add(jButtonChangeExercice);
        jPanelHomeUsers.add(jButtonDeleteExercice);

        jPanelHomeUsers.add(jLabelAllWorkouts);
        jPanelHomeUsers.add(jScrollPaneAllWorkouts);
        jScrollPaneAllWorkouts.setViewportView(jTableAllWorkouts);
        jPanelHomeUsers.add(jButtonAddWorkoutToUser);

        jPanelHomeUsers.add(jLabelAllExercices);
        jPanelHomeUsers.add(jScrollPaneAllExercices);
        jScrollPaneAllExercices.setViewportView(jTableAllExercices);
        jPanelHomeUsers.add(jButtonAddExerciceToWorkout);

        refresh();
    }

    private void inicialize() {
        jListUsuaris = new javax.swing.JList<>();

        DefaultTableModel dtmUserWorkouts = new DefaultTableModel();
        dtmUserWorkouts.setColumnIdentifiers(new String[]{"ID", "Fecha", "ID Usuario", "Comentarios"});
        jTableUsersWorkouts.setModel(dtmUserWorkouts);

        DefaultTableModel dtmjTableUsersExercicis = new DefaultTableModel();
        dtmjTableUsersExercicis.setColumnIdentifiers(new String[]{"ID", "Nom Exercici", "Descripció", "Demo foto"});
        jTableUsersExercicis.setModel(dtmjTableUsersExercicis);

        DefaultTableModel dtmjTableAllWorkouts = new DefaultTableModel();
        dtmjTableAllWorkouts.setColumnIdentifiers(new String[]{"ID", "Fecha", "ID Usuario", "Comentarios"});
        jTableAllWorkouts.setModel(dtmjTableAllWorkouts);

        DefaultTableModel dtmjTableAllExercices = new DefaultTableModel();
        dtmjTableAllExercices.setColumnIdentifiers(new String[]{"ID", "Fecha", "ID Usuario", "Comentarios"});
        jTableAllExercices.setModel(dtmjTableAllExercices);
    }

    private void refresh() {
        inicializeJLitesteners();
        getListMyUsers();
        getListMyUsersWorkouts(0);
        getListExercicisPerWorkout(0);
        getListAllMyUsersWorkouts();
        getListAllExercicisPerWorkout();
    }

    private void inicializeJLitesteners() {
        jListUsuaris.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListUsuarisValueChanged(evt);
            }
        });

        jTableUsersWorkouts.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jTableUsuarisWorkoutsValuechanged(evt);
            }
        });
    }

    // Metodo para obtener la lista de usuarios del instructor que ha iniciado sesión en la aplicación.
    public void getListMyUsers() {
        if (jListUsuaris == null) {
            jListUsuaris = new javax.swing.JList<>();
            jScrollPaneUsersList.setViewportView(jListUsuaris);
        }

        UsuariDAO usuariDAO = new UsuariDAOImpl();
        try {
            var usuaris = usuariDAO.getMyUsers(sessionUsername);
            DefaultListModel<Usuari> dfmu = new DefaultListModel<>();
            for (Usuari u : usuaris) {
                dfmu.addElement(u);
            }
            jListUsuaris.setModel(dfmu);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Metodo para obtener la lista de workouts del usuario que hemos marcado en la lista de usuarios.
    private void getListMyUsersWorkouts(int id) {
        WorkoutDAO workoutDAO = new WorkoutDAOImpl();
        try {
            var workouts = workoutDAO.getWorkoutsPerUser(id);
            DefaultTableModel dtm = null;
            dtm = new DefaultTableModel();
            dtm.setColumnIdentifiers(new String[]{"ID", "Fecha", "ID Usuario", "Comentarios"});

            for (Workout u : workouts) {
                dtm.addRow(new Object[]{
                    u.getId(),
                    u.getForDate(),
                    u.getUserId(),
                    u.getComments()
                });
            }

            jTableUsersWorkouts.setModel(dtm);

            // Como cojemos los datos de las columnas, necesito que los datos estén impresos en ella, entonces para simplificarlo ocultamos la primera columna.
            jTableUsersWorkouts.getColumnModel().getColumn(0).setMinWidth(0);
            jTableUsersWorkouts.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableUsersWorkouts.getColumnModel().getColumn(0).setWidth(0);

            // Como cojemos los datos de las columnas, necesito que los datos estén impresos en ella, entonces para simplificarlo ocultamos la primera columna.
            jTableUsersWorkouts.getColumnModel().getColumn(2).setMinWidth(0);
            jTableUsersWorkouts.getColumnModel().getColumn(2).setMaxWidth(0);
            jTableUsersWorkouts.getColumnModel().getColumn(2).setWidth(0);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Metodo para obtener la lista de todos los workouts que existen en la BBDD.
    private void getListAllMyUsersWorkouts() {
        WorkoutDAO workoutDAO = new WorkoutDAOImpl();
        try {
            var workouts = workoutDAO.getAll();

            DefaultTableModel dtm = new DefaultTableModel();
            dtm.setColumnIdentifiers(new String[]{"ID", "Fecha", "ID Usuario", "Comentarios"});

            for (Workout u : workouts) {
                dtm.addRow(new Object[]{
                    u.getId(),
                    u.getForDate(),
                    u.getUserId(),
                    u.getComments()
                });
            }

            jTableAllWorkouts.setModel(dtm);

            // Como cojemos los datos de las columnas, necesito que los datos estén impresos en ella, entonces para simplificarlo ocultamos la primera columna.
            jTableAllWorkouts.getColumnModel().getColumn(0).setMinWidth(0);
            jTableAllWorkouts.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableAllWorkouts.getColumnModel().getColumn(0).setWidth(0);

            // Como cojemos los datos de las columnas, necesito que los datos estén impresos en ella, entonces para simplificarlo ocultamos la primera columna.
            jTableAllWorkouts.getColumnModel().getColumn(2).setMinWidth(0);
            jTableAllWorkouts.getColumnModel().getColumn(2).setMaxWidth(0);
            jTableAllWorkouts.getColumnModel().getColumn(2).setWidth(0);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Metodo para obtener la lista de Ejercicios del Workouto que hemos marcado en la lista de workouts.
    private void getListExercicisPerWorkout(int id) {
        ExerciciDAO exerciciDAO = new ExerciciDAOImpl();
        try {
            var exercicis = exerciciDAO.getExercicisPerWorkout(id);

            DefaultTableModel dtm = new DefaultTableModel();
            dtm.setColumnIdentifiers(new String[]{"ID", "Nom Exercici", "Descripció", "Demo foto"});

            for (Exercici exercici : exercicis) {
                dtm.addRow(new Object[]{
                    exercici.getId(),
                    exercici.getNomExercici(),
                    exercici.getDescripcio(),
                    exercici.getDemoFoto()
                });
            }
            jTableUsersExercicis.setModel(dtm);

            // Como cojemos los datos de las columnas, necesito que los datos estén impresos en ella, entonces para simplificarlo ocultamos la primera columna.
            jTableUsersExercicis.getColumnModel().getColumn(0).setMinWidth(0);
            jTableUsersExercicis.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableUsersExercicis.getColumnModel().getColumn(0).setWidth(0);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Metodo para obtener la lista de todos los Ejercicios que existen en la BBDD.
    private void getListAllExercicisPerWorkout() {
        ExerciciDAO exerciciDAO = new ExerciciDAOImpl();
        try {
            var exercicis = exerciciDAO.getAll();

            DefaultTableModel dtm = new DefaultTableModel();
            dtm.setColumnIdentifiers(new String[]{"ID", "Nom Exercici", "Descripció", "Demo foto"});

            for (Exercici exercici : exercicis) {
                dtm.addRow(new Object[]{
                    exercici.getId(),
                    exercici.getNomExercici(),
                    exercici.getDescripcio(),
                    exercici.getDemoFoto()
                });
            }
            jTableAllExercices.setModel(dtm);

            // Como cojemos los datos de las columnas, necesito que los datos estén impresos en ella, entonces para simplificarlo ocultamos la primera columna.
            jTableAllExercices.getColumnModel().getColumn(0).setMinWidth(0);
            jTableAllExercices.getColumnModel().getColumn(0).setMaxWidth(0);
            jTableAllExercices.getColumnModel().getColumn(0).setWidth(0);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void jListUsuarisValueChanged(javax.swing.event.ListSelectionEvent evt) {
        Usuari selectedUser = jListUsuaris.getSelectedValue();
        if (selectedUser != null) {
            int userId = selectedUser.getId();
            getListMyUsersWorkouts(userId);
        } else {
        }
    }

    private void jTableUsuarisWorkoutsValuechanged(javax.swing.event.ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting()) {
            int selectedRow = jTableUsersWorkouts.getSelectedRow();

            if (selectedRow != -1) { // Verifica que haya una fila seleccionada
                int selectedWorkout = (int) jTableUsersWorkouts.getValueAt(selectedRow, 0);
                getListExercicisPerWorkout(selectedWorkout);
            } else {
            }
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHomeUsers = new javax.swing.JPanel();
        jLabelUserList = new javax.swing.JLabel();
        jScrollPaneUsersList = new javax.swing.JScrollPane();
        jButtonAddUser = new javax.swing.JButton();
        jButtonChangeUser = new javax.swing.JButton();
        jButtonDeleteUser = new javax.swing.JButton();
        jLabelUsersWorkouts = new javax.swing.JLabel();
        jScrollPaneUsersWorkout = new javax.swing.JScrollPane();
        jTableUsersWorkouts = new javax.swing.JTable();
        jButtonAddWorkout = new javax.swing.JButton();
        jButtonChangeWorkout = new javax.swing.JButton();
        jButtonDeleteWorkout = new javax.swing.JButton();
        jLabelUsersExercicis = new javax.swing.JLabel();
        jScrollPaneUsersExercicis = new javax.swing.JScrollPane();
        jTableUsersExercicis = new javax.swing.JTable();
        jButtonAddExercice = new javax.swing.JButton();
        jButtonChangeExercice = new javax.swing.JButton();
        jButtonDeleteExercice = new javax.swing.JButton();
        jLabelAllWorkouts = new javax.swing.JLabel();
        jScrollPaneAllWorkouts = new javax.swing.JScrollPane();
        jTableAllWorkouts = new javax.swing.JTable();
        jButtonAddWorkoutToUser = new javax.swing.JButton();
        jLabelAllExercices = new javax.swing.JLabel();
        jScrollPaneAllExercices = new javax.swing.JScrollPane();
        jTableAllExercices = new javax.swing.JTable();
        jButtonAddExerciceToWorkout = new javax.swing.JButton();
        jButtonRefresh = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setLayout(null);

        jPanelHomeUsers.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelHomeUsers.setLayout(null);

        jLabelUserList.setText("My users");
        jPanelHomeUsers.add(jLabelUserList);
        jLabelUserList.setBounds(20, 20, 110, 16);

        jScrollPaneUsersList.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelHomeUsers.add(jScrollPaneUsersList);
        jScrollPaneUsersList.setBounds(20, 40, 330, 420);

        jButtonAddUser.setText("Add");
        jButtonAddUser.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddUserActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonAddUser);
        jButtonAddUser.setBounds(20, 460, 50, 20);

        jButtonChangeUser.setText("Change");
        jButtonChangeUser.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonChangeUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangeUserActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonChangeUser);
        jButtonChangeUser.setBounds(110, 470, 50, 20);

        jButtonDeleteUser.setText("Delete");
        jButtonDeleteUser.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelHomeUsers.add(jButtonDeleteUser);
        jButtonDeleteUser.setBounds(190, 460, 70, 20);

        jLabelUsersWorkouts.setText("User workouts");
        jPanelHomeUsers.add(jLabelUsersWorkouts);
        jLabelUsersWorkouts.setBounds(380, 20, 160, 16);

        jScrollPaneUsersWorkout.setViewportBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableUsersWorkouts.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTableUsersWorkouts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPaneUsersWorkout.setViewportView(jTableUsersWorkouts);

        jPanelHomeUsers.add(jScrollPaneUsersWorkout);
        jScrollPaneUsersWorkout.setBounds(380, 50, 230, 140);

        jButtonAddWorkout.setText("Add Workout");
        jButtonAddWorkout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAddWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddWorkoutActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonAddWorkout);
        jButtonAddWorkout.setBounds(380, 200, 40, 20);

        jButtonChangeWorkout.setText("Change Workout");
        jButtonChangeWorkout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonChangeWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangeWorkoutActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonChangeWorkout);
        jButtonChangeWorkout.setBounds(440, 220, 60, 20);

        jButtonDeleteWorkout.setText("Delete Workout");
        jButtonDeleteWorkout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonDeleteWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteWorkoutActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonDeleteWorkout);
        jButtonDeleteWorkout.setBounds(520, 210, 60, 20);

        jLabelUsersExercicis.setText("User exercice");
        jPanelHomeUsers.add(jLabelUsersExercicis);
        jLabelUsersExercicis.setBounds(690, 20, 240, 16);

        jScrollPaneUsersExercicis.setViewportBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableUsersExercicis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPaneUsersExercicis.setViewportView(jTableUsersExercicis);

        jPanelHomeUsers.add(jScrollPaneUsersExercicis);
        jScrollPaneUsersExercicis.setBounds(680, 20, 180, 200);

        jButtonAddExercice.setText("Add exercice");
        jButtonAddExercice.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAddExercice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddExerciceActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonAddExercice);
        jButtonAddExercice.setBounds(670, 260, 50, 20);

        jButtonChangeExercice.setText("Change exercice");
        jButtonChangeExercice.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonChangeExercice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangeExerciceActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonChangeExercice);
        jButtonChangeExercice.setBounds(720, 260, 50, 20);

        jButtonDeleteExercice.setText("Delete exercice");
        jButtonDeleteExercice.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonDeleteExercice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteExerciceActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonDeleteExercice);
        jButtonDeleteExercice.setBounds(770, 230, 70, 20);

        jLabelAllWorkouts.setText("All Workouts");
        jPanelHomeUsers.add(jLabelAllWorkouts);
        jLabelAllWorkouts.setBounds(430, 290, 130, 16);

        jScrollPaneAllWorkouts.setViewportBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableAllWorkouts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPaneAllWorkouts.setViewportView(jTableAllWorkouts);

        jPanelHomeUsers.add(jScrollPaneAllWorkouts);
        jScrollPaneAllWorkouts.setBounds(390, 310, 200, 130);

        jButtonAddWorkoutToUser.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAddWorkoutToUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddWorkoutToUserActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonAddWorkoutToUser);
        jButtonAddWorkoutToUser.setBounds(400, 480, 220, 4);

        jLabelAllExercices.setText("All Exercices");
        jPanelHomeUsers.add(jLabelAllExercices);
        jLabelAllExercices.setBounds(670, 300, 130, 16);

        jTableAllExercices.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPaneAllExercices.setViewportView(jTableAllExercices);

        jPanelHomeUsers.add(jScrollPaneAllExercices);
        jScrollPaneAllExercices.setBounds(670, 320, 170, 110);

        jButtonAddExerciceToWorkout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAddExerciceToWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddExerciceToWorkoutActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonAddExerciceToWorkout);
        jButtonAddExerciceToWorkout.setBounds(660, 480, 170, 4);

        jButtonRefresh.setText("Refresh");
        jButtonRefresh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });
        jPanelHomeUsers.add(jButtonRefresh);
        jButtonRefresh.setBounds(1040, 10, 43, 20);

        add(jPanelHomeUsers);
        jPanelHomeUsers.setBounds(0, 0, 1160, 540);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonChangeExerciceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangeExerciceActionPerformed
        main.UnderDevelopment();
    }//GEN-LAST:event_jButtonChangeExerciceActionPerformed

    private void jButtonAddExerciceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddExerciceActionPerformed
        main.showAddExercicisDialog();
    }//GEN-LAST:event_jButtonAddExerciceActionPerformed

    private void jButtonAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddUserActionPerformed
        //Main main = new Main();
        main.showAddUsersDialog();
    }//GEN-LAST:event_jButtonAddUserActionPerformed

    private void jButtonChangeUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangeUserActionPerformed
        main.UnderDevelopment();
    }//GEN-LAST:event_jButtonChangeUserActionPerformed

    private void jButtonAddWorkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddWorkoutActionPerformed
        main.showAddWorkoutsDialog();
    }//GEN-LAST:event_jButtonAddWorkoutActionPerformed

    private void jButtonDeleteExerciceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteExerciceActionPerformed
        int selectedRow = jTableUsersExercicis.getSelectedRow();
        ExerciciDAO exerciciDAO = new ExerciciDAOImpl();
        if (selectedRow != -1) { // Verifica que haya una fila seleccionada
            int selectedWorkout = (int) jTableUsersExercicis.getValueAt(selectedRow, 0);
            try {
                int result = exerciciDAO.deleteExercici(selectedWorkout);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al eliminar el ejercicio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
        }
        refresh();
    }//GEN-LAST:event_jButtonDeleteExerciceActionPerformed

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        refresh();
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jButtonDeleteWorkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteWorkoutActionPerformed
        int selectedRow = jTableUsersWorkouts.getSelectedRow();
        WorkoutDAO workoutDAO = new WorkoutDAOImpl();
        if (selectedRow != -1) { // Verifica que haya una fila seleccionada
            int selectedWorkout = (int) jTableUsersWorkouts.getValueAt(selectedRow, 0);
            try {
                int result = workoutDAO.deleteWorkout(selectedWorkout);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: Delete asociated exercice first");
            }
        } else {
        }
        refresh();
    }//GEN-LAST:event_jButtonDeleteWorkoutActionPerformed

    private void jButtonChangeWorkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangeWorkoutActionPerformed
        main.UnderDevelopment();
    }//GEN-LAST:event_jButtonChangeWorkoutActionPerformed

    private void jButtonAddExerciceToWorkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddExerciceToWorkoutActionPerformed
        main.UnderDevelopment();
    }//GEN-LAST:event_jButtonAddExerciceToWorkoutActionPerformed

    private void jButtonAddWorkoutToUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddWorkoutToUserActionPerformed
        main.UnderDevelopment();
    }//GEN-LAST:event_jButtonAddWorkoutToUserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddExercice;
    private javax.swing.JButton jButtonAddExerciceToWorkout;
    private javax.swing.JButton jButtonAddUser;
    private javax.swing.JButton jButtonAddWorkout;
    private javax.swing.JButton jButtonAddWorkoutToUser;
    private javax.swing.JButton jButtonChangeExercice;
    private javax.swing.JButton jButtonChangeUser;
    private javax.swing.JButton jButtonChangeWorkout;
    private javax.swing.JButton jButtonDeleteExercice;
    private javax.swing.JButton jButtonDeleteUser;
    private javax.swing.JButton jButtonDeleteWorkout;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JLabel jLabelAllExercices;
    private javax.swing.JLabel jLabelAllWorkouts;
    private javax.swing.JLabel jLabelUserList;
    private javax.swing.JLabel jLabelUsersExercicis;
    private javax.swing.JLabel jLabelUsersWorkouts;
    private javax.swing.JPanel jPanelHomeUsers;
    private javax.swing.JScrollPane jScrollPaneAllExercices;
    private javax.swing.JScrollPane jScrollPaneAllWorkouts;
    private javax.swing.JScrollPane jScrollPaneUsersExercicis;
    private javax.swing.JScrollPane jScrollPaneUsersList;
    private javax.swing.JScrollPane jScrollPaneUsersWorkout;
    private javax.swing.JTable jTableAllExercices;
    private javax.swing.JTable jTableAllWorkouts;
    private javax.swing.JTable jTableUsersExercicis;
    private javax.swing.JTable jTableUsersWorkouts;
    // End of variables declaration//GEN-END:variables
}
