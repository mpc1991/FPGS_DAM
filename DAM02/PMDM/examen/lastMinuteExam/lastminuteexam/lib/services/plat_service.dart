import 'package:flutter/material.dart';

class PlatService extends ChangeNotifier{
  final String _baseUrl = 'fir-api-8ad8b-default-rtdb.europe-west1.firebasedatabase.app';
  final String _plat = 'plats.json';

  PlatService(){
    this.loadPlats();
  }

  Future loadPlats() async{
    
  }
}