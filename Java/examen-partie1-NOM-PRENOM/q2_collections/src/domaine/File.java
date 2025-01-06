package domaine;

import java.time.LocalDate;
import java.util.*;

public class File {
    private String name;
    private int size;
    private User owner;

    private SortedMap<User, List<Permission>> permissions = new TreeMap<>(Comparator.comparingInt(User::getId));

    public File(String name, int size, User owner) {
        this.name = name;
        this.size = size;
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }




    /**
     *  Vérifie que l'utilisateur a toutes les permissions en paramètre
     * @param user
     * @param read
     * @param write
     * @param execute
     * @return true si l'utilisateur a toutes les permissions, false sinon.
     */
    public boolean hasPermission(User user, boolean read, boolean write, boolean execute) {

        if (user.equals(owner)) {
            return true;
        }

        boolean hasRead = false;
        boolean hasWrite = false;
        boolean hasExecute = false;
        if (permissions.containsKey(user)) {
            for (Permission permission : permissions.get(user)) {
                hasRead = hasRead || permission.isRead();
                hasWrite = hasWrite || permission.isWrite();
                hasExecute = hasExecute || permission.isExecute();
            }
        }
        return ( hasRead || !read ) && ( hasWrite || !write ) && ( hasExecute || !execute );
        /*
        for (Permission permission : permissions) {

            if (permission.getUser().equals(user) && permission.getFile().equals(this) && !permission.isExpired()) {
                hasRead = hasRead || permission.isRead();
                hasWrite = hasWrite || permission.isWrite();
                hasExecute = hasExecute || permission.isExecute();
            }
        }
        return ( hasRead || !read ) && ( hasWrite || !write ) && ( hasExecute || !execute );
    */
    }



    /**
     *  Annule toutes les permissions pour l'utilisateur
     * @param user
     * @return true si l'utilisateur avait des permissions, false sinon.
     */
    public boolean revokeAllPermissions(User user) {
        boolean revoked = false;
        Iterator<Permission> iterator = permissions.get(user).iterator();
        while (iterator.hasNext()) {
            Permission permission = iterator.next();
            if (permission.getUser().equals(user)) {
                iterator.remove(); // Suppression sûre
                revoked = true;
            }
        }
        return revoked;
    }


    /**
     * Ajoute une permission pour l'utilisateur
     * @param user1
     * @param r
     * @param w
     * @param x
     */
    public void addPermission(User user1, boolean r, boolean w, boolean x) {
        if (permissions.containsKey(user1)) {
            List<Permission> existingPerms = permissions.get(user1);
            Permission newPerm = new Permission(user1, this, r, w, x);
            existingPerms.add(newPerm);
            permissions.replace(user1, existingPerms);
        } else {
            List<Permission> newPerms = new ArrayList<>();
            newPerms.add(new Permission(user1, this, r,w,x));
            permissions.put(user1, newPerms);
        }
    }

    public void addPermission(User user1, boolean r, boolean w, boolean x, LocalDate expirationDate) {
        if (permissions.containsKey(user1)) {
            List<Permission> existingPerms = permissions.get(user1);
            Permission newPerm = new Permission(user1, this, r, w, x, expirationDate);
            existingPerms.add(newPerm);
            permissions.replace(user1, existingPerms);
        } else {
            List<Permission> newPerms = new ArrayList<>();
            newPerms.add(new Permission(user1, this, r,w,x, expirationDate));
            permissions.put(user1, newPerms);
        }
    }
    public void print() {
        System.out.println("File: " + name + " (" + size + " octets)");
    }

    public void printPermissions() {
        System.out.println("Permissions for file: " + name);
        permissions.forEach((user1, perm) -> System.out.println(user1.getName() + " " + perm));
    }


}
