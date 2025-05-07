import 'package:flutter/material.dart';

class SecondScreen extends StatelessWidget {
  final int nbClicks;

  const SecondScreen({super.key, this.nbClicks = 0});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Second screen"),
        backgroundColor: Theme.of(context).colorScheme.primaryContainer,
      ),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            const Text("Hello from second screen."),
            const SizedBox(height: 16),
            Text("There were $nbClicks clicks in first screen."),
          ],
        ),
      ),
    );
  }
}