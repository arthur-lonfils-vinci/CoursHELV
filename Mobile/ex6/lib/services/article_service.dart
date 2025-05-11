import 'package:ex6/models/article_model.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';
import 'package:sqflite_common_ffi_web/sqflite_ffi_web.dart';

class ArticleService {
  late Database _database;

  Database get database => _database;

  Future<Article> createArticle(name, price, quantity) async {
    final id = await _database.insert('Article', {
      "name": name,
      "price": price,
      "quantity": quantity,
    });
    final article = Article(id: id, name: name, price: price, quantity: quantity);
    return article;
  }

  Future<void> deleteArticle(id) async {
    await _database.delete('Article', where: 'id = ?', whereArgs: [id]);
  }

  Future<List<Article>> getArticles() async {
    final maps = await _database.query('Article');
    return List.generate(maps.length, (i) {
      return Article(
        id: maps[i]['id'] as int?,
        name: maps[i]['name'] as String,
        price: maps[i]['price'] as double,
        quantity: maps[i]['quantity'] as int,
      );
    });
  }

  Future<void> initDatabase() async {
    if (kIsWeb) {
      WidgetsFlutterBinding.ensureInitialized();
      databaseFactory = databaseFactoryFfiWeb;
    }

    _database = await openDatabase(
      join(await getDatabasesPath(), 'test.db'),
      version: 1,
    );

    final result = await _database.rawQuery(
      "SELECT count(*) AS count FROM sqlite_master WHERE type='table' AND name='Article'",
    );

    if (result[0]["count"] == 0) {
      await _database.execute(
        'CREATE TABLE Article(id INTEGER PRIMARY KEY, name TEXT, price DOUBLE, quantity INTEGER)',
      );
      await _database.insert('Article', <String, Object?>{
        'name': 'Article 1',
        'price': 10.0,
        'quantity': 1,
      });
      await _database.insert('Article', <String, Object?>{
        'name': 'Article 2',
        'price': 20.0,
        'quantity': 2,
      });
      await _database.insert('Article', <String, Object?>{
        'name': 'Article 3',
        'price': 30.0,
        'quantity': 3,
      });
      await _database.insert('Article', <String, Object?>{
        'name': 'Article 4',
        'price': 40.0,
        'quantity': 4,
      });
      await _database.insert('Article', <String, Object?>{
        'name': 'Article 5',
        'price': 50.0,
        'quantity': 5,
      });
    }
  }
}