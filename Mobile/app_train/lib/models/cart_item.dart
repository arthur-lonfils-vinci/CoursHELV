import 'dish.dart';

class CartItem {
  final Dish dish;
  int count;

  CartItem({required this.dish, this.count = 0});
}
