package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    // Configura tu URL, usuario y contraseña según tu entorno local de MySQL
    private final String URL = "jdbc:mysql://localhost:3306/biblioteca?useSSL=false&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "admin";

    private DatabaseConnection() {
        try {
            // Registrar el driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    // Método estático para aplicar el patrón Singleton
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else {
            try {
                if (instance.connection.isClosed()) {
                    instance = new DatabaseConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}