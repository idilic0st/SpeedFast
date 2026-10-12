package dao;

import modelo.Prestamo;

public class ProcesoPrestamoThread implements Runnable {
    private Prestamo prestamo;
    private PrestamoDAO prestamoDAO;
    private LibroDAO libroDAO;
    private int stockActual;

    public ProcesoPrestamoThread(Prestamo prestamo, int stockActual) {
        this.prestamo = prestamo;
        this.prestamoDAO = new PrestamoDAO();
        this.libroDAO = new LibroDAO();
        this.stockActual = stockActual;
    }

    @Override
    public void run() {
        System.out.println("[Hilo] Procesando préstamo en segundo plano...");

        // 1. Registrar préstamo en la base de datos
        boolean guardado = prestamoDAO.registrarPrestamo(prestamo);

        // 2. Actualizar stock de forma sincronizada si hay stock disponible
        if (guardado && stockActual > 0) {
            boolean stockActualizado = libroDAO.actualizarStock(prestamo.getIdLibro(), stockActual - 1);
            if (stockActualizado) {
                System.out.println("[Hilo] ¡Préstamo registrado y stock actualizado con éxito!");
            }
        } else {
            System.out.println("[Hilo] Error: No se pudo procesar el préstamo o no hay stock.");
        }
    }
}