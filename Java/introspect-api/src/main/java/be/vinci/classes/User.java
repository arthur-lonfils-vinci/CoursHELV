package be.vinci.classes;

import java.util.ArrayList;
import java.util.List;

public class User {

    public static int userIdSequence = 1;

    public int id;
    protected String firstName;
    protected String lastName;
    private String visa;
    private List<Order> orders = new ArrayList<Order>();

    public User(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public boolean addOrder(Order order) {
        return this.orders.add(order);
    }

    // Mock method, for testing purpose only
    private boolean block() {
        return true;
    }

    // Mock method, for testing purpose only
    protected void unblock() { }

    // Mock method, for testing purpose only
    public static boolean checkUser(User u, String test) {
        return true;
    }

}
