import 'package:flutter/foundation.dart';

class TextDocument extends ChangeNotifier {
  String _fileName;
  String _content;

  TextDocument({String fileName = "fichier.txt", String content = ""})
    : _fileName = fileName,
      _content = content;

  String get fileName => _fileName;
  String get content => _content;

  set fileName(String value) {
    _fileName = value;
    notifyListeners();
  }

  set content(String value) {
    _content = value;
    notifyListeners();
  }
}
