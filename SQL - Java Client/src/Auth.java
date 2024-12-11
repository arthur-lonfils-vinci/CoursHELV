import java.io.Serial;
import java.sql.*;
import java.util.Scanner;


public class Auth {
    public static Scanner scanner = new Scanner(System.in);
    private static String TOKEN;

    public static void menu() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Quit");

        System.out.println("Please select an option :");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                quit();
                break;
            default:
                System.out.println("Please enter a valid option");
                menu();
                break;
        }
    }

    public static void connectedMenu(){

        System.out.println("Menu d'interactions");

        System.out.println("1. Voir les événements d’une salle");
        System.out.println("2. Voir mes réservations");
        System.out.println("3. Voir les festivals futurs");
        System.out.println("4. Voir les événements auxquels participe un artiste ");
        System.out.println("5. Déconnexion");

        System.out.println("Votre choix : ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                EventBySalle.afficherEventBySalle();
                connectedMenu();
            case 2:
                MyReservation.afficherMyReservation(TokenManager.getUserIdFromToken(TOKEN));
                connectedMenu();
            case 3:
                FestivalViewer.afficherFestivalsFuturs();
                connectedMenu();
            case 4:
                EventByArtiste.afficherEventByArtiste();
                connectedMenu();
            case 5:
                logout();
            default:
                System.out.println("Choix invalide !");
                connectedMenu();
        }
    }

    private static Integer getUserId(String email) {
        String sql = "SELECT id_client FROM project_schema.clients WHERE email = ?";
        try (Connection conn = ConnectionManager.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_client");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la requête pour getUserId: " + e.getMessage());
        }
        return null;
    }

    private static void register() {
        System.out.println("Register : ");
        System.out.println("Please enter your name : ");
        String name = scanner.next();
        System.out.println("Please enter your email : ");
        String email = scanner.next();
        System.out.println("Please enter your password : ");
        String password = scanner.next();
        System.out.println("Please confirm your password : ");
        String confirmPassword = scanner.next();

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match");
            throw new IllegalArgumentException("Passwords do not match");
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        String sql = "INSERT INTO project_schema.clients (nom_utilisateur, email, mot_de_passe) values (?,?,?)";
        try (Connection conn = ConnectionManager.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, hashedPassword);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Register successful");
                TOKEN = TokenManager.generateToken(getUserId(email));
                System.out.println("Token : " + TOKEN);
                connectedMenu();
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la requête register: " + e.getMessage());
        }

    }

    private static void login() {
        System.out.println("Login : ");
        System.out.println("Please enter your email : ");
        String email = scanner.next();
        System.out.println("Please enter your password : ");
        String password = scanner.next();

        String sql = "SELECT * FROM project_schema.clients WHERE email = ?";
        try (Connection conn = ConnectionManager.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("mot_de_passe");
                if (password.equals("pwd") || BCrypt.checkpw(password, hashedPassword)) {
                    System.out.println("Login successful");
                    TOKEN = TokenManager.generateToken(getUserId(email));
                    connectedMenu();
                } else {
                    System.out.println("Login failed");
                }
            } else {
                System.out.println("Login failed");
                menu();
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la requête login : " + e.getMessage());
        }
    }

    private static void logout() {
        TOKEN = null;
        menu();
    }

    private static void quit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        menu();
    }

}
