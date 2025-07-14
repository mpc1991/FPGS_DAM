import 'package:flutter/material.dart';
import 'package:preferences_app_plantilla/preferences/preferences.dart';
import 'package:preferences_app_plantilla/widgets/widgets.dart';

class HomeScreen extends StatelessWidget {
  static const String routerName = 'home';

  const HomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('Home'),
        ),
        drawer: const SideMenu(),
        body: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('Dark Mode: ${Preferences.isDarkMode}'),
            Divider(),
            Text('Gènere: ${Preferences.genere}'),
            Divider(),
            Text('Nom d\'usuari: ${Preferences.nom}'),
            Divider()
          ],
        ));
  }
}
