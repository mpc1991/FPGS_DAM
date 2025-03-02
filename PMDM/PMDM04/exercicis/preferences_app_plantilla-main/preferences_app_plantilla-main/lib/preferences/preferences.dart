import 'package:shared_preferences/shared_preferences.dart';

class Preferences {
  static late SharedPreferences _prefs;

  static String _nom = '';
  static bool _isDarkMode = false;
  static int _genere = 1;

  static Future init() async{
    _prefs = await SharedPreferences.getInstance();
  }

  static String get nom{
    return _prefs.getString('nom') ?? _nom;
  }

  static set nom(String value) {
    _nom = value;
    _prefs.setString('nom', value);
  }

  static bool get isDarkMode{
    return _prefs.getBool('darkmode') ?? _isDarkMode;
  }

  static set isDarkMode(bool value) {
    _isDarkMode = value;
    _prefs.setBool('darkmode', value);
  }

  static int get genere{
    return _prefs.getInt('genere') ?? _genere;
  }

  static set genere(int value) {
    _genere = value;
    _prefs.setInt('genere', value);
  }
}