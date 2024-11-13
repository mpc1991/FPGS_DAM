abstract class Vehicle {

  String? _matricula;
  String? _marca;
  String? _model;
  bool? _isLlogat;
  String? _dni;
  double? _quilometratge;

  // Constructor buit
  Vehicle(){}

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
    required matricula, 
    required marca, 
    required model, 
    required bool isLlogat, 
    required dni, 
    required double quilometratge,
    }){
    _matricula = matricula;
    _marca = marca;
    _model = model;
    _isLlogat = isLlogat;
    _dni = dni;
    _quilometratge = quilometratge;
  }
 
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

  String toString(){
    return "$_dni";
  }
}