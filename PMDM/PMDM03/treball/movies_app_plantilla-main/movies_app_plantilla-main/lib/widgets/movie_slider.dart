import 'package:flutter/material.dart';
import 'package:movies_app/models/modelsSeries.dart';

class MovieSlider extends StatelessWidget {
  // const MovieSlider({Key? key}) : super(key: key);
  final List<Result> series;

  const MovieSlider({super.key, required this.series});
  

  @override
  Widget build(BuildContext context) {
    print(series.length);

    return Container(
      width: double.infinity,
      height: 270,
      // color: Colors.red,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Padding(
            padding: EdgeInsets.symmetric(horizontal: 20),
            child: Text('Series',
                style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
          ),
          const SizedBox(height: 5),
          Expanded(
            child: ListView.builder(
                scrollDirection: Axis.horizontal,
                itemCount: series.length,
                itemBuilder: (_, int index) => _MoviePoster(serie: series[index])),
          )
        ],
      ),
    );
  }
}

class _MoviePoster extends StatelessWidget {
  final Result serie;

  const _MoviePoster({super.key, required this.serie});
  
  @override
  Widget build(BuildContext context) {
    return Container(
      width: 130,
      height: 190,
      // color: Colors.green,
      margin: const EdgeInsets.symmetric(horizontal: 10),
      child: Column(
        children: [
          GestureDetector(
            onTap: () => Navigator.pushNamed(context, 'details',
                arguments: serie),
            child: ClipRRect(
              borderRadius: BorderRadius.circular(20),
              child: FadeInImage(
                placeholder: AssetImage('assets/no-image.jpg'),
                image: NetworkImage(serie.getFullImagePath),
                width: 130,
                height: 190,
                fit: BoxFit.cover,
              ),
            ),
          ),
          const SizedBox(
            height: 5,
          ),
          Text(
            serie.title,
            maxLines: 2,
            overflow: TextOverflow.ellipsis,
            textAlign: TextAlign.center,
          )
        ],
      ),
    );
  }
}
