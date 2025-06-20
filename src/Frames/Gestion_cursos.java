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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nacho
 */
public class Gestion_cursos extends javax.swing.JFrame {

    
    public static int idcurso=0;
    DefaultTableModel modelo= new DefaultTableModel();
    
    
    
    public Gestion_cursos() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
         try {
            
             PreparedStatement ps =cn.prepareStatement("SELECT * FROM curso");
             ResultSet rs=ps.executeQuery();
             
             tabla_gestion_cursos= new JTable(modelo);           
             jScrollPane1.setViewportView(tabla_gestion_cursos);
             
             modelo.addColumn("Id");
             modelo.addColumn("Curso");
      
             
             while(rs.next()){
             
                 Object[] fila=new Object[2];
             
                 for (int i = 0; i < 2; i++) {
                     
                     fila[i]=rs.getObject(i+1);
                 }
                modelo.addRow(fila);
             
             }
           
            cn.close();
        } catch (SQLException e) {
            System.err.println("Error en el llenado de tabla cursos");
        }
         
    }
    public void cerrar() {
    try {
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                confirmarsalida();
            }
        });
    } catch (Exception e) {
        System.err.println("Error al configurar el cierre: " + e.getMessage());
    }
}

    public void confirmarsalida() {
    // Lógica de confirmación de salida
    int valor = JOptionPane.showConfirmDialog(
        this,
        "¿Desea cerrar la aplicación?",
        "Advertencia",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
    );

    if (valor == JOptionPane.YES_OPTION) {
        JOptionPane.showMessageDialog(
            null,
            "Hasta pronto",
            "",
            JOptionPane.INFORMATION_MESSAGE
        );
        System.exit(0);  // Cierra la aplicación
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_gestion_cursos = new javax.swing.JTable();
        btnvolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel1.setText("Gestion de cursos");

        tabla_gestion_cursos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabla_gestion_cursos);

        btnvolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/11214_arrow_down_left_return_icon.png"))); // NOI18N
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
                        .addGap(286, 286, 286)
                        .addComponent(jLabel1)
                        .addGap(0, 260, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnvolver))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnvolver, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnvolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvolverActionPerformed

        Principal principal = new Principal();
        principal.setVisible(true);
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
            java.util.logging.Logger.getLogger(Gestion_cursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestion_cursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestion_cursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestion_cursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gestion_cursos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btnvolver;
    javax.swing.JLabel jLabel1;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JTable tabla_gestion_cursos;
    // End of variables declaration//GEN-END:variables

    Conectar con=new Conectar();
    Connection cn=con.conexion();

}
