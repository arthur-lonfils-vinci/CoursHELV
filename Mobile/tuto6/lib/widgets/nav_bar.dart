// widgets/nav_bar.dart
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:provider/provider.dart';
import 'package:tuto6/view_models/theme_view_model.dart';

PreferredSizeWidget navBar(BuildContext context, String title) {
  final themeViewModel = Provider.of<ThemeViewModel>(context);
  return AppBar(
    title: Text(title),
    backgroundColor: themeViewModel.mainColorMaterial,
    actions: [
      IconButton(
        icon: const Icon(Icons.add),
        onPressed: () {
          context.go('/new_post');
        },
      ),
      IconButton(
        icon: const Icon(Icons.color_lens),
        onPressed: () {
          context.go('/settings');
        },
      ),
    ],
  );
}