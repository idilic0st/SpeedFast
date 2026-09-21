package modelo;

import java.util.List;
import java.util.Random;

public class Repartidor implements Runnable {
    private String nombre;
    private List<Pedido> listaPedidos;
    private Random random = new Random();

    public Repartidor(String nombre, List<Pedido> listaPedidos) {
        this.nombre = nombre;
        this.listaPedidos = listaPedidos;
    }

    @Override
    public void run() {
        for (Pedido pedido : listaPedidos) {
            String tipo = pedido.getClass().getSimpleName();
            System.out.println("[Repartidor: " + nombre + "] Entregando " + tipo + " #" + pedido.getIdPedido() + "...");
            try {
                Thread.sleep(1000 + random.nextInt(2000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("[Repartidor: " + nombre + "] Pedido #" + pedido.getIdPedido() + " entregado.");
        }
    }
}