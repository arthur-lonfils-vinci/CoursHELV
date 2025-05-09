import 'package:flutter/material.dart';

import '../../models/dish.dart';
import 'dish_widget.dart';

class MenuWidget extends StatelessWidget {
  final List<Dish> menu;

  const MenuWidget({super.key, required this.menu});

  @override
  Widget build(BuildContext context) {
    final starters = menu.where((dish) => dish.category == "starters");
    final main = menu.where((element) => element.category == "main");
    final desserts = menu.where((element) => element.category == "desserts");
    final drinks = menu.where((element) => element.category == "drinks");

    return SizedBox(
      width: 512,
      child: ListView(
        children: [
          const SizedBox(height: 32),
          const Center(
            child: Text(
              "Starters",
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
            ),
          ),
          for (final dish in starters) DishWidget(dish: dish),
          const SizedBox(height: 32),
          const Center(
            child: Text(
              "Main",
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
            ),
          ),
          for (final dish in main) DishWidget(dish: dish),
          const SizedBox(height: 32),
          const Center(
            child: Text(
              "Desserts",
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
            ),
          ),
          for (final dish in desserts) DishWidget(dish: dish),
          const SizedBox(height: 32),
          const Center(
            child: Text(
              "Drinks",
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
            ),
          ),
          for (final dish in drinks) DishWidget(dish: dish),
          const SizedBox(height: 32),
        ],
      ),
    );
  }
}
