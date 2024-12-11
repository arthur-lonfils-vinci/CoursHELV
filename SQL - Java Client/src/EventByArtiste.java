import java.sql.*;

public class EventByArtiste {
    public static void afficherEventByArtiste() {
        System.out.println("Entrez le nom de l'artiste : ");
        String artiste = Auth.scanner.next();

        String sql = "SELECT * FROM project_schema.get_evenements_artiste(?)";

        try (Connection conn = ConnectionManager.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, artiste);
            System.out.println("Evènements par artiste :");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String nomEvent = rs.getString("nom_event");
                    Date dateEvent = rs.getDate("date_event");
                    String salle = rs.getString("salle");
                    double prix = rs.getDouble("prix");
                    boolean complet = rs.getBoolean("complet");

                    System.out.println("Event : " + nomEvent);
                    System.out.printf("Date: %s, Salle: %s, Prix: %.2f, Complet: %b%n\n",
                            dateEvent, salle, prix, complet);
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la requête : " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la requête : " + e.getMessage());
        }
    }
}
