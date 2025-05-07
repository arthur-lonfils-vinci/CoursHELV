import 'package:ex4/view_models/article_view_model.dart';
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:provider/provider.dart';

class ListScreen extends StatelessWidget {
  const ListScreen({super.key});
  @override
  Widget build(BuildContext context) {
    final articleViewModel = Provider.of<ArticleViewModel>(context);
    final showReadState = articleViewModel.showRead;
    final defaultArticles = articleViewModel.getAllArticles();
    var articles = [
      for (var article in defaultArticles)
        if (showReadState || !article.read) article,
    ]; // Using state from view model

    return Scaffold(
      appBar: AppBar(
        title: const Text('Articles'),
        backgroundColor: Theme.of(context).colorScheme.primaryContainer,
        actions: [
          IconButton(
            icon:
                articleViewModel.showRead
                    ? const Icon(Icons.check_box)
                    : const Icon(Icons.check_box_outline_blank),
            onPressed: () {
              articleViewModel.toggleShowRead();
            }, // TODO F07 show/hide read articles
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => context.go('/article'), // TODO F06 go to form screen
        child: const Icon(Icons.add),
      ),
      body: Padding(
        padding: const EdgeInsets.all(32.0),
        child:
            articles.isEmpty
                ? const Center(
                  child: Text("There are no articles yet. Create one!"),
                )
                : ListView.separated(
                  itemCount: articles.length,
                  itemBuilder: (context, index) {
                    var article = articles[index];
                    return ListTile(
                      leading: IconButton(
                        icon:
                            article.read
                                ? const Icon(Icons.check_box)
                                : const Icon(Icons.check_box_outline_blank),
                        onPressed: () => articleViewModel.markAsReadOrNot(article.id), // TODO F07 mark as read
                      ),
                      title: Text(article.title),
                      subtitle: Text(article.author),
                      onTap: () => context.go('/article/${article.id}'),
                      trailing: IconButton(
                        icon: const Icon(Icons.delete),
                        onPressed: () => articleViewModel.removeArticle(article.id), // TODO F07 delete article
                      ),
                    );
                  },
                  separatorBuilder: (context, index) => const Divider(),
                ),
      ),
    );
  }
}
