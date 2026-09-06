public class PedidoEncomienda extends Pedido {
    public PedidoEncomienda(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public double calcularTiempoEntrega() {
        return distanciaKm * 8; // Ejemplo: 8 minutos por km
    }
}
