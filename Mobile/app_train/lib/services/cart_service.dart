import 'package:app_train/models/cart_item.dart';
import 'package:app_train/models/dish.dart';
import 'package:shared_preferences/shared_preferences.dart';


class CartService {
  static const _cartKey = 'cart';

  void saveCart(List<String> cart) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.remove(_cartKey);
    await prefs.setStringList(_cartKey, cart);
  }

  Future<List<CartItem>> loadCart() async {
    final prefs = await SharedPreferences.getInstance();
    var menu = prefs.getStringList(_cartKey) ?? [];
    return List.generate( menu.length, (i) {
      var item = menu[i].split(',');
      return CartItem(
        dish: Dish(
          name: item[0],
          price: double.parse(item[2]),
          category: item[3],
          imagePath: item[4],
        ),
        count: int.parse(item[1]),
      );
    }
    );
  }
}