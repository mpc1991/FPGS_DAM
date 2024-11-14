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

  void llogar(){
    _isLlogat = true;
  }
  void retornar(){
    _isLlogat = false;
  }
  estaLlogat() {
    return _isLlogat;
  }

  @override
  compareTo(Vehicle a, Vehicle b) {
    if (a is Cotxe && b is Cotxe) { return a.quilometratge.compareTo(b.quilometratge);
    } else if (a is Moto && b is Moto) {return a.quilometratge.compareTo(b.quilometratge);
    } else { return -1;}
  }
}
