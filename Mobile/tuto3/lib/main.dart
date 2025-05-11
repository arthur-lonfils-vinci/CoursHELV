import 'package:flutter/material.dart';
import 'home_screen.dart';

const colors = {
  "red": Colors.red,
  "pink": Colors.pink,
  "purple": Colors.purple,
  "deep purple": Colors.deepPurple,
  "indigo": Colors.indigo,
  "blue": Colors.blue,
  "light blue": Colors.lightBlue,
  "cyan": Colors.cyan,
  "teal": Colors.teal,
  "green": Colors.green,
  "light green": Colors.lightGreen,
  "lime": Colors.lime,
  "yellow": Colors.yellow,
  "amber": Colors.amber,
  "orange": Colors.orange,
  "deep orange": Colors.deepOrange,
  "brown": Colors.brown,
  "blue grey": Colors.blueGrey,
  "grey": Colors.grey,
};

MaterialColor getColorValue(String color) => colors[color] ?? Colors.grey;

void main() => runApp(const MyApp());

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  var color = "red";

  void setColor(String value) => setState(() => color = value);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        useMaterial3: true,
        colorScheme: ColorScheme.fromSeed(seedColor: getColorValue(color)),
      ),
      home: HomeScreen(color: color, setColor: setColor),
    );
  }
}
