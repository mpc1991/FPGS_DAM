import 'package:exercici2/src/pages/alert_page.dart';
import 'package:exercici2/src/pages/avatar_page.dart';
import 'package:exercici2/src/pages/home_page.dart';
import 'package:exercici2/src/routes/routes.dart';
import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        localizationsDelegates: [
          GlobalMaterialLocalizations.delegate,
          GlobalWidgetsLocalizations.delegate,
          GlobalCupertinoLocalizations.delegate,
        ],
        supportedLocales: [
          Locale('en', ''), // English, no country code
          Locale('es', ''), // Spanish, no country code
        ],
        debugShowCheckedModeBanner: false,
        title: 'Components',
        //home: HomePage(),
        routes: getRoutes(),
        onGenerateRoute: (RouteSettings settings) {
          print('Hem anat a: ${settings.name}');
          return MaterialPageRoute(
              builder: (BuildContext context) => AlertPage());
        });
  }
}
