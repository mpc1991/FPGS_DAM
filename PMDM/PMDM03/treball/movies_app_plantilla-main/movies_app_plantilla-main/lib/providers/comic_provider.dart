import 'package:flutter/material.dart';
import 'dart:convert' as convert;
import 'package:http/http.dart' as http;
import 'package:movies_app/models/models.dart';

class ComicProvider extends ChangeNotifier{
  String _baseURL = 'gateway.marvel.com';
  String _comicsEndpoint = 'v1/public/comics'; // debe ir modificandose
  String _popularEndpoint = '3/movie/popular';
  String _apiKey = '52d9b256a95352280deb22d50b1255be';
  String _ts = '1991';
  String _hash = 'c561570af98ad4f900bd7bdeca217eb3';

  String _language = 'en-US';
  String _page = '1';

  List<Comic> onDisplayComic = [];

    ComicProvider(){
      print('1');
      getOnComicProvider();

    }

    getOnComicProvider() async { // realizará solicitud http al servidor para recibir la info
      print('2');
      var url =
        //Uri.https('www.googleapis.com', '/books/v1/volumes', {'q': '{http}'});
        Uri.https(_baseURL, _comicsEndpoint, {
          'ts': _ts,
          'api_key': _apiKey,
          'hash': _hash
          //'language': _language,
          //'page' : _page
          });

    // Await the http get response, then decode the json-formatted response.
    final result  = await http.get(url);
    final comicResponse = ComicResponse.fromJson(result.body);

    onDisplayComic = comicResponse.data?.results ?? []; // llenamos la lista
    notifyListeners(); // avisar de que se ha llenado la lista y repinta la app
    }
}