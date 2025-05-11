import 'package:flutter/material.dart';

import '../services/theme_service.dart';

const COLORS = {
  "red": Colors.red,
  "purple": Colors.purple,
  "blue": Colors.blue,
  "green": Colors.green,
  "yellow": Colors.yellow,
};

class ThemeViewModel with ChangeNotifier {
  late ThemeService _themeService;

  String _mainColor = "red";

  String get mainColor => _mainColor;

  set mainColor(String color) {
    _mainColor = color;
    _themeService.setMainColor(color);
    notifyListeners();
  }

  MaterialColor get mainColorMaterial => COLORS[_mainColor] ?? Colors.red;

  void _init() async {
    var savedColor = await _themeService.getMainColor();
    _mainColor = savedColor != "" ? savedColor : "red";
    notifyListeners();
  }

  ThemeViewModel() {
    _themeService = ThemeService();
    _init();
  }

}