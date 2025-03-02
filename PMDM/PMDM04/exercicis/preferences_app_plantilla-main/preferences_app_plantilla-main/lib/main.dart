import 'package:flutter/material.dart';
import 'package:preferences_app_plantilla/preferences/preferences.dart';
import 'package:preferences_app_plantilla/providers/theme_provider.dart';
import 'package:provider/provider.dart';

import 'screens/screens.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized(); // Widget para asegurar ejecutar metodos asincronos antes del runApp
  await Preferences.init();
  runApp(MultiProvider(
    providers: [  //Inicializar el proveedor de serviocio para cambiar el color del Theme
      ChangeNotifierProvider(create: ( _ ) => ThemeProvider(isDarkMode: Preferences.isDarkMode))
    ],
    child: MyApp()));
} 

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Material App',
      initialRoute: HomeScreen.routerName,
      routes: {
        HomeScreen.routerName: (_) => const HomeScreen(),
        SettingsScreen.routerName: (_) => const SettingsScreen()
      },
      theme: Provider.of<ThemeProvider>(context).currentTheme,
    );
  }
}
