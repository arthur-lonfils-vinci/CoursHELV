import java.sql.*;

public class MyReservation {
    public static void afficherMyReservation(int idClient) {
        String sql = "SELECT * FROM project_schema.get_reservation_client(?)";

        try (Connection conn = ConnectionManager.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, idClient);

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Réservations :");
                while (rs.next()) {
                    String nomEvent = rs.getString("nom_evenement");
                    Date dateEvent = rs.getDate("date_evenement");
                    String salle = rs.getString("salle_evenement");
                    Integer numReservation = rs.getInt("num_reservation");
                    Integer nbTickets = rs.getInt("nb_tickets");

                    System.out.println("Réservations n° " + numReservation + " :");

                    System.out.printf("Event: %s, Date: %s, Salle: %s, Nombre de tickets: %d%n\n",
                            nomEvent, dateEvent, salle, nbTickets);
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la requête : " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la requête : " + e.getMessage());
        }
    }

}
