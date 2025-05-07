import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_web_plugins/flutter_web_plugins.dart';
import 'package:go_router/go_router.dart';
import 'package:path/path.dart';
import 'package:provider/provider.dart';
import 'package:sqflite/sqflite.dart';
import 'package:sqflite_common_ffi_web/sqflite_ffi_web.dart';
import 'package:tuto6/services/post_service.dart';
import 'package:tuto6/view_models/post_view_model.dart';
import 'package:tuto6/view_models/theme_view_model.dart';
import 'package:tuto6/views/newpost_screen.dart';
import 'package:tuto6/views/postdetails_screen.dart';
import 'package:tuto6/views/postlist_screen.dart';
import 'package:tuto6/views/settings_screen.dart';

final _router = GoRouter(
  initialLocation: '/',
  routes: [
    GoRoute(
      path: '/',
      builder: (context, state) {
        return PostlistScreen(title: 'Post List');
      },
      routes: [
        GoRoute(
          path: '/new_post',
          builder: (context, state) {
            return NewPostScreen(title: 'New Post');
          },
        ),
        GoRoute(
          path: '/settings',
          builder: (context, state) {
            return const SettingsScreen(title: 'Settings');
          },
        ),
        GoRoute(
          path: 'posts/:id',
          builder:
              (context, state) =>
                  PostDetails(postId: state.pathParameters['id'] ?? ''),
        ),
      ],
    ),
  ],
);

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  final databaseProvider = PostService();
  await databaseProvider.initDatabase();
  usePathUrlStrategy();
  runApp(MyApp(postService: databaseProvider));
}

class MyApp extends StatelessWidget {
  final PostService postService;

  const MyApp({super.key, required this.postService});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider<PostViewModel>(
          create: (context) => PostViewModel(postService),
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

Future<Database> initDatabase() async {
  // Initialize your database here
  if (kIsWeb) {
    WidgetsFlutterBinding.ensureInitialized();
    databaseFactory = databaseFactoryFfiWeb; // sqflite web "hack"
  }

  var database = await openDatabase(
    join(await getDatabasesPath(), 'test.db'),
    version: 1,
  );

  await database.execute('DROP TABLE IF EXISTS Post');
  await database.execute(
    'CREATE TABLE Post(id INTEGER PRIMARY KEY, name TEXT, content TEXT)',
  );
  await database.insert('Post', <String, Object?>{
    'name': 'Post 1',
    'content': 'Content 1',
  });
  await database.insert('Post', <String, Object?>{
    'name': 'Post 2',
    'content': 'Content 2',
  });

  final records = await database.query('Post');
  print(records);

  return database;
}
