import 'package:flutter/material.dart';
import 'package:productes_app/models/models.dart';

// Provider que gestiona el estado del formulario para editar un producto
// Se utiliza para validar y actualizar el producto mientras se edita en la UI
class ProductFormProvider extends ChangeNotifier{
  // Llave global usada para acceder al estado del formulario (validación, reset, etc.)
  GlobalKey<FormState> formKey = new GlobalKey<FormState>();

  // Producto temporal que se edita antes de guardarlo
  //Product tempProduct;    // Forma Dart
  late Product tempProduct; // Forma Java
  
  // Constructor que recibe el producto y lo guarda como temporal
  //ProductFromProvider(this.tempProduct);  // Forma Dart
  ProductFormProvider(Product tempProduct){ // Forma Java
    this.tempProduct = tempProduct;
  }

  // Métodos
  bool isValidForm(){
    print(tempProduct.name);
    print(tempProduct.price);
    print(tempProduct.available);
    print(tempProduct.available);
    return formKey.currentState?.validate() ?? false; // Verifica si el formulario es válido
  }

  // Notifica a los widgets que la disponibilidad del producto ha cambiado
  updateAvailability(){
    print(tempProduct.available);
    notifyListeners();
  } // alternativa Dart
  updateAvailabilityDart(bool value){
    print(value);
    this.tempProduct.available = value;
    notifyListeners();
  }

}