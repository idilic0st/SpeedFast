public class PedidoExpress extends Pedido implements Despachable, Cancelable {
    private boolean prioridadAlta;

    public PedidoExpress(int numeroPedido, String direccion, double distanciaKm, boolean prioridadAlta) {
        super(numeroPedido, direccion, distanciaKm);
        this.prioridadAlta = prioridadAlta;
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (distanciaKm * 2) + 5;
    }

    @Override
    public void asignarRepartidor() {
        this.repartidor = "Carlos Pérez (Bicicleta Express)";
    }

    @Override
    public void despachar() {
        System.out.println("[Pedido Express]");
        mostrarResumen();
        System.out.println("Pedido despachado correctamente.\n");
    }

    @Override
    public void cancelar() {
        System.out.println("Cancelando Pedido Express #" + numeroPedido + "...");
        System.out.println("→ Pedido cancelado exitosamente.\n");
    }
}