import 'package:ex6/models/article_model.dart';
import 'package:ex6/services/article_service.dart';
import 'package:flutter/material.dart';

class ArticleViewModel with ChangeNotifier {
  ArticleService articleService;
  List<Article> _articles = [];

  ArticleViewModel(this.articleService) {
    articleService.getArticles().then((articles) {
      _articles = articles;
      notifyListeners();
    });
  }

  List<Article> get articles => _articles;

  Article getArticle(String id) =>
      articles.firstWhere((article) => article.id.toString() == id);

  Future<void> addArticle(String name, double price, int quantity) async {
    final article = await articleService.createArticle(name, price, quantity);
    _articles.add(article);
    notifyListeners();
  }

  Future<void> deleteArticle(int id) async {
    await articleService.deleteArticle(id);
    _articles.removeWhere((article) => article.id == id);
    notifyListeners();
  }

  void increaseQuantity(int id) {
    final article = _articles.firstWhere((article) => article.id == id);
    article.quantity += 1;
    notifyListeners();
    // In a real app, you would also update the backend
    // articleService.updateArticle(id, article.name, article.price, article.quantity);
  }

  void decreaseQuantity(int id) {
    final article = _articles.firstWhere((article) => article.id == id);
    if (article.quantity > 0) {
      article.quantity -= 1;
      notifyListeners();
      // In a real app, you would also update the backend
      // articleService.updateArticle(id, article.name, article.price, article.quantity);
    }
  }
}
