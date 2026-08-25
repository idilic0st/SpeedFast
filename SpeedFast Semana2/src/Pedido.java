public abstract class Pedido {
    // Atributos encapsulados
    private String idPedido;
    private String direccionEntrega;
    private double distanciaKm;

    // Constructor
    public Pedido(String idPedido, String direccionEntrega, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
    }

    // Getters y Setters
    public String getIdPedido() { return idPedido; }
    public void setIdPedido(String idPedido) { this.idPedido = idPedido; }

    public String getDireccionEntrega() { return direccionEntrega; }
    public void setDireccionEntrega(String direccionEntrega) { this.direccionEntrega = direccionEntrega; }

    public double getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(double distanciaKm) { this.distanciaKm = distanciaKm; }

    // Metodo implementado para mostrar los datos básicos
    public void mostrarResumen() {

        System.out.println(this.getClass().getSimpleName() + " #" + this.idPedido);
        System.out.println("Dirección: " + this.direccionEntrega);
        System.out.println("Distancia: " + (int)this.distanciaKm + " km");
    }

    // Metodo abstracto
    public abstract int calcularTiempoEntrega();
}
