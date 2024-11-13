import 'Vehicle.dart';

class Cotxe extends Vehicle {

  // Constructor Matricula + valors predeterminats
  Cotxe.nomesMatricula({
    required String matricula,
    String marca = "",
    String model = "",
    bool isLlogat = false,
    String dni = "",
    double quilometratge = 0.0,
  }) : super.nomesMatricula(
    matricula: matricula,
    marca: marca,
    model: model,
    isLlogat: isLlogat,
    dni: dni,
    quilometratge: quilometratge
    );
  
  // Constructor complet
  Cotxe.complet({
    required String matricula, 
    required String marca, 
    required String model, 
    required bool isLlogat, 
    required String dni, 
    required double quilometratge,
    }) : super.complet(
    matricula: matricula,
    marca: marca,
    model: model,
    isLlogat: isLlogat,
    dni: dni,
    quilometratge: quilometratge
    );
  
  @override
  String toString(){
    return "$dni";
  }
}