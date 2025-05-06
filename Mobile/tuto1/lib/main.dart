import 'package:flutter/material.dart';
import 'package:tuto1/Product/product_widget.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Welcome to Flutter',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: const Text('Welcome to Flutter'),
        ),
        body: Center(
          child: Column(
            children: [
              ProductWidget(
                name: "iPhone 15 Pro Max",
                price: 1479,
                description:
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                    "Aliquam et risus vel ipsum faucibus ultrices. "
                    "Fusce nec leo nisi. Vestibulum vehicula, "
                    "orci ac varius eleifend, ante erat efficitur tortor, "
                    "quis tincidunt elit ex ut est.",
                imagePath: "images/iphone.jpg",
              ),
              ProductWidget(
                name: "GeForce RTX 4080 SUPER",
                price: 1110,
                description:
                    "Des jeux ultra-réalistes, ultra-fluides et ultra-immersifs, "
                    "la carte graphique GeForce RTX 4080 SUPER met à votre "
                    "disposition les technologies les plus avancées pour "
                    "vous permettre de jouer dans les meilleures conditions, "
                    "en très haute résolution ou en Réalité Virtuelle.",
                imagePath: "images/cg.jpg",
              ),
            ],
          ),
        ),
      ),
    );
  }
}
