import 'package:ex4/view_models/article_view_model.dart';
import 'package:ex4/views/article_screen.dart';
import 'package:ex4/views/form_screen.dart';
import 'package:ex4/views/list_screen.dart';
import 'package:flutter/material.dart';
import 'package:flutter_web_plugins/flutter_web_plugins.dart';
import 'package:go_router/go_router.dart';
import 'package:provider/provider.dart';

final _router = GoRouter(
  initialLocation: '/',
  routes: [
    GoRoute(
      path: '/',
      builder: (context, state) {
        
        return ListScreen();
      },
      routes: [
        GoRoute(
          path: 'article/:id',
          builder: (context, state) {
            final articleId = int.parse(state.pathParameters['id']!);
            return ArticleScreen(articleId: articleId);
          },
        ),
        GoRoute(
          path: 'article',
          builder: (context, state) {
            return const FormScreen();
          },
        ),
        
      ],
    ),
  ],
);

void main() {
  usePathUrlStrategy();
  runApp(
    MultiProvider(
      providers: [
        ChangeNotifierProvider(
          create: (context) => ArticleViewModel(),
        ),
      ],
      child: const MyApp(),
    ),
  );
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
      debugShowCheckedModeBanner: false,
    );
  }
}
