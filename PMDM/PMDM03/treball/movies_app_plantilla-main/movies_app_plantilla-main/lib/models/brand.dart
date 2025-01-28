import 'package:movies_app/models/models.dart';

class Brand {
    String id;
    int width;
    int height;
    String url;

    Brand({
        required this.id,
        required this.width,
        required this.height,
        required this.url,
    });

    factory Brand.fromJson(String str) => Brand.fromMap(json.decode(str));

    //String toJson() => json.encode(toMap());

    factory Brand.fromMap(Map<String, dynamic> json) => Brand(
        id: json["id"],
        width: json["width"],
        height: json["height"],
        url: json["url"],
    );

    Map<String, dynamic> toMap() => {
        "id": id,
        "width": width,
        "height": height,
        "url": url,
    };
}

class Weight {
    String imperial;
    String metric;

    Weight({
        required this.imperial,
        required this.metric,
    });

    factory Weight.fromJson(String str) => Weight.fromMap(json.decode(str));

    //String toJson() => json.encode(toMap());

    factory Weight.fromMap(Map<String, dynamic> json) => Weight(
        imperial: json["imperial"],
        metric: json["metric"],
    );

    Map<String, dynamic> toMap() => {
        "imperial": imperial,
        "metric": metric,
    };
}