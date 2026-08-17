public class PedidoExpress extends Pedido {

    public PedidoExpress(int idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Pedido Express");
    }

    // Metodo SOBRESCRITO
    @Override
    public void asignarRepartidor() {
        super.asignarRepartidor();
        System.out.println("→ Repartidor más cercano con disponibilidad inmediata encontrado.");
    }
}