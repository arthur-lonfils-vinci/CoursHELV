package domaine;

import java.util.ArrayList;
import java.util.List;

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
     *  Check if the user has all the given permissions on this file
     * @param user
     * @param permissionType
     * @return true if user has permissions, false otherwise
     */
    public boolean hasPermission(User user, Permission.PermissionType permissionType) {

        if (user.equals(owner)) {
            return true;
        }

        boolean hasRead = false;
        boolean hasWrite = false;
        boolean hasExecute = false;
        for (Permission permission : permissions) {

            if (permission.getUser().equals(user) && permission.getFile().equals(this) ) {
                hasRead = hasRead || permission.isRead();
                hasWrite = hasWrite || permission.isWrite();
                hasExecute = hasExecute || permission.isExecute();
            }
        }

        return ( hasRead || !permissionType.isRead() ) && ( hasWrite || !permissionType.isWrite() ) && ( hasExecute || !permissionType.isExecute() );

    }



    /**
     * Revoke all permissions for the given user on this file
     * @param user
     * @return true if the user had any permissions on this file, false otherwise
     */
    public boolean revokeAllPermissions(User user) {
        boolean revoked = false;
        for (Permission permission : permissions) {
            if (permission.getUser().equals(user)) {
                permissions.remove(permission);
                revoked = true;
            }
        }
        return revoked;
    }

    public void print() {
        System.out.println("File: " + name + " (" + size + " octets)");
    }

    public void addPermission(User user1, Permission.PermissionType permissionType) {
        permissions.add(new Permission(user1, this, permissionType));
    }
}
