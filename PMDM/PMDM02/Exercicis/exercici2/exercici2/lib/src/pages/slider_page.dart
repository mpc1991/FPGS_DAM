import 'package:flutter/material.dart';

class SliderPage extends StatefulWidget {
  const SliderPage({super.key});

  @override
  State<SliderPage> createState() => _SliderPageState();
}

class _SliderPageState extends State<SliderPage> {
  double _valueSlider = 100;
  var _valorCheckBox = true;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Slider page'),
      ),
      body: Container(
        padding: EdgeInsets.only(top: 50),
        child: Column(
          children: [
            _crearSlider(),
            _crearCheckBox(),
            _crearSwitch(),
            Expanded(child: _crearImatge()),
            
          ],
        ),
      ),
    );
  }
  
  _crearSlider() {
    return Slider(
      activeColor: Colors.indigo,
      label:  'Grandaria de l\'imatge',
      divisions: 5,
      value: _valueSlider, 
      min: 10,
      max: 400,
      onChanged: (_valorCheckBox) ? null : (valor) {
        setState(() {
          _valueSlider = valor;
        });
      });
  }

  _crearImatge(){
    return Image(
      image: NetworkImage('https://p.kindpng.com/picc/s/176-1766643_come-to-the-dart-side-flutter-custom-bottom.png'),
      width: _valueSlider,
      fit: BoxFit.contain,
    );
  }

  _crearCheckBox(){
    return CheckboxListTile(
      title: Text('Bloquejar Slider'),
      value: _valorCheckBox, 
      onChanged: (estat){
        setState(() {
          _valorCheckBox = estat!;
        });
    });
  }

  _crearSwitch(){
    return SwitchListTile(
      title: Text('Bloquejar Slider'),
      value: _valorCheckBox, 
      onChanged: (estat){
        setState(() {
          _valorCheckBox = estat!;
        });
    });
  }
}