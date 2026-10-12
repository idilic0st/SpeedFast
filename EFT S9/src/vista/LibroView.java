package vista;

import dao.LibroDAO;
import modelo.Libro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LibroView extends JFrame {
    private JTable tablaLibros;
    private DefaultTableModel modeloTabla;
    private LibroDAO libroDAO;

    public LibroView() {
        libroDAO = new LibroDAO();
        setTitle("Gestión de Libros e Inventario");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tabla
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Título", "Autor", "ISBN", "Editorial", "Stock", "Cat ID"}, 0);
        tablaLibros = new JTable(modeloTabla);
        add(new JScrollPane(tablaLibros), BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel();
        JButton btnCargar = new JButton("Actualizar Tabla");
        JButton btnAgregar = new JButton("Agregar Libro de Prueba");
        JButton btnEliminar = new JButton("Eliminar Seleccionado");

        panelBotones.add(btnCargar);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        cargarDatos();

        // Acciones
        btnCargar.addActionListener(e -> cargarDatos());

        btnAgregar.addActionListener(e -> {
            Libro nuevo = new Libro(0, "Nuevo Libro Java", "Autor Ejemplo", "978956000000X", "Editorial Duoc", 10, 1);
            if (libroDAO.agregarLibro(nuevo)) {
                JOptionPane.showMessageDialog(this, "Libro agregado con éxito.");
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar libro.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tablaLibros.getSelectedRow();
            if (fila >= 0) {
                int idLibro = (int) modeloTabla.getValueAt(fila, 0);
                if (libroDAO.eliminarLibro(idLibro)) {
                    JOptionPane.showMessageDialog(this, "Libro eliminado.");
                    cargarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar (puede tener préstamos asociados).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un libro de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<Libro> lista = libroDAO.listarLibros();
        for (Libro l : lista) {
            modeloTabla.addRow(new Object[]{
                    l.getId(), l.getTitulo(), l.getAutor(), l.getIsbn(), l.getEditorial(), l.getStock(), l.getIdCategoria()
            });
        }
    }
}