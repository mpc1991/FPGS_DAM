import 'package:flutter/cupertino.dart';
import 'dart:convert' as convert;
import 'package:http/http.dart' as http;
import 'package:movies_app/models/models.dart';
import 'package:movies_app/models/movie.dart';
import 'package:movies_app/models/now_playing_response.dart';

class PersonalProvider extends ChangeNotifier {
  String _baseURL = 'gateway.marvel.com';
  String _endpoint = '3/movie/now_playing'; // debe ir modificandose
  String _popularEndpoint = '3/movie/popular';
  String _apiKey = '52d9b256a95352280deb22d50b1255be';
  String _ts = '1991';
  String _hash = 'c561570af98ad4f900bd7bdeca217eb3';
  String _language = 'en-US';
  String _page = '1';

  List<Movie> onDisplayMovies = []; // Lista vacía
  List<Movie> onPoular = [];

  Map<int, List<Cast>> casting = {}; // id de pelicula, lista del cast de esa peli


  PersonalProvider() {
    print('ASD1');
    this.getOnDisplay();
  }

  getOnDisplay() async {
    var url =
        //Uri.https('www.googleapis.com', '/books/v1/volumes', {'q': '{http}'});
        Uri.https(_baseURL, _endpoint, {
          'api_key': _apiKey,
          'language': _language,
          'page' : _page
          });

    // Await the http get response, then decode the json-formatted response.
    final result  = await http.get(url);
    final nowPlayngResponse = NowPlayingResponse.fromJson(result.body);

    onDisplayMovies = nowPlayngResponse.results; // llenamos la lista
    notifyListeners(); // avisar de que se ha llenado la lista y repinta la app
  }

  getOnPopulars() async {
    print('populars');
    var url =
        Uri.https(_baseURL, _popularEndpoint, { // modificar endpoint
          'api_key': _apiKey,
          'language': _language,
          'page' : _page
          });

    // Await the http get response, then decode the json-formatted response.
    final result  = await http.get(url);
    final nowPlayngResponse = NowPlayingResponse.fromJson(result.body);

    onDisplayMovies = nowPlayngResponse.results; // llenamos la lista
    notifyListeners(); // avisar de que se ha llenado la lista y repinta la app
  }

  Future<List<Cast>> getMovieCast(int idMovie) async {
    print('Demanam info al servidor');
    var url =
        //Uri.https('www.googleapis.com', '/books/v1/volumes', {'q': '{http}'});
        Uri.https(_baseURL, '3/movie/$idMovie/credits', {
          'api_key': _apiKey,
          'language': _language,
          });

    // Await the http get response, then decode the json-formatted response.
    final result  = await http.get(url);
    final creditsResponse = CreditsResponse.fromJson(result.body);

    casting[idMovie] = creditsResponse.cast;
    return creditsResponse.cast;
  }
}
