import java.util.LinkedList;
import java.util.Queue;

public class ZonaDeCarga {
    private final Queue<Pedido> colaPedidos = new LinkedList<>();

    public synchronized void agregarPedido(Pedido p) {
        colaPedidos.add(p);
        System.out.println("Pedido #" + p.getId() + " agregado. Destino: " + p.getDireccionEntrega());
    }

    public synchronized Pedido retirarPedido() {
        // Retorna el siguiente pedido de forma segura (thread-safe)
        return colaPedidos.poll();
    }

    public synchronized boolean estaVacia() {
        return colaPedidos.isEmpty();
    }
}