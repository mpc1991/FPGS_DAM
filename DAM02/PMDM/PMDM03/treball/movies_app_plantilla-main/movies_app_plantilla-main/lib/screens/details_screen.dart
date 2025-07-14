import 'package:flutter/material.dart';
import 'package:movies_app/models/models.dart';
import 'package:movies_app/widgets/widgets.dart';

class DetailsScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final Comic comic = ModalRoute.of(context)?.settings.arguments as Comic;

    return Scaffold(
      body: CustomScrollView(
        slivers: [
          _CustomAppBar(comic: comic),
          SliverList(
            delegate: SliverChildListDelegate(
              [
                _PosterAndTitile(comic: comic),
                _Overview(comic: comic),
                CastingCards(comic.id),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

class _CustomAppBar extends StatelessWidget {
  final Comic comic;

  const _CustomAppBar({super.key, required this.comic});

  @override
  Widget build(BuildContext context) {
    // Exactament igual que la AppBaer però amb bon comportament davant scroll
    return SliverAppBar(
      backgroundColor: Colors.indigo,
      expandedHeight: 200,
      floating: false,
      pinned: true,
      flexibleSpace: FlexibleSpaceBar(
        centerTitle: true,
        titlePadding: EdgeInsets.all(0),
        title: Container(
          width: double.infinity,
          alignment: Alignment.bottomCenter,
          color: Colors.black12,
          padding: const EdgeInsets.only(bottom: 10),
          child: Text(
            comic.title,
            style: TextStyle(
              fontSize: 16,
              color: Colors.white, // Establece el color blanco para el texto
            ),
          ),
        ),
        background: FadeInImage(
          placeholder: AssetImage('assets/loading.gif'),
          image: NetworkImage(comic.getFullImagePath),
          fit: BoxFit.cover,
        ),
      ),
    );
  }
}

class _PosterAndTitile extends StatelessWidget {
  final Comic comic;

  const _PosterAndTitile({super.key, required this.comic});

  @override
  Widget build(BuildContext context) {
    final TextTheme textTheme = Theme.of(context).textTheme;
    return Container(
      margin: const EdgeInsets.only(top: 20),
      padding: const EdgeInsets.symmetric(horizontal: 20),
      child: Row(
        children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(20),
            child: FadeInImage(
              placeholder: AssetImage('assets/loading.gif'),
              image: NetworkImage(comic.getFullImagePath),
              height: 150,
            ),
          ),
          const SizedBox(
            width: 20,
          ),
          // Usamos Expanded porque el texto se salía de la pantalla
          Expanded(
              child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                comic.title,
                style: textTheme.headlineSmall,
                overflow: TextOverflow.ellipsis,
                maxLines: 2,
              ),
              Text(
                comic.format,
                style: textTheme.titleMedium,
                overflow: TextOverflow.ellipsis,
                maxLines: 2,
              ),
              Row(
                children: [
                  const Icon(Icons.star_outline, size: 15, color: Colors.grey),
                  const SizedBox(width: 5),
                  Text('Nota mitjana', style: textTheme.bodySmall),
                ],
              )
            ],
          )),
        ],
      ),
    );
  }
}

class _Overview extends StatelessWidget {
  final Comic comic;

  const _Overview({super.key, required this.comic});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 10),
      child: Text(
        // Sacado con ayuda de chat, es necesario acceder a un array y dentro de ese array, al valor text.
        // Adicionalmente, es posible que no tenga descripción
        comic.textObjects.isNotEmpty
            ? (comic.textObjects[0].text ?? "No description available.")
            : "No description available.",
        textAlign: TextAlign.justify,
        style: Theme.of(context).textTheme.titleMedium,
      ),
    );
  }
}
