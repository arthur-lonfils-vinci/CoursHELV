import 'package:exam/models/mural.dart';
import 'package:exam/view_models/app_view_model.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class MuralWidget extends StatelessWidget {
  final Mural mural;

  const MuralWidget({super.key, required this.mural});

  @override
  Widget build(BuildContext context) {
    final appViewModel = Provider.of<AppViewModel>(context);

    return Card(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Image.network(mural.imagePath, fit: BoxFit.cover),
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: Text(
              mural.name,
              style: Theme.of(context).textTheme.bodyLarge,
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(8.0),
            // Display the cartoonist, address and surface area
            child: Text(
              '${mural.cartoonist} - ${mural.address} - ${mural.surface} m²',
              style: Theme.of(context).textTheme.bodyMedium,
            ),
          ),
          // icon to add mural to itinerary
          IconButton(
            icon: const Icon(Icons.add),
            onPressed: () {
              appViewModel.addToItinary(mural);
            },
          ),
        ],
      ),
    );
  }
}
