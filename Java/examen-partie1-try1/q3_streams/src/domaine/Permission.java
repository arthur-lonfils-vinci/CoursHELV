package domaine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Permission {
    private User user;
    private File file;
    private boolean read;
    private boolean write;
    private boolean execute;
    private LocalDate expirationDate;

    public Permission(User user, File file, boolean read, boolean write, boolean execute, LocalDate expirationDate) {
        this.user = user;
        this.file = file;
        this.read = read;
        this.write = write;
        this.execute = execute;
        this.expirationDate = expirationDate;
    }

    public Permission(User user, File file, boolean read, boolean write, boolean execute) {
        this.user = user;
        this.file = file;
        this.read = read;
        this.write = write;
        this.execute = execute;
        this.expirationDate = LocalDateTime.now().plusDays(30).toLocalDate();
    }

    public User getUser() {
        return user;
    }

    public File getFile() {
        return file;
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

    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void print() {
        System.out.println("Permission: " + user.getName() + " " + file.getName() + " " + read + " " + write + " " + execute + " " + expirationDate);
    }

}
