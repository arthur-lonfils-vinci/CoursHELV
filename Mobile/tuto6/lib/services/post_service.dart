import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';
import 'package:sqflite_common_ffi_web/sqflite_ffi_web.dart';
import 'package:tuto6/models/post_model.dart';

class PostService {
  late Database _database;

  Database get database => _database;

  Future<Post> createPost(name, content) async {
    final id = await _database.insert('Post', {
      "name": name,
      "content": content,
    });
    final post = Post(id: id, name: name, content: content);
    return post;
  }

  Future<void> deletePost(id) async {
    await _database.delete('Post', where: 'id = ?', whereArgs: [id]);
  }

  Future<List<Post>> getPosts() async {
    final maps = await _database.query('Post');
    return List.generate(maps.length, (i) {
      return Post(
        id: maps[i]['id'] as int?,
        name: maps[i]['name'] as String,
        content: maps[i]['content'] as String,
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
      "SELECT count(*) AS count FROM sqlite_master WHERE type='table' AND name='Post'",
    );

    if (result[0]["count"] == 0) {
      await _database.execute(
        'CREATE TABLE Post(id INTEGER PRIMARY KEY, name TEXT, content TEXT)',
      );
      await _database.insert('Post', <String, Object?>{
        'name': 'Post 1',
        'content': 'Content 1',
      });
      await _database.insert('Post', <String, Object?>{
        'name': 'Post 2',
        'content': 'Content 2',
      });
    }
  }
}