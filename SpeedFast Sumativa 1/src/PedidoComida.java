public class PedidoComida extends Pedido implements Despachable, Cancelable {
    private String restaurante;

    public PedidoComida(int numeroPedido, String direccion, double distanciaKm, String restaurante) {
        super(numeroPedido, direccion, distanciaKm);
        this.restaurante = restaurante;
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (distanciaKm * 3) + 15;
    }

    @Override
    public void asignarRepartidor() {
        this.repartidor = "Luis Díaz (Moto)";
    }

    @Override
    public void despachar() {
        System.out.println("[Pedido Comida - " + restaurante + "]");
        mostrarResumen();
        System.out.println("Pedido despachado correctamente.\n");
    }

    @Override
    public void cancelar() {
        System.out.println("Cancelando Pedido Comida #" + numeroPedido + "...");
        System.out.println("→ Pedido cancelado exitosamente.\n");
    }
}