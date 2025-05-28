import 'dart:convert';

import 'package:exam/models/mural.dart';
import 'package:http/http.dart' as http;

const _apiUrl = "https://opendata.brussels.be/api/explore/v2.1/catalog/datasets/bruxelles_parcours_bd/records?limit=70";

class MuralService {
  Future<List<Mural>> get murals async => await fetchMurals();

  Future<List<Mural>> fetchMurals() async {
    final response = await http.get(Uri.parse(_apiUrl));

    if (response.statusCode == 200) {
      final jsonObj = jsonDecode(response.body);
      final murals = (jsonObj['results'] as List).map((item) {
        return Mural(
          name: item['nom_de_la_fresque'] ?? '',
          cartoonist: item['dessinateur'] ?? '',
          address: item['adresse'] ?? '',
          surface: (item['surface_m2'] as num?)?.toDouble() ?? 0.0,
          imagePath: item['image'] ?? '',
        );
      }).toList();
      return murals;
    } else {
      throw Exception('Failed to load murals');
    }
  }
}
