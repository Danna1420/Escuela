/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Clases.Conectar;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nacho
 */
public class Registrar_alumnos extends javax.swing.JFrame {

    /**
     * Creates new form Registrar_alumnos
     */
    public Registrar_alumnos() {
        initComponents();
        
        TextPrompt nombre= new TextPrompt("Escribe tu nombre",txtnombre);
        TextPrompt apellidos = new TextPrompt("Escribe tus apellidos",txtapellidos);
        TextPrompt telefono = new TextPrompt("Escribe tu telefono",txttelefono);
        TextPrompt buscar = new TextPrompt("Buscar",txtbuscar);
        
        this.setLocationRelativeTo(this);
        limpiar();
        txtid_alumno.setEnabled(false);
        txtcantidad.setEditable(false);
        
        mostrartabla("");
        cerrar();
        cargarcombocurso(cmb_materia);
        
          for(int i=0; i<=tabla_registro_alumnos.getRowCount(); i++)
             {
               txtcantidad.setText(""+i);
             
             }
        
        
    }
    void limpiar(){
    
        txtid_alumno.setText(null);
        txtnombre.setText(null);
        txtapellidos.setText(null);
        txttelefono.setText(null);
        
        cmb_grado.setSelectedIndex(0);
        cmb_materia.setSelectedIndex(0);   
    
    }
    void mostrartabla(String valor){
    
       DefaultTableModel modelo = new DefaultTableModel();
       
       modelo.addColumn("Id");
       modelo.addColumn("Nombre");
       modelo.addColumn("Apellidos");
       modelo.addColumn("Grado");
       modelo.addColumn("Telefono");
       modelo.addColumn("Materia");
       
       tabla_registro_alumnos.setModel(modelo);
       
       String sql="SELECT * FROM alumnos WHERE CONCAT(nombre,' ',apellido,' ',grado,' ',id_curso_asignado)LIKE '%"+valor+"%'";
       
       String datos[]= new String[6];
       Statement st;
       
        try {
            
           st=cn.createStatement();
           ResultSet rs = st.executeQuery(sql);
           
           while(rs.next()){
           
              datos[0]=rs.getString(1);
              datos[1]=rs.getString(2);
              datos[2]=rs.getString(3);
              datos[3]=rs.getString(4);
              datos[4]=rs.getString(5);
              datos[5]=rs.getString(6);
              
              modelo.addRow(datos);
           }
           
           tabla_registro_alumnos.setModel(modelo);
            
        } catch (SQLException e) {
            System.err.println(e);
        }     
    
    }
    
