/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package net.porcel.ut6.practica.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import net.porcel.ut6.practica.component.aux.MPCEx;
import net.porcel.ut6.practica.component.data.DataAccess;
import net.porcel.ut6.practica.component.dto.Actor;
import net.porcel.ut6.practica.component.dto.Categoria;
import net.porcel.ut6.practica.component.dto.Film;

public class Main extends java.awt.Frame {
    DataAccess dataAccess = new DataAccess();

    public Main() throws MPCEx {
        initComponents();
        setSize(610, 650);
        inicialice();

    }

    public void inicialice() throws MPCEx {
        jComboBoxCategoria.removeAllItems();
        List<Categoria> categorias = new ArrayList<>();
        categorias = dataAccess.getCategory();
        jComboBoxCategoria.addItem(new Categoria(null, "Todo"));
        for (Categoria categoria : categorias) {
            jComboBoxCategoria.addItem(categoria);
        }

        jComboBoxInterpret.removeAllItems();
        List<Actor> actors = new ArrayList<>();
        actors = dataAccess.getActor();
        jComboBoxInterpret.addItem(new Actor(null, "Todo", ""));
        for (Actor actor : actors) {
            jComboBoxInterpret.addItem(actor);
        }

        refresh(null, null);
    }

    public void refresh(Integer idActor, Integer idCategory) throws MPCEx {
        List<Film> films = new ArrayList<>();
        films = dataAccess.getFilmsByActorAndCategory(idActor, idCategory);
        
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Film film : films) {
            model.addElement(film.toString());
        }
        
        jList1.setModel(model);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxCategoria = new javax.swing.JComboBox<>();
        jComboBoxInterpret = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(600, 600));
        jPanel1.setLayout(null);

        jLabel1.setText("Categoria");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 20, 90, 16);

        jLabel2.setText("Intèrpret");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 50, 60, 16);

        jComboBoxCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxCategoriaItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBoxCategoria);
        jComboBoxCategoria.setBounds(100, 20, 470, 22);

        jComboBoxInterpret.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxInterpretItemStateChanged(evt);
            }
        });
        jComboBoxInterpret.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxInterpretActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxInterpret);
        jComboBoxInterpret.setBounds(100, 50, 470, 22);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 90, 580, 500);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void jComboBoxCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxCategoriaItemStateChanged
        Categoria categoria = (Categoria) jComboBoxCategoria.getSelectedItem();
        Actor actor = (Actor) jComboBoxInterpret.getSelectedItem();
        Integer idCategoria;
        Integer idActor;

        try {
            idActor = actor.getId();
        } catch (NullPointerException e) {
            idActor = null;
        }
        try {
            idCategoria = categoria.getId();
        } catch (NullPointerException e) {
            idCategoria = null;
        }

        try {
            refresh(idActor, idCategoria);
        } catch (MPCEx ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBoxCategoriaItemStateChanged

    private void jComboBoxInterpretActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxInterpretActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxInterpretActionPerformed

    private void jComboBoxInterpretItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxInterpretItemStateChanged
        Categoria categoria = (Categoria) jComboBoxCategoria.getSelectedItem();
        Actor actor = (Actor) jComboBoxInterpret.getSelectedItem();
        Integer idCategoria;
        Integer idActor;

        try {
            idActor = actor.getId();
        } catch (NullPointerException e) {
            idActor = null;
        }
        try {
            idCategoria = categoria.getId();
        } catch (NullPointerException e) {
            idCategoria = null;
        }

        try {
            refresh(idActor, idCategoria);
        } catch (MPCEx ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBoxInterpretItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main().setVisible(true);
                } catch (MPCEx ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Categoria> jComboBoxCategoria;
    private javax.swing.JComboBox<Actor> jComboBoxInterpret;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
