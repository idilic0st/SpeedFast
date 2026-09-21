package modelo;

public class PedidoExpress extends Pedido {
    public PedidoExpress(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public double calcularTiempoEntrega() {
        return distanciaKm * 3;
    }
}