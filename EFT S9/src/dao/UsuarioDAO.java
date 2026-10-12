package dao;

import conexion.DatabaseConnection;
import modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario validarLogin(String correo, String contraseña) {
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND contraseña = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);
            pstmt.setString(2, contraseña);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("rut"),
                        rs.getString("correo"),
                        rs.getString("contraseña"),
                        rs.getString("rol")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}