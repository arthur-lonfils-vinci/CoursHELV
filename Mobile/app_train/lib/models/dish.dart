import 'package:flutter/material.dart';

class Dish {
  final String id = UniqueKey().toString();
  final String name;
  final double price;
  final String category;
  final String imagePath;

  Dish({
    required this.name,
    required this.price,
    required this.category,
    required this.imagePath,
  });
}
