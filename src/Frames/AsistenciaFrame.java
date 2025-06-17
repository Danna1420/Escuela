
package Frames;

import Clases.Conectar;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.*;

public class AsistenciaFrame extends JFrame {

    private JComboBox<String> comboCursos;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JButton btnGuardar;

    public AsistenciaFrame() {
        setTitle("Registro de Asistencia");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        comboCursos = new JComboBox<>();
        cargarCursos();
        comboCursos.addActionListener(e -> cargarAlumnos());

        modelo = new DefaultTableModel(new Object[]{"ID", "Nombre", "Presente"}, 0) {
            public Class<?> getColumnClass(int column) {
                return column == 2 ? Boolean.class : String.class;
            }
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        btnGuardar = new JButton("Guardar Asistencia");
        btnGuardar.addActionListener(e -> guardarAsistencia());

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Curso:"));
        topPanel.add(comboCursos);

        add(topPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(btnGuardar, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void cargarCursos() {
        try (Connection cn = new Conectar().getConexion();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT nombre_curso FROM curso")) {
            while (rs.next()) {
                comboCursos.addItem(rs.getString("nombre_curso"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error cargando cursos: " + e.getMessage());
        }
    }

    private void cargarAlumnos() {
        modelo.setRowCount(0);
        String curso = (String) comboCursos.getSelectedItem();
        String sql = "SELECT id_alumno, CONCAT(nombre, ' ', apellido) AS nombre_completo FROM alumnos WHERE id_curso_asignado = ?";
        try (Connection cn = new Conectar().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, curso);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getInt("id_alumno"), rs.getString("nombre_completo"), false});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error cargando alumnos: " + e.getMessage());
        }
    }

    private void guardarAsistencia() {
        LocalDate fecha = LocalDate.now();
        try (Connection cn = new Conectar().getConexion()) {
            String sql = "INSERT INTO asistencia (id_alumno, fecha, presente) VALUES (?, ?, ?)";
            PreparedStatement ps = cn.prepareStatement(sql);

            for (int i = 0; i < modelo.getRowCount(); i++) {
                int id = (int) modelo.getValueAt(i, 0);
                boolean presente = (boolean) modelo.getValueAt(i, 2);

                ps.setInt(1, id);
                ps.setDate(2, Date.valueOf(fecha));
                ps.setBoolean(3, presente);
                ps.addBatch();
            }

            ps.executeBatch();
            JOptionPane.showMessageDialog(this, "Asistencia guardada exitosamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar asistencia: " + e.getMessage());
        }
    }
}
