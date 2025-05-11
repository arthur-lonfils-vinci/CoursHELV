import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:file_picker/file_picker.dart';
import 'package:provider/provider.dart';
import 'dart:convert';
import '../models/text_document.dart';
import '../services/file_service.dart';

class EditorScreen extends StatefulWidget {
  final PlatformFile? file;

  const EditorScreen({super.key, this.file});

  @override
  State<EditorScreen> createState() => _EditorScreenState();
}

class _EditorScreenState extends State<EditorScreen> {
  late TextEditingController _contentController;
  late TextEditingController _fileNameController;
  late TextDocument _document;
  final FileService _fileService = FileService();

  @override
  void initState() {
    super.initState();

    String initialFileName = "fichier.txt";
    String initialContent = "";

    if (widget.file != null) {
      initialFileName = widget.file!.name;
      if (widget.file!.bytes != null) {
        try {
          initialContent = utf8.decode(widget.file!.bytes!);
        } catch (e) {
          debugPrint('Error decoding file content: $e');
        }
      }
    }

    _document = TextDocument(
      fileName: initialFileName,
      content: initialContent,
    );
    _contentController = TextEditingController(text: _document.content);
    _fileNameController = TextEditingController(text: _document.fileName);
  }

  @override
  void dispose() {
    _contentController.dispose();
    _fileNameController.dispose();
    super.dispose();
  }

  Future<void> _saveFile() async {
    // On récupère les valeurs des contrôleurs
    _document.fileName = _fileNameController.text;
    _document.content = _contentController.text;

    await _fileService.saveFile(_document.fileName, _document.content);

    if (!mounted) return;
    Navigator.pop(context);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Éditeur de texte'),
        actions: [
          IconButton(
            icon: const Icon(Icons.save),
            onPressed: _saveFile,
            tooltip: 'Sauvegarder',
          ),
        ],
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            if (kIsWeb)
              Padding(
                padding: const EdgeInsets.only(bottom: 16.0),
                child: TextField(
                  controller: _fileNameController,
                  decoration: const InputDecoration(
                    labelText: 'Nom du fichier',
                    border: OutlineInputBorder(),
                  ),
                ),
              )
            else
              Padding(
                padding: const EdgeInsets.only(bottom: 16.0),
                child: Text(
                  'Fichier: ${_document.fileName}',
                  style: const TextStyle(
                    fontSize: 16,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
            const SizedBox(height: 8.0),
            Expanded(
              child: TextField(
                controller: _contentController,
                maxLines: null,
                expands: true,
                textAlignVertical: TextAlignVertical.top,
                decoration: const InputDecoration(
                  hintText: 'Saisissez votre texte ici...',
                  border: OutlineInputBorder(),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
