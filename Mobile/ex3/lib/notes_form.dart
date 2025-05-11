import 'package:ex3/notes.dart';
import 'package:flutter/material.dart';

class NotesForm extends StatefulWidget {
  final void Function(Notes) addNotes;

  const NotesForm(this.addNotes, {super.key});

  @override
  State<NotesForm> createState() => _MyFormState();
}

class _MyFormState extends State<NotesForm> {
  final titleController = TextEditingController();
  final contentController = TextEditingController();
  final formKey = GlobalKey<FormState>();

  @override
  void dispose() {
    titleController.dispose();
    contentController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Form(
      key: formKey,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.end,
        children: [
          TextFormField(
            controller: titleController,
            decoration: const InputDecoration(labelText: "Enter title"),
            validator: (value) =>
                (value == null || value.isEmpty)
                    ? "Title can't be empty"
                    : null,
          ),
          const SizedBox(height: 16.0),
          TextFormField(
            controller: contentController,
            decoration: const InputDecoration(labelText: "Enter content"),
            validator: (value) =>
                (value == null || value.isEmpty)
                    ? "Content can't be empty"
                    : null,
            maxLines: 3,
          ),
          ElevatedButton(
            child: const Text("Add note"),
            onPressed: () {
              if (formKey.currentState!.validate()) {
                widget.addNotes(Notes(
                  title: titleController.text,
                  content: contentController.text,
                ));
                titleController.clear();
                contentController.clear();
              }
            },
          ),
        ],
      ),
    );
  }
}
