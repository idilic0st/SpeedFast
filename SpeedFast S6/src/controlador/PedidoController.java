package controlador;

import modelo.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoController {
    private List<Pedido> listaPedidos;

    public PedidoController() {
        this.listaPedidos = new ArrayList<>();
    }

    public boolean agregarPedido(Pedido pedido) {
        for (Pedido p : listaPedidos) {
            if (p.getIdPedido() == pedido.getIdPedido()) {
                return false; // ID duplicado
            }
        }
        listaPedidos.add(pedido);
        return true;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }
}