package Main;

import domaine.File;
import domaine.User;

import java.time.LocalDateTime;
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
        file1.addPermission(user2, false, true, false, LocalDateTime.now().plusDays(1).toLocalDate());
        // Expired yesterday
        file1.addPermission(user2, false, false, true, LocalDateTime.now().minusDays(1).toLocalDate());


        file2.addPermission(user1, true, false, false);
        file2.addPermission(user1, false, true, false);
        file2.addPermission(user3, false, false, true);

        file3.addPermission(user1, true, false, true);
        file3.addPermission(user1, false, true, false);
        file3.addPermission(user2, true, true, true);


        // Check expired permissions
        file1.revokeAllPermissions(user2);
        file1.addPermission(user2, false, false, true, LocalDateTime.now().minusDays(3).toLocalDate());
        file1.addPermission(user2, false, true, true, LocalDateTime.now().minusDays(5).toLocalDate());

        System.out.println("Expired permissions:");
        var expired = file1.expiredPermissionsSortedByExpirationDate();
        for (var permission : expired) {
            permission.print();
        }

        // Check Permission map
        System.out.println("\nPermission map:");
        var map = file1.userPermissionsMap();
        for (var entry : map.entrySet()) {
            System.out.println(entry.getKey().getName() + ":");
            for (var permission : entry.getValue()) {
                permission.print();
            }
        }



        file1.revokeAllPermissions(user2);
        file1.addPermission(user2, false, false, true);
        file1.addPermission(user2, false, true, true);

        System.out.println("\nGrouped permissions for user2 on file1:");
        var grouped = file1.groupedNonExpiredUserPermissions(user2);

        grouped.print();
        System.out.println("Expected: false true true");

        /*
            Expired permissions:
            Permission: user2 file1 false true true 2024-01-16
            Permission: user2 file1 false false true 2024-01-14

            Permission map:
            user2:
            Permission: user2 file1 false false true 2024-01-16
            Permission: user2 file1 false true true 2024-01-14
            user3:
            Permission: user3 file1 false true false 2024-02-18

            Grouped permissions for user2 on file1:
            Permission: user2 file1 false true true 2024-01-19
            Expected: false true true
         */

    }
}
