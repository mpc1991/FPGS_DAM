import 'package:flutter/material.dart';

import 'package:movies_app/models/models.dart' as models;

class CastingCards extends StatelessWidget {
  final List<models.Series> characters;

  const CastingCards({super.key, required this.characters});
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.only(bottom: 30),
      width: double.infinity,
      height: 180,
      // color: Colors.red,
      child: ListView.builder(
          itemCount: characters.length,
          scrollDirection: Axis.horizontal,
          itemBuilder: (BuildContext context, int index) => _CastCard(character: characters[index])),
    );
  }
}

class _CastCard extends StatelessWidget {
  final models.Series character;

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
              image: NetworkImage(character.getFullImagePath),
              height: 140,
              width: 100,
              fit: BoxFit.cover,
            ),
          ),
          const SizedBox(
            height: 5,
          ),
          Text(
            character.name,
            maxLines: 2,
            overflow: TextOverflow.ellipsis,
            textAlign: TextAlign.center,
          )
        ],
      ),
    );
  }
}
