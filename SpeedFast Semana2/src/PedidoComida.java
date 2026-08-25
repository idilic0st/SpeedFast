public class PedidoComida extends Pedido {

    public PedidoComida(String idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        // Fórmula: 15 min + 2 min por cada kilómetro
        return (int) (15 + (2 * getDistanciaKm()));
    }
}