package Main;

import domaine.File;
import domaine.User;

import static domaine.Permission.PermissionType.*;

// import static domaine.Permission.PermissionType.*;


public class Main {
    /**
     * Génère des exemples d'ajout, de révocation et de vérification de permissions
     * @param args
     */
    public static void main(String[] args) {
        User user1 = new User("user1");
        User user2 = new User("user2");
        User user3 = new User("user3");


        File file1 = new File("file1", 10, user1);
        File file2 = new File("file2", 20, user2);
        File file3 = new File("file3", 30, user3);

        /*
        // Create various permissions
        file1.addPermission(user2, true, false, false);
        file1.addPermission(user3, false, true, false);

        file2.addPermission(user1, true, true, false);
        file2.addPermission(user3, false, false, true);

        file3.addPermission(user1, true, true, true);
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
         */
        // Même test avec les enums

        // Create various permissions
        file1.addPermission(user2, R__);
        file1.addPermission(user3, _W_);

        file2.addPermission(user1, RW_);
        file2.addPermission(user3, __X);

        file3.addPermission(user1, RWX);
        file3.addPermission(user2, RWX);


        System.out.println("\nCheck permissions with enums:");
        System.out.println("file1: user1 rwx: " + file1.hasPermission(user1, RWX) + " expected: true");

        System.out.println("file1: user2 rwx: " + file1.hasPermission(user2, RWX) + " expected: false");
        System.out.println("file1: user2 r: " + file1.hasPermission(user2, R__) + " expected: true");
        System.out.println("file1: user3 rwx: " + file1.hasPermission(user3, RWX) + " expected: false");
        System.out.println("file1: user3 w: " + file1.hasPermission(user3, _W_) + " expected: true");
        System.out.println("file2: user1 rwx: " + file2.hasPermission(user1, RWX) + " expected: false");
        System.out.println("file2: user1 rw: " + file2.hasPermission(user1, RW_) + " expected: true");
        System.out.println("file2: user2 rwx: " + file2.hasPermission(user2, RWX) + " expected: true");
        System.out.println("file2: user3 rwx: " + file2.hasPermission(user3, RWX) + " expected: false");
        System.out.println("file2: user3 x: " + file2.hasPermission(user3, __X) + " expected: true");
        System.out.println("file3: user1 rwx: " + file3.hasPermission(user1, RWX) + " expected: true");
        System.out.println("file3: user2 rwx: " + file3.hasPermission(user2, RWX) + " expected: true");
        System.out.println("file3: user3 rwx: " + file3.hasPermission(user3, RWX) + " expected: true");

    }
}
