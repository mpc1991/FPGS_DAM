import 'package:flutter/material.dart';
import 'dart:convert' as convert;
import 'package:http/http.dart' as http;
import 'package:movies_app/models/models.dart' as models;

class ComicProvider extends ChangeNotifier{
  String _provider = 'https://developer.marvel.com/account';
  String _conversorHash = 'https://cryptii.com/pipes/md5-hash';

  String _baseURL = 'gateway.marvel.com';
  String _comicsEndpoint = 'v1/public/comics'; // debe ir modificandose
  String _characterEndpoint = 'v1/public/characters'; 
  String _apiKey = 'bf1a25e7ec20c136eb8286d63b4a17fa';
  String _ts = '1991';
  String _hash = 'c561570af98ad4f900bd7bdeca217eb3';

  String _language = 'en-US';
  String _page = '1';

  List<models.Comic> onDisplayComic = [];
  List<models.Series> onDisplayCharacters = [];

    ComicProvider(){
      getOnComicProvider();
    }

    getOnComicProvider() async { // realizará solicitud http al servidor para recibir la info
      var url =
        //Uri.https('www.googleapis.com', '/books/v1/volumes', {'q': '{http}'});
        Uri.https(_baseURL, _comicsEndpoint, {
          'ts': _ts,
          'apikey': _apiKey,
          'hash': _hash
          });

    // Await the http get response, then decode the json-formatted response.
    final result  = await http.get(url);
    //print("Response body: ${result.body}"); // Imprime el JSON de la respuesta
    final comicResponse = models.ComicResponse.fromJson(result.body);

    onDisplayComic = comicResponse.data.results; // llenamos la lista
    
    notifyListeners(); // avisar de que se ha llenado la lista y repinta la app
    }

    getOnCharactersProvider(int comicId) async { // realizará solicitud http al servidor para recibir la info
       comicId = 291;
      var url =
        //Uri.https('www.googleapis.com', '/books/v1/volumes', {'q': '{http}'});
        //Uri.https(_baseURL, '$_comicsEndpoint/$comicId/characters', {
        Uri.https(_baseURL, _comicsEndpoint, {
          'ts': _ts,
          'apikey': _apiKey,
          'hash': _hash
          });

    // Await the http get response, then decode the json-formatted response.
    final result  = await http.get(url);
    //print("Response body: ${result.body}"); // Imprime el JSON de la respuesta
    final characterResponse = models.Characters.fromJson(result.body);

    onDisplayCharacters = characterResponse.items; // llenamos la lista
    
    notifyListeners(); // avisar de que se ha llenado la lista y repinta la app
    }
}