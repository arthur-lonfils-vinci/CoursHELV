import 'package:exam/models/mural.dart';
import 'package:exam/models/stop.dart';
import 'package:shared_preferences/shared_preferences.dart';

class ItineraryService {
  static const _itineraryKey = "itinerary";

  void saveItinerary(List<String> itinerary) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.remove(_itineraryKey);
    await prefs.setStringList(_itineraryKey, itinerary);
  }

  Future<List<Stop>> restoreItinerary() async {
    final prefs = await SharedPreferences.getInstance();
    final itinerary = prefs.getStringList(_itineraryKey) ?? [];
    
    return List.generate(itinerary.length, (i) {
      final stopData = itinerary[i].split(',');
      return Stop(
        mural: Mural(
          name: stopData[0],
          cartoonist: stopData[1],
          address: stopData[2],
          surface: double.tryParse(stopData[3]) ?? 0.0,
          imagePath: stopData[4],
        ),
      );
    });
  }
}
