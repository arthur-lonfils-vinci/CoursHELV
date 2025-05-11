class Post {
  final int? id;
  final String name;
  final String content;

  Post({this.id, required this.name, required this.content});

  @override
  String toString() {
    return 'Post{id: $id, name: $name, content: $content}';
  }
}