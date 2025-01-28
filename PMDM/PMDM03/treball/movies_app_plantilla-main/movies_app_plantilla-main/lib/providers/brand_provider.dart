import 'package:flutter/material.dart';

class BrandProvider extends ChangeNotifier{
    BrandProvider(){
      print('1');
      getOnBrandProvider();

    }

    getOnBrandProvider() async { // realizará solicitud al servidor para recibir la info
      print('2');
    }
}