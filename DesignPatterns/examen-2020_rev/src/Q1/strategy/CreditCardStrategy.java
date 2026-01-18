package Q1.strategy;

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
    public void pay(int amount) {
        String encryptedCardNumber = cardNumber.replace(".", "*");
        System.out.println(amount + " euros paid using CreditCard / name: " + name + ", car number: " + encryptedCardNumber + ", expiry date: " + expiryDate);
    }
}
