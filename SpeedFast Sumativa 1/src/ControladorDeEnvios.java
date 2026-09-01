import java.util.ArrayList;

public class ControladorDeEnvios implements Rastreable {
    private ArrayList<Pedido> historialEntregas;

    public ControladorDeEnvios() {
        this.historialEntregas = new ArrayList<>();
    }

    public void registrarEntrega(Pedido pedido) {
        historialEntregas.add(pedido);
    }

    @Override
    public void verHistorial() {
        System.out.println("Historial:");
        for (Pedido p : historialEntregas) {
            System.out.println("- " + p.getClass().getSimpleName() + " #" + p.getNumeroPedido() + " – entregado por " + p.getRepartidor());
        }
        System.out.println();
    }
}