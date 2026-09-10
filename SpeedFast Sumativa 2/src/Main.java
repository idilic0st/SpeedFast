import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("[Zona de carga inicializada]");
        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        // 1. Agregar 5 pedidos
        zonaDeCarga.agregarPedido(new Pedido(1, "Santiago Centro"));
        zonaDeCarga.agregarPedido(new Pedido(2, "Providencia"));
        zonaDeCarga.agregarPedido(new Pedido(3, "Ñuñoa"));
        zonaDeCarga.agregarPedido(new Pedido(4, "Recoleta"));
        zonaDeCarga.agregarPedido(new Pedido(5, "Las Condes"));

        System.out.println();

        // 2. Crear un pool de 3 hilos para los repartidores
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(new Repartidor("Juan", zonaDeCarga));
        executor.execute(new Repartidor("Camila", zonaDeCarga));
        executor.execute(new Repartidor("Pedro", zonaDeCarga));

        // Finalizar el ExecutorService y esperar a que terminen todas las entregas
        executor.shutdown();
        try {
            if (executor.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("\n[Zona de carga vacía]");
                System.out.println("Todos los pedidos han sido entregados correctamente.");
            }
        } catch (InterruptedException e) {
            System.err.println("Error esperando la finalización de hilos: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}