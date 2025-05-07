// widgets/color_picker.dart
import 'package:flutter/material.dart';

import '../view_models/theme_view_model.dart';

class ColorPicker extends StatelessWidget {
  final String selectedColor;
  final ValueChanged<String> onColorSelected;

  const ColorPicker({
    super.key,
    required this.selectedColor,
    required this.onColorSelected,
  });

  @override
  Widget build(BuildContext context) {
    final colors = ['red', 'green', 'blue', 'yellow', 'purple'];

    return Wrap(
      spacing: 8.0,
      children: colors.map((possibleColor) {
        return ChoiceChip(
          label: Text(possibleColor, style: TextStyle(color: COLORS[possibleColor])),
          selected: selectedColor == possibleColor,
          onSelected: (selected) {
            if (selected) {
              onColorSelected(possibleColor);
            }
          },
        );
      }).toList(),
    );
  }
}