package Q1.strategy;

public class PaypalStrategy implements PaymentStrategy {

    private String email;
    private String password;

    public PaypalStrategy(String password, String email) {
        this.password = password;
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        String encryptedPassword = password.replaceAll(".", "*");
        System.out.println(amount + " euros paid using Paypal / email: "+ email + ", password: " + encryptedPassword);
    }
}
