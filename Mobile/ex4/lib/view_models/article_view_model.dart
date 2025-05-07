import 'package:ex4/models/article.dart';
import 'package:flutter/material.dart';

class ArticleViewModel extends ChangeNotifier {
  bool _showRead = false;
  bool get showRead => _showRead;

  // Store a single instance of articles
  final List<Article> _articles = defaultArticles;

  void toggleShowRead() {
    _showRead = !_showRead;
    notifyListeners();
  }

  List<Article> getAllArticles() {
    return _articles;
  }

  Article getArticleById(int id) {
    for (var article in _articles) {
      if (article.id == id) {
        return article;
      }
    }
    throw Exception("Article not found for id $id");
  }

  void markAsReadOrNot(int id) {
    for (var article in _articles) {
      if (article.id == id) {
        article.read = !article.read;
        notifyListeners();
        return;
      }
    }
    throw Exception("Article not found for id $id");
  }

  void addArticle(Article article) {
    _articles.add(article);
    notifyListeners();
  }

  void removeArticle(int id) {
    _articles.removeWhere((article) => article.id == id);
    notifyListeners();
  }
}
