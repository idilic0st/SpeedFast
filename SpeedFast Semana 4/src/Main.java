import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // Asignación de pedidos para Repartidor 1
        List<Pedido> pedidosCamila = new ArrayList<>();
        pedidosCamila.add(new PedidoComida(101, "Av. Providencia 1234", 3.2));
        pedidosCamila.add(new PedidoExpress(104, "Calle San Martin 56", 1.5));

        // Asignación de pedidos para Repartidor 2
        List<Pedido> pedidosLuis = new ArrayList<>();
        pedidosLuis.add(new PedidoExpress(102, "Av. Grecia 890", 4.0));
        pedidosLuis.add(new PedidoEncomienda(105, "Las Condes 4321", 8.1));

        // Asignación de pedidos para Repartidor 3
        List<Pedido> pedidosCarlos = new ArrayList<>();
        pedidosCarlos.add(new PedidoEncomienda(103, "Gran Avenida 555", 6.0));
        pedidosCarlos.add(new PedidoComida(106, "Av. Matta 777", 2.3));

        // Instanciar Repartidores (Runnables)
        Repartidor r1 = new Repartidor("Camila", pedidosCamila);
        Repartidor r2 = new Repartidor("Luis", pedidosLuis);
        Repartidor r3 = new Repartidor("Carlos", pedidosCarlos);

        // ExecutorService para la ejecución en paralelo
        ExecutorService executor = Executors.newFixedThreadPool(3);

        System.out.println("--- Inicio de simulacion de entregas SpeedFast ---");
        executor.execute(r1);
        executor.execute(r2);
        executor.execute(r3);

        // Cierre ordenado del ExecutorService
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("--- Todas las entregas han sido finalizadas con exito ---");
    }
}