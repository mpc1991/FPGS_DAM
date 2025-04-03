import 'package:flutter/material.dart';
import 'package:productes_app/providers/login_form_provider.dart';
import 'package:productes_app/screens/product_screen.dart';
import 'package:productes_app/screens/screens.dart';
import 'package:productes_app/services/services.dart';
import 'package:provider/provider.dart';

void main() => runApp(AppState());

class AppState extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MultiProvider(providers: [
      ChangeNotifierProvider(
        create: ( _ ) => ProductsService(), // creamos un productServices
      ),
      ChangeNotifierProvider(
          create: (_) => LoginFormProvider(), // Proveedor para LoginFormProvider
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
      title: 'Productes App',
      initialRoute: 'home',
      routes: {
        'login': (_) => LoginScreen(),
        'home': (_) => HomeScreen(),
        'product': (_) => ProductScreen(),
      },
      theme: ThemeData.light().copyWith(
        scaffoldBackgroundColor: Colors.grey[300],
      ),
    );
  }
}
