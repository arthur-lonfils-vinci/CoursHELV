import 'package:exam_blanc/models/cart_item.dart';
import 'package:exam_blanc/models/dish.dart';
import 'package:exam_blanc/services/cart_service.dart';
import 'package:exam_blanc/services/dish_service.dart';
import 'package:flutter/foundation.dart';

class AppViewModel extends ChangeNotifier {
  DishService dishService;
  CartService cartService;
  List<CartItem> _cart = [];
  List<Dish> _menu = [];

  AppViewModel(this.dishService, this.cartService) {
    dishService.fetchDishes().then((value) {
      _menu = value;
      notifyListeners();
    });
    cartService.getCart(_menu).then((value) {
      _cart = value;
      notifyListeners();
    });
  }

  List<Dish> get menu => _menu;

  Future<List<Dish>> getDishes() async {
    _menu = await dishService.fetchDishes();
    return List.generate(_menu.length, (i) {
      return Dish(
        id: _menu[i].id,
        name: _menu[i].name,
        price: _menu[i].price,
        category: _menu[i].category,
        imagePath: _menu[i].imagePath,
      );
    });
  }

  List<CartItem> get cart => _cart;

  void addToCart(Dish dish) {
    cartService.addToCart(dish);
    getCart();
    notifyListeners();
  }

  void removeFromCart(Dish dish, bool? allRemove) {
    cartService.removeFromCart(dish.id, allRemove);
    getCart();
    notifyListeners();
  }

  List<CartItem> getCart() {
    return _cart;
  }

  void saveCart() {
    cartService.saveCart(_cart);
  }

  void restoreCart() {
    cartService.getCartFromDB(_menu).then((value) {
      _cart = value;
      notifyListeners();
    });
  }

  void clearCart() {
    cartService.clearCart();
    _cart.clear();
    notifyListeners();
  }
}
