public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(String idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        // Fórmula: 20 min + 1.5 min por kilómetro
        return (int) Math.round(20 + (1.5 * getDistanciaKm()));
    }
}
