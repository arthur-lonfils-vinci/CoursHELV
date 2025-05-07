import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:universal_html/html.dart' as html;

Future<void> saveFileToPlatform(String fileName, String content) async {
  if (kIsWeb) {
    // Solution pour le web: téléchargement direct du fichier
    final bytes = utf8.encode(content);
    final blob = html.Blob([bytes]);
    final url = html.Url.createObjectUrlFromBlob(blob);

    // Création d'un lien invisible et déclenchement du téléchargement
    final anchor =
        html.AnchorElement(href: url)
          ..setAttribute('download', fileName)
          ..style.display = 'none';

    html.document.body?.children.add(anchor);
    anchor.click();

    // Nettoyage
    html.document.body?.children.remove(anchor);
    html.Url.revokeObjectUrl(url);

    debugPrint('Fichier téléchargé sur le web: $fileName');
  }
}
