import 'package:card_swiper/card_swiper.dart';
import 'package:flutter/material.dart';
import 'package:movies_app/models/comic.dart';

class CardSwiper extends StatelessWidget {
  final List<Comic> comics;

  const CardSwiper({super.key, required this.comics});

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;

    return Container(
        width: double.infinity,
        // Aquest multiplicador estableix el tant per cent de pantalla ocupada 50%
        height: size.height * 0.5,
        // color: Colors.red,
        child: Swiper(
          itemCount: comics.length,
          layout: SwiperLayout.STACK,
          itemWidth: size.width * 0.6,
          itemHeight: size.height * 0.4,
          itemBuilder: (BuildContext context, int index) {
            final comic = comics[index];
            return GestureDetector(
              onTap: () => Navigator.pushNamed(context, 'details',
                  arguments: comic),
              child: ClipRRect(
                borderRadius: BorderRadius.circular(20),
                child: FadeInImage(
                    placeholder: AssetImage('assets/no-image.jpg'),
                    image: NetworkImage(comic.getFullImagePath),
                    fit: BoxFit.cover),
              ),
            );
          },
        ));
  }
}
