import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ReservationEvent {

    public static void reserverEvenement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir le nom de l'évènement");
        String nomEvenement = scanner.next();
        System.out.println("Veuillez saisir le nombre de tickets");
        int nbTickets = scanner.nextInt();

        String sql = "SELECT project_schema.reserver_evenement(?, ?, ?)";

        try (Connection conn = ConnectionManager.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, TokenManager.getUserIdFromToken(Auth.TOKEN));
            pstmt.setString(2, nomEvenement);
            pstmt.setInt(3, nbTickets);

            try {
                pstmt.execute();
                System.out.println("Réservation effectuée");
                Auth.connectedMenu();// Use execute for CALL statements
            } catch (Exception e) {
                System.out.println("Erreur lors de la réservation : " + e.getMessage());
                Auth.connectedMenu();
            }

        } catch (Exception e) {
            System.out.println("Erreur lors de la réservation : " + e.getMessage());
        }
    }
}
