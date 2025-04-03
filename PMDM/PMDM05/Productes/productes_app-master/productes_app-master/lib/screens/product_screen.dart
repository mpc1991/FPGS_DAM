import 'package:flutter/material.dart';
import 'package:productes_app/services/services.dart';
import 'package:productes_app/widgets/widgets.dart';
import 'package:provider/provider.dart';

import '../ui/input_decorations.dart';

class ProductScreen extends StatelessWidget {
  const ProductScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // Obtenemos el servicio de productos desde el Provider
    final ProductsService productService = Provider.of<ProductsService>(context);

    return Scaffold(
      body: SingleChildScrollView( // Permite desplazar todo el contenido si excede la pantalla
        child: Column(
          children: [
             // Sección para mostrar la imagen del producto y botones flotantes encima
            Stack(
              children: [
                ProductImage(productService.selectedProduct.picture), // Muestra la imagen del producto
                Positioned(
                  top: 60,
                  left: 20,
                  child: IconButton(
                    onPressed: () => Navigator.of(context).pop(), // Navega a la pantalla anterior
                    icon: Icon(
                      Icons.arrow_back_ios_new,
                      size: 30,
                      color: Colors.white,
                    ),
                  ),
                ),
                Positioned(
                  top: 60,
                  right: 20,
                  child: IconButton(
                    onPressed: () {
                      //TODO: Implementar funcionalitat de cercar imatge de la galeria
                    },
                    icon: Icon(
                      Icons.camera_alt_outlined,
                      size: 30,
                      color: Colors.white,
                    ),
                  ),
                ),
              ],
            ),
            _ProductForm(), // Carga el formulario para editar el producto
            SizedBox(
              height: 100, // Espacio vacío en la parte inferior
            )
          ],
        ),
      ),
      // Botón flotante para guardar el producto
      floatingActionButtonLocation: FloatingActionButtonLocation.endDocked,
      floatingActionButton: FloatingActionButton(
          child: Icon(Icons.save_outlined), // Icono de guardar
          onPressed: (() {
            //TODO: Emmagatzemar producte
          })),
    );
  }
}

class _ProductForm extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 10),
      child: Container(
        padding: EdgeInsets.symmetric(horizontal: 20),
        width: double.infinity,
        decoration: _buildBoxDecoration(),
        child: Form(
          child: Column(
            children: [
              SizedBox(height: 10),
              TextFormField(
                decoration: InputDecorations.authInputDecoration(
                    hintText: 'Nom del producte', labelText: 'Nom:'), // Campo para el nombre del producto
              ),
              SizedBox(height: 30),
              TextFormField(
                keyboardType: TextInputType.number, // Campo para el precio, solo acepta números
                decoration: InputDecorations.authInputDecoration(
                    hintText: '99€', labelText: 'Preu:'), // Campo para el precio del producto
              ),
              SizedBox(height: 30),
              SwitchListTile.adaptive(
                value: true, // Valor inicial de la disponibilidad
                title: Text('Disponible'), // Título del interruptor
                activeColor: Colors.indigo, // Color cuando el interruptor está activado
                onChanged: (value) {
                  //TODO: Implementar
                },
              ),
              SizedBox(height: 30),
            ],
          ),
        ),
      ),
    );
  }

  // Función que devuelve el BoxDecoration para el formulario
  BoxDecoration _buildBoxDecoration() => BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.only(
          bottomRight: Radius.circular(25),
          bottomLeft: Radius.circular(25),
        ),
        boxShadow: [
          BoxShadow(
              color: Colors.black.withOpacity(0.05),
              offset: Offset(0, 5),
              blurRadius: 5),
        ],
      );
}
