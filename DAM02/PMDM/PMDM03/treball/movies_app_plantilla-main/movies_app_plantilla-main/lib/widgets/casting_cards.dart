import 'package:flutter/material.dart';
import 'package:movies_app/models/modelsCharacter.dart';
import 'package:movies_app/providers/comic_provider.dart';
import 'package:provider/provider.dart';

class CastingCards extends StatelessWidget {
  final int idComic;

  const CastingCards(this.idComic);

  @override
  Widget build(BuildContext context) {
    final characterProvider =
        Provider.of<ComicProvider>(context, listen: false);

    return FutureBuilder(
      future: characterProvider.getOnCharactersProvider(idComic),
      //snapshot son los datos que devuelve getOnCharactersProvider
      builder: (BuildContext context, AsyncSnapshot<List<Result>> snapshot) {
        if (!snapshot.hasData) {
          return Container(
            child: Center(child: CircularProgressIndicator()),
          );
        }

        final casting = snapshot.data!;

        return Container(
          margin: const EdgeInsets.only(bottom: 30),
          width: double.infinity,
          height: 180,
          // color: Colors.red,
          child: ListView.builder(
              itemCount: casting.length,
              scrollDirection: Axis.horizontal,
              itemBuilder: (BuildContext context, int index) =>
                  _CastCard(character: casting[index])),
        );
      },
    );
  }
}

class _CastCard extends StatelessWidget {
  final Result character;
  const _CastCard({super.key, required this.character});

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.symmetric(horizontal: 10),
      width: 110,
      height: 100,
      // color: Colors.green,
      child: Column(
        children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(20),
            child: FadeInImage(
              placeholder: AssetImage('assets/no-image.jpg'),
              image: NetworkImage(character.getFullProfilePath),
              height: 140,
              width: 100,
              fit: BoxFit.cover,
            ),
          ),
          const SizedBox(
            height: 5,
          ),
          Expanded(
            child: Text(
              character.name,
              maxLines: 2,
              overflow: TextOverflow.ellipsis,
              textAlign: TextAlign.center,
              softWrap: true,
            ),
          ),
        ],
      ),
    );
  }
}
