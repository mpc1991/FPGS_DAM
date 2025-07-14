class Client {
  late String _dni;
  late String _nom;
  String? _llinatges;
  String? _nomComplet;
  String? _correu;
  int? _telefon;
  int? _targetaCredit;

  // Todos son named
  // DNI y nom son obligatorios
  // El resto son opcionales
  Client(
      {required dni,
      required String nom,
      String? llinatges,
      String? correu,
      int? telefon,
      int? targetaCredit}) {
    _dni = dni;
    _nom = nom;
    _llinatges = llinatges;
    _correu = correu;
    _telefon = telefon;
    _targetaCredit = targetaCredit;
    _nomComplet = "$nom $llinatges";
  }

  get dni => _dni;
  set dni(dni) => _dni = dni;
  get nom => _nom;
  set nom(nom) => _nom = nom;
  get llinatges => _llinatges;
  set llinatges(llinatges) => _llinatges = llinatges;
  get nomComplet => _nomComplet;
  set nomComplet(nomComplet) => _nomComplet = nomComplet;
  get correu => _correu;
  set correu(correu) => _correu = correu;
  get telefon => _telefon;
  set telefon(telefon) => _telefon = telefon;
  get targetaCredit => _targetaCredit;
  set targetaCredit(targetaCredit) => _targetaCredit = targetaCredit;

  toString() {
    return "Nom: $_nom";
  }
}
