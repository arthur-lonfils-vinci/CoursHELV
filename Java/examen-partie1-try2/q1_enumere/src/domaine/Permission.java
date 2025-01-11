package domaine;

public class Permission {
    private User user;
    private File file;
    private boolean read;
    private boolean write;
    private boolean execute;
    private PermissionType type;

    public Permission(User user, File file, PermissionType type) {
        this.user = user;
        this.file = file;
        this.type = type;
        this.read = type.isRead();
        this.write = type.isWrite();
        this.execute = type.isExecute();
    }

    public User getUser() {
        return user;
    }

    public File getFile() {
        return file;
    }

    public PermissionType getType() {
        return type;
    }

    public boolean isRead() {
        return read;
    }

    public boolean isWrite() {
        return write;
    }

    public boolean isExecute() {
        return execute;
    }


    public void print() {
        System.out.println("Permission: " + user.getName() + " " + file.getName() + " " + read + " " + write + " " + execute);
    }


    public enum PermissionType {
        RWX(true, true,true), RW_(true, true, false), R__(true, false, false),
        R_X (true, false, true), _WX(false, true, true), _W_(false, true, false),
        __X(false, false, true);

        private boolean read;
        private boolean write;
        private boolean execute;

        PermissionType(boolean read, boolean write, boolean execute ) {
            this.read = read;
            this.write = write;
            this.execute = execute;
        }

        public boolean isRead() {
            return read;
        }

        public boolean isWrite() {
            return write;
        }

        public boolean isExecute() {
            return execute;
        }
    }

}
