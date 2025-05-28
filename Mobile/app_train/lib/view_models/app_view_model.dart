import 'package:app_train/models/cart_item.dart';
import 'package:app_train/models/dish.dart';
import 'package:app_train/services/cart_service.dart';
import 'package:app_train/services/dish_service.dart';
import 'package:flutter/material.dart';

class AppViewModel extends ChangeNotifier {
  final DishService _dishService;
  final CartService _cartService;
  List<CartItem> _cart = [];
  List<Dish> _menu = [];

  AppViewModel(this._dishService, this._cartService) {
    _loadCart();
    _loadMenu();
  }

  List<CartItem> get cart => _cart;
  List<Dish> get menu => _menu;
  List<String> get categories => _menu.map((dish) => dish.category).toSet().toList();
  int get totalItems => _cart.fold(0, (sum, item) => sum + item.count);
  // max 2 decimal places for total price
  String get totalPrice => totalPriceBrut.toStringAsFixed(2);
  double get totalPriceBrut => _cart.fold(0, (sum, item) => sum + (item.dish.price * item.count));

  void _loadCart() async {
    _cart = await _cartService.loadCart();
    notifyListeners();
  }

  void _loadMenu() async {
    _menu = await _dishService.dishes;
    notifyListeners();
  }

  Future<void> addDish(Dish dish) async {
    await _dishService.addDish(dish);
    _loadMenu();
  }

  void addToCart(Dish dish) {
    final index = _cart.indexWhere((item) => item.dish.id == dish.id);

    if (index >= 0) {
      _cart[index].count++;
    } else {
      _cart.add(CartItem(dish: dish, count: 1));
    }

    notifyListeners();
  }

  void removeFromCart(Dish dish) {
    final index = _cart.indexWhere((item) => item.dish.id == dish.id);

    if (index >= 0) {
      _cart[index].count--;
      if (_cart[index].count <= 0) {
        _cart.removeAt(index);
      }
    }

    notifyListeners();
  }

  void removeAllFromCart() {
    _cart.clear();
    notifyListeners();
  }

  void saveCart() {
    _cartService.saveCart(
      _cart
          .map(
            (item) =>
                '${item.dish.name},${item.count},${item.dish.price},${item.dish.category},${item.dish.imagePath}',
          )
          .toList(),
    );
  }

  void restoreCart() {
    _loadCart();
    notifyListeners();
  }
}
