import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;

class Film {
  static const baseUrl = "https://sebstreb.github.io/flutter-fiche-5/ghibli-films";

  final String id;
  final String title;
  final String originalTitle;
  final String originalTitleRomanised;
  final String image;
  final String movieBanner;
  final String description;
  final String director;
  final String producer;
  final String releaseDate;
  final int runningTime;
  final String rtScore;
  final List<String> people;
  final List<String> species;
  final List<String> locations;
  final List<String> vehicles;
  final String url;

  const Film({
    required this.id,
    required this.title,
    required this.originalTitle,
    required this.originalTitleRomanised,
    required this.image,
    required this.movieBanner,
    required this.description,
    required this.director,
    required this.producer,
    required this.releaseDate,
    required this.runningTime,
    required this.rtScore,
    required this.people,
    required this.species,
    required this.locations,
    required this.vehicles,
    required this.url,
  });

  @override
  String toString() =>
      'Film: $title, directed by $director, $runningTime min';

  static Future<Film> fetchFilm(String id) async {
    var response = await http.get(Uri.parse("$baseUrl/$id"));

    if (response.statusCode != 200) {
      throw Exception("Error ${response.statusCode} fetching movie");
    }

    final jsonObj = jsonDecode(response.body);

    return Film(
      id: jsonObj["id"],
      title: jsonObj["title"],
      originalTitle: jsonObj["original_title"],
      originalTitleRomanised: jsonObj["original_title_romanised"],
      image: jsonObj["image"],
      movieBanner: jsonObj["movie_banner"],
      description: jsonObj["description"],
      director: jsonObj["director"],
      producer: jsonObj["producer"],
      releaseDate: jsonObj["release_date"],
      runningTime: int.parse(jsonObj["running_time"]),
      rtScore: jsonObj["rt_score"],
      people: List<String>.from(jsonObj["people"]),
      species: List<String>.from(jsonObj["species"]),
      locations: List<String>.from(jsonObj["locations"]),
      vehicles: List<String>.from(jsonObj["vehicles"]),
      url: jsonObj["url"],
    );
  }

  static Future<List<Film>> fetchFilms() async {
    var response = await http.get(Uri.parse("$baseUrl/"));

    if (response.statusCode != 200) {
      throw Exception("Error ${response.statusCode} fetching movies");
    }

    return compute((input) {
      final jsonList = jsonDecode(input);
      return jsonList.map<Film>((jsonObj) => Film(
        id: jsonObj["id"],
        title: jsonObj["title"],
        originalTitle: jsonObj["original_title"],
        originalTitleRomanised: jsonObj["original_title_romanised"],
        image: jsonObj["image"],
        movieBanner: jsonObj["movie_banner"],
        description: jsonObj["description"],
        director: jsonObj["director"],
        producer: jsonObj["producer"],
        releaseDate: jsonObj["release_date"],
        runningTime: int.parse(jsonObj["running_time"]),
        rtScore: jsonObj["rt_score"],
        people: List<String>.from(jsonObj["people"]),
        species: List<String>.from(jsonObj["species"]),
        locations: List<String>.from(jsonObj["locations"]),
        vehicles: List<String>.from(jsonObj["vehicles"]),
        url: jsonObj["url"],
      )).toList();
    }, response.body);
  }
}