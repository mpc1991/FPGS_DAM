import 'Cotxe.dart';
import 'Moto.dart';

abstract class Vehicle {
  String? _matricula;
  String? _marca;
  String? _model;
  bool? _isLlogat;
  String? _dni;
  double? _quilometratge;

  // Constructor buit
  Vehicle() {}

  // Constructor Matricula + valors predeterminats
  Vehicle.nomesMatricula({
    required String matricula,
    String marca = "",
    String model = "",
    bool isLlogat = false,
    String dni = "",
    double quilometratge = 0.0,
  }) {
    _matricula = matricula;
    _marca = marca;
    _model = model;
    _isLlogat = isLlogat;
    _dni = dni;
    _quilometratge = quilometratge;
  }

  // Constructor complet
  Vehicle.complet({
    required String matricula,
    required String marca,
    required String model,
    required bool isLlogat,
    required String dni,
    required double quilometratge,
  }) {
    _matricula = matricula;
    _marca = marca;
    _model = model;
    _isLlogat = isLlogat;
    _dni = dni;
    _quilometratge = quilometratge;
  }

  // getters y setters
  get matricula => _matricula;
  set matricula(matricula) => _matricula = matricula;

  get marca => _marca;
  set marca(marca) => _marca = marca;

  get model => _model;
  set model(model) => _model = model;

  get isLlogat => _isLlogat;
  set isLlogat(isLlogat) => _isLlogat = isLlogat;

  get dni => _dni;
  set dni(dni) => _dni = dni;

  get quilometratge => _quilometratge;
  set quilometratge(quilometratge) => _quilometratge = quilometratge;

  String toString() {
    return "Matrícula: $_matricula, Marca: $_marca, Modelo: $_model, Llogat: $_isLlogat, DNI: $_dni, Quilometratge: $_quilometratge";
  }

  void llogar(String dni) {
    _isLlogat = true;
    _dni = dni;
  }

  void retornar() {
    _isLlogat = false;
    _dni = "";
  }

  estaLlogat() {
    return _isLlogat;
  }


  @override
  int compareTo(Object a) {
    if (a is Vehicle) {
      if (this.runtimeType == a.runtimeType) {
        return this.quilometratge!.compareTo(a.quilometratge!);
      } else {
        throw ArgumentError(
            'No se pueden comparar vehículos de diferentes tipos');
      }
    } else {
      throw ArgumentError('El argumento debe ser un objeto de tipo Vehicle');
    }
  }
}
