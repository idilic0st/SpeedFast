public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(int idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Pedido Encomienda");
    }

    // Metodo SOBRESCRITO
    @Override
    public void asignarRepartidor() {
        super.asignarRepartidor();
        System.out.println("→ Validando peso y embalaje... OK");
    }
}