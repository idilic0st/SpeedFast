public abstract class Pedido implements Despachable, Cancelable, Rastreable {
    protected int idPedido;
    protected String direccionEntrega;
    protected double distanciaKm;

    public Pedido(int idPedido, String direccionEntrega, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
    }

    public abstract double calcularTiempoEntrega();

    public void mostrarResumen() {
        System.out.println("Pedido #" + idPedido + " | Destino: " + direccionEntrega + " | Distancia: " + distanciaKm + " km");
    }

    @Override
    public void despachar() {
        System.out.println("Despachando pedido #" + idPedido);
    }

    @Override
    public void cancelar() {
        System.out.println("Pedido #" + idPedido + " ha sido cancelado.");
    }

    @Override
    public String obtenerUbicacion() {
        return "En ruta hacia " + direccionEntrega;
    }

    public int getIdPedido() {
        return idPedido;
    }
}