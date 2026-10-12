package dao;

import conexion.DatabaseConnection;
import modelo.Estudiante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("rut"),
                        rs.getString("curso"),
                        rs.getString("correo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean agregarEstudiante(Estudiante est) {
        String sql = "INSERT INTO estudiantes (nombre, rut, curso, correo) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, est.getNombre());
            pstmt.setString(2, est.getRut());
            pstmt.setString(3, est.getCurso());
            pstmt.setString(4, est.getCorreo());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarEstudiante(int idEstudiante) {
        String sql = "DELETE FROM estudiantes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idEstudiante);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}