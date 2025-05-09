import 'package:exam_blanc/services/cart_service.dart';
import 'package:exam_blanc/services/dish_service.dart';
import 'package:exam_blanc/view_models/app_view_model.dart';
import 'package:exam_blanc/views/screens/cart_screen.dart';
import 'package:exam_blanc/views/screens/home_screen.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_web_plugins/flutter_web_plugins.dart';
import 'package:go_router/go_router.dart';
import 'package:path/path.dart';
import 'package:provider/provider.dart';

final _router = GoRouter(
  initialLocation: '/',
  routes: [
    GoRoute(
      path: '/',
      builder: (context, state) {
        return HomeScreen();
      },
      routes: [
        GoRoute(
          path: '/cart',
          builder: (context, state) {
            return CartScreen();
          },
        ),
      ],
    ),
  ],
);

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  final dishService = DishService();
  final cartService = CartService();
  await cartService.initDatabase();
  usePathUrlStrategy();
  runApp(MyApp(dishService: dishService, cartService: cartService));
}

class MyApp extends StatelessWidget {
  final DishService dishService;
  final CartService cartService;

  const MyApp({
    super.key,
    required this.dishService,
    required this.cartService,
  });

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider<AppViewModel>(
          create: (context) => AppViewModel(dishService, cartService),
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
