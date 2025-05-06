import 'package:flutter/material.dart';
import './main.dart';

class MySquare extends StatelessWidget {
  final String color;

  const MySquare({super.key, required this.color});

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: Center(
        child: Container(
          width: 100,
          height: 100,
          color: getColorValue(color),
        ),
      ),
    );
  }
}