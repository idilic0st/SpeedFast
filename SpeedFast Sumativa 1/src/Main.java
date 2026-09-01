public class Main {
    public static void main(String[] args) {
        ControladorDeEnvios controlador = new ControladorDeEnvios();

        // Instanciación de tipos de pedidos
        PedidoComida p1 = new PedidoComida(101, "Av. Providencia 1234", 3.5, "Burguer Palace");
        PedidoEncomienda p2 = new PedidoEncomienda(102, "Av. Santa Rosa 567", 7.0, 12.5);
        PedidoExpress p3 = new PedidoExpress(103, "Calle Las Condes 890", 2.0, true);

        // Asignación automática y manual
        p1.asignarRepartidor(); // Automática
        p2.asignarRepartidor("Daniela Tapia"); // Sobrecarga manual
        p3.asignarRepartidor(); // Automática

        // Simulación de despachos e historial
        controlador.registrarEntrega(p1);
        p2.despachar();
        controlador.registrarEntrega(p2);

        // Cancelación de pedido
        p3.cancelar();

        // Mostrar historial de entregas
        controlador.verHistorial();
    }
}