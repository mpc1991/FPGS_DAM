import 'package:movies_app/models/models.dart';

class CreditsResponse {
    int id;
    List<Cast> cast;
    List<Cast> crew;

    CreditsResponse({
        required this.id,
        required this.cast,
        required this.crew,
    });

    factory CreditsResponse.fromJson(String str) => CreditsResponse.fromMap(json.decode(str));

    //String toJson() => json.encode(toMap());

    factory CreditsResponse.fromMap(Map<String, dynamic> json) => CreditsResponse(
        id: json["id"],
        cast: List<Cast>.from(json["cast"].map((x) => Cast.fromMap(x))),
        crew: List<Cast>.from(json["crew"].map((x) => Cast.fromMap(x))),
    );

    // Map<String, dynamic> toMap() => {
    //     "id": id,
    //     "cast": List<dynamic>.from(cast.map((x) => x.toMap())),
    //     "crew": List<dynamic>.from(crew.map((x) => x.toMap())),
    // };
}

