package domaine;

import java.util.Objects;

public class User {
    private String name;
    private int id;
    static int nexId = 0;

    public User(String name) {

        this.name = name;
        this.id = nexId++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void print() {
        System.out.println("User: " + name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}


