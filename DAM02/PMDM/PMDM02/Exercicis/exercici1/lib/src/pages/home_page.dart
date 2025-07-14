import 'package:flutter/material.dart';

class HomePage extends StatelessWidget {
  final estil = TextStyle(fontSize: 24);
  int contador = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('$contador'),
        centerTitle: true,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('Nombre de clicks:', style: estil),
            Text('0', style: estil),

          ],
        ),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.endFloat,
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          print('hola');
          contador++;
        },
        child: Icon(Icons.add),
        
      ),
    );
  }
}