    public void cargarcombocurso(JComboBox combodelcurso){
    
        try {
             
            String sql="SELECT nombre_curso FROM curso";
            Statement st=cn.createStatement();
            ResultSet rs= st.executeQuery(sql);
            
            
            while(rs.next()){
            
            combodelcurso.addItem(rs.getString("nombre_curso"));
            
            
            }
            
            
            
        } catch (SQLException e) {
            System.err.println(e);
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

        popborrar = new javax.swing.JPopupMenu();
        popeliminar = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtid_alumno = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtapellidos = new javax.swing.JTextField();
        txttelefono = new javax.swing.JTextField();
        cmb_grado = new javax.swing.JComboBox();
        cmb_materia = new javax.swing.JComboBox();
        btnagregar = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        btnvolver = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_registro_alumnos = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();

        popeliminar.setText("Borrar");
        popeliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popeliminarActionPerformed(evt);
            }
        });
        popborrar.add(popeliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel1.setText("Registro de alumnos");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alumnos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Black", 1, 14))); // NOI18N

        jLabel2.setText("Id alumno:");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Apellidos:");

        jLabel5.setText("Grado:");

        jLabel6.setText("Telefono:");

        jLabel7.setText("Materia:");

        txtid_alumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtid_alumnoActionPerformed(evt);
            }
        });

        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });

        txtapellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapellidosKeyTyped(evt);
            }
        });

        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoKeyTyped(evt);
            }
        });

        cmb_grado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione grado", "1. Pasos", "2. Fundamentos I", "3. Fundamentos II", "4. Liderazgo I", "5. Liderazgo II", "6. Liderazgo III", "7. Liderazgo IV", "8. Practicantes", " " }));
        cmb_grado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_gradoActionPerformed(evt);
            }
        });

        cmb_materia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione materia" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cmb_grado, 0, 231, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmb_materia, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtid_alumno, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnombre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtapellidos, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttelefono, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(10, 10, 10))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtid_alumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmb_grado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmb_materia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnagregar.setText("Agregar");
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });

        btnactualizar.setText("Actualizar");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        btnvolver.setText("Volver");
        btnvolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvolverActionPerformed(evt);
            }
        });

        tabla_registro_alumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_registro_alumnos.setComponentPopupMenu(popborrar);
        tabla_registro_alumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_registro_alumnosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_registro_alumnos);

        jLabel8.setText("Buscar:");

        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        jLabel9.setText("Cantidad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(btnagregar)
                                .addGap(53, 53, 53)
                                .addComponent(btnactualizar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 307, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(btnvolver))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(335, 335, 335)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnagregar)
                    .addComponent(btnactualizar))
                .addGap(26, 26, 26)
                .addComponent(btnvolver)
                .addGap(147, 147, 147))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtid_alumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtid_alumnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtid_alumnoActionPerformed

    private void popeliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popeliminarActionPerformed

        try {
            PreparedStatement ps=cn.prepareStatement("DELETE FROM alumnos WHERE id_alumno='"+txtid_alumno.getText()+"'");
            
            int respuesta= ps.executeUpdate();
            
            if(respuesta>0){
            
             JOptionPane.showMessageDialog(null,"Alumno eliminado");
             limpiar();
             mostrartabla("");
             
             for(int i=0; i<=tabla_registro_alumnos.getRowCount(); i++)
             {
               txtcantidad.setText(""+i);
             
             }
            
            }
            else{
            
             JOptionPane.showMessageDialog(null,"No ha seleccionado fila");
            }
            
            
        } catch (SQLException e) {
            System.err.println();
            JOptionPane.showMessageDialog(null,"Error al eliminar alumno...contacte al administrador");
            
        }
        
        
    }//GEN-LAST:event_popeliminarActionPerformed

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed

        
        String id_curso_asignado = cmb_materia.getSelectedItem().toString();
        String materia =(String) cmb_materia.getSelectedItem();
        String grado=(String)cmb_grado.getSelectedItem();
        
        try {
            
            if(txtnombre.getText().isEmpty())
            {
              JOptionPane.showMessageDialog(null,"No puedes dejar el campo nombre vacio");
                        
            }
           else if(txtapellidos.getText().isEmpty())
            {
              JOptionPane.showMessageDialog(null,"No puedes dejar el campo apellidos vacio");
                        
            }
            
           else if(txttelefono.getText().isEmpty())
            {
              JOptionPane.showMessageDialog(null,"No puedes dejar el campo telefono vacio");
                        
            }
            
           else if(grado.equals("Seleccione grado"))
            {
              JOptionPane.showMessageDialog(null,"Debe seleccionar un grado");
                        
            }
            
            else if(materia.equals("Seleccione materia"))
            {
              JOptionPane.showMessageDialog(null,"Debe seleccionar una materia");
                        
            }
            else{
            
            PreparedStatement ps= cn.prepareStatement("INSERT INTO alumnos (nombre,apellido,grado,telefono,id_curso_asignado)VALUES (?,?,?,?,?)");
            ps.setString(1,txtnombre.getText());
            ps.setString(2,txtapellidos.getText());
            ps.setString(3,cmb_grado.getSelectedItem().toString());
            ps.setString(4,txttelefono.getText());
            ps.setString(5,id_curso_asignado);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Alumno registrado con exito");
            mostrartabla("");
            limpiar();
            
              for(int i=0; i<=tabla_registro_alumnos.getRowCount(); i++)
             {
               txtcantidad.setText(""+i);
             
             }
                                 
            }
            
            
            
        } catch (SQLException e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null,"error al guardar alumno");
        }
        
        
        
    }//GEN-LAST:event_btnagregarActionPerformed

    private void tabla_registro_alumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_registro_alumnosMouseClicked

       int fila=tabla_registro_alumnos.getSelectedRow();
       
       this.txtid_alumno.setText(this.tabla_registro_alumnos.getValueAt(fila,0).toString());
       this.txtnombre.setText(this.tabla_registro_alumnos.getValueAt(fila,1).toString());
       this.txtapellidos.setText(this.tabla_registro_alumnos.getValueAt(fila,2).toString());
       this.cmb_grado.setSelectedItem(this.tabla_registro_alumnos.getValueAt(fila,3).toString());
       this.txttelefono.setText(this.tabla_registro_alumnos.getValueAt(fila,4).toString());
       this.cmb_materia.setSelectedItem(this.tabla_registro_alumnos.getValueAt(fila,5).toString());
        
    }//GEN-LAST:event_tabla_registro_alumnosMouseClicked

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed

        String materia=(String)cmb_materia.getSelectedItem();
        String grado=(String)cmb_grado.getSelectedItem();
        
        try {
             if(txtnombre.getText().isEmpty())
             {
               JOptionPane.showMessageDialog(null,"No puedes dejar el campo nombre vacio");
             }
            ////////////////////////////////////////////////////////////
            
          else if(txtapellidos.getText().isEmpty())
            {
              JOptionPane.showMessageDialog(null,"No puedes dejar el campo apellidos vacio");
                        
            }
            
           else if(txttelefono.getText().isEmpty())
            {
              JOptionPane.showMessageDialog(null,"No puedes dejar el campo telefono vacio");
                        
            }
            
           else if(grado.equals("Seleccione grado"))
            {
              JOptionPane.showMessageDialog(null,"Debe seleccionar un grado");
                        
            }
            
            else if(materia.equals("Seleccione materia"))
            {
              JOptionPane.showMessageDialog(null,"Debe seleccionar una materia");
                        
            }   
             
             /////////////////////////
             
            else 
            {
                PreparedStatement ps= cn.prepareStatement("UPDATE alumnos SET nombre='"+txtnombre.getText()+"',apellido='"+txtapellidos.getText()+"',grado="
                        + "'"+cmb_grado.getSelectedItem().toString()+"',"
                        + "telefono='"+txttelefono.getText()+"',id_curso_asignado='"+cmb_materia.getSelectedItem().toString()+"' WHERE id_alumno='"+txtid_alumno.getText()+"'");
               
                int respuesta=ps.executeUpdate();
                if(respuesta>0){
                 
                    JOptionPane.showMessageDialog(null,"Datos del alumno modificados");
                    limpiar();
                    mostrartabla("");
                
                }
                else{
                   JOptionPane.showMessageDialog(null,"No has seleccionado fila");
                
                }
                
            
            }
            
        } catch (SQLException e) {
            
            System.err.println(e);
            JOptionPane.showMessageDialog(null,"Error al actualizar alumno... contacte al administrador");
        }
        
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void btnvolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvolverActionPerformed

        Principal principal=new Principal();
        principal.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_btnvolverActionPerformed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased

        
        mostrartabla(txtbuscar.getText());
          for(int i=0; i<=tabla_registro_alumnos.getRowCount(); i++)
             {
               txtcantidad.setText(""+i);
             
             }
        

    }//GEN-LAST:event_txtbuscarKeyReleased

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped

        char c=evt.getKeyChar();
        if((c<'a' || c>'z')&&(c<'A')| c>'Z' && c!=KeyEvent.VK_SPACE)evt.consume();
    }//GEN-LAST:event_txtnombreKeyTyped

    private void txtapellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidosKeyTyped

        char c=evt.getKeyChar();
        if((c<'a' || c>'z')&&(c<'A')| c>'Z' && c!=KeyEvent.VK_SPACE)evt.consume();
      
    }//GEN-LAST:event_txtapellidosKeyTyped

    private void txttelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyTyped

        char c= evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
        
    }//GEN-LAST:event_txttelefonoKeyTyped

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarActionPerformed

    private void cmb_gradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_gradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_gradoActionPerformed

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
            java.util.logging.Logger.getLogger(Registrar_alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrar_alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrar_alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrar_alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrar_alumnos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btnactualizar;
    javax.swing.JButton btnagregar;
    javax.swing.JButton btnvolver;
    javax.swing.JComboBox cmb_grado;
    javax.swing.JComboBox cmb_materia;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel5;
    javax.swing.JLabel jLabel6;
    javax.swing.JLabel jLabel7;
    javax.swing.JLabel jLabel8;
    javax.swing.JLabel jLabel9;
    javax.swing.JPanel jPanel2;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JPopupMenu popborrar;
    javax.swing.JMenuItem popeliminar;
    javax.swing.JTable tabla_registro_alumnos;
    javax.swing.JTextField txtapellidos;
    javax.swing.JTextField txtbuscar;
    javax.swing.JTextField txtcantidad;
    javax.swing.JTextField txtid_alumno;
    javax.swing.JTextField txtnombre;
    javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables

  Conectar con=new Conectar();
  Connection cn=con.conexion();

}
