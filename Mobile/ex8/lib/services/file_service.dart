import 'dart:convert';
import 'dart:typed_data';
import 'package:file_picker/file_picker.dart';
import 'package:flutter/foundation.dart';

// Imports conditionnels pour éviter les problèmes de compilation
import 'file_service_web.dart'
    if (dart.library.io) 'file_service_io.dart'
    as platform;

class FileService {
  Future<PlatformFile?> pickFile() async {
    try {
      FilePickerResult? result = await FilePicker.platform.pickFiles(
        type: FileType.custom,
        allowedExtensions: ['txt'],
        withData: true,
      );

      if (result != null) {
        return result.files.first;
      }
    } catch (e) {
      debugPrint('Error picking file: $e');
    }
    return null;
  }

  Future<void> saveFile(String fileName, String content) async {
    try {
      // Utilisation de l'implémentation spécifique à la plateforme
      await platform.saveFileToPlatform(fileName, content);
      debugPrint('Fichier sauvegardé: $fileName');
    } catch (e) {
      debugPrint('Error saving file: $e');
    }
  }

  String? bytesToString(Uint8List? bytes) {
    if (bytes == null) return null;
    try {
      return utf8.decode(bytes);
    } catch (e) {
      debugPrint('Error decoding bytes: $e');
      return null;
    }
  }
}
