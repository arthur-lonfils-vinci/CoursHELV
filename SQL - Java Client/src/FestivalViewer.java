import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class FestivalViewer {

    // Méthode principale pour récupérer et afficher les festivals futurs
    public static void afficherFestivalsFuturs() {
        String sql = "SELECT * FROM project_schema.festivalsFuturs";

        try (Connection conn = ConnectionManager.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Festivals futurs :");
            while (rs.next()) {
                String nomEvenement = rs.getString("nom");
                String dateDebut = rs.getString("date_premier");
                String dateFin = rs.getString("date_dernier");
                double prixTotal = rs.getDouble("somme_prix");

                System.out.printf("Nom: %s | Début: %s | Fin: %s | Prix total: %.2f€%n",
                        nomEvenement, dateDebut, dateFin, prixTotal);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la requête : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        afficherFestivalsFuturs();
    }

}
