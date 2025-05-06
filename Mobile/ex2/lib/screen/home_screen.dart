import 'package:ex2/Class/propriete.dart';
import 'package:ex2/Widget/property_widget.dart';
import 'package:flutter/material.dart';

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  var isLocation = true; // if false only show Vente

  void _toggleMode() => setState(() => isLocation = !isLocation);

  @override
  Widget build(BuildContext context) {
    // Filter the properties based on the selected mode
    final displayedProprietes =
        defaultProprietes
            .where(
              (propriete) =>
                  isLocation
                      ? propriete.state == "Location"
                      : propriete.state == "Vente",
            )
            .toList();

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          children: [
            SizedBox(
              width: 512.0,
              child: Column(
                children: [
                  Switch(
                    value: isLocation,
                    activeColor: Colors.red,
                    inactiveThumbColor: Colors.blue,
                    onChanged: (bool value) {
                      setState(() {
                        isLocation = value;
                      });
                    },
                  ),
                  IconButton(
                    icon: Icon(isLocation ? Icons.home : Icons.location_city),
                    onPressed: _toggleMode,
                  ),
                ],
              ),
            ),
            Expanded(
              child: ListView.builder(
                itemCount: displayedProprietes.length,
                itemBuilder:
                    (context, index) =>
                        PropertyWidget(propriete: displayedProprietes[index]),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
