import 'package:examen_final_porcel/screens/home_screen.dart';
import 'package:examen_final_porcel/services/plats_services.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'screens/plat_form_screen.dart';
import 'screens/screens.dart';

void main() {
  runApp( AppState());
}

class AppState extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MultiProvider(providers: [
       ChangeNotifierProvider(
         create: ( _ ) => PlatsServices(), // creamos un productServices
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
      title: 'examen_final_porcel',
      initialRoute: 'home',
      routes: {
        'login': (_) => LoginScreen(),
        'home': (_) => HomeScreen(),
        'plat': (_) => ProductScreen(),
      },
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
    );
  }
}