package net.porcel.main2images;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTextArea;
import net.porcel.workout2calendar.Workout2Calendar;
import net.porcel.component2images.MyEventListeners;
import net.porcel.workout2calendar.Listeners.MyCalendarListeners;
import net.porcel.workout2calendar.Listeners.hasWorkoutsEventArgs;

public class JFrameMain2Images extends javax.swing.JFrame {

    private String connectionString;
    private String containerName;
    private JTextArea jTextArea;

    public JFrameMain2Images() {
        initComponents();
        inicialice();
        //pack();
    }

    private void inicialice() {

        this.setSize(600, 700);
        this.setLocationRelativeTo(null); // Centrar la ventana
        this.setVisible(true);

        //component2image
        connectionString = "DefaultEndpointsProtocol=https;AccountName=di02fileserver;AccountKey=sLHDVoLFZ80d6t04+DDAseWvKvT+/fvRsqld1xYxAM3zu8jhtCaMyVwy7vdQ0wtmpbaGwMLdvERX+AStRPZNTQ==;EndpointSuffix=core.windows.net";
        containerName = "di02resources";
        jLabelInterval.setBounds(10, 140, 100, 20);

        jSliderRequestInterval.setMinimum(1);
        jSliderRequestInterval.setMaximum(60);
        jSliderRequestInterval.setBounds(10, 150, 210, 40);

        jButtonStart.setBounds(10, 180, 100, 30);
        jButtonStop.setBounds(120, 180, 100, 30);

        jPanel2Images.setBounds(10, 10, 100, 100);
        jTextArea = new JTextArea();
        jTextArea.setBounds(250, 10, 300, 200);

        this.add(jPanel2Images);
        this.add(jTextArea);

        this.add(jLabelInterval);
        this.add(jSliderRequestInterval);
        this.add(jButtonStart);
        this.add(jButtonStop);

        // calendar
        workout2Calendar.setVisible(false);
        jButtonShowcalendar.setBounds(10, 210, 150, 30);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonStart = new javax.swing.JButton();
        jButtonStop = new javax.swing.JButton();
        jSliderRequestInterval = new javax.swing.JSlider();
        jLabelInterval = new javax.swing.JLabel();
        jButtonShowcalendar = new javax.swing.JButton();
        jButtonHideCalendar = new javax.swing.JButton();
        workout2Calendar = new net.porcel.workout2calendar.Workout2Calendar();
        jPanel2Images = new net.porcel.component2images.JPanel2Images();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jButtonStart.setText("Start");
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonStart);
        jButtonStart.setBounds(6, 271, 75, 23);

        jButtonStop.setText("Stop");
        jButtonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStopActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonStop);
        jButtonStop.setBounds(87, 271, 75, 23);

        jSliderRequestInterval.setMaximum(10000);
        jSliderRequestInterval.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jSliderRequestIntervalMouseDragged(evt);
            }
        });
        jSliderRequestInterval.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jSliderRequestIntervalMouseReleased(evt);
            }
        });
        getContentPane().add(jSliderRequestInterval);
        jSliderRequestInterval.setBounds(6, 233, 156, 20);

        jLabelInterval.setText("Interval 0ms - 60s");
        getContentPane().add(jLabelInterval);
        jLabelInterval.setBounds(19, 211, 95, 16);

        jButtonShowcalendar.setText("Show Calendar");
        jButtonShowcalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowcalendarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonShowcalendar);
        jButtonShowcalendar.setBounds(180, 270, 109, 23);

        jButtonHideCalendar.setText("Hide Calendar");
        jButtonHideCalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHideCalendarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonHideCalendar);
        jButtonHideCalendar.setBounds(10, 10, 120, 23);
        getContentPane().add(workout2Calendar);
        workout2Calendar.setBounds(170, 40, 500, 400);
        getContentPane().add(jPanel2Images);
        jPanel2Images.setBounds(40, 60, 120, 90);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartActionPerformed
        jPanel2Images.setConnectionString(connectionString);
        jPanel2Images.setContainerName(containerName);
        jPanel2Images.setRequestInterval(jSliderRequestInterval.getValue());
        
        jPanel2Images.addListener(new MyEventListeners(){
            @Override
            public void onBlobDifference(String msg) {
                jTextArea.setText(msg);            
            }
        });

        jPanel2Images.setIsPolling(true);
    }//GEN-LAST:event_jButtonStartActionPerformed

    private void jButtonStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStopActionPerformed
        jPanel2Images.setIsPolling(false);
    }//GEN-LAST:event_jButtonStopActionPerformed

    private void jSliderRequestIntervalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSliderRequestIntervalMouseReleased
        jLabelInterval.setText(String.valueOf(jSliderRequestInterval.getValue()) + "seconds");
    }//GEN-LAST:event_jSliderRequestIntervalMouseReleased

    private void jSliderRequestIntervalMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSliderRequestIntervalMouseDragged
        jLabelInterval.setText(String.valueOf(jSliderRequestInterval.getValue()) + "seconds");
    }//GEN-LAST:event_jSliderRequestIntervalMouseDragged

    private void jButtonShowcalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowcalendarActionPerformed
        if (workout2Calendar != null) {
            this.remove(workout2Calendar); // Eliminar del JFrame
        }

        workout2Calendar = new Workout2Calendar();
        workout2Calendar.setColor(Color.RED);
        workout2Calendar.setBounds(10, 250, 500, 300);
        this.add(workout2Calendar);

        workout2Calendar.initializeCalendar();
        workout2Calendar.setListeners(new MyCalendarListeners() {
            @Override
            public void hasWorkoutListener(hasWorkoutsEventArgs eventArg) {
                jTextArea.setText(eventArg.toString());
            }
        });

        workout2Calendar.setVisible(true);
        this.revalidate();
        this.repaint();

    }//GEN-LAST:event_jButtonShowcalendarActionPerformed

    private void jButtonHideCalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHideCalendarActionPerformed
        if (workout2Calendar != null) {
            this.remove(workout2Calendar); 
            workout2Calendar.setVisible(false); 
            this.revalidate(); 
            this.repaint(); 
            jTextArea.setText("");
        }
    }//GEN-LAST:event_jButtonHideCalendarActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameMain2Images.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameMain2Images.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameMain2Images.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameMain2Images.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameMain2Images().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonHideCalendar;
    private javax.swing.JButton jButtonShowcalendar;
    private javax.swing.JButton jButtonStart;
    private javax.swing.JButton jButtonStop;
    private javax.swing.JLabel jLabelInterval;
    private net.porcel.component2images.JPanel2Images jPanel2Images;
    private javax.swing.JSlider jSliderRequestInterval;
    private net.porcel.workout2calendar.Workout2Calendar workout2Calendar;
    // End of variables declaration//GEN-END:variables
}
