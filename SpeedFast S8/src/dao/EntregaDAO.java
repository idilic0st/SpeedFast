package dao;

import modelo.Entrega;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {

    public boolean create(Entrega entrega) {
        String sql = "INSERT INTO entregas (id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, entrega.getIdPedido());
            stmt.setInt(2, entrega.getIdRepartidor());
            stmt.setDate(3, entrega.getFecha());
            stmt.setTime(4, entrega.getHora());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar entrega: " + e.getMessage());
            return false;
        }
    }

    public List<Entrega> readAll() {
        List<Entrega> lista = new ArrayList<>();
        String sql = "SELECT * FROM entregas";
        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Entrega(
                        rs.getInt("id"),
                        rs.getInt("id_pedido"),
                        rs.getInt("id_repartidor"),
                        rs.getDate("fecha"),
                        rs.getTime("hora")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar entregas: " + e.getMessage());
        }
        return lista;
    }

    public boolean update(Entrega entrega) {
        String sql = "UPDATE entregas SET id_pedido = ?, id_repartidor = ?, fecha = ?, hora = ? WHERE id = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, entrega.getIdPedido());
            stmt.setInt(2, entrega.getIdRepartidor());
            stmt.setDate(3, entrega.getFecha());
            stmt.setTime(4, entrega.getHora());
            stmt.setInt(5, entrega.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar entrega: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM entregas WHERE id = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar entrega: " + e.getMessage());
            return false;
        }
    }
}