import 'dart:math';

import 'package:flutter/material.dart';

class CardPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Cards'),
      ),
      body: ListView(
        padding: EdgeInsets.all(10),
        children: [
          _cardTipus1(),
          SizedBox(height: 30),
          _cardTipus2(),
          SizedBox(height: 30),
          _cardTipus3(),
          SizedBox(height: 30),
          _cardTipus1(),
          SizedBox(height: 30),
          _cardTipus2(),
          SizedBox(height: 30),
          _cardTipus3(),
          SizedBox(height: 30),
          _cardTipus1(),
          SizedBox(height: 30),
          _cardTipus2(),
          SizedBox(height: 30),
          _cardTipus3(),
          SizedBox(height: 30),
          _cardTipus1(),
          SizedBox(height: 30),
          _cardTipus2(),
          SizedBox(height: 30),
          _cardTipus3(),
          SizedBox(height: 30),
        ],
      ),
    );
  }
}

_cardTipus3() {
  final targeta = Container(
    //elevation: 10, //sombretja devall la tarjeta
    //clipBehavior: Clip.antiAlias, // para redondear tarjetas con imagenes
    child: Column(
      children: [
        //Image(
        //  image: NetworkImage('https://getwallpapers.com/wallpaper/full/e/5/a/299879.jpg'),
        //),
        FadeInImage(
          placeholder: AssetImage('assets/jar-loading.gif'),
          image: NetworkImage(
              'https://getwallpapers.com/wallpaper/full/e/5/a/299879.jpg'),
              //fadeInDuration: Duration(microseconds: 100), // tiempo que va a mostrar la imagen de carga, si es demasiado corta, peta?
              height: 250,
              fit: BoxFit.cover,
        ),
        Padding(
          padding: const EdgeInsets.all(8.0),
          child: Text('Puente en lago'),
        ),
      ],
    ),
  );
  return Container(
    child: ClipRRect(
      child: targeta,
      borderRadius: BorderRadius.circular(30),
    ),
    decoration: BoxDecoration(
      borderRadius: BorderRadius.circular(30),
      boxShadow: <BoxShadow> [
        BoxShadow(
          color: Colors.black26,
          blurRadius: 10,
          spreadRadius: 2,
          offset: Offset(2, 10)
        )
      ],
      color: Colors.white
      //color:Colors.red
    ),
  );
}

_cardTipus2() {
  return Card(
    elevation: 10, //sombretja devall la tarjeta
    clipBehavior: Clip.antiAlias, // para redondear tarjetas con imagenes
    child: Column(
      children: [
        //Image(
        //  image: NetworkImage('https://getwallpapers.com/wallpaper/full/e/5/a/299879.jpg'),
        //),
        FadeInImage(
          placeholder: AssetImage('assets/jar-loading.gif'),
          image: NetworkImage(
              'https://getwallpapers.com/wallpaper/full/e/5/a/299879.jpg'),
              //fadeInDuration: Duration(microseconds: 100), // tiempo que va a mostrar la imagen de carga, si es demasiado corta, peta?
              height: 250,
              fit: BoxFit.cover,
        ),
        Padding(
          padding: const EdgeInsets.all(8.0),
          child: Text('Puente en lago'),
        ),
      ],
    ),
  );
}

_cardTipus1() {
  return Card(
    elevation: 10, //sombretja devall la tarjeta
    shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(30)),
    child: Column(
      children: [
        ListTile(
          leading: Icon(
            Icons.photo_album,
            color: Colors.blue,
          ),
          title: Text('prova'),
          subtitle: Text(
              'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.'),
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.end,
          children: [
            TextButton(onPressed: () {}, child: Text('ok')),
            TextButton(onPressed: () {}, child: Text('Cancelar')),
          ],
        ),
      ],
    ),
  );
}
