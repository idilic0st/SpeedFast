package dao;

import conexion.DatabaseConnection;
import modelo.Prestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrestamoDAO {

    public boolean registrarPrestamo(Prestamo prestamo) {
        String sql = "INSERT INTO prestamos (id_estudiante, id_libro, fecha_prestamo, fecha_devolucion, devuelto) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, prestamo.getIdEstudiante());
            pstmt.setInt(2, prestamo.getIdLibro());
            pstmt.setDate(3, prestamo.getFechaPrestamo());
            pstmt.setDate(4, prestamo.getFechaDevolucion());
            pstmt.setBoolean(5, prestamo.isDevuelto());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}