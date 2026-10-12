package vista;

import dao.ProcesoPrestamoThread;
import modelo.Prestamo;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;

public class MainView extends JFrame {

    public MainView(Usuario usuario) {
        setTitle("Sistema de Gestión de Biblioteca Escolar - Panel Principal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Etiqueta de bienvenida superior
        JLabel lblBienvenida = new JLabel("Bienvenido: " + usuario.getNombre() + " | Rol: " + usuario.getRol(), JLabel.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 14));
        lblBienvenida.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(lblBienvenida, BorderLayout.NORTH);

        // Panel central con los botones de opciones
        JPanel panelCentro = new JPanel(new GridLayout(3, 1, 15, 15));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton btnGestionLibros = new JButton("Gestión de Libros e Inventario");
        JButton btnGestionEstudiantes = new JButton("Gestión de Estudiantes");
        JButton btnRealizarPrestamo = new JButton("Registrar Préstamo (Probar Hilos y Concurrencia)");

        panelCentro.add(btnGestionLibros);
        panelCentro.add(btnGestionEstudiantes);
        panelCentro.add(btnRealizarPrestamo);
        add(panelCentro, BorderLayout.CENTER);

        // Restricción por roles: Si es estudiante, se deshabilitan las gestiones administrativas
        if ("estudiante".equals(usuario.getRol())) {
            btnGestionLibros.setEnabled(false);
            btnGestionEstudiantes.setEnabled(false);
        }

        // --- ACCIONES DE LOS BOTONES ---

        // 1. Abrir vista de Libros
        btnGestionLibros.addActionListener(e -> {
            new LibroView().setVisible(true);
        });

        // 2. Abrir vista de Estudiantes
        btnGestionEstudiantes.addActionListener(e -> {
            new EstudianteView().setVisible(true);
        });

        // 3. Registrar Préstamo con Hilos (Concurrencia)
        btnRealizarPrestamo.addActionListener(e -> {
            try {
                String idEstInput = JOptionPane.showInputDialog(this, "Ingrese el ID del Estudiante (ej. 1, 2...):");
                if (idEstInput == null) return;

                String idLibInput = JOptionPane.showInputDialog(this, "Ingrese el ID del Libro (ej. 1 al 10):");
                if (idLibInput == null) return;

                String stockInput = JOptionPane.showInputDialog(this, "Ingrese el Stock Actual de ese libro:");
                if (stockInput == null) return;

                int idEst = Integer.parseInt(idEstInput);
                int idLib = Integer.parseInt(idLibInput);
                int stock = Integer.parseInt(stockInput);

                // Crear objeto préstamo con fechas automáticas (hoy y en 7 días más)
                Prestamo nuevoPrestamo = new Prestamo(
                        0, idEst, idLib,
                        Date.valueOf(LocalDate.now()),
                        Date.valueOf(LocalDate.now().plusDays(7)),
                        false
                );

                // Instanciar y arrancar el hilo en segundo plano
                Thread hiloPrestamo = new Thread(new ProcesoPrestamoThread(nuevoPrestamo, stock));
                hiloPrestamo.start();

                JOptionPane.showMessageDialog(this, "¡Préstamo en curso! Revisa la consola de IntelliJ para ver la ejecución del hilo.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}