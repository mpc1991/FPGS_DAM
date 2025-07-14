import 'package:flutter/material.dart';

class ContadorPages extends StatefulWidget{

  @override
  createState(){
    return _ContadorPagesState();
  }
}

class _ContadorPagesState extends State<ContadorPages> {
  final _estil = TextStyle(fontSize: 24);
  int _contador = 0;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Contador amb statefull'),
        centerTitle: true,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('Nombre de clicks:', style: _estil),
            Text('$_contador'),
          ],
        ),
      ),
      //floatingActionButtonLocation: FloatingActionButtonLocation.endFloat,
      floatingActionButton: _crearBotons(),
    );
  }

  Widget _crearBotons(){
    return Row(
      mainAxisAlignment: MainAxisAlignment.end,
      children: <Widget>[
        SizedBox(width: 30.0,),
        FloatingActionButton(child: Icon(Icons.exposure_zero), onPressed: _reiniciar),
        Expanded(child: SizedBox()),
        FloatingActionButton(child: Icon(Icons.remove), onPressed: _restar),
        SizedBox(width: 5.0,),
        FloatingActionButton(child: Icon(Icons.add), onPressed: _sumar),
      ],
    );
  }

void _sumar(){
  setState(() {
    _contador++;
  });
}

void _restar(){
  setState(() {
    _contador--;
  });
}

void _reiniciar(){
  setState(() { 
    _contador = 0;
  });
}
}