import 'dart:convert';
import 'package:flutter/services.dart' show rootBundle;

/*
* Clase encargada de leer el json con los datos que van a usar los botones de la página principal.
*/
class _MenuProvider {
  List<dynamic> opcions = [];
  _MenuProvider() {
    carregarDades();
  }

  Future<List<dynamic>> carregarDades() async {
    final resposta = await rootBundle.loadString('data/menu_opts.json');
    Map dataMap = json.decode(resposta);

    opcions = dataMap['rutes'];
    return opcions;
  }
}

final menuProvider = new _MenuProvider();
