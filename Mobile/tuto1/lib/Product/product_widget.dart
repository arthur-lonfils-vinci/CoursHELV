import 'package:flutter/material.dart';

class ProductWidget extends StatelessWidget {
  final String name;
  final String description;
  final int price;
  final String imagePath;

  const ProductWidget({
    super.key,
    required this.name,
    required this.description,
    required this.price,
    required this.imagePath,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: SizedBox(
        width: 500,
        child: Row(
          children: [
            Image.asset(
              imagePath,
              width: 100,
              height: 100,
            ),
            const SizedBox(width: 10),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    name,
                    style: const TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                  ),
                  const SizedBox(height: 5),
                  Text(description),
                  const SizedBox(height: 5),
                  Text('\$${price.toString()}'),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
