import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class EventBySalle {

    // Méthode principale pour récupérer et afficher les festivals futurs
    public static void afficherEventBySalle() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez le nom de la salle : ");
        String salleName = scanner.next();

        String sql = "SELECT * FROM project_schema.get_evenements_salle(?) ORDER BY date_event";

        try (Connection conn = ConnectionManager.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            System.out.printf("Evènements pour la salle %s :", salleName);
            pstmt.setString(1, salleName);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String nomEvent = rs.getString("nom_event");
                    java.sql.Date dateEvent = rs.getDate("date_event");
                    String salle = rs.getString("salle");
                    String artiste = rs.getString("artiste");
                    double prix = rs.getDouble("prix");
                    boolean complet = rs.getBoolean("complet");

                    System.out.printf("Event: %s, Date: %s, Salle: %s, Artiste: %s, Prix: %.2f, Complet: %b%n\n",
                            nomEvent, dateEvent, salle, artiste, prix, complet);
                }

                System.out.println("Voulez-vous réserver un évènement ? (y/n)");
                String reserver = scanner.next();
                if (reserver.equals("y")) {
                    ReservationEvent.reserverEvenement();
                } else {
                    System.out.println("Retour au menu");
                    Auth.connectedMenu();
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la requête : " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la requête : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        afficherEventBySalle();
    }

}
