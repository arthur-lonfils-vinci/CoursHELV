import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_web_plugins/flutter_web_plugins.dart';
import 'package:go_router/go_router.dart';
import 'package:path/path.dart';
import 'package:provider/provider.dart';
import 'package:sqflite/sqflite.dart';
import 'package:sqflite_common_ffi_web/sqflite_ffi_web.dart';
import 'package:ex6/services/article_service.dart';
import 'package:ex6/view_models/article_view_model.dart';
import 'package:ex6/view_models/theme_view_model.dart';
import 'package:ex6/views/newarticle_screen.dart';
import 'package:ex6/views/articleslist_screen.dart';
import 'package:ex6/views/settings_screen.dart';

final _router = GoRouter(
  initialLocation: '/',
  routes: [
    GoRoute(
      path: '/',
      builder: (context, state) {
        return ArticleslistScreen(title: 'Articles List');
      },
      routes: [
        GoRoute(
          path: '/new_article',
          builder: (context, state) {
            return NewArticleScreen(title: 'New Article');
          },
        ),
        GoRoute(
          path: '/settings',
          builder: (context, state) {
            return const SettingsScreen(title: 'Settings');
          },
        ),
      ],
    ),
  ],
);

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  final databaseProvider = ArticleService();
  await databaseProvider.initDatabase();
  usePathUrlStrategy();
  runApp(MyApp(articleService: databaseProvider));
}

class MyApp extends StatelessWidget {
  final ArticleService articleService;

  const MyApp({super.key, required this.articleService});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider<ArticleViewModel>(
          create: (context) => ArticleViewModel(articleService),
        ),
        ChangeNotifierProvider<ThemeViewModel>(
          create: (context) => ThemeViewModel(),
        ),
      ],
      child: MaterialApp.router(
        title: 'Flutter Demo',
        debugShowCheckedModeBanner: false,
        theme: ThemeData(),
        routerConfig: _router,
      ),
    );
  }
}
