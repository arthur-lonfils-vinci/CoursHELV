
import 'package:flutter/material.dart';

class AddDishScreen extends StatelessWidget {
  const AddDishScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Add Dish'),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back),
          onPressed: () => provider.go('/'),
        ),
      ),
      body: Center(
        child: Text('Add Dish Screen'),
      ),
    );
  }

}