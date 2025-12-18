import java.util.List;

public class PaypalStrategy implements PaymentStrategy {
    private String email;
    private String password;

    public PaypalStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }


    @Override
    public void pay(List<Item> items) {
        if (items.isEmpty()) throw new RuntimeException("Your shopping cart is empty");
        int amount = items.stream().mapToInt(Item::getPrice).sum();
        String encryptedPassword = password.replaceAll(".", "*");
        System.out.println(amount + " euros paid using Paypal / email: " + email + ", password: " + encryptedPassword);
        System.out.println("Thank you and goodbye");
        System.out.println("------------------");
    }
}
