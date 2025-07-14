import 'package:movies_app/models/modelsSeries.dart';


class SeriesResponse {
    int code;
    String status;
    String copyright;
    String attributionText;
    String attributionHtml;
    String etag;
    Series data;

    SeriesResponse({
        required this.code,
        required this.status,
        required this.copyright,
        required this.attributionText,
        required this.attributionHtml,
        required this.etag,
        required this.data,
    });

    factory SeriesResponse.fromJson(String str) => SeriesResponse.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory SeriesResponse.fromMap(Map<String, dynamic> json) => SeriesResponse(
        code: json["code"],
        status: json["status"],
        copyright: json["copyright"],
        attributionText: json["attributionText"],
        attributionHtml: json["attributionHTML"],
        etag: json["etag"],
        data: Series.fromMap(json["data"]),
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

