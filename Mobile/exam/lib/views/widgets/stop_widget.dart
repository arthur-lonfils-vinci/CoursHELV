import 'package:exam/view_models/app_view_model.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../../models/stop.dart';

class StopWidget extends StatelessWidget {
  final Stop stop;

  const StopWidget({super.key, required this.stop});

  @override
  Widget build(BuildContext context) {
    final appViewModel = Provider.of<AppViewModel>(context);

    return Card(
      child: ListTile(
        // Display the mural image but with a fixed height
        leading: SizedBox(
          width: 50,
          height: 50,
          child: Image.network(
            stop.mural.imagePath,
            fit: BoxFit.cover,
          ),
        ),
        title: Text(stop.mural.name),
        subtitle: Text('${stop.mural.cartoonist} - ${stop.mural.address}'),
        trailing: Row(
          mainAxisSize: MainAxisSize.min,
          children: [
            IconButton(
              icon: const Icon(Icons.arrow_upward),
              onPressed: () => appViewModel.upStopInterary(stop),
            ),
            IconButton(
              icon: const Icon(Icons.arrow_downward),
              onPressed: () => appViewModel.downStopInterary(stop),
            ),
            IconButton(
              icon: const Icon(Icons.delete),
              onPressed: () {
                appViewModel.removeFromItinary(stop);
                ScaffoldMessenger.of(context).showSnackBar(
                  SnackBar(content: Text('${stop.mural.name} supprimé de l\'itinéraire')),
                );
              },
            ),
          ],
        ),
  
      ),
    );
  }
}
