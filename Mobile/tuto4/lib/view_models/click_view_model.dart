import 'package:flutter/material.dart';

class ClickViewModel extends ChangeNotifier {
  var _clicks = 0;

  int get clicks => _clicks;

  void increment() {
    _clicks++;
    notifyListeners();
  }
}