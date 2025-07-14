import 'package:flutter/material.dart';

class AvatarPage extends StatelessWidget {
  const AvatarPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Pagina Avatar'),
        actions: [
          Container(
            padding: EdgeInsets.all(5),
            child: CircleAvatar(
              backgroundImage: NetworkImage('https://media.entertainmentearth.com/assets/images/4724d1e543034d5bb3a837d425315232xl.jpg'),
              radius: 30,
            ),
          ),
          Container(
            margin: EdgeInsets.only(right: 10),
            child: CircleAvatar(
              child: Text('MP'),
              backgroundColor: Colors.brown,
            ),
          ),
        ],
      ),
      body: Center(
        child: FadeInImage(
          image: NetworkImage('https://media.entertainmentearth.com/assets/images/4724d1e543034d5bb3a837d425315232xl.jpg'),
        placeholder: AssetImage('assets/jar-loading.gif')
        ),
      ),
    );
  }
}
