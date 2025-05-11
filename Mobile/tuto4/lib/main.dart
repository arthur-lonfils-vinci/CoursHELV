import 'package:flutter/material.dart';
import 'package:flutter_web_plugins/flutter_web_plugins.dart';
import 'package:go_router/go_router.dart';
import 'package:provider/provider.dart';
import 'package:tuto4/view_models/click_view_model.dart';
import 'views/second_screen.dart';
import 'views/first_screen.dart';
import 'views/user_screen.dart';

final _router = GoRouter(
  initialLocation: '/',
  routes: [
    GoRoute(
      path: '/',
      builder: (context, state) => const FirstScreen(),
      routes: [
        GoRoute(
          path: 'second',
          builder: (context, state) {
            final nbClicks =
                Provider.of<ClickViewModel>(context, listen: false).clicks;
            return SecondScreen(nbClicks: nbClicks);
          },
        ),
        GoRoute(
          path: 'users/:username',
          builder:
              (context, state) =>
                  UserScreen(username: state.pathParameters['username'] ?? ''),
        ),
      ],
    ),
  ],
);

void main() {
  usePathUrlStrategy();
  runApp(
    ChangeNotifierProvider<ClickViewModel>(
      create: (context) => ClickViewModel(),
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
    );
  }
}
