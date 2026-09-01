public abstract class Pedido {
    protected int numeroPedido;
    protected String direccion;
    protected double distanciaKm;
    protected String repartidor;

    public Pedido(int numeroPedido, String direccion, double distanciaKm) {
        this.numeroPedido = numeroPedido;
        this.direccion = direccion;
        this.distanciaKm = distanciaKm;
        this.repartidor = "Sin asignar";
    }

    public abstract int calcularTiempoEntrega();

    public void mostrarResumen() {
        System.out.println("Pedido #" + numeroPedido);
        System.out.println("Dirección: " + direccion);
        System.out.println("Distancia: " + distanciaKm + " km");
        System.out.println("Repartidor asignado: " + repartidor);
        System.out.println("Tiempo estimado: " + calcularTiempoEntrega() + " minutos");
    }

    public void asignarRepartidor(String nombre) {
        this.repartidor = nombre;
    }

    public abstract void asignarRepartidor();

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public String getRepartidor() {
        return repartidor;
    }
}