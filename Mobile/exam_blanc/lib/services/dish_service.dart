import 'dart:convert';

import 'package:exam_blanc/models/dish.dart';
import 'package:http/http.dart' as http;

const baseUrl = "https://sebstreb.github.io/binv2110-examen-blanc-api/dishes";


class DishService {
  Future<List<Dish>> get diches async => await fetchDishes();

  Future<Dish> fetchDish(String name) async {
    var response = await http.get(Uri.parse("$baseUrl/$name"));

    if (response.statusCode != 200) {
      throw Exception("Error ${response.statusCode} fetching movie");
    }

    final jsonObj = jsonDecode(response.body);

    return Dish(
      id: jsonObj["id"],
      name: jsonObj["name"],
      price: jsonObj["price"].toDouble(),
      category: jsonObj["category"],
      imagePath: jsonObj["imagePath"],
    );
  }

  Future<List<Dish>> fetchDishes() async {
    var response = await http.get(Uri.parse(baseUrl));

    if (response.statusCode != 200) {
      throw Exception("Error ${response.statusCode} fetching movie");
    }

    final jsonObj = jsonDecode(response.body);

    return (jsonObj as List)
        .map((e) => Dish(
              id: e["id"],
              name: e["name"],
              price: e["price"].toDouble(),
              category: e["category"],
              imagePath: e["imagePath"],
            ))
        .toList();
  }
}