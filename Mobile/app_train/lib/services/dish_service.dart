import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/dish.dart';

const _apiUrl = "https://sebstreb.github.io/binv2110-examen-blanc-api/dishes";

class DishService {
  Future<List<Dish>> get dishes async => await fetchDishes();

  Future<List<Dish>> fetchDishes() async {
    var response = await http.get(Uri.parse(_apiUrl));

    if (response.statusCode != 200) {
      throw Exception("Error ${response.statusCode} fetching dishes");
    }

    final jsonObj = jsonDecode(response.body);

    return (jsonObj as List)
        .map(
          (dish) => Dish(
            name: dish['name'],
            price: dish['price'].toDouble(),
            category: dish['category'],
            imagePath: dish['imagePath'],
          ),
        )
        .toList();
  }
}
