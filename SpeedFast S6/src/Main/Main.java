package main;

import controlador.PedidoController;
import vista.VentanaPrincipal;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PedidoController controller = new PedidoController();
            VentanaPrincipal principal = new VentanaPrincipal(controller);
            principal.setVisible(true);
        });
    }
}