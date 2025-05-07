import 'package:flutter/material.dart';
import 'package:file_picker/file_picker.dart';
import '../services/file_service.dart';
import 'editor_screen.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Éditeur de Texte')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: () => _openExistingFile(context),
              style: ElevatedButton.styleFrom(
                padding: const EdgeInsets.symmetric(
                  horizontal: 32,
                  vertical: 16,
                ),
                minimumSize: const Size(250, 60),
              ),
              child: const Text('Ouvrir un fichier existant'),
            ),
            const SizedBox(height: 24),
            ElevatedButton(
              onPressed: () => _createNewFile(context),
              style: ElevatedButton.styleFrom(
                padding: const EdgeInsets.symmetric(
                  horizontal: 32,
                  vertical: 16,
                ),
                minimumSize: const Size(250, 60),
              ),
              child: const Text('Créer un nouveau fichier texte'),
            ),
          ],
        ),
      ),
    );
  }

  Future<void> _openExistingFile(BuildContext context) async {
    final fileService = FileService();
    final file = await fileService.pickFile();

    if (file != null && context.mounted) {
      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => EditorScreen(file: file)),
      );
    }
  }

  void _createNewFile(BuildContext context) {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => const EditorScreen()),
    );
  }
}
