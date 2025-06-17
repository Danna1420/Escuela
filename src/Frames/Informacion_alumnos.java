/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Clases.Conectar;
import static Frames.Gestion_alumnos.idalumno;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nacho
 */
public class Informacion_alumnos extends javax.swing.JFrame {

    
    DefaultTableModel modelo=new DefaultTableModel();
    
    int idalumno=0;
    
    public static int idcalificacion=0;
    
    
    
    /**
     * Creates new form Informacion_alumnos
     */
    public Informacion_alumnos() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        txtnombre.setEditable(false);
        txtapellidos.setEditable(false);
        txttelefono.setEditable(false);
        txtcalificacion.setEditable(false);
        lblaprobado.setEditable(false);
        cmbgrado.setEnabled(false);        
        idalumno=Gestion_alumnos.idalumno;
        
        try {
            
            Connection cn=con.conexion();
            PreparedStatement ps=cn.prepareStatement("SELECT * FROM alumnos WHERE id_alumno='"+idalumno+"' ");
            
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()){
            
               setTitle("Información del alumno"+rs.getString("nombre"));
               lblinfo_alumno.setText("Información del alumno:" +rs.getString("nombre"));
               
               txtnombre.setText(rs.getString("nombre"));
               txtapellidos.setText(rs.getString("apellido"));
               cmbgrado.setSelectedItem(rs.getString("grado"));
               txttelefono.setText(rs.getString("telefono"));
                
            }           
                      
            
            
        } catch (SQLException e) {
            
            System.err.println(e);
            JOptionPane.showMessageDialog(null,"Error al cargar alumno...Contacte al administrador");
                       
        }
        ///////////////
HashMap<String, Double> promediosMinimos = new HashMap<>();
promediosMinimos.put("1. Pasos", 3.0);
promediosMinimos.put("2. Fundamentos I", 3.0);
promediosMinimos.put("3. Fundamentos II", 3.0);
promediosMinimos.put("4. Liderazgo I", 3.5);
promediosMinimos.put("5. Liderazgo II", 3.0);
promediosMinimos.put("6. Liderazgo III", 4.0);
promediosMinimos.put("7. Liderazgo IV", 4.3);
promediosMinimos.put("8. Practicantes", 4.5);

