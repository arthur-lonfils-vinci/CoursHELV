
import 'package:exam/models/mural.dart';
import 'package:exam/models/stop.dart';
import 'package:exam/services/itinerary_service.dart';
import 'package:exam/services/mural_service.dart';
import 'package:flutter/material.dart';

class AppViewModel extends ChangeNotifier {
  final ItineraryService _itineraryService;
  final MuralService _muralService;
  List<Stop> _stops = [];
  List<Mural> _murals = [];

  AppViewModel(this._itineraryService, this._muralService) {
    _loadStops();
    _loadMurals();
  }

  List<Stop> get stops => _stops;
  List<Mural> get murals => _murals;
  //get total stops and total different artists
  int get totalStops => _stops.length | 0;
  int get totalArtists => _stops.map((stop) => stop.mural.cartoonist).toSet().length;

  void _loadStops() async {
    _stops = await _itineraryService.restoreItinerary();
    notifyListeners();
  }

  void _loadMurals() async {
    _murals = await _muralService.murals;
    notifyListeners();
  }

  void addToItinary(Mural mural) {
    _stops.add(Stop(mural: mural));
    notifyListeners();
  }

  void removeFromItinary(Stop stop) {
    _stops.remove(stop);
    notifyListeners();
  }

  void saveItinary() {
    _itineraryService.saveItinerary(
      _stops
          .map(
            (stop) =>
                '${stop.mural.name},${stop.mural.cartoonist},${stop.mural.address},${stop.mural.surface},${stop.mural.imagePath}',
          )
          .toList(),
    );
  }

  void restoreCart() {
    _loadStops();
    notifyListeners();
  }

  void filterMurals(String query) {
    if (query.isEmpty) {
      _loadMurals();
    } else {
      _murals = _murals
          .where((mural) =>
              mural.name.toLowerCase().contains(query.toLowerCase()) ||
              mural.cartoonist.toLowerCase().contains(query.toLowerCase()))
          .toList();
    }
    notifyListeners();
  }

  void upStopInterary(Stop stop) {
    final index = _stops.indexOf(stop);
    if (index > 0) {
      final temp = _stops[index - 1];
      _stops[index - 1] = stop;
      _stops[index] = temp;
      notifyListeners();
    }
  }

  void downStopInterary(Stop stop) {
    final index = _stops.indexOf(stop);
    if (index < _stops.length - 1) {
      final temp = _stops[index + 1];
      _stops[index + 1] = stop;
      _stops[index] = temp;
      notifyListeners();
    }
  }
}
