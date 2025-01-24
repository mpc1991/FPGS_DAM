import 'package:flutter/material.dart';

/*
* Clase donde listaremos los iconos que van a usar los botones de la página principal
*/
Icon getIcon(String nomIcona) {
  return Icon(_icons[nomIcona], color: Colors.blue);
}

final _icons = <String, IconData>{
  'personal'        : Icons.person_rounded,
  'widget'          : Icons.widgets,
};
