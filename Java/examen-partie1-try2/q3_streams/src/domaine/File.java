package domaine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class File {
    private String name;
    private int size;
    private User owner;

    private List<Permission> permissions = new ArrayList<Permission>();

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



    public List<Permission> getPermissions() {
        return permissions;
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
        for (Permission permission : permissions) {

            if (permission.getUser().equals(user) && permission.getFile().equals(this) && !permission.isExpired()) {
                hasRead = hasRead || permission.isRead();
                hasWrite = hasWrite || permission.isWrite();
                hasExecute = hasExecute || permission.isExecute();
            }
        }
        return ( hasRead || !read ) && ( hasWrite || !write ) && ( hasExecute || !execute );

    }



    /**
     *  Annule toutes les permissions pour l'utilisateur
     * @param user
     * @return true si l'utilisateur avait des permissions, false sinon.
     */
    public boolean revokeAllPermissions(User user) {
        boolean revoked = false;
        Iterator<Permission> iterator = permissions.iterator();
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
     *  Renvoie la  liste des permissions expirées triées par date croissante d'expiration
     * @return
     */
    public List<Permission> expiredPermissionsSortedByExpirationDate() {

        return permissions
                .stream()
                .filter(Permission::isExpired)
                .sorted()
                .collect(Collectors.toList()); // TODO compléter
    }

    /**
     * REnvoie une map de tous les utilisateurs et leurs permissions sur ce fichier
     * a map of all users and their permissions on this file
     * @return
     */
    public Map<User, List<Permission>> userPermissionsMap() {

        return permissions.stream()
                .collect(Collectors
                        .groupingBy(Permission::getUser, Collectors.toList())
                ); // TODO compléter
    }



    /**
     * Prend toutes les permissions non expirées d'un utilisateur sur ce fichier, et les groupe en une seule permission
     * C'est équivalent à faire un OU logique sur toutes les permissions
     * La date d'expiration de la permission groupée est la date du jour (now)
     * Par exemple si on a les 3 permissions suivantes:
     * R__
     * _W_
     * R__
     * Le résultat sera RW_
     * @param user
     * @return
     */
    public Permission groupedNonExpiredUserPermissions(User user){
        return permissions.stream()
                .filter(e -> e.getUser().equals(user))
                .filter(e -> !e.isExpired())
                .reduce(new Permission(user, this, false, false, false, LocalDate.now()),
                        (p1, p2) -> new Permission(
                                user,
                                this,
                                p1.isRead() || p2.isRead(),
                                p1.isWrite() || p2.isWrite(),
                                p1.isExecute() || p2.isExecute(),
                                LocalDate.now()
                        )
                );
    }



    public void addPermission(User user1, boolean r, boolean w, boolean x) {
        permissions.add(new Permission(user1, this, r, w, x));
    }

    public void addPermission(User user1, boolean r, boolean w, boolean x, LocalDate expirationDate) {
        permissions.add(new Permission(user1, this, r, w, x, expirationDate));
    }

    public void print() {
        System.out.println("File: " + name + " (" + size + " octets)");
    }

    public void printPermissions() {
        System.out.println("Permissions for file: " + name);
        for (Permission permission : permissions) {
            permission.print();
        }
    }


}
