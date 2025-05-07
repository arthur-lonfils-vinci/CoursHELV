import 'dart:io';
import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:file_picker/file_picker.dart';
import 'package:path_provider/path_provider.dart';

Future<void> saveFileToPlatform(String fileName, String content) async {
  if (!kIsWeb) {
    try {
      // Utilisation de file_picker pour choisir l'emplacement de sauvegarde
      String? outputPath = await FilePicker.platform.getDirectoryPath();

      if (outputPath != null) {
        // Chemin complet du fichier (dossier + nom)
        String filePath = '$outputPath${Platform.pathSeparator}$fileName';

        // Écriture du fichier
        File file = File(filePath);
        await file.writeAsString(content);

        debugPrint('Fichier sauvegardé localement: $filePath');
      } else {
        debugPrint('Opération annulée: aucun dossier sélectionné');
      }
    } catch (e) {
      debugPrint('Erreur lors de la sélection du dossier ou sauvegarde: $e');

      // Tentative de sauvegarde alternative dans le dossier temporaire
      try {
        final directory = await getTemporaryDirectory();
        final file = File(
          '${directory.path}${Platform.pathSeparator}$fileName',
        );
        await file.writeAsString(content);
        debugPrint(
          'Fichier sauvegardé dans le dossier temporaire: ${file.path}',
        );
      } catch (e2) {
        debugPrint('Erreur lors de la sauvegarde alternative: $e2');
        rethrow;
      }
    }
  }
}
