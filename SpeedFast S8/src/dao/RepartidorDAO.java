package dao;

import modelo.Repartidor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO {

    public boolean create(Repartidor repartidor) {
        String sql = "INSERT INTO repartidores (nombre) VALUES (?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, repartidor.getNombre());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear repartidor: " + e.getMessage());
            return false;
        }
    }

    public List<Repartidor> readAll() {
        List<Repartidor> lista = new ArrayList<>();
        String sql = "SELECT * FROM repartidores";
        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Repartidor(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar repartidores: " + e.getMessage());
        }
        return lista;
    }

    public boolean update(Repartidor repartidor) {
        String sql = "UPDATE repartidores SET nombre = ? WHERE id = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, repartidor.getNombre());
            stmt.setInt(2, repartidor.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar repartidor: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM repartidores WHERE id = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar repartidor: " + e.getMessage());
            return false;
        }
    }
}