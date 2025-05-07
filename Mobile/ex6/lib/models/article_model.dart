class Article {
  final int? id;
  final String name;
  final double price;
  int quantity;

  Article({this.id, required this.name, required this.price, this.quantity = 0});

  @override
  String toString() {
    return 'Article{id: $id, name: $name, content: $price, quantity: $quantity}';
  }
}
