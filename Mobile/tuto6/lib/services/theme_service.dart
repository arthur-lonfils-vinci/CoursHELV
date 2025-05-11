import'package:shared_preferences/shared_preferences.dart';

class ThemeService {
  Future<void> setMainColor(String value) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString("main_color", value);
  }

  Future<String> getMainColor() async {
    final prefs = await SharedPreferences.getInstance();
    return prefs.getString("main_color") ?? "red";
  }
}