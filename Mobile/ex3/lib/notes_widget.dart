import 'package:ex3/notes.dart';
import 'package:flutter/material.dart';

class NotesWidget extends StatelessWidget {
  final List<Notes> notes;
  final Function(int) onDelete;

  const NotesWidget(this.notes, this.onDelete, {super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(16.0),
      height: MediaQuery.of(context).size.height * 0.7,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text('Notes', style: Theme.of(context).textTheme.headlineLarge),
          const SizedBox(height: 8.0),
          Expanded(
            child:
                notes.isEmpty
                    ? const Center(child: Text('No notes yet. Add one below!'))
                    : ListView.builder(
                      itemCount: notes.length,
                      itemBuilder: (context, index) {
                        return Card(
                          child: ListTile(
                            title: Text(notes[index].title),
                            subtitle: Text(notes[index].content),
                            trailing: IconButton(
                              icon: const Icon(Icons.delete),
                              onPressed: () => onDelete(index),
                            ),
                          ),
                        );
                      },
                    ),
                  
          ),
        ],
      ),
    );
  }
}
