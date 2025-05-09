import 'dish.dart';

class CartItem {
  int? id;
  final Dish dish;
  int count;

  CartItem({this.id, required this.dish, this.count = 0});
}
