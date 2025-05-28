import 'package:exam/services/itinerary_service.dart';
import 'package:exam/services/mural_service.dart';
import 'package:exam/view_models/app_view_model.dart';
import 'package:exam/views/screens/home_screen.dart';
import 'package:exam/views/screens/itinerary_screen.dart';
import 'package:go_router/go_router.dart';
import 'package:flutter/material.dart';
import 'package:flutter_web_plugins/flutter_web_plugins.dart';
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
      path: '/itinary',
      builder: (context, state) {
        return const ItineraryScreen();
      },
    ),
  ],
);

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  usePathUrlStrategy();
  final itineraryService = ItineraryService();
  final muralService = MuralService();
  runApp(MyApp(itineraryService: itineraryService, muralService: muralService));
}

class MyApp extends StatelessWidget {
  final ItineraryService itineraryService;
  final MuralService muralService;

  const MyApp({super.key,
    required this.itineraryService,
    required this.muralService,
  });

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider<AppViewModel>(
          create: (context) => AppViewModel(itineraryService, muralService),
        ),
      ],
      child: MaterialApp.router(
        title: 'Fresques',
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
