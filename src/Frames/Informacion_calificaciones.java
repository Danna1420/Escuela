/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Clases.Conectar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nacho
 */
public class Informacion_calificaciones extends javax.swing.JFrame {

    
    int idalumno=0;
    int idcalifa=0;
    String nombre_alumno="";
    
    
    
    /**
     * Creates new form Informacion_calificaciones
     */
    public Informacion_calificaciones() {
        initComponents();
        
        //cerrar();
        
        TextPrompt tarea = new TextPrompt("actualizar tarea", txttarea);
        TextPrompt calificacion = new TextPrompt("actualizar Calificacion", txtcalificacion);
        
        this.setLocationRelativeTo(null);
        
        txtnombre.setEditable(false);
        
        idcalifa=Informacion_alumnos.idcalificacion;
        idalumno =Gestion_alumnos.idalumno;
        
        try {
            PreparedStatement  ps=cn.prepareStatement("SELECT nombre FROM alumnos WHERE id_alumno='"+idalumno+"'");
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()){
            
                 nombre_alumno= rs.getString("nombre");
            }
            
            
        } catch (SQLException e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null,"Error al consultar nombre alumno");
        }
        
        ///////////////////////////////////////////////////////
        try {
            
            PreparedStatement ps=cn.prepareStatement("SELECT tarea,calificacion FROM notas WHERE id_nota='"+idcalifa+"'");
            ResultSet rs =ps.executeQuery();
            
            if(rs.next()){
            
               txttarea.setText(rs.getString("tarea"));
               txtcalificacion.setText(rs.getString("calificacion"));
            
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al consultar la informacion de calificaciones");
        }
        
        txtnombre.setText(nombre_alumno);
        
        /////////////////////////////////////////////////
        
        DefaultTableModel modelo= new DefaultTableModel();
        
        modelo.addColumn("Id nota");
        modelo.addColumn("Tarea");
        modelo.addColumn("Calificacion");
        
        tabla_informacion_calificacion.setModel(modelo);
        String sql="SELECT id_nota,tarea,calificacion FROM notas WHERE id_alumno_nota='"+idalumno+"'";
        String datos[]=new String[3];
        
        Statement st;
        
        try {
            
            st=cn.createStatement();
            ResultSet rs =st.executeQuery(sql);
            
            while(rs.next()){
            
                datos[0]=rs.getString("id_nota");
                datos[1]=rs.getString("tarea");
                datos[2]=rs.getString("calificacion");
                
                modelo.addRow(datos);
            }
            
            tabla_informacion_calificacion.setModel(modelo);
            
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null,"Error al llenar la tabla de calificaciones");
        }
       //////////////////////////////////////// 
        
        
    }
 


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txttarea = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtcalificacion = new javax.swing.JTextField();
        btnactualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_informacion_calificacion = new javax.swing.JTable();
        btnvolver = new javax.swing.JButton();

        jMenuItem1.setText("jMenuItem1");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Informacion calificacion");

        jLabel2.setText("Alumno:");

        jLabel3.setText("Tarea:");

        jLabel4.setText("Calificacion");

        btnactualizar.setText("Actualizar");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        tabla_informacion_calificacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_informacion_calificacion.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tabla_informacion_calificacion);

        btnvolver.setText("Volver");
        btnvolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(txtnombre)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(txttarea)
                                    .addComponent(txtcalificacion, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnactualizar)
                                .addGap(28, 28, 28)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnvolver)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(11, 11, 11)
                        .addComponent(txttarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtcalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnactualizar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnvolver)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed

        try {
            
            if (txttarea.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,"no puedes dejar el campo tarea vacio");
            }
            else if(txtcalificacion.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"No puedes dejar el campo calificacion vacio");
            } 
            else{
            
                PreparedStatement ps=cn.prepareStatement
                ("UPDATE notas SET tarea='"+txttarea.getText()+"',calificacion='"+txtcalificacion.getText()+"'"
                        + "WHERE id_nota='"+idcalifa+"'");
            
                int respuesta=ps.executeUpdate();
                
                if (respuesta>0) {
                    JOptionPane.showMessageDialog(null,"Datos actualizados");
                }
                
            }
            
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null,"Error al actualizar datos de calificaiones");
        }
      
        
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        try {
            
            PreparedStatement ps=cn.prepareStatement("DELETE FROM notas WHERE id_nota='"+idcalifa+"'");
            
            int respuesta=ps.executeUpdate();
            
            if(respuesta>0){
            
                JOptionPane.showMessageDialog(null,"datos borrados");
                
            }
            
            else{
             JOptionPane.showMessageDialog(null,"No ha seleccionado fila");
            
            }
            
        } catch (Exception e) {
        }
               
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnvolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvolverActionPerformed

                Informacion_alumnos info_alumno=new Informacion_alumnos(); 
                info_alumno.setVisible(true);
                dispose();


    }//GEN-LAST:event_btnvolverActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Informacion_calificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Informacion_calificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Informacion_calificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Informacion_calificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Informacion_calificaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btnactualizar;
    javax.swing.JButton btnvolver;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel4;
    javax.swing.JMenuItem jMenuItem1;
    javax.swing.JPopupMenu jPopupMenu1;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JTable tabla_informacion_calificacion;
    javax.swing.JTextField txtcalificacion;
    javax.swing.JTextField txtnombre;
    javax.swing.JTextField txttarea;
    // End of variables declaration//GEN-END:variables

    Conectar con=new Conectar();
    Connection cn=con.conexion();

}
