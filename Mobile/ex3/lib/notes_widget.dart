import 'package:flutter/material.dart';

class NotesWidget extends StatelessWidget {
  const NotesWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(16.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            'Notes',
            style: Theme.of(context).textTheme.headlineLarge,
          ),
          const SizedBox(height: 8.0),
          Expanded(
            child: (
              return ListTile(
                title: const Text('Note 1'),
                subtitle: const Text('Contenu de la note 1'),
                trailing: IconButton(
                  icon: const Icon(Icons.delete),
                  onPressed: () {
                    // Action de suppression de la note
                  },
                ),
              )
            )
          ),
        ],
      ),
    );
  }
}