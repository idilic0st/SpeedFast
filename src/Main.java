public class Main {
    public static void main(String[] args) {
        // polimorfismo
        Pedido comida = new PedidoComida(1, "Av. Providencia 123");
        Pedido encomienda = new PedidoEncomienda(2, "Calle Las Flores 456");
        Pedido express = new PedidoExpress(3, "Gran Avenida 789");

        // Llamando a los métodos sobrecargados
        comida.asignarRepartidor("Juan Pérez");
        encomienda.asignarRepartidor("Camila Soto");
        express.asignarRepartidor("Luis Díaz");

        // Llamando a los métodos sobrecargados por segunda vez
        comida.asignarRepartidor("Juan Pérez");
        encomienda.asignarRepartidor("Camila Soto");
        express.asignarRepartidor("Luis Díaz");
    }
}