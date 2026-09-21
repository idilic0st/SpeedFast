package vista;

import controlador.PedidoController;
import modelo.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaListaPedidos extends JFrame {
    private PedidoController controller;
    private JTable tablaPedidos;
    private DefaultTableModel model;

    public VentanaListaPedidos(PedidoController controller) {
        this.controller = controller;

        setTitle("Listado de Pedidos");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"ID", "Tipo", "Dirección", "Distancia (Km)", "Tiempo Est. (min)"}, 0);
        tablaPedidos = new JTable(model);

        add(new JScrollPane(tablaPedidos), BorderLayout.CENTER);

        JPanel panelBajo = new JPanel();
        JButton btnRefrescar = new JButton("Refrescar");
        panelBajo.add(btnRefrescar);
        add(panelBajo, BorderLayout.SOUTH);

        btnRefrescar.addActionListener(e -> cargarDatos());

        cargarDatos();
    }

    private void cargarDatos() {
        model.setRowCount(0);
        for (Pedido p : controller.getListaPedidos()) {
            model.addRow(new Object[]{
                    p.getIdPedido(),
                    p.getClass().getSimpleName().replace("Pedido", ""),
                    p.getDireccionEntrega(),
                    p.getDistanciaKm(),
                    p.calcularTiempoEntrega()
            });
        }
    }
}