/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Clases.Conectar;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;





/**
 *
 * @author nacho
 */
public class Gestion_calificaciones extends javax.swing.JFrame {

    /**
     * Creates new form Gestion_calificaciones
     */
    public Gestion_calificaciones() {
        initComponents();
        
        mostrartabla("");
        this.setLocationRelativeTo(null);
        
    }
    
    void mostrartabla(String valor){
    
        DefaultTableModel modelo=new DefaultTableModel();
        
        modelo.addColumn("nombre");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Grado");
        modelo.addColumn("Materia");
        modelo.addColumn("Promedio");
        
        tabla_gestion_calificaciones.setModel(modelo);
        
        String sql="SELECT alumnos.nombre AS nombre,"
                + "alumnos.apellido AS apellido,"
                + "alumnos.grado AS grado,"
                + "alumnos.id_curso_asignado AS curso,"
                + " SUM(notas.calificacion) AS totalcalificaion"
                + " FROM alumnos"
                + " INNER JOIN notas"
                + " ON alumnos.id_alumno=notas.id_alumno_nota"
                + " GROUP BY alumnos.id_alumno";
        
        String datos[]=new String[5];
        
        Statement st;
        
        try {
            
            st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next()){
            
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                
                modelo.addRow(datos);            
            }
            
            tabla_gestion_calificaciones.setModel(modelo);
            
        } catch (SQLException e) {
            
            System.err.println(e);
            JOptionPane.showMessageDialog(null,"Error en el inner join");
            
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
        btnvolver = new javax.swing.JButton();
        btnimprimir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_gestion_calificaciones = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Gestion de calificaciones");

        btnvolver.setText("Volver");
        btnvolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvolverActionPerformed(evt);
            }
        });

        btnimprimir.setText("Imprimir");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        tabla_gestion_calificaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla_gestion_calificaciones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(327, 327, 327)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnvolver)
                        .addGap(18, 18, 18)
                        .addComponent(btnimprimir))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnvolver)
                    .addComponent(btnimprimir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnvolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvolverActionPerformed

        
        Principal principal = new Principal();
        principal.setVisible(true);
        dispose();
        
        
    }//GEN-LAST:event_btnvolverActionPerformed

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
    
         Document documento = new Document();
Calendar cal = Calendar.getInstance();
Date fecha = new Date(cal.getTimeInMillis());
SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
String verfecha = formato.format(fecha);

try {
    String ruta = System.getProperty("user.home");
    PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/desktop/" + "CALIFICACIONES" + ".pdf"));

    Paragraph parrafo = new Paragraph();
    parrafo.setAlignment(Paragraph.ALIGN_CENTER);
    parrafo.setFont(FontFactory.getFont("Arial", 20, Font.BOLD, BaseColor.BLACK));
    parrafo.add("CALIFICACIONES. \n \n");

    Paragraph poner_fecha = new Paragraph();
    poner_fecha.setAlignment(Paragraph.ALIGN_RIGHT);
    poner_fecha.add("Fecha: " + verfecha + " \n \n");

    documento.open();
    documento.add(parrafo);
    documento.add(poner_fecha);

    PdfPTable tablaalumno = new PdfPTable(5);

    // Cabecera de la tabla
    tablaalumno.addCell("Nombre");
    tablaalumno.addCell("Apellido");
    tablaalumno.addCell("Grado");
    tablaalumno.addCell("Materia");
    tablaalumno.addCell("Promedio");

    try {
        // Consulta para obtener la suma de calificaciones y el número de calificaciones
        PreparedStatement ps = cn.prepareStatement(
                "SELECT alumnos.nombre, alumnos.apellido, alumnos.grado, alumnos.id_curso_asignado, "
                + "SUM(notas.calificacion) AS total_calificaciones, "
                + "COUNT(notas.calificacion) AS num_calificaciones "
                + "FROM alumnos "
                + "INNER JOIN notas ON alumnos.id_alumno = notas.id_alumno_nota "
                + "GROUP BY alumnos.id_alumno");

        ResultSet rs = ps.executeQuery();

        // Procesar los resultados
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String grado = rs.getString("grado");
            String materia = rs.getString("id_curso_asignado");
            double totalCalificaciones = rs.getDouble("total_calificaciones");
            int numCalificaciones = rs.getInt("num_calificaciones");

            // Calcular el promedio, evitando división por cero
            String promedio;
            if (numCalificaciones > 0) {
                promedio = String.format("%.2f", totalCalificaciones / numCalificaciones);
            } else {
                promedio = "No calificaciones";
            }

            // Agregar los datos a la tabla del PDF
            tablaalumno.addCell(nombre);
            tablaalumno.addCell(apellido);
            tablaalumno.addCell(grado);
            tablaalumno.addCell(materia);
            tablaalumno.addCell(promedio);
        }

        // Añadir la tabla al documento PDF
        documento.add(tablaalumno);

    } catch (SQLException e) {
        System.err.println("Error al obtener datos del alumno: " + e);
    }

    documento.close();
    JOptionPane.showMessageDialog(null, "Documento creado con éxito");

} catch (DocumentException | IOException e) {
    System.err.println("Error en la generación del PDF: " + e);
    JOptionPane.showMessageDialog(null, "Error al generar el PDF");
}
        

    }//GEN-LAST:event_btnimprimirActionPerformed

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
            java.util.logging.Logger.getLogger(Gestion_calificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestion_calificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestion_calificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestion_calificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gestion_calificaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btnimprimir;
    javax.swing.JButton btnvolver;
    javax.swing.JLabel jLabel1;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JTable tabla_gestion_calificaciones;
    // End of variables declaration//GEN-END:variables

    Conectar con=new Conectar();
    Connection cn=con.conexion();

}
