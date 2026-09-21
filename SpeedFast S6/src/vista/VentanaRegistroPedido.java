package vista;

import controlador.PedidoController;
import modelo.*;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {
    private PedidoController controller;
    private JTextField txtId;
    private JTextField txtDireccion;
    private JTextField txtDistancia;
    private JComboBox<String> cbTipo;

    public VentanaRegistroPedido(PedidoController controller) {
        this.controller = controller;

        setTitle("Registrar Pedido");
        setSize(350, 280);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 8, 8));

        add(new JLabel("  ID Pedido:"));
        txtId = new JTextField();
        add(txtId);

        add(new JLabel("  Dirección:"));
        txtDireccion = new JTextField();
        add(txtDireccion);

        add(new JLabel("  Distancia (Km):"));
        txtDistancia = new JTextField();
        add(txtDistancia);

        add(new JLabel("  Tipo de Pedido:"));
        cbTipo = new JComboBox<>(new String[]{"Comida", "Encomienda", "Express"});
        add(cbTipo);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        add(btnGuardar);
        add(btnCancelar);

        btnGuardar.addActionListener(e -> guardarPedido());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void guardarPedido() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String direccion = txtDireccion.getText().trim();
            double distancia = Double.parseDouble(txtDistancia.getText().trim());
            String tipo = (String) cbTipo.getSelectedItem();

            if (direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar una dirección.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Pedido nuevoPedido;
            switch (tipo) {
                case "Comida":
                    nuevoPedido = new PedidoComida(id, direccion, distancia);
                    break;
                case "Encomienda":
                    nuevoPedido = new PedidoEncomienda(id, direccion, distancia);
                    break;
                default:
                    nuevoPedido = new PedidoExpress(id, direccion, distancia);
                    break;
            }

            boolean exito = controller.agregarPedido(nuevoPedido);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Pedido registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "El ID ya existe. Ingrese uno diferente.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese datos válidos en ID y Distancia.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }
}