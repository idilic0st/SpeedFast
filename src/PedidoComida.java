public class PedidoComida extends Pedido {

    public PedidoComida(int idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Pedido Comida");
    }

    // Metodo SOBRESCRITO
    @Override
    public void asignarRepartidor() {
        super.asignarRepartidor();
        System.out.println("→ Verificando mochila térmica... OK");
    }
}