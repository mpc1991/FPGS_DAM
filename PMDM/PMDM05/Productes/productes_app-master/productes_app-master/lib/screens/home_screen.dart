import 'package:flutter/material.dart';
import 'package:productes_app/screens/loading_screen.dart';
import 'package:productes_app/services/services.dart';
import 'package:productes_app/widgets/widgets.dart';
import 'package:provider/provider.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // Obtenemos el servicio de productos a través del Provider
    final productService = Provider.of<ProductsService>(context); // variable de service/productService
    
    // Si los productos aún están cargando, mostramos la pantalla de carga
    // Cuando notifyListeners() se llame, la pantalla se reconstruirá automáticamente.
    if (productService.isLoading) return LoadingScreen();

    return Scaffold(
      appBar: AppBar(
        title: Text('Productes'),
      ),
      
      body: ListView.builder( // constructor que genera dinámicamente los widgets de la lista.
        itemCount: productService.products.length, // Número de productos en la lista
        // bucle for, donde index toma valores desde 0 hasta products.length - 1.
        
        itemBuilder: (BuildContext context, int index) => GestureDetector(
          // Widget para mostrar la información del producto
          child: ProductCard(product: productService.products[index]), // Producto en la posición "i"
          // Equivalencia en JAVA
          // for (int index = 0; index < productService.products.length; index++) {
            // Widget productWidget = ProductCard(product: productService.products[index]);
          //}

          // Al hacer tap en un producto, navegamos a la pantalla de detalles
          onTap: () {
            // Guardamos el producto seleccionado para modificarlo o visualizarlo
            // Rellenamos el atributo selectedProduct de la classe services/product_services
            productService.selectedProduct = productService.products[index].getProduct(); // setter en Dart
            productService.setProduct(productService.products[index].getProduct()); // setter en Java
            Navigator.of(context).pushNamed('product');
          }
        ),
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add), // Icono de añadir producto
        onPressed: () {},
      ),
    );
  }
}
