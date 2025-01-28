import 'package:movies_app/models/models.dart';

class Cast {
    bool adult;
    int gender;
    int id;
    //Department knownForDepartment;
    String knownForDepartment;
    String name;
    String originalName;
    double popularity;
    String? profilePath;
    int? castId;
    String? character;
    String creditId;
    int? order;
    //Department department;
    String? department;
    String? job;

    Cast({
        required this.adult,
        required this.gender,
        required this.id,
        required this.knownForDepartment,
        required this.name,
        required this.originalName,
        required this.popularity,
        required this.profilePath,
        required this.castId,
        required this.character,
        required this.creditId,
        required this.order,
        required this.department,
        required this.job,
    });

    get fullProfilePath{
      if (this.profilePath != null) {
        return 'https://image.tmdb.org/t/p/w500${profilePath}';
      }
      return 'https://i.stack.imgur.com/GNhxO.png';
    }

    factory Cast.fromJson(String str) => Cast.fromMap(json.decode(str));

    //String toJson() => json.encode(toMap());

    factory Cast.fromMap(Map<String, dynamic> json) => Cast(
        adult: json["adult"],
        gender: json["gender"],
        id: json["id"],
        //knownForDepartment: departmentValues.map[json["known_for_department"]],
        knownForDepartment: json["known_for_department"],
        name: json["name"],
        originalName: json["original_name"],
        popularity: json["popularity"].toDouble(),
        profilePath: json["profile_path"],
        castId: json["cast_id"],
        character: json["character"],
        creditId: json["credit_id"],
        order: json["order"],
        //department: departmentValues.map[json["department"]],
        department: json["department"],
        job: json["job"],
    );

    // Map<String, dynamic> toMap() => {
    //     "adult": adult,
    //     "gender": gender,
    //     "id": id,
    //     "known_for_department": departmentValues.reverse[knownForDepartment],
    //     "name": name,
    //     "original_name": originalName,
    //     "popularity": popularity,
    //     "profile_path": profilePath,
    //     "cast_id": castId,
    //     "character": character,
    //     "credit_id": creditId,
    //     "order": order,
    //     "department": departmentValues.reverse[department],
    //     "job": job,
    // };
}

enum Department {
    ACTING,
    ART,
    CAMERA,
    COSTUME_MAKE_UP,
    CREW,
    DIRECTING,
    EDITING,
    PRODUCTION,
    SOUND,
    VISUAL_EFFECTS,
    WRITING
}

final departmentValues = EnumValues({
    "Acting": Department.ACTING,
    "Art": Department.ART,
    "Camera": Department.CAMERA,
    "Costume & Make-Up": Department.COSTUME_MAKE_UP,
    "Crew": Department.CREW,
    "Directing": Department.DIRECTING,
    "Editing": Department.EDITING,
    "Production": Department.PRODUCTION,
    "Sound": Department.SOUND,
    "Visual Effects": Department.VISUAL_EFFECTS,
    "Writing": Department.WRITING
});

class EnumValues<T> {
    Map<String, T> map;
    late Map<T, String> reverseMap;

    EnumValues(this.map);

    Map<T, String> get reverse {
            reverseMap = map.map((k, v) => MapEntry(v, k));
            return reverseMap;
    }
}
