import 'package:flutter/material.dart';
import 'dart:convert' as convert;
import 'package:http/http.dart' as http;
import 'package:movies_app/models/character_response.dart';
import 'package:movies_app/models/models.dart' as models;
import 'package:movies_app/models/modelsCharacter.dart' as modelsCharacter;
import 'package:movies_app/models/modelsSeries.dart' as modelsSeries;

class ComicProvider extends ChangeNotifier {
  String _provider = 'https://developer.marvel.com/account';
  String _conversorHash = 'https://cryptii.com/pipes/md5-hash';

  String _baseURL = 'gateway.marvel.com';
  String _apiKey = 'bf1a25e7ec20c136eb8286d63b4a17fa';
  String _ts = '1991';
  String _hash = 'c561570af98ad4f900bd7bdeca217eb3';

  List<models.Comic> onDisplayComic = [];
  List<modelsSeries.Result> onDisplaySerie = [];
  Map<int, List<modelsCharacter.Result>> characters = {};

  ComicProvider() {
    getOnComicProvider();
    getOnSeriesProvider();
  }

  getOnComicProvider() async {
    // realizará solicitud http al servidor para recibir la info
    String _comicsEndpoint = 'v1/public/comics'; // debe ir modificandose
    var url = Uri.https(_baseURL, _comicsEndpoint,
        {'ts': _ts, 'apikey': _apiKey, 'hash': _hash});
    final result = await http.get(url);
    final comicResponse = models.ComicResponse.fromJson(result.body);
    onDisplayComic = comicResponse.data.results; // llenamos la lista

    notifyListeners(); // avisar de que se ha llenado la lista y repinta la app
  }

  getOnSeriesProvider() async {
    String _seriesEndpoint = 'v1/public/series'; // debe ir modificandose
    var url = Uri.https(_baseURL, _seriesEndpoint,
        {'ts': _ts, 'apikey': _apiKey, 'hash': _hash});
    final result = await http.get(url);
    final seriesResponse = modelsSeries.SeriesResponse.fromJson(result.body);
    onDisplaySerie = seriesResponse.data.results; // llenamos la lista

    notifyListeners(); // avisar de que se ha llenado la lista y repinta la app
  }

  Future<List<modelsCharacter.Result>> getOnCharactersProvider(
      int idComic) async {
    // realizará solicitud http al servidor para recibir la info
    var url = Uri.https(_baseURL, 'v1/public/comics/$idComic/characters',
        {'ts': _ts, 'apikey': _apiKey, 'hash': _hash});
    final result = await http.get(url);
    final characterResponse = CharactersResponse.fromJson(result.body);
    characters[idComic] = characterResponse.data.results; // llenamos la lista

    notifyListeners(); // avisar de que se ha llenado la lista y repinta la app
    return characterResponse.data.results;
  }
}
