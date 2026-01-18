package Q1;

import Q1.strategy.CreditCardStrategy;
import Q1.strategy.PaypalStrategy;

public class ShoppingCartTest {

	public static void main(String[] args) {
		ShoppingCart cart = new ShoppingCart();
		
		Item item1 = new Item("1234",10);
		Item item2 = new Item("5678",40);
		
		cart.addItem(item1);
		cart.addItem(item2);
		
		//pay by paypal
		cart.pay(new PaypalStrategy("christophe.damas@vinci.be", "azerty123"));
		
		ShoppingCart cart2 = new ShoppingCart();
		
		cart2.addItem(item1);
		cart2.addItem(item1);
		//pay by credit card
		cart2.pay(new CreditCardStrategy("Christophe Damas", "1234567890123456", "12/22"));
	}

}