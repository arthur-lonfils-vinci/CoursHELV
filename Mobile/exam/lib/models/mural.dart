import 'package:flutter/material.dart';

class Mural {
  final String id = UniqueKey().toString();
  final String name;
  final String cartoonist;
  final String address;
  final double surface;
  final String imagePath;

  Mural({
    required this.name,
    required this.cartoonist,
    required this.address,
    required this.surface,
    required this.imagePath,
  });

}

/*
final testMural1 = Mural(
  name: "Cori le Moussaillon",
  cartoonist: "Bob de Moor",
  address: "21 rue Haute 1000 Bruxelles",
  surface: 100.0,
  imagePath:
  "https://www.parcoursbd.brussels/wp-content/uploads/2021/09/11_cori_mousaillon_rue_fabriques_04_vb-685x1024.jpg",
);

final testMural2 = Mural(
  name: "La Vache",
  cartoonist: "Johan De Moor",
  address: "Rue du Damier 25 1000 Bruxelles",
  surface: 200.0,
  imagePath:
  "https://www.parcoursbd.brussels/wp-content/uploads/2021/09/39_vache_rue_damier_09_vb-768x514.jpg",
);

final testMural3 = Mural(
  name: "Asterix le Gauloir",
  cartoonist: "Goscinny",
  address: "Rue plate 25 1050 Bruxelles",
  surface: 300.0,
  imagePath:
  "https://www.parcoursbd.brussels/wp-content/uploads/2021/09/15_asterix_obelix_rue_buanderie_06-768x576.jpg",
);

final testMurals = [
  testMural1,
  testMural2,
  testMural3,
];
*/