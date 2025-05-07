import 'package:ex4/views/article_screen.dart';
import 'package:ex4/views/list_screen.dart';
import 'package:flutter/material.dart';
import 'package:flutter_web_plugins/flutter_web_plugins.dart';
import 'package:go_router/go_router.dart';
import 'models/article.dart';

final _router = GoRouter(
  initialLocation: '/',
  routes: [
    GoRoute(
      path: '/',
      builder: (context, state) => const ListScreen(),
      routes: [
        GoRoute(
          path: 'article/:id',
          builder: (context, state) {
            final articleId = int.parse(state.pathParameters['id']!);
            final article = state.extra as Article?;
            return ArticleScreen(articleId: articleId, article: article);
          },
        ),
      ],
    ),
  ],
);

void main() {
  usePathUrlStrategy();
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp.router(
      routerConfig: _router,
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
    );
  }
}
