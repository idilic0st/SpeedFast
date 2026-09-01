public class PedidoEncomienda extends Pedido implements Despachable, Cancelable {
    private double pesoKg;

    public PedidoEncomienda(int numeroPedido, String direccion, double distanciaKm, double pesoKg) {
        super(numeroPedido, direccion, distanciaKm);
        this.pesoKg = pesoKg;
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (distanciaKm * 4) + 20;
    }

    @Override
    public void asignarRepartidor() {
        this.repartidor = "Daniela Tapia (Furgón)";
    }

    @Override
    public void despachar() {
        System.out.println("[Pedido Encomienda]");
        mostrarResumen();
        System.out.println("Pedido despachado correctamente.\n");
    }

    @Override
    public void cancelar() {
        System.out.println("Cancelando Pedido Encomienda #" + numeroPedido + "...");
        System.out.println("→ Pedido cancelado exitosamente.\n");
    }
}