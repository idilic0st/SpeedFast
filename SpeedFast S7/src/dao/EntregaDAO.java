package dao;

import modelo.Entrega;
import java.sql.*;

public class EntregaDAO {

    public boolean guardar(Entrega entrega) {
        String sql = "INSERT INTO entrega (id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, entrega.getIdPedido());
            stmt.setInt(2, entrega.getIdRepartidor());
            stmt.setDate(3, entrega.getFecha());
            stmt.setTime(4, entrega.getHora());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al guardar entrega: " + e.getMessage());
            return false;
        }
    }
}