import 'models.dart';

class Characters {
    int available;
    String collectionUri;
    List<Series> items;
    int returned;

    Characters({
        required this.available,
        required this.collectionUri,
        required this.items,
        required this.returned,
    });

    // get getFullImagePath{
    //   if (this.resourceURI != null) {
    //     // Obtener el path y la ext
    //     return '${resourceURI.path}/portrait_uncanny.${resourceURI.extension}';
    //   }
    //   return 'https://i.stack.imgur.com/GNhxO.png';
    // }

    factory Characters.fromJson(String str) => Characters.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory Characters.fromMap(Map<String, dynamic> json) => Characters(
        available: json["available"],
        collectionUri: json["collectionURI"],
        items: List<Series>.from(json["items"].map((x) => Series.fromMap(x))),
        returned: json["returned"],
    );

    Map<String, dynamic> toMap() => {
        "available": available,
        "collectionURI": collectionUri,
        "items": List<dynamic>.from(items.map((x) => x.toMap())),
        "returned": returned,
    };
}