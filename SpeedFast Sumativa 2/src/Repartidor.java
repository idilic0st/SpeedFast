public class Repartidor implements Runnable {
    private String nombre;
    private ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    @Override
    public void run() {
        while (true) {
            Pedido pedido = zonaDeCarga.retirarPedido();

            // Si ya no hay más pedidos en la zona de carga, finaliza el hilo
            if (pedido == null) {
                break;
            }

            System.out.println("[Repartidor - " + nombre + "] Retirando pedido #" + pedido.getId() + "...");

            // Actualizar estado a EN_REPARTO
            pedido.setEstado(EstadoPedido.EN_REPARTO);
            System.out.println("[Repartidor - " + nombre + "] Estado: " + pedido.getEstado());

            // Simulación del tiempo de entrega
            try {
                System.out.println("[Repartidor - " + nombre + "] Entregando pedido #" + pedido.getId() + "...");
                Thread.sleep(1500); // 1.5 segundos de simulación
            } catch (InterruptedException e) {
                System.err.println("Error en la simulación del envío: " + e.getMessage());
                Thread.currentThread().interrupt();
            }

            // Actualizar estado a ENTREGADO
            pedido.setEstado(EstadoPedido.ENTREGADO);
            System.out.println("[Repartidor - " + nombre + "] Estado: " + pedido.getEstado());
        }
    }
}