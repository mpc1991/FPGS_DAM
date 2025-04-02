import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:productes_app/models/products.dart';

import 'package:http/http.dart' as http; // importamos con alias

class ProductsService extends ChangeNotifier{

  final String _baseUrl = 'flutter-app-productes-mpc-default-rtdb.europe-west1.firebasedatabase.app';
  final List<Product> products = [];
  bool isLoading = true;

  // Constructor
  ProductsService() { 
    this.loadProducts(); 
  }

  // Método a futuro
  Future loadProducts() async {
    isLoading = true;
    notifyListeners();

    final url = Uri.https(_baseUrl, 'products.json');
    final respuesta = await http.get(url); // Usamos el alias http

    final Map<String, dynamic> productsMap = json.decode(respuesta.body);
  
    productsMap.forEach((key, value) {
      final tempProduct = Product.fromMap(value); 
      tempProduct.id = key;
      products.add(tempProduct);
    });

    isLoading = false;
    notifyListeners();
  }
}