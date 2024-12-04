import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InterfaceClient {
    public InterfaceClient() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "postgres", "3689");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            System.exit(1);
        }

        if (conn != null) {
            String query = "SELECT * FROM sql_project.festivalsfuture";
            try (PreparedStatement pstmt = conn.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    String name = rs.getString("nom");
                    Date datePremier = rs.getDate("date_premier_event");
                    Date dateDernier = rs.getDate("date_dernier_event");
                    int montant = rs.getInt("somme_prix_tickets");

                    System.out.println("Name: " + name + ", Date Premier: " + datePremier + ", Date Dernier: " + dateDernier + ", Montant: " + montant);
                }

            } catch (SQLException e) {
                System.out.println("Query execution failed: " + e.getMessage());
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close connection: " + e.getMessage());
                }
            }
        }
    }
}