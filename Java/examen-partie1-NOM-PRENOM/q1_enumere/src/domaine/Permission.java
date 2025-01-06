package domaine;

public class Permission {
    private User user;
    private File file;
    private PermissionType permissionType;

    public Permission(User user, File file, PermissionType permissionType) {
        this.user = user;
        this.file = file;
        this.permissionType = permissionType;
    }

    public User getUser() {
        return user;
    }

    public File getFile() {
        return file;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public boolean isRead() {
        return permissionType.isRead();
    }

    public boolean isWrite() {
        return permissionType.isWrite();
    }

    public boolean isExecute() {
        return permissionType.isExecute();
    }

    public void print() {
        System.out.println("Permission: " + user.getName() + " " + file.getName() + " " + isRead() + " " + isWrite() + " " + isExecute());
    }

    public enum PermissionType {
        R__, _W_, __X,
        RW_, R_X, _WX,
        RWX;

        public boolean isRead() {
            switch (this) {
                case R__: return true;
                case R_X: return true;
                case RW_: return true;
                case RWX: return true;
                default: return false;
            }
        }

        public boolean isWrite() {
            switch (this) {
                case _W_: return true;
                case RW_: return true;
                case RWX: return true;
                case _WX: return true;
                default: return false;
            }
        }

        public boolean isExecute() {
            switch (this) {
                case RWX : return true;
                case R_X : return true;
                case __X: return true;
                case _WX: return true;
                default: return false;
            }
        }
    }


}
