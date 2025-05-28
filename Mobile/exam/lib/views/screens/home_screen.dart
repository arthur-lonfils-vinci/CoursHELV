import 'package:exam/view_models/app_view_model.dart';
import 'package:go_router/go_router.dart';
import 'package:provider/provider.dart';

import 'package:flutter/material.dart';

import '../widgets/mural_list_widget.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});
  @override
  Widget build(BuildContext context) {
    final appViewModel = Provider.of<AppViewModel>(context);
    final murals = appViewModel.murals;
    final totalStops = appViewModel.totalStops;
    return Scaffold(
      appBar: AppBar(
        title: const Text("Toutes les fresques"),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        actions: [
          Stack(
            alignment: Alignment.center,
            children: [
              IconButton(
                icon: const Icon(Icons.map),
                onPressed: () => context.go('/itinary'),
              ),
              Positioned(
                right: 3,
                bottom: 3,
                child: Container(
                  padding: const EdgeInsets.all(2),
                  decoration: BoxDecoration(
                    color: Colors.red,
                    borderRadius: BorderRadius.circular(6),
                  ),
                  constraints: const BoxConstraints(
                    minWidth: 16,
                    minHeight: 16,
                  ),
                  child: Text(
                    '$totalStops',
                    style: const TextStyle(color: Colors.white, fontSize: 11),
                    textAlign: TextAlign.center,
                  ),
                ),
              ),
            ],
          ),
        ],
      ),
      body: Center(
        child: Column(
          children: [
            SizedBox(
              height: 16,
            ),
            SizedBox(
              width: 512,
              height: 64,
              child: TextField(
                decoration: InputDecoration(
                  labelText: 'Filtrer les fresques',
                  prefixIcon: const Icon(Icons.search),
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(8),
                  ),
                ),
                onChanged: (value) {
                  appViewModel.filterMurals(value);
                },
              ),
            ),
            Expanded(
              child:
              murals.isNotEmpty
                      ? MuralListWidget(murals: murals)
                      : const Center(
                          child: Text("Aucune fresque trouvée."),
                      )
            ),
          ],
        ),
      ),
    );
  }
}
