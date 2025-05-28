import './mural_widget.dart';
import 'package:flutter/material.dart';

import '../../models/mural.dart';

class MuralListWidget extends StatelessWidget {
  final List<Mural> murals;

  const MuralListWidget({super.key, required this.murals});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 512,
      child: ListView(
        children: [

          for (final mural in murals) MuralWidget(mural: mural),
        ],
      ),
    );
  }
}

