package dao;

import modelo.Repartidor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO {

    public boolean guardar(Repartidor repartidor) {
        String sql = "INSERT INTO repartidor (nombre) VALUES (?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, repartidor.getNombre());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al guardar repartidor: " + e.getMessage());
            return false;
        }
    }

    public List<Repartidor> listarTodos() {
        List<Repartidor> lista = new ArrayList<>();
        String sql = "SELECT * FROM repartidor";
        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Repartidor r = new Repartidor(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
                lista.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar repartidores: " + e.getMessage());
        }
        return lista;
    }
}