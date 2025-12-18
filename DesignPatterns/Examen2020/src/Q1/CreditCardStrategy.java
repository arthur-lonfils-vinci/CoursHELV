import java.util.List;

public class CreditCardStrategy implements PaymentStrategy {
    private String name;
    private String cardNumber;
    private String expiryDate;

    public CreditCardStrategy(String name, String cardNumber, String expiryDate) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    @Override
    public void pay(List<Item> items) {
        if (items.isEmpty()) throw new RuntimeException("Your shopping cart is empty");
        int amount = items.stream().mapToInt(Item::getPrice).sum();
        String encryptedCardNumber = cardNumber.replaceAll(".", "*");
        System.out.println(amount + " euros paid using CreditCard / name: " + name + ", card number: " + encryptedCardNumber + " (expiration date: " + expiryDate + ")");
        System.out.println("Thank you and goodbye");
        System.out.println("------------------");
    }
}
