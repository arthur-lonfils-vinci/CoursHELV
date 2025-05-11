import 'package:ex4/view_models/article_view_model.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class ArticleScreen extends StatelessWidget {
  final int articleId;

  const ArticleScreen({super.key, required this.articleId});

  @override
  Widget build(BuildContext context) {
    // Use the passed article if available, otherwise look it up using the ID
    final articleViewModel = Provider.of<ArticleViewModel>(context);
    final articleToDisplay = articleViewModel.getArticleById(articleId);

    return Scaffold(
      appBar: AppBar(
        title: const Text("Article"),
        backgroundColor: Theme.of(context).colorScheme.primaryContainer,
      ),
      floatingActionButton: FloatingActionButton(
        child:
            articleToDisplay.read
                ? const Icon(Icons.check_box)
                : const Icon(Icons.check_box_outline_blank),
        onPressed: () => articleViewModel.markAsReadOrNot(articleToDisplay.id), // TODO F07 mark as read
      ),
      body: Padding(
        padding: const EdgeInsets.all(32),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              articleToDisplay.title,
              style: const TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
            ),
            Text(
              articleToDisplay.author,
              style: TextStyle(fontSize: 18, color: Colors.grey[700]),
            ),
            const SizedBox(height: 16),
            Expanded(
              child: SingleChildScrollView(
                child: SizedBox(
                  width: double.infinity,
                  child: Text(
                    articleToDisplay.content,
                    textAlign: TextAlign.justify,
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
