package vista;

import controlador.PedidoController;
import modelo.*;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VentanaPrincipal extends JFrame {
    private PedidoController controller;

    public VentanaPrincipal(PedidoController controller) {
        this.controller = controller;

        setTitle("SpeedFast - Gestión de Entregas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Sistema de Gestión SpeedFast", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton btnRegistrar = new JButton("Registrar Pedido");
        JButton btnListar = new JButton("Listar Pedidos");
        JButton btnSimular = new JButton("Asignar / Iniciar Entregas");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnListar);
        panelBotones.add(btnSimular);

        add(panelBotones, BorderLayout.CENTER);

        // Eventos de navegación
        btnRegistrar.addActionListener(e -> {
            VentanaRegistroPedido ventanaRegistro = new VentanaRegistroPedido(this.controller);
            ventanaRegistro.setVisible(true);
        });

        btnListar.addActionListener(e -> {
            VentanaListaPedidos ventanaLista = new VentanaListaPedidos(this.controller);
            ventanaLista.setVisible(true);
        });

        btnSimular.addActionListener(e -> iniciarSimulacion());
    }

    private void iniciarSimulacion() {
        if (controller.getListaPedidos().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay pedidos registrados para entregar.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Repartidor r1 = new Repartidor("Camila", controller.getListaPedidos());
        executor.execute(r1);
        executor.shutdown();

        JOptionPane.showMessageDialog(this, "Simulación iniciada. Revisa la consola para ver el progreso.", "Simulación", JOptionPane.INFORMATION_MESSAGE);
    }
}