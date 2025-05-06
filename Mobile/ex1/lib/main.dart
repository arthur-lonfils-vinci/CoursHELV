import 'package:ex1/laureat_widget.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
      ),
      home: const MyHomePage(title: 'Flutter Exercise 1'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text(
              'Laureats',
              style: TextStyle(
                fontSize: 40,
                fontWeight: FontWeight.bold,
              ),
            ),
            const SizedBox(height: 20),
            ...laureats.map((laureat) => LaureatWidget(
              id: laureat["id"] ?? '',
              firstname: laureat["firstname"] ?? '',
              surname: laureat["surname"] ?? '',
              motivation: laureat["motivation"] ?? '',
              share: laureat["share"] ?? '',
            )).toList(),
          ],
        ),
      ),
    );
  }
}


const laureats = [
  {
    "id" : "1",
    "firstname" : "Albert",
    "surname" : "Einstein",
    "motivation" : "for his services to Theoretical Physics, and especially for his discovery of the law of the photoelectric effect",
    "share" : "2"
  },
  {
    "id" : "2",
    "firstname" : "Marie",
    "surname" : "Curie",
    "motivation" : "in recognition of her services to the advancement of chemistry by the discovery of the elements radium and polonium, by the isolation of radium and the study of the nature and compounds of this remarkable element",
    "share" : "2"
  },
  {
    "id" : "3",
    "firstname" : "Jean-Pierre",
    "surname" : "Serre",
    "motivation" : "",
    "share" : ""
  }
];


