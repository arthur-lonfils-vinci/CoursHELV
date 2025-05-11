import 'package:flutter/material.dart';
import 'package:tuto6/models/post_model.dart';
import 'package:tuto6/services/post_service.dart';

class PostViewModel with ChangeNotifier {
  PostService postService;
  List<Post> _posts = [];

  PostViewModel(this.postService) {
    postService.getPosts().then((posts) {
      _posts = posts;
      notifyListeners();
    });
  }

  List<Post> get posts => _posts;

  Post getPost(String id) =>
      posts.firstWhere((post) => post.id.toString() == id);

  Future<void> addPost(String name, String content) async {
    final post = await postService.createPost(name, content);
    _posts.add(post);
    notifyListeners();
  }

  Future<void> deletePost(int id) async {
    await postService.deletePost(id);
    _posts.removeWhere((post) => post.id == id);
    notifyListeners();
  }
}