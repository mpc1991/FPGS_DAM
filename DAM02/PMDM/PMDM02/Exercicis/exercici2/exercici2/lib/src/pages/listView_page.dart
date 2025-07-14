import 'dart:async';

import 'package:flutter/material.dart';

class LlistaPage extends StatefulWidget {
  const LlistaPage({super.key});

  @override
  State<LlistaPage> createState() => _LlistaPageState();
}

class _LlistaPageState extends State<LlistaPage> {
  List<int> _llistaNombres = [];
  int _darrerItem = 0;
  ScrollController _scrollController = new ScrollController();
  bool _estaCarregant = false;

  @override
  void initState() {
    super.initState();
    _carregarNou();
    _scrollController.addListener(() {
      print(_scrollController.position.pixels);
      if (_scrollController.position.atEdge) {
        if (_scrollController.position.pixels == 0) {
          print('scroll a linici');
        } else {
          fetchData();
        }
      }
    });
  }

  void dispose(){
    super.dispose();
    _scrollController.dispose();
  }

  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Llistes'),
      ),
      body: Stack(
        children: [
          _crearLlista(),
          _crearLoading(),
        ]
      ),
    );
  }

  _crearLlista() {
    return RefreshIndicator(
      onRefresh: obtenirPagina,
      child: ListView.builder(
      controller: _scrollController,
      itemCount: _llistaNombres.length,
      itemBuilder: (BuildContext context, int index) {
        final image = _llistaNombres[index];
        return FadeInImage(
            placeholder: AssetImage('assets/jar-loading.gif'),
            image: NetworkImage('https://picsum.photos/id/$image/500/400'));
      },
    ),
    );
  }

  _carregarNou() {
    for (int i = 0; i < 3; i++) {
      _llistaNombres.add(_darrerItem);
      _darrerItem++;
    }
  }

  Future<Timer> fetchData() async {
    _estaCarregant = true;
    setState(() {});
    final duration = new Duration(seconds: 2);
    return Timer(duration, peticioHTTP);
  }

  void peticioHTTP() {
    _estaCarregant = false;
    _carregarNou();
    _scrollController.animateTo(
      _scrollController.position.pixels + 200, 
      duration: Duration(milliseconds: 250), 
      curve: Curves.fastOutSlowIn,
      );
  }
  
  _crearLoading() {
    if (_estaCarregant == true) {
      return Column(
        mainAxisAlignment: MainAxisAlignment.end,
        mainAxisSize: MainAxisSize.max,
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              CircularProgressIndicator()
            ],
          ),
          SizedBox(height: 15)
        ],
      );
    } else if (_estaCarregant == false) {
      return Container();
    }
  }

  Future<Null> obtenirPagina() async {
    final duration = new Duration (seconds: 2);
    new Timer(duration, () {
      _llistaNombres.clear();
      _darrerItem++;
      _carregarNou();
    });
    return Future.delayed(duration);
  }
}
