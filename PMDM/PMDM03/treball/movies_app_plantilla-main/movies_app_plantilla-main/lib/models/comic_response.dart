import 'package:movies_app/models/models.dart';

class ComicResponse {
  int code;
  String? status;
  String? copyright;
  String? attributionText;
  String? attributionHtml;
  String? etag;
  Data data;

  ComicResponse({
    required this.code,
    required this.status,
    required this.copyright,
    required this.attributionText,
    required this.attributionHtml,
    required this.etag,
    required this.data,
  });

  factory ComicResponse.fromJson(String str) =>
      ComicResponse.fromMap(json.decode(str));

  //String toJson() => json.encode(toMap());

  factory ComicResponse.fromMap(Map<String, dynamic> json) => ComicResponse(
        code: json["code"],
        status: json["status"],
        copyright: json["copyright"],
        attributionText: json["attributionText"],
        attributionHtml: json["attributionHTML"],
        etag: json["etag"],
        data: Data.fromMap(json["data"]),
        //data: json["data"] != null ? Data.fromMap(json["data"]) : null, // Verificar si 'data' es null
        // data: json["data"] != null ? Data.fromMap(json["data"]) : Data(
        //   offset: 0,
        //   limit: 0,
        //   total: 0,
        //   count: 0,
        //   results: [],

        //),  // Default empty Data if null
      );

  // Map<String, dynamic> toMap() => {
  //     "code": code,
  //     "status": status,
  //     "copyright": copyright,
  //     "attributionText": attributionText,
  //     "attributionHTML": attributionHtml,
  //     "etag": etag,
  //     "data": data.toMap(),
  // };
}

class Data {
  int offset;
  int limit;
  int total;
  int count;
  List<Comic> results;

  Data({
    required this.offset,
    required this.limit,
    required this.total,
    required this.count,
    required this.results,
  });

  factory Data.fromJson(String str) => Data.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory Data.fromMap(Map<String, dynamic> json) => Data(
        offset: json["offset"],
        limit: json["limit"],
        total: json["total"],
        count: json["count"],
        results: List<Comic>.from(json["results"].map((x) => Comic.fromMap(x))),
      );

  Map<String, dynamic> toMap() => {
        "offset": offset,
        "limit": limit,
        "total": total,
        "count": count,
        "results": List<dynamic>.from(results.map((x) => x.toMap())),
      };
}