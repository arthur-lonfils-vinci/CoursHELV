package Main;

import domaine.File;
import domaine.User;

import java.time.LocalDate;
import java.util.Date;

public class Main {
    /**
     * Generate examples of permission addition, revoke, and check
     * @param args
     */
    public static void main(String[] args) {
        User user1 = new User("user1");
        User user2 = new User("user2");
        User user3 = new User("user3");

        File file1 = new File("file1", 10, user1);
        File file2 = new File("file2", 20, user2);
        File file3 = new File("file3", 30, user3);

        // Create various permissions
        file1.addPermission(user3, false, true, false);
        file1.addPermission(user2, true, false, false);
        // Expires tomorow
        file1.addPermission(user2, false, true, false, LocalDate.from(java.time.LocalDateTime.now().plusDays(1)));
        // Expired yesterday
        file1.addPermission(user2, false, false, true, LocalDate.from(java.time.LocalDateTime.now().minusDays(1)));


        file2.addPermission(user1, true, false, false);
        file2.addPermission(user1, false, true, false);
        file2.addPermission(user3, false, false, true);

        file3.addPermission(user1, true, false, true);
        file3.addPermission(user1, false, true, false);
        file3.addPermission(user2, true, true, true);

        // Check permissions
        System.out.println("Check permissions:");
        System.out.println("file1: user1 rwx: " + file1.hasPermission(user1, true, true, true) + " expected: true");
        System.out.println("file1: user2 rwx: " + file1.hasPermission(user2, true, true, true) + " expected: false");
        System.out.println("file1: user2 r: " + file1.hasPermission(user2, true, false, false) + " expected: true");
        System.out.println("file1: user3 rwx: " + file1.hasPermission(user3, true, true, true) + " expected: false");
        System.out.println("file1: user3 w: " + file1.hasPermission(user3, false, true, false) + " expected: true");
        System.out.println("file2: user1 rwx: " + file2.hasPermission(user1, true, true, true) + " expected: false");
        System.out.println("file2: user1 rw: " + file2.hasPermission(user1, true, true, false) + " expected: true");
        System.out.println("file2: user2 rwx: " + file2.hasPermission(user2, true, true, true) + " expected: true");
        System.out.println("file2: user3 rwx: " + file2.hasPermission(user3, true, true, true) + " expected: false");
        System.out.println("file2: user3 x: " + file2.hasPermission(user3, false, false, true) + " expected: true");
        System.out.println("file3: user1 rwx: " + file3.hasPermission(user1, true, true, true) + " expected: true");
        System.out.println("file3: user2 rwx: " + file3.hasPermission(user2, true, true, true) + " expected: true");
        System.out.println("file3: user3 rwx: " + file3.hasPermission(user3, true, true, true) + " expected: true");

        System.out.println("Permissions before revoke:");
        file1.printPermissions();

        file1.revokeAllPermissions(user2);
        System.out.println("Permissions after revoke:");
        file1.printPermissions();


    }
}
