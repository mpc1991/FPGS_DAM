import 'package:flutter/material.dart';

class HomePageTemp extends StatelessWidget {
  final elements = ['Element1', 'Element2'];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('Components Temp'),
        ),
        body: ListView(
          children: _crearElementsCurt(),
        ));
  }

  List<Widget> _crearElements() {
    List<Widget> llista = [];

    for (String element in elements) {
      final tempWidget = ListTile(
        title: Text(element),
      );
      llista.add(tempWidget);
      llista.add(Divider());
    }
    return llista;
  }

  List<Widget> _crearElementsCurt() {
    var widgets = elements.map((element) {
      return Column(
        children: [
          ListTile(
            title: Text(element + '!'),
          ),
        ],
      );
    });

    return widgets.toList();
  }
}