try {
    PreparedStatement pst = cn.prepareStatement(
        "SELECT id_nota, tarea, calificacion FROM notas WHERE id_alumno_nota = ?"
    );
    pst.setInt(1, idalumno);

    ResultSet rs = pst.executeQuery();

    // Inicializar la tabla y sus columnas
    tabla_calificaciones = new JTable(modelo);
    jScrollPane1.setViewportView(tabla_calificaciones);

    modelo.setRowCount(0);  // Limpia la tabla antes de llenarla
    modelo.setColumnIdentifiers(new Object[]{"Id_nota", "Tarea", "Calificación"});

    double total = 0.0;
    int count = 0;

    while (rs.next()) {
        int idNota = rs.getInt("id_nota");
        String tarea = rs.getString("tarea");
        double calificacion = rs.getDouble("calificacion");

        modelo.addRow(new Object[]{idNota, tarea, calificacion});

        total += calificacion;
        count++;
    }

    // Calcular el promedio si hay calificaciones
    if (count > 0) {
        double promedio = total / count;
        txtcalificacion.setText(String.format("%.2f", promedio));

        // Obtener el curso seleccionado
        String cursoSeleccionado = cmbgrado.getSelectedItem().toString();

        // Obtener el promedio mínimo requerido según el curso, por defecto 3.5 si no se encuentra
        Double notaMinima = promediosMinimos.getOrDefault(cursoSeleccionado, 3.5);

        if (promedio >= notaMinima) {
            lblaprobado.setText("Aprobado");
            txtcalificacion.setBackground(Color.green);
        } else {
            lblaprobado.setText("Reprobado");
            txtcalificacion.setBackground(Color.red);
        }
    } else {
        txtcalificacion.setText("0.00");
        lblaprobado.setText("Sin notas");
    }

} catch (SQLException e) {
    System.err.println("Error en el llenado de la tabla calificaciones: " + e.getMessage());
    JOptionPane.showMessageDialog(null, "Error al obtener las calificaciones.");
}
                tabla_calificaciones.addMouseListener(new MouseAdapter() {
            
            @Override
         public void mouseClicked(MouseEvent e){
         
         int fila_point=tabla_calificaciones.rowAtPoint(e.getPoint());
         int columna_point=0;
                 
         if(fila_point>=0){
         
             idcalificacion=(int)modelo.getValueAt(fila_point,columna_point);
             Informacion_calificaciones informacion_calificaciones=new Informacion_calificaciones();
             informacion_calificaciones.setVisible(true);
             dispose();
         
         }
         }
});               
                           
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

        lblinfo_alumno = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtapellidos = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbgrado = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_calificaciones = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        lblaprobado = new javax.swing.JTextField();
        btnimprimir = new javax.swing.JButton();
        btnvolver = new javax.swing.JButton();
        btnregistrar_alumnos = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtcalificacion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lblinfo_alumno.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        lblinfo_alumno.setText("Información alumno");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Apellidos:");

        jLabel4.setText("Grado:");

        cmbgrado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Pasos", "2. Fundamentos I", "3. Fundamentos II", "4. Liderazgo I", "5. Liderazgo II", "6. Liderazgo III", "7. Liderazgo IV", "8. Practicantes", " " }));

        jLabel5.setText("Telefono:");

        tabla_calificaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla_calificaciones);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Estatus:");

        lblaprobado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblaprobadoActionPerformed(evt);
            }
        });

        btnimprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/2620507_employee_job_print_seeker_unemployee_icon.png"))); // NOI18N
        btnimprimir.setText("Imprimir");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        btnvolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/11214_arrow_down_left_return_icon.png"))); // NOI18N
        btnvolver.setText("Volver");
        btnvolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvolverActionPerformed(evt);
            }
        });

        btnregistrar_alumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/285661_star_icon.png"))); // NOI18N
        btnregistrar_alumnos.setText("Registrar calificación");
        btnregistrar_alumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrar_alumnosActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Calificación");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(txtnombre)
                            .addComponent(jLabel3)
                            .addComponent(txtapellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addComponent(cmbgrado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txttelefono))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(203, 203, 203)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblaprobado, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(btnvolver)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnregistrar_alumnos)
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtcalificacion))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnimprimir))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addComponent(lblinfo_alumno)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lblinfo_alumno)
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(lblaprobado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbgrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(btnimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnvolver, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnregistrar_alumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtcalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblaprobadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblaprobadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblaprobadoActionPerformed

    private void btnvolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvolverActionPerformed

         Gestion_alumnos gestion_alumnos =new Gestion_alumnos();
         gestion_alumnos.setVisible(true);
         dispose();
        
    }//GEN-LAST:event_btnvolverActionPerformed

    private void btnregistrar_alumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrar_alumnosActionPerformed

        Registrar_calificaciones registrar_calificaciones =new Registrar_calificaciones();
        registrar_calificaciones.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnregistrar_alumnosActionPerformed

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed

        Document documento =new Document();
        
        Calendar cal=Calendar.getInstance();
        Date fecha = new Date(cal.getTimeInMillis());
        SimpleDateFormat formato= new SimpleDateFormat("dd/M/yyyy");
        String verfecha= formato.format(fecha);
        
        try {
            
            String ruta =System.getProperty("user.home");
            PdfWriter.getInstance(documento,new FileOutputStream(ruta+"/desktop/"+txtnombre.getText()+".pdf"));
            
            Paragraph parrafo =new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Ariel", 20, BaseColor.BLACK));
            parrafo.add("Informacion del alumno. \n \n");
            
            Paragraph poner_fecha= new Paragraph();
            poner_fecha.setAlignment(Paragraph.ALIGN_RIGHT);
            poner_fecha.add("fecha. "+verfecha+"\n \n");
            
            documento.open();
            documento.add(parrafo);
            
            documento.add(poner_fecha);
            
            PdfPTable tablaalumno= new PdfPTable(4);
            
            tablaalumno.addCell("Nombre");
            tablaalumno.addCell("Apellido");
            tablaalumno.addCell("Grado");
            tablaalumno.addCell("Materia");
            
              try {
                 
                  PreparedStatement ps =cn.prepareStatement("SELECT * FROM alumnos WHERE id_alumno='"+idalumno+"'");
                  
                  ResultSet rs =ps.executeQuery();
                  
                  if(rs.next()){
                      do {
                          
                          tablaalumno.addCell(rs.getString(2));
                          tablaalumno.addCell(rs.getString(3));
                          tablaalumno.addCell(rs.getString(4));
                          tablaalumno.addCell(rs.getString(6));
                          
                      } while (rs.next());
                     {
                  
                      documento.add(tablaalumno);
                  
                  }
                      
                  
                  }
                  
                  Paragraph parrafo2= new Paragraph();
                  parrafo2.setAlignment(Paragraph.ALIGN_CENTER);
                  parrafo2.setFont(FontFactory.getFont("Arial", 20, BaseColor.BLACK));
                  parrafo2.add("\n \n tareas registradas \n \n");
                  
                  documento.add(parrafo2);
                  PdfPTable tablatareas= new PdfPTable(2);
                  
                  tablatareas.addCell("Tarea");
                  tablatareas.addCell("Calificacion");
                  
                  try {
                      Connection cn2= con.conexion();
                      PreparedStatement ps2=cn2.prepareStatement("SELECT tarea,calificacion FROM notas WHERE id_alumno_nota='"+idalumno+"'");
                      
                      ResultSet rs2 =ps2.executeQuery();
                      
                      if(rs2.next()){
                      
                          do {
                              tablatareas.addCell(rs2.getString(1));
                              tablatareas.addCell(rs2.getString(2));
                              
                          } while (rs2.next());
                          {
                            documento.add(tablatareas);
                          
                          
                          }
                          
                      
                      }
                      
                  } catch (SQLException e) {
                      System.err.println("Error al cargar tareas "+e);
                  }
            //////////////////////////////////////////////////////////////      
                  
            } catch (SQLException e) {
                System.err.println("Error al obtener datos del alumno "+e);
            }
            //////////////////////////////////////////////////////////////
              
              documento.close();
              
              JOptionPane.showMessageDialog(null,"Documento creado con exito");
            
        } catch (DocumentException | IOException e) {
            
             System.err.println("Erro en el pdf o en la ruta "+e);
             JOptionPane.showMessageDialog(null,"Error al generar pdf... contacte al administrador");
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
            java.util.logging.Logger.getLogger(Informacion_alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Informacion_alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Informacion_alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Informacion_alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Informacion_alumnos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btnimprimir;
    javax.swing.JButton btnregistrar_alumnos;
    javax.swing.JButton btnvolver;
    javax.swing.JComboBox cmbgrado;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel5;
    javax.swing.JLabel jLabel6;
    javax.swing.JLabel jLabel7;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JTextField lblaprobado;
    javax.swing.JLabel lblinfo_alumno;
    javax.swing.JTable tabla_calificaciones;
    javax.swing.JTextField txtapellidos;
    javax.swing.JTextField txtcalificacion;
    javax.swing.JTextField txtnombre;
    javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
    Conectar con=new Conectar();
    Connection cn=con.conexion();


}
