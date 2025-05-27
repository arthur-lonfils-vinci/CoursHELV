import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import '../models/dish.dart';

const _apiUrl = "https://sebstreb.github.io/binv2110-examen-blanc-api/dishes";

class DishService {
  static const _menuKey = 'menu';

  // Future<List<Dish>> get dishes async => await fetchDishes(); // Basic Exam
  Future<List<Dish>> get dishes async => await loadMenu();

  // create shared preferences, adding the fetchDishes method
  // to fetch the dishes from the API and return a list of Dish objects
  Future<List<Dish>> loadMenu() async {
    final prefs = await SharedPreferences.getInstance();
    var menu = prefs.getStringList(_menuKey) ?? [];
    if (menu.isEmpty) {
      // If no menu is stored, fetch from API and save it
      var fetchedDishes = await fetchDishes();
      menu = fetchedDishes.map((dish) => 
        '${dish.name},${dish.price},${dish.category},${dish.imagePath}').toList();
      await prefs.setStringList(_menuKey, menu);
    }
    return List.generate(menu.length, (i) {
      var item = menu[i].split(',');
      return Dish(
        name: item[0],
        price: double.parse(item[1]),
        category: item[2],
        imagePath: item[3],
      );
    });
  }

  // Add a new dish into the menu (shared preferences)
  Future<void> addDish(Dish dish) async {
    final prefs = await SharedPreferences.getInstance();
    var menu = prefs.getStringList(_menuKey) ?? [];
    menu.add('${dish.name},${dish.price},${dish.category},${dish.imagePath}');
    await prefs.setStringList(_menuKey, menu);
  }

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
