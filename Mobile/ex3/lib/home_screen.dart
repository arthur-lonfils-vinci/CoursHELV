import 'package:ex3/notes.dart';
import 'package:ex3/notes_form.dart';
import 'package:ex3/notes_widget.dart';
import 'package:flutter/material.dart';

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  List<Notes> notes = [];

  @override
  void initState() {
    super.initState();
    // Add default notes only once when the widget initializes
    notes.addAll(defaultNotes);
  }

  void addNotes(Notes note) {
    setState(() {
      notes.add(note);
    });
  }

  void deleteNote(int index) {
    setState(() {
      notes.removeAt(index);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: <Widget>[
            Expanded(child: NotesWidget(notes, deleteNote)),
            const SizedBox(height: 16.0), // Add spacing between widgets
            NotesForm(addNotes),
          ],
        ),
      ),
    );
  }
}
