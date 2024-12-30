import 'dart:convert';
import 'package:flutter/services.dart' show rootBundle;

class _MenuProvider {
  List<dynamic> opcions = [];
  _MenuProvider() {
    CarregarDades();
  }

  Future<List<dynamic>> CarregarDades() async {
    final resposta = await rootBundle.loadString('data/menu_opts.json');
    Map dataMap = json.decode(resposta);

    //print(dataMap); // imprimir todo
    //print(dataMap['rutes']); // imprimir sólo una ruta del json

    opcions = dataMap['rutes'];
    return opcions;
  }
}

final menuProvider = new _MenuProvider();
