import 'package:examen_final_porcel/screens/loading_screen.dart';
import 'package:examen_final_porcel/widgets/plat_card.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../services/services.dart';

class HomeScreen extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    final platsServices = Provider.of<PlatsServices>(context);
    if (platsServices.isLoading) return LoadingScreen();
    
    return Scaffold(
      appBar: AppBar(
        title: Text('Home Screen'),
      ),

      body: ListView.builder(
        itemCount: platsServices.plats.length,
        itemBuilder: (BuildContext context, int index) => GestureDetector(
          child: PlatCard(plat: platsServices.plats[index]),
          onTap: () {
            //platsServices.newPicture = null;
            platsServices.selectedPlat = platsServices.plats[index].getPlat();
            Navigator.of(context).pushNamed('plat');
          },
        ),
      )
    );
  }
}

