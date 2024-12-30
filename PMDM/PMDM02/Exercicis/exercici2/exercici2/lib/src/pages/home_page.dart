import 'package:exercici2/src/pages/alert_page.dart';
import 'package:exercici2/src/providers/menu_providers.dart';
import 'package:exercici2/src/utils/icono_string_util.dart';
import 'package:flutter/material.dart';

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Components'),
      ),
      body: _llista(),
    );
  }
}

_llista() {
  //menuProvider.CarregarDades().then((data) {
  //print ('Llista: ');
  //print(data);
  //});

  return FutureBuilder(
      future: menuProvider.CarregarDades(), //enllaçat al que esperam
      initialData: [], // opcional, valor inicial
      builder: (context, AsyncSnapshot<List<dynamic>> snapshot) {
        //rep contexte i tipus de dada que retorna el future
        print('builder');
        print(snapshot.data);
        return ListView(
          children: _llistaElements(context, snapshot.data),
        );
      });
}

List<Widget> _llistaElements(BuildContext context, List<dynamic>? data) {
  final List<Widget> elements = [];
  data?.forEach((element) {
    final widgetTemp = ListTile(
      title: Text(element['texte']),
      leading: getIcon(element['icona']),
      trailing: Icon(Icons.keyboard_arrow_right, color: Colors.blue),
      onTap: () {
        Navigator.pushNamed(context, element['ruta']);
        //final route = MaterialPageRoute(builder: (context) {
          //return AlertPage();
        //});
      },
    );

    elements
      ..add(widgetTemp)
      ..add(Divider());
  });
  return elements;
}
