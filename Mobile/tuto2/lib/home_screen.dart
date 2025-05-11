import 'package:flutter/material.dart';
import 'package:tuto2/contact.dart';
import 'package:tuto2/contact_row.dart';

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  var showAllContacts = true; // if false only show favorites

  void _toggleMode() => setState(() => showAllContacts = !showAllContacts);

  @override
  Widget build(BuildContext context) {
    final displayedContacts = [
      for (final contact in defaultContacts)
        if (showAllContacts || contact.isFavorite) contact,
    ];

    return Scaffold(
      appBar: AppBar(
        title: const Text("Contact list"),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        actions: [
          IconButton(
            icon: Icon(showAllContacts ? Icons.star_border : Icons.star),
            onPressed: _toggleMode,
          ),
        ],
      ),
      body: Container(
        padding: const EdgeInsets.all(16.0),
        child: Center(
          child: SizedBox(
            width: 512.0,
            child: ListView.builder(
              itemCount: displayedContacts.length,
              itemBuilder:
                  (context, index) =>
                      ContactRow(contact: displayedContacts[index]),
            ),
          ),
        ),
      ),
    );
  }
}
