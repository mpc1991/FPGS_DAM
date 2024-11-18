import 'Vehicle.dart';

class Moto extends Vehicle {
  double? _cilindrada;

  // Constructor Matricula + valors predeterminats
  Moto.nomesMatricula({
    required String matricula,
    String marca = "",
    String model = "",
    bool isLlogat = false,
    String dni = "",
    double quilometratge = 0.0,
    double cilindrada = 0.0,
  }) : super.nomesMatricula(
          matricula: matricula,
          marca: marca,
          model: model,
          isLlogat: isLlogat,
          dni: dni,
          quilometratge: quilometratge,
        ) {
    _cilindrada = cilindrada;
  }

  // Constructor complet
  Moto.complet({
    required String matricula,
    required String marca,
    required String model,
    required bool isLlogat,
    required String dni,
    required double quilometratge,
    required double cilindrada,
  }) : super.complet(
            matricula: matricula,
            marca: marca,
            model: model,
            isLlogat: isLlogat,
            dni: dni,
            quilometratge: quilometratge) {
    _cilindrada = cilindrada;
  }

  get cilindrada => _cilindrada;
  set cilindrada(cilindrada) => _cilindrada = cilindrada;

  String toString() {
    return "Matrícula: $matricula, Marca: $marca, Modelo: $model, Llogat: $isLlogat, DNI: $dni, Quilometratge: $quilometratge, Cilindrada: $_cilindrada";
  }
}
