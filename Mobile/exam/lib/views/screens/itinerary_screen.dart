import 'package:exam/view_models/app_view_model.dart';
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:provider/provider.dart';
import '../widgets/stop_widget.dart';

class ItineraryScreen extends StatelessWidget {
  const ItineraryScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final appViewModel = Provider.of<AppViewModel>(context);
    final stops = appViewModel.stops;
    final totalStops = appViewModel.totalStops;
    final totalArtists = appViewModel.totalArtists;
    return Scaffold(
      appBar: AppBar(
        title: const Text('Itinéraire'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        leading: IconButton(
          icon: const Icon(Icons.arrow_back),
          onPressed: () => context.go('/'),
        ),
        actions: [IconButton(icon: const Icon(Icons.save), onPressed: () => appViewModel.saveItinary())],
      ),
      body: Column(
        children: [
          Expanded(
            child: SizedBox(
              width: 512,
              child: stops.isNotEmpty
                  ? ListView.builder(
                      itemCount: stops.length,
                      itemBuilder: (context, index) =>
                          StopWidget(stop: stops[index]),
                    )
                  : const Center(child: Text("Aucune étape prévue.")),
            ),
          ),
          Center(
            child: Text(
              '$totalStops étapes et $totalArtists dessinateurs différents',
              style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
          ),
        ],
      ),
    );
  }
}
