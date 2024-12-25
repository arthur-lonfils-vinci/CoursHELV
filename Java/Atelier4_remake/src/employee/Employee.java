package employee;

public class Employee {

    private int id;
    private String lastname;
    private String firstname;
    private String email;
    private boolean fullTime;

    public Employee(String line) {
        String[] attrs = line.split(";");
        id = Integer.parseInt(attrs[0]);
        lastname = attrs[1];
        firstname = attrs[2];
        email = attrs[3];
        fullTime = attrs[4].equals("TP");
    }

    public int getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    @Override
    public String toString() {
        return "Employee#" + id +
                ": " + lastname +
                " " + firstname +
                " (" + email + ")" +
                " [" + (fullTime?"PT":"MT") + "]" ;
    }
}
