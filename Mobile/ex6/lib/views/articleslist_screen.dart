import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:provider/provider.dart';
import 'package:ex6/view_models/article_view_model.dart';
import 'package:ex6/widgets/nav_bar.dart';

class ArticleslistScreen extends StatelessWidget {
  final String title;

  const ArticleslistScreen({super.key, required this.title});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: navBar(context, title),
      body: Consumer<ArticleViewModel>(
        builder: (context, model, child) {
          if (model.articles.isEmpty) {
            return const Center(
              child: Text('No articles found', style: TextStyle(fontSize: 18)),
            );
          }

          return ListView.builder(
            padding: const EdgeInsets.all(8.0),
            itemCount: model.articles.length,
            itemBuilder: (context, index) {
              final article = model.articles[index];
              return Card(
                elevation: 2,
                margin: const EdgeInsets.symmetric(vertical: 8, horizontal: 4),
                child: ListTile(
                  contentPadding: const EdgeInsets.symmetric(
                    horizontal: 16,
                    vertical: 8,
                  ),
                  title: Text(
                    article.name,
                    style: const TextStyle(
                      fontWeight: FontWeight.bold,
                      fontSize: 18,
                    ),
                  ),
                  subtitle: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      const SizedBox(height: 4),
                      Text(
                        'Price: \$${article.price.toStringAsFixed(2)}',
                        style: TextStyle(
                          color: Colors.green.shade700,
                          fontWeight: FontWeight.w500,
                        ),
                      ),
                      const SizedBox(height: 8),
                      Row(
                        children: [
                          const Text(
                            'Quantity: ',
                            style: TextStyle(fontWeight: FontWeight.w500),
                          ),
                          IconButton(
                            icon: const Icon(Icons.remove_circle_outline),
                            color: Colors.red,
                            onPressed:
                                article.quantity > 0
                                    ? () => model.decreaseQuantity(article.id!)
                                    : null,
                          ),
                          Text(
                            '${article.quantity}',
                            style: const TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 16,
                            ),
                          ),
                          IconButton(
                            icon: const Icon(Icons.add_circle_outline),
                            color: Colors.green,
                            onPressed:
                                () => model.increaseQuantity(article.id!),
                          ),
                        ],
                      ),
                    ],
                  ),
                  trailing: IconButton(
                    icon: const Icon(Icons.delete, color: Colors.red),
                    onPressed: () {
                      showDialog(
                        context: context,
                        builder:
                            (ctx) => AlertDialog(
                              title: const Text('Delete Article'),
                              content: Text(
                                'Are you sure you want to delete "${article.name}"?',
                              ),
                              actions: [
                                TextButton(
                                  onPressed: () => Navigator.of(ctx).pop(),
                                  child: const Text('CANCEL'),
                                ),
                                TextButton(
                                  onPressed: () {
                                    model.deleteArticle(article.id!);
                                    Navigator.of(ctx).pop();
                                  },
                                  child: const Text('DELETE'),
                                ),
                              ],
                            ),
                      );
                    },
                  ),
                ),
              );
            },
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => context.go('/new_article'),
        backgroundColor: Theme.of(context).primaryColor,
        child: const Icon(Icons.add),
        tooltip: 'Add new article',
      ),
    );
  }
}
