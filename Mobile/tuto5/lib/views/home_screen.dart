import 'package:flutter/material.dart';

import '../models/film.dart';
import 'film_row.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: const Text("Tutoriel 5"),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: FutureBuilder(
          future: Future.delayed(
            const Duration(seconds: 1),
            () => Film.fetchFilms(),
          ),
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              // Completed with a value
              final films = snapshot.data!;
              return ListView.separated(
                itemCount: films.length,
                itemBuilder: (context, index) => FilmRow(film: films[index]),
                separatorBuilder: (context, index) => const Divider(),
              );
            }

            if (snapshot.hasError) {
              // Completed with an error
              return Center(child: Text("${snapshot.error}"));
            }

            // Uncompleted
            return const Center(child: CircularProgressIndicator());
          },
        ),
      ),
    );
  }
}
