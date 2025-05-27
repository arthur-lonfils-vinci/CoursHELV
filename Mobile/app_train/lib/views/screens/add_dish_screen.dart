import 'package:app_train/models/dish.dart';
import 'package:app_train/view_models/app_view_model.dart';
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:provider/provider.dart';

class AddDishScreen extends StatelessWidget {
  const AddDishScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final appViewModel = Provider.of<AppViewModel>(context);
    final categories = appViewModel.categories;

    Dish newDish = Dish(
      name: '',
      category: '',
      price: 0.0,
      imagePath: '',
    );

    return Scaffold(
      appBar: AppBar(
        title: const Text("Add Dish"),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Text(
              'Add Dish Screen',
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                // Logic to add a new dish will go here
                // For now, we will just navigate back to the home screen
                context.go('/');
              },
              child: const Text('Go Back to Menu'),
            ),
            const SizedBox(height: 20),
            // Placeholder for the form to add a new dish
            const Text(
              'Form to add a new dish will go here',
              style: TextStyle(fontSize: 18),
            ),
            const SizedBox(height: 20),
            // Displaying categories as a dropdown
            DropdownButton<String>(
              value: newDish.category.isEmpty ? null : newDish.category,
              hint: const Text('Select Category'),
              items: categories.map((String category) {
                return DropdownMenuItem<String>(
                  value: category,
                  child: Text(category),
                );
              }).toList(),
              onChanged: (String? newValue) {
                if (newValue != null) {
                  newDish = Dish(
                    name: newDish.name,
                    category: newValue,
                    price: newDish.price,
                    imagePath: newDish.imagePath,
                  );
                }
              },
            ),
            const SizedBox(height: 20),
            // Placeholder for the dish name input
            TextField(
              decoration: const InputDecoration(
                labelText: 'Dish Name',
                border: OutlineInputBorder(),
              ),
              onChanged: (String value) {
                newDish = Dish(
                  name: value,
                  category: newDish.category,
                  price: newDish.price,
                  imagePath: newDish.imagePath,
                );
              },
            ),
            const SizedBox(height: 20),
            // Placeholder for the dish price input
            TextField(
              decoration: const InputDecoration(
                labelText: 'Dish Price',
                border: OutlineInputBorder(),
              ),
              keyboardType: TextInputType.number,
              onChanged: (String value) {
                final double? price = double.tryParse(value);
                if (price != null) {
                  newDish = Dish(
                    name: newDish.name,
                    category: newDish.category,
                    price: price,
                    imagePath: newDish.imagePath,
                  );
                }
              },
            ),
            const SizedBox(height: 20),
            // Placeholder for the dish image input
            TextField(
              decoration: const InputDecoration(
                labelText: 'Dish Image Path',
                border: OutlineInputBorder(),
              ),
              onChanged: (String value) {
                newDish = Dish(
                  name: newDish.name,
                  category: newDish.category,
                  price: newDish.price,
                  imagePath: value,
                );
              },
            ),
            const SizedBox(height: 20),
            // Button to save the new dish
            ElevatedButton(
              onPressed: () {
                if (newDish.name.isNotEmpty &&
                    newDish.category.isNotEmpty &&
                    newDish.price > 0 &&
                    newDish.imagePath.isNotEmpty) {
                  appViewModel.addDish(newDish);
                  context.go('/');
                } else {
                  ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(
                      content: Text('Please fill all fields correctly.'),
                    ),
                  );
                }
              },
              child: const Text('Save Dish'),
            ),
          ],
        ),
      ),
    );
  }
}
