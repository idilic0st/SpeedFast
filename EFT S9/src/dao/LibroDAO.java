package dao;

import conexion.DatabaseConnection;
import modelo.Libro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    public List<Libro> listarLibros() {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libros";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Libro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn"),
                        rs.getString("editorial"),
                        rs.getInt("stock"),
                        rs.getInt("id_categoria")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean agregarLibro(Libro libro) {
        String sql = "INSERT INTO libros (titulo, autor, isbn, editorial, stock, id_categoria) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setString(3, libro.getIsbn());
            pstmt.setString(4, libro.getEditorial());
            pstmt.setInt(5, libro.getStock());
            pstmt.setInt(6, libro.getIdCategoria());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarLibro(int idLibro) {
        String sql = "DELETE FROM libros WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idLibro);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public synchronized boolean actualizarStock(int idLibro, int nuevoStock) {
        String sql = "UPDATE libros SET stock = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, nuevoStock);
            pstmt.setInt(2, idLibro);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}