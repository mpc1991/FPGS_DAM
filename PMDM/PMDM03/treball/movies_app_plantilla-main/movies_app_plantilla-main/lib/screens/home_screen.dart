import 'package:flutter/material.dart';
import 'package:movies_app/models/models.dart';

import 'package:movies_app/models/modelsSeries.dart';
import 'package:movies_app/providers/comic_provider.dart';
import 'package:movies_app/widgets/widgets.dart';
import 'package:provider/provider.dart';

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final comicsProvider = Provider.of<ComicProvider>(
        context); // Selecciona la primera instancia del proveedor instanciado en Main

    return Scaffold(
      appBar: AppBar(
        title: const Text('Comics'),
        elevation: 0,
        actions: [
          IconButton(onPressed: () {}, icon: const Icon(Icons.search_outlined))
        ],
      ),
      body: SingleChildScrollView(
        child: Container(
          child: Column(
            children: [
              // Targetes principals
              CardSwiper(comics: comicsProvider.onDisplayComic),

              // Slider de pel·licules
              MovieSlider(series: comicsProvider.onDisplaySerie),
              // Poodeu fer la prova d'afegir-ne uns quants, veureu com cada llista és independent
              // MovieSlider(),
              // MovieSlider(),
            ],
          ),
        ),
      ),
    );
  }
}
