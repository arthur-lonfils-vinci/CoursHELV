class Article {
  static int nextId = 1;

  final int id = nextId++;
  final String title;
  final String author;
  final String content;
  bool read = false;

  Article({required this.title, required this.author, required this.content});

  @override
  String toString() => title;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is Article && runtimeType == other.runtimeType && id == other.id;

  @override
  int get hashCode => id.hashCode;
}

List<Article> get defaultArticles {
  final articles = [
    Article(title: "title", author: "author", content: "content"),
    Article(
        title: "Lorem Ipsum",
        author: "www.lipsum.com",
        content:
            """Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec et diam at nulla scelerisque bibendum. Donec sagittis facilisis odio in fermentum. Nunc in sapien eros. Sed commodo purus ac bibendum laoreet. In quam leo, fermentum vel vulputate a, volutpat at leo. Sed dignissim, sapien nec commodo dapibus, dolor velit sodales ex, sit amet ornare sem tellus quis nibh. Donec placerat justo sed lectus laoreet, egestas tincidunt enim luctus. Aenean vel ligula nisi. Pellentesque arcu tellus, aliquam a orci a, scelerisque accumsan lectus. Curabitur ac urna ac massa mollis semper sed id tortor.

Praesent at mi aliquet, cursus arcu ac, congue magna. Maecenas ut ipsum nec dui imperdiet scelerisque. Curabitur fringilla posuere ipsum porta ornare. Nulla porttitor eget diam ac auctor. Quisque efficitur tortor vitae arcu lacinia rhoncus. Donec aliquam viverra sem, non pellentesque risus eleifend nec. Curabitur molestie, dui eget auctor rutrum, sapien sem condimentum leo, quis imperdiet lacus sem sit amet libero. Morbi nulla neque, suscipit nec molestie id, auctor mattis urna. Curabitur id tincidunt felis, id sollicitudin nunc. Aliquam eu justo eget quam semper placerat quis fringilla est. Vestibulum finibus quam sed lacus cursus, in tempus nunc tincidunt. Quisque ullamcorper purus quis ligula pulvinar, eu rhoncus libero luctus. Duis accumsan magna sed feugiat feugiat. Morbi imperdiet ligula eu dignissim efficitur. Phasellus commodo mi et sagittis ultrices. Vivamus sagittis blandit dui, eget tincidunt neque accumsan quis.

Suspendisse in est ultricies, finibus urna ut, rhoncus lorem. Duis at ligula mi. Aliquam sit amet justo non lacus bibendum aliquam. Vestibulum id risus enim. Vestibulum mattis suscipit est, eget rhoncus purus rhoncus ac. Vivamus ac urna in turpis sagittis auctor. Nunc imperdiet rutrum pretium. Fusce rutrum, ligula at mattis tempus, ipsum mi commodo quam, ut finibus nunc lorem vitae massa. Aliquam dapibus augue non mi vehicula tincidunt. Fusce cursus, ex a interdum tincidunt, lorem tellus iaculis urna, et egestas lacus odio a enim. Donec feugiat fermentum velit, quis aliquet augue pharetra id. Nunc condimentum leo non sapien eleifend malesuada. In congue sem pulvinar augue dictum congue vitae vel mi. Curabitur non lacinia ipsum, nec molestie massa. Vestibulum tincidunt augue quis nunc viverra ultricies at ut turpis. Nullam non suscipit odio."""),
    Article(title: "hidden", author: "hidden", content: "hidden"),
  ];
  articles[2].read = true;
  return articles;
}
