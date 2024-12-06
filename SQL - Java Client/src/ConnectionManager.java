import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static java.sql.Connection conn = null;
    private ConnectionManager() {
    }
    public static java.sql.Connection getInstance() {
        if (conn == null) {
            try {
                String url = "jdbc:postgresql://localhost:5432/postgres";
                String user = "postgres";
                String password = "Guepari28";

                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Connexion établie !");
            } catch (SQLException e) {
                System.out.println("Erreur de connexion : " + e.getMessage());
            }
        }
        return conn;
    }

    public static void main(String[] args) {
        getInstance();
    }
}
