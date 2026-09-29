package vista;

import dao.PedidoDAO;
import dao.RepartidorDAO;
import modelo.Pedido;
import modelo.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    // Componentes para Pedidos
    private JTextField txtDireccion;
    private JComboBox<String> cbTipo;
    private JComboBox<String> cbEstado;
    private JButton btnGuardarPedido;

    // Componentes para Repartidores
    private JTextField txtNombreRepartidor;
    private JButton btnGuardarRepartidor;

    // Tabla
    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;
    private JButton btnCargarTabla;

    // DAOs
    private PedidoDAO pedidoDAO;
    private RepartidorDAO repartidorDAO;

    public VentanaPrincipal() {
        pedidoDAO = new PedidoDAO();
        repartidorDAO = new RepartidorDAO();

        setTitle("SpeedFast - Gestión de Pedidos y Repartidores");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- PANEL SUPERIOR: Formularios de Registro ---
        JPanel panelFormularios = new JPanel(new GridLayout(1, 2, 10, 10));

        // Formulario Pedido
        JPanel panelPedido = new JPanel(new GridLayout(5, 2, 5, 5));
        panelPedido.setBorder(BorderFactory.createTitledBorder("Registrar Pedido"));

        panelPedido.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelPedido.add(txtDireccion);

        panelPedido.add(new JLabel("Tipo:"));
        cbTipo = new JComboBox<>(new String[]{"COMIDA", "ENCOMIENDA", "EXPRESS"});
        panelPedido.add(cbTipo);

        panelPedido.add(new JLabel("Estado:"));
        cbEstado = new JComboBox<>(new String[]{"PENDIENTE", "EN_REPARTO", "ENTREGADO"});
        panelPedido.add(cbEstado);

        btnGuardarPedido = new JButton("Guardar Pedido");
        panelPedido.add(new JLabel()); // Espacio
        panelPedido.add(btnGuardarPedido);

        // Formulario Repartidor
        JPanel panelRepartidor = new JPanel(new GridLayout(3, 2, 5, 5));
        panelRepartidor.setBorder(BorderFactory.createTitledBorder("Registrar Repartidor"));

        panelRepartidor.add(new JLabel("Nombre:"));
        txtNombreRepartidor = new JTextField();
        panelRepartidor.add(txtNombreRepartidor);

        btnGuardarRepartidor = new JButton("Guardar Repartidor");
        panelRepartidor.add(new JLabel()); // Espacio
        panelRepartidor.add(btnGuardarRepartidor);

        panelFormularios.add(panelPedido);
        panelFormularios.add(panelRepartidor);

        add(panelFormularios, BorderLayout.NORTH);

        // --- PANEL CENTRAL: Tabla de Pedidos ---
        JPanel panelTabla = new JPanel(new BorderLayout(5, 5));
        panelTabla.setBorder(BorderFactory.createTitledBorder("Listado de Pedidos en BD"));

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Dirección", "Tipo", "Estado"}, 0);
        tablaPedidos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaPedidos);
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        btnCargarTabla = new JButton("Actualizar / Cargar Tabla");
        panelTabla.add(btnCargarTabla, BorderLayout.SOUTH);

        add(panelTabla, BorderLayout.CENTER);

        // --- EVENTOS ---
        btnGuardarPedido.addActionListener(e -> registrarPedido());
        btnGuardarRepartidor.addActionListener(e -> registrarRepartidor());
        btnCargarTabla.addActionListener(e -> cargarTablaPedidos());

        // Cargar la tabla al iniciar
        cargarTablaPedidos();
    }

    private void registrarPedido() {
        String direccion = txtDireccion.getText().trim();
        String tipo = (String) cbTipo.getSelectedItem();
        String estado = (String) cbEstado.getSelectedItem();

        if (direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese una dirección.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Pedido pedido = new Pedido(direccion, tipo, estado);
        if (pedidoDAO.guardar(pedido)) {
            JOptionPane.showMessageDialog(this, "Pedido registrado correctamente en MySQL.");
            txtDireccion.setText("");
            cargarTablaPedidos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el pedido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarRepartidor() {
        String nombre = txtNombreRepartidor.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el nombre del repartidor.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Repartidor repartidor = new Repartidor(nombre);
        if (repartidorDAO.guardar(repartidor)) {
            JOptionPane.showMessageDialog(this, "Repartidor registrado correctamente en MySQL.");
            txtNombreRepartidor.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el repartidor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarTablaPedidos() {
        modeloTabla.setRowCount(0);
        List<Pedido> lista = pedidoDAO.listarTodos();
        for (Pedido p : lista) {
            modeloTabla.addRow(new Object[]{p.getId(), p.getDireccion(), p.getTipo(), p.getEstado()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}