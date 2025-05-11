import 'package:flutter/material.dart';

class MySwitch extends StatelessWidget {
  final String color;
  final void Function(String) setColor;

  const MySwitch({
    super.key,
    required this.color,
    required this.setColor,
  });

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        const Text(
          "red",
          style: TextStyle(color: Colors.red, fontWeight: FontWeight.bold),
        ),
        Switch(
          value: color == "green",
          activeColor: Colors.green,
          inactiveThumbColor: Colors.red,
          inactiveTrackColor: Colors.red.withOpacity(0.4),
          onChanged: (value) => setColor(value ? "green" : "red"),
        ),
        const Text(
          "green",
          style: TextStyle(color: Colors.green, fontWeight: FontWeight.bold),
        ),
      ],
    );
  }
}