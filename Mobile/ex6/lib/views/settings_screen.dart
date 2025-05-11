import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:ex6/view_models/theme_view_model.dart';
import 'package:ex6/widgets/color_picker.dart';
import 'package:ex6/widgets/nav_bar.dart';

class SettingsScreen extends StatelessWidget {
  final String title;
  const SettingsScreen({super.key, required this.title});

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: navBar(context, 'Settings'),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Consumer<ThemeViewModel>(
          builder: (context, themeViewModel, child) {
            return Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const Text('Pick a main color:'),
                const SizedBox(height: 16.0),
                ColorPicker(
                  selectedColor: themeViewModel.mainColor,
                  onColorSelected: (color) {
                    themeViewModel.mainColor = color;
                  },
                ),
              ],
            );
          },
        ),
      ),
    );
  }
}
