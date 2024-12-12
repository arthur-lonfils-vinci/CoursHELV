import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    // Database configuration properties (adjust as necessary)
    private static final String DB_URL = "jdbc:postgresql://172.19.236.180:5432/postgres";
    //private static final String DB_USER = "postgres";
    //private static final String DB_PASSWORD = "Guepari28";
    private static final String DB_USER = "etudiant2";
    private static final String DB_PASSWORD = "password";

    // Private constructor to prevent instantiation
    ConnectionManager() {}

    // Always return a new connection on every call
    public static Connection getInstance() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Test connection (Optional, useful for debugging or manual testing)
    public static void main(String[] args) {
        try (Connection conn = ConnectionManager.getInstance()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Connexion établie avec succès !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }
}