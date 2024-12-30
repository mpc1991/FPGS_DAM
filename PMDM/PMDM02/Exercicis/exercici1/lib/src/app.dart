
import 'package:exercici1/src/pages/contador_pages.dart';
import 'package:flutter/material.dart';

class saMevaApp extends StatelessWidget{
  @override
  Widget build(context){
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Center(
      child: ContadorPages(),
    ),
    );
  }
}