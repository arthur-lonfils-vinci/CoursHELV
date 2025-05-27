import 'package:app_train/services/cart_service.dart';
import 'package:app_train/services/dish_service.dart';
import 'package:app_train/views/screens/add_dish_screen.dart';
import 'package:app_train/views/screens/cart_screen.dart';
import 'package:go_router/go_router.dart';
import 'package:flutter/material.dart';
import 'package:flutter_web_plugins/flutter_web_plugins.dart';
import 'views/screens/home_screen.dart';
import 'package:app_train/view_models/app_view_model.dart';
import 'package:provider/provider.dart';

final _router = GoRouter(
  initialLocation: '/',
  routes: [
    GoRoute(
      path: '/',
      builder: (context, state) {
        return HomeScreen();
      },
    ),
    GoRoute(
      path: '/cart',
      builder: (context, state) {
        return const CartScreen();
      },
    ),
    GoRoute(
      path: '/add-dish',
      builder: (context, state) {
        return const AddDishScreen();
      },
    ),
  ],
);

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  usePathUrlStrategy();
  final dishService = DishService();
  final cartService = CartService();
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
        title: 'Restaurant App',
        theme: ThemeData(
          primarySwatch: Colors.blue,
          colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue),
        ),
        routerConfig: _router,
        debugShowCheckedModeBanner: false,
      ),
    );
  }
}
