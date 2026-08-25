public class Main {
    public static void main(String[] args) {
        // 1. Instanciación de objetos
        Pedido comida = new PedidoComida("001", "Av. Italia 456", 4);
        Pedido encomienda = new PedidoEncomienda("002", "Av. Independencia 123", 6);
        Pedido express = new PedidoExpress("003", "Av. Apoquindo 1500", 7);



        Pedido[] listaPedidos = {comida, encomienda, express, comida, encomienda, express};

        // 2. Ejecución de métodos
        for (Pedido pedido : listaPedidos) {
            pedido.mostrarResumen();
            System.out.println("Tiempo estimado de entrega: " + pedido.calcularTiempoEntrega() + " minutos");
        }
    }
}