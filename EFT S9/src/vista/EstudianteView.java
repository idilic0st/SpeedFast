package vista;

import dao.EstudianteDAO;
import modelo.Estudiante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EstudianteView extends JFrame {
    private JTable tablaEstudiantes;
    private DefaultTableModel modeloTabla;
    private EstudianteDAO estudianteDAO;

    public EstudianteView() {
        estudianteDAO = new EstudianteDAO();
        setTitle("Gestión de Estudiantes");
        setSize(650, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "RUT", "Curso", "Correo"}, 0);
        tablaEstudiantes = new JTable(modeloTabla);
        add(new JScrollPane(tablaEstudiantes), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnCargar = new JButton("Actualizar Tabla");
        JButton btnAgregar = new JButton("Agregar Estudiante de Prueba");
        JButton btnEliminar = new JButton("Eliminar Seleccionado");

        panelBotones.add(btnCargar);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        cargarDatos();

        btnCargar.addActionListener(e -> cargarDatos());

        btnAgregar.addActionListener(e -> {
            Estudiante nuevo = new Estudiante(0, "Estudiante Prueba", "20111222-3", "3ro Medio B", "prueba@correo.cl");
            if (estudianteDAO.agregarEstudiante(nuevo)) {
                JOptionPane.showMessageDialog(this, "Estudiante agregado con éxito.");
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar estudiante.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tablaEstudiantes.getSelectedRow();
            if (fila >= 0) {
                int idEst = (int) modeloTabla.getValueAt(fila, 0);
                if (estudianteDAO.eliminarEstudiante(idEst)) {
                    JOptionPane.showMessageDialog(this, "Estudiante eliminado.");
                    cargarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar (puede tener préstamos asociados).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un estudiante de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<Estudiante> lista = estudianteDAO.listarEstudiantes();
        for (Estudiante e : lista) {
            modeloTabla.addRow(new Object[]{
                    e.getId(), e.getNombre(), e.getRut(), e.getCurso(), e.getCorreo()
            });
        }
    }
}