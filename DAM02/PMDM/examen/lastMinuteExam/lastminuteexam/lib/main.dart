import 'package:flutter/material.dart';
import 'package:lastminuteexam/screens/home_screen.dart';
import 'package:lastminuteexam/services/plat_service.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(MyApp());
}

class AppState extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MultiProvider (providers: [
      ChangeNotifierProvider (
        create: (_) => PlatService(),
      ),
    ],
    child: MyApp()
    );
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Last Minute Exam',
      initialRoute: 'home',
      routes: {
        'home': (_) => HomeScreen(),
      },
    );
  }
}
