import 'package:movies_app/models/models.dart';
import 'package:movies_app/models/modelsCharacter.dart';

class CharactersResponse {
  int code;
  String status;
  String copyright;
  String attributionText;
  String attributionHtml;
  String etag;
  Character data;

  CharactersResponse({
    required this.code,
    required this.status,
    required this.copyright,
    required this.attributionText,
    required this.attributionHtml,
    required this.etag,
    required this.data,
  });

  factory CharactersResponse.fromJson(String str) =>
      CharactersResponse.fromMap(json.decode(str));

  //String toJson() => json.encode(toMap());

  factory CharactersResponse.fromMap(Map<String, dynamic> json) =>
      CharactersResponse(
        code: json["code"],
        status: json["status"],
        copyright: json["copyright"],
        attributionText: json["attributionText"],
        attributionHtml: json["attributionHTML"],
        etag: json["etag"],
        data: Character.fromMap(json["data"]),
      );

  Map<String, dynamic> toMap() => {
        "code": code,
        "status": status,
        "copyright": copyright,
        "attributionText": attributionText,
        "attributionHTML": attributionHtml,
        "etag": etag,
        "data": data.toMap(),
      };
}