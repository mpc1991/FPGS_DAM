import 'package:flutter/material.dart';
import 'package:porcel_cifre_macia_pmdm02_treball/src/dto/persona.dart';
import 'package:porcel_cifre_macia_pmdm02_treball/src/pages/home_page.dart';
import 'package:porcel_cifre_macia_pmdm02_treball/src/pages/personal_page.dart';
import 'package:porcel_cifre_macia_pmdm02_treball/src/pages/widget_page.dart';

/*
* Clase donde listaremos las rutas que van a usar los botones de la página principal
*/
Map<String, WidgetBuilder> getRoutes(Persona persona){
  return <String, WidgetBuilder> {
    '/'             : (BuildContext context) => HomePage(persona),
    'personal'      : (BuildContext context) => PersonalPage(persona: persona,),
    'widget'        : (BuildContext context) => WidgetPage(),
  };
}