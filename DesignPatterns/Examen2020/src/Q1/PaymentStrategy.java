import java.util.List;

public interface PaymentStrategy {
    void pay(List<Item> items);
}
