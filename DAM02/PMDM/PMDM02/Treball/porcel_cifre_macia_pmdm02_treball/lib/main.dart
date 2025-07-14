import 'dart:developer';
import 'package:flutter/material.dart';
import 'package:porcel_cifre_macia_pmdm02_treball/src/dto/persona.dart';
import 'package:porcel_cifre_macia_pmdm02_treball/src/pages/home_page.dart';
import 'package:porcel_cifre_macia_pmdm02_treball/src/routes/routes.dart';

void main() {
  final persona = Persona.biuda();
  runApp(MyApp(persona));
}

class MyApp extends StatelessWidget {
  final Persona persona;
  MyApp(this.persona, {super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        theme: ThemeData(
          appBarTheme: const AppBarTheme(
            centerTitle: true,
            backgroundColor: Colors.blueAccent,
          ),
          colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
          useMaterial3: true,
        ),
        routes: getRoutes(persona),
        onGenerateRoute: (RouteSettings settings) {
          return MaterialPageRoute(
            builder: (BuildContext context) => HomePage(persona),
          );
        });
  }
}
