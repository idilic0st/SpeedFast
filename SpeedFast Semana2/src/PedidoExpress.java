public class PedidoExpress extends Pedido {

    public PedidoExpress(String idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        // Fórmula: 10 min base. Si es > 5 km, se agregan 5 min extra.
        int tiempo = 10;
        if (getDistanciaKm() > 5) {
            tiempo += 5;
        }
        return tiempo;
    }
}