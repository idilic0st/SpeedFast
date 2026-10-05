package vista;

import dao.EntregaDAO;
import dao.PedidoDAO;
import dao.RepartidorDAO;
import modelo.Entrega;
import modelo.Pedido;
import modelo.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    // Componentes Repartidor
    private JTextField txtNombreRepartidor;
    private JTable tablaRepartidores;
    private DefaultTableModel modeloRepartidores;

    // Componentes Pedido
    private JTextField txtDireccionPedido;
    private JComboBox<String> cbTipoPedido;
    private JComboBox<String> cbEstadoPedido;
    private JTable tablaPedidos;
    private DefaultTableModel modeloPedidos;

    // Componentes Entrega
    private JComboBox<PedidoItem> cbPedidosEntrega;
    private JComboBox<Repartidor> cbRepartidoresEntrega;
    private JTable tablaEntregas;
    private DefaultTableModel modeloEntregas;

    // DAOs
    private final RepartidorDAO repartidorDAO = new RepartidorDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final EntregaDAO entregaDAO = new EntregaDAO();

    public VentanaPrincipal() {
        setTitle("SpeedFast - Lógica de Negocio y Persistencia CRUD");
        setSize(950, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Repartidores", crearPanelRepartidores());
        tabbedPane.addTab("Pedidos", crearPanelPedidos());
        tabbedPane.addTab("Entregas", crearPanelEntregas());

        add(tabbedPane);

        cargarTodasLasTablas();
    }

    // --- PANEL REPARTIDORES ---
    private JPanel crearPanelRepartidores() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));

        form.add(new JLabel("Nombre:"));
        txtNombreRepartidor = new JTextField(15);
        form.add(txtNombreRepartidor);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnEliminar = new JButton("Eliminar Seleccionado");
        form.add(btnGuardar);
        form.add(btnEliminar);

        panel.add(form, BorderLayout.NORTH);

        modeloRepartidores = new DefaultTableModel(new String[]{"ID", "Nombre"}, 0);
        tablaRepartidores = new JTable(modeloRepartidores);
        panel.add(new JScrollPane(tablaRepartidores), BorderLayout.CENTER);

        btnGuardar.addActionListener(e -> {
            String nombre = txtNombreRepartidor.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un nombre válido.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (repartidorDAO.create(new Repartidor(nombre))) {
                JOptionPane.showMessageDialog(this, "Repartidor registrado.");
                txtNombreRepartidor.setText("");
                cargarTodasLasTablas();
            }
        });

        btnEliminar.addActionListener(e -> {
            int row = tablaRepartidores.getSelectedRow();
            if (row >= 0) {
                int id = (int) modeloRepartidores.getValueAt(row, 0);
                if (repartidorDAO.delete(id)) {
                    JOptionPane.showMessageDialog(this, "Repartidor eliminado.");
                    cargarTodasLasTablas();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una fila primero.");
            }
        });

        return panel;
    }

    // --- PANEL PEDIDOS ---
    private JPanel crearPanelPedidos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));

        form.add(new JLabel("Dirección:"));
        txtDireccionPedido = new JTextField(12);
        form.add(txtDireccionPedido);

        form.add(new JLabel("Tipo:"));
        cbTipoPedido = new JComboBox<>(new String[]{"COMIDA", "ENCOMIENDA", "EXPRESS"});
        form.add(cbTipoPedido);

        form.add(new JLabel("Estado:"));
        cbEstadoPedido = new JComboBox<>(new String[]{"PENDIENTE", "EN_REPARTO", "ENTREGADO"});
        form.add(cbEstadoPedido);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnEliminar = new JButton("Eliminar Seleccionado");
        form.add(btnGuardar);
        form.add(btnEliminar);

        panel.add(form, BorderLayout.NORTH);

        modeloPedidos = new DefaultTableModel(new String[]{"ID", "Dirección", "Tipo", "Estado"}, 0);
        tablaPedidos = new JTable(modeloPedidos);
        panel.add(new JScrollPane(tablaPedidos), BorderLayout.CENTER);

        btnGuardar.addActionListener(e -> {
            String direccion = txtDireccionPedido.getText().trim();
            if (direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese una dirección válida.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Pedido p = new Pedido(direccion, (String) cbTipoPedido.getSelectedItem(), (String) cbEstadoPedido.getSelectedItem());
            if (pedidoDAO.create(p)) {
                JOptionPane.showMessageDialog(this, "Pedido registrado.");
                txtDireccionPedido.setText("");
                cargarTodasLasTablas();
            }
        });

        btnEliminar.addActionListener(e -> {
            int row = tablaPedidos.getSelectedRow();
            if (row >= 0) {
                int id = (int) modeloPedidos.getValueAt(row, 0);
                if (pedidoDAO.delete(id)) {
                    JOptionPane.showMessageDialog(this, "Pedido eliminado.");
                    cargarTodasLasTablas();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una fila primero.");
            }
        });

        return panel;
    }

    // --- PANEL ENTREGAS ---
    private JPanel crearPanelEntregas() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));

        cbPedidosEntrega = new JComboBox<>();
        cbRepartidoresEntrega = new JComboBox<>();

        form.add(new JLabel("Pedido:"));
        form.add(cbPedidosEntrega);
        form.add(new JLabel("Repartidor:"));
        form.add(cbRepartidoresEntrega);

        JButton btnRegistrar = new JButton("Registrar Entrega");
        JButton btnEliminar = new JButton("Eliminar Seleccionada");
        form.add(btnRegistrar);
        form.add(btnEliminar);

        panel.add(form, BorderLayout.NORTH);

        modeloEntregas = new DefaultTableModel(new String[]{"ID", "ID Pedido", "ID Repartidor", "Fecha", "Hora"}, 0);
        tablaEntregas = new JTable(modeloEntregas);
        panel.add(new JScrollPane(tablaEntregas), BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> {
            PedidoItem pedidoSel = (PedidoItem) cbPedidosEntrega.getSelectedItem();
            Repartidor repartidorSel = (Repartidor) cbRepartidoresEntrega.getSelectedItem();

            if (pedidoSel == null || repartidorSel == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un Pedido y un Repartidor.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            long millis = System.currentTimeMillis();
            Entrega entrega = new Entrega(pedidoSel.getId(), repartidorSel.getId(), new Date(millis), new Time(millis));

            if (entregaDAO.create(entrega)) {
                JOptionPane.showMessageDialog(this, "Entrega registrada correctamente.");
                cargarTodasLasTablas();
            }
        });

        btnEliminar.addActionListener(e -> {
            int row = tablaEntregas.getSelectedRow();
            if (row >= 0) {
                int id = (int) modeloEntregas.getValueAt(row, 0);
                if (entregaDAO.delete(id)) {
                    JOptionPane.showMessageDialog(this, "Entrega eliminada.");
                    cargarTodasLasTablas();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una fila primero.");
            }
        });

        return panel;
    }

    private void cargarTodasLasTablas() {
        // Cargar Repartidores
        modeloRepartidores.setRowCount(0);
        cbRepartidoresEntrega.removeAllItems();
        List<Repartidor> repartidores = repartidorDAO.readAll();
        for (Repartidor r : repartidores) {
            modeloRepartidores.addRow(new Object[]{r.getId(), r.getNombre()});
            cbRepartidoresEntrega.addItem(r);
        }

        // Cargar Pedidos
        modeloPedidos.setRowCount(0);
        cbPedidosEntrega.removeAllItems();
        List<Pedido> pedidos = pedidoDAO.readAll();
        for (Pedido p : pedidos) {
            modeloPedidos.addRow(new Object[]{p.getId(), p.getDireccion(), p.getTipo(), p.getEstado()});
            cbPedidosEntrega.addItem(new PedidoItem(p.getId(), p.getDireccion()));
        }

        // Cargar Entregas
        modeloEntregas.setRowCount(0);
        List<Entrega> entregas = entregaDAO.readAll();
        for (Entrega ent : entregas) {
            modeloEntregas.addRow(new Object[]{ent.getId(), ent.getIdPedido(), ent.getIdRepartidor(), ent.getFecha(), ent.getHora()});
        }
    }

    // Helper interno para presentar los pedidos legibles en el JComboBox
    private static class PedidoItem {
        private final int id;
        private final String direccion;

        public PedidoItem(int id, String direccion) {
            this.id = id;
            this.direccion = direccion;
        }

        public int getId() { return id; }

        @Override
        public String toString() {
            return id + " - " + direccion;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}