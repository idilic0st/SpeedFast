package modelo;

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

    public int getIdPedido() { return idPedido; }
    public String getDireccionEntrega() { return direccionEntrega; }
    public double getDistanciaKm() { return distanciaKm; }

    @Override
    public void despachar() {}

    @Override
    public void cancelar() {}

    @Override
    public String obtenerUbicacion() {
        return "En ruta hacia " + direccionEntrega;
    }
}