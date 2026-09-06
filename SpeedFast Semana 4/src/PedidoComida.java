public class PedidoComida extends Pedido {
    public PedidoComida(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public double calcularTiempoEntrega() {
        return distanciaKm * 5; // Ejemplo: 5 minutos por km
    }
}