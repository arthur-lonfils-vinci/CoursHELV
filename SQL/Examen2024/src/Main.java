import java.sql.*;
import java.util.Scanner;

public class Main {

    private final static String USER = "postgres";
    private final static String PASSWORD = "Guepari28";
    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static Connection connection;

    public static void main(String[] args) {
        initialiserConnection();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the nationality of the participant you want to view: ");
        String nationalite = scanner.nextLine();

        try {
            String query = "SELECT * FROM examen.view WHERE nationalite = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nationalite);
            ResultSet rsd = statement.executeQuery();
            while (rsd.next()) {
                System.out.println(rsd.getString("nom") + " " + rsd.getString("prenom") + " " + rsd.getString("nationalite") + " " + rsd.getString("niveau"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scanner.close();


    }

    private static void initialiserConnection(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}