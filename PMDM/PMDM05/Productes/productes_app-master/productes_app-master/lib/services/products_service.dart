import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:productes_app/models/products.dart';

import 'package:http/http.dart' as http; // importamos con alias

// Clase que gestiona la lógica de productos y se comunica con Firebase
// Extiende ChangeNotifier para permitir notificar a los widgets cuando cambian los datos
class ProductsService extends ChangeNotifier{

  // URL de FireBase
  final String _baseUrl = 'flutter-app-productes-mpc-default-rtdb.europe-west1.firebasedatabase.app';
  final List<Product> products = []; // lista de productos
  // producto a modificar antes de guardarlo en la lista y la BBDD
  late Product selectedProduct; // se rellena desde home_screen en onTap().
  bool isLoading = true; 
  bool isSaving = false;

  // Constructor, carga los productos
  ProductsService() { 
    this.loadProducts(); 
  }

  // // Método asincrónico para cargar productos desde Firebase
  Future loadProducts() async {
    isLoading = true; // Indicamos que estamos cargando datos
    notifyListeners(); // Notificamos a los widgets que dependen de este provider

    // Construimos la URL para hacer la petición GET a Firebase
    final url = Uri.https(_baseUrl, 'products.json');
    final respuesta = await http.get(url); // Hacemos la petición HTTP GET

    // Convertimos la respuesta JSON en un mapa de productos
    final Map<String, dynamic> productsMap = json.decode(respuesta.body);
  
    // Recorremos cada producto recibido y lo agregamos a la lista
    products.clear();
    productsMap.forEach((key, value) {
      final tempProduct = Product.fromMap(value); // Convertimos el JSON a un objeto Product
      tempProduct.id = key; // Asignamos el ID del producto desde la base de datos
      products.add(tempProduct); // Lo añadimos a la lista de productos
    });

    isLoading = false;
    notifyListeners(); // Notificamos a los widgets para actualizar la UI
  }

  void setProduct(Product product) {
    this.selectedProduct = product;
  }

  Future saveOrCreateProduct (Product product) async {
    isSaving = true;
    notifyListeners();

      if (product.id == null) {
        // Creant producte

      } else {
        // Actualitzant producte
        await updateProduct(product);
      }

    isSaving = false;
    notifyListeners();
  }

  Future<String> updateProduct (Product product) async {
    // Construimos la URL para hacer la petición PUT a Firebase
    final url = Uri.https(_baseUrl, 'products/${product.id}.json');
    final respuesta = await http.put(url, body: product.toJson()); // Hacemos la petición HTTP PUT
    final decodeData = respuesta.body;
    print (decodeData);

    // Actualiza la lista local de productos
    // LoadProducts vacía la lista y vuelve a llenarla con los productos actuales.
    await loadProducts();

    // Forma para quitar el producto de la lista local sin llamar a loadProducts
    //final index = this.products.indexWhere((element) => element.id == product.id);
    //this.products[index] = product;

    return product.id!;

  }
}