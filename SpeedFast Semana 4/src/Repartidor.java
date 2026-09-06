import java.util.List;
import java.util.Random;

public class Repartidor implements Runnable {
    private String nombre;
    private List<Pedido> listaPedidos;
    private Random random = new Random();

    public Repartidor(String nombre, List<Pedido> listaPedidos) {
        this.nombre = nombre;
        this.listaPedidos = listaPedidos;
    }

    @Override
    public void run() {
        for (Pedido pedido : listaPedidos) {
            String tipoPedido = pedido.getClass().getSimpleName();
            System.out.println("[Repartidor: " + nombre + "] Entregando " + tipoPedido + " #" + pedido.getIdPedido() + "...");

            try {
                // Pausa aleatoria entre 1000ms y 3000ms para simular la entrega
                int tiempoPausa = 1000 + random.nextInt(2000);
                Thread.sleep(tiempoPausa);
            } catch (InterruptedException e) {
                System.err.println("La entrega del repartidor " + nombre + " fue interrumpida: " + e.getMessage());
                Thread.currentThread().interrupt();
            }

            System.out.println("[Repartidor: " + nombre + "] Pedido #" + pedido.getIdPedido() + " entregado.");
        }
    }
}
