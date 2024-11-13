class Moto {
  String? _matricula;
  String? _marca;
  String? _model;
  bool? _isLlogat;
  String? _dni;
  double? _quilometratge;
  double? _cilindrada;

  // Constructor buit
  Moto(){}

  // Constructor Matricula + valors predeterminats
  Moto.nomesMatricula({
    required String matricula,
    String marca = "",
    String model = "",
    bool isLlogat = false,
    String dni = "",
    double quilometratge = 0.0,
    double cilindrada = 0,
  }) {
    _matricula = matricula;
    _marca = marca;
    _model = model;
    _isLlogat = isLlogat;
    _dni = dni;
    _quilometratge = quilometratge;
    _cilindrada = cilindrada;
  }

  // Constructor complet
  Moto.complet({
    required matricula, 
    required marca, 
    required model, 
    required bool isLlogat, 
    required dni, 
    required double quilometratge,
    required double cilindrada,
    }){
    _matricula = matricula;
    _marca = marca;
    _model = model;
    _isLlogat = isLlogat;
    _dni = dni;
    _quilometratge = quilometratge;
    _cilindrada = cilindrada;
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

  get cilindrada => _cilindrada;
  set cilindrada (cilindrada) => _cilindrada = cilindrada;

  String toString(){
    return "$_dni";
  }
}