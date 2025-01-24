class Persona {
  String? _nom;
  String? _llinatges;
  String? _dataNaixament;
  String? _correu;
  String? _contrasenya;

  Persona(String nom, String llinatges, String dataNaixament, String correu,
      String contrasenya) {
    this._nom = nom;
    this._llinatges = llinatges;
    this._dataNaixament = dataNaixament;
    this._correu = correu;
    this._contrasenya = contrasenya;
  }

  Persona.biuda() {
    this._nom = '';
    this._llinatges = '';
    this._dataNaixament = '';
    this._correu = '';
    this._contrasenya = '';
  }

  @override
  String toString() {
    return 'Nom: $_nom \nLlinatges $_llinatges \nData Naizament $_dataNaixament \nCorreu $_correu';
  }

  getNom() {
    return _nom;
  }

  void setNom(String nom) {
    this._nom = nom;
  }

  getLlinatges() {
    return _llinatges;
  }

  void setLlinatges(String llinatges) {
    this._llinatges = llinatges;
  }

  getDataNaixament() {
    return _dataNaixament;
  }

  void setDataNaixament(String dataNaixament) {
    this._dataNaixament = dataNaixament;
  }

  getCorreu() {
    return _correu;
  }

  void setCorreu(String correu) {
    this._correu = correu;
  }

  getContrasenya() {
    return _contrasenya;
  }

  void setContrasenya(String contrasenya) {
    this._contrasenya = contrasenya;
  }
}
