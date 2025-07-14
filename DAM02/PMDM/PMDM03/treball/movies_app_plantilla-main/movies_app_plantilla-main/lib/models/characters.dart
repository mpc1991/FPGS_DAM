import 'package:movies_app/models/models.dart';

class Character {
  int offset;
  int limit;
  int total;
  int count;
  List<Result> results;

  Character({
    required this.offset,
    required this.limit,
    required this.total,
    required this.count,
    required this.results,
  });

  factory Character.fromJson(String str) => Character.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory Character.fromMap(Map<String, dynamic> json) => Character(
        offset: json["offset"],
        limit: json["limit"],
        total: json["total"],
        count: json["count"],
        results:
            List<Result>.from(json["results"].map((x) => Result.fromMap(x))),
      );

  Map<String, dynamic> toMap() => {
        "offset": offset,
        "limit": limit,
        "total": total,
        "count": count,
        "results": List<dynamic>.from(results.map((x) => x.toMap())),
      };
}

class Result {
  int id;
  String name;
  String description;
  String modified;
  Thumbnail thumbnail;
  String resourceUri;
  Comics comics;
  Comics series;
  Stories stories;
  Comics events;
  List<Url> urls;

  Result({
    required this.id,
    required this.name,
    required this.description,
    required this.modified,
    required this.thumbnail,
    required this.resourceUri,
    required this.comics,
    required this.series,
    required this.stories,
    required this.events,
    required this.urls,
  });

  get getFullProfilePath {
    if (this.thumbnail != null && thumbnail.path.isNotEmpty) {
      // Obtener el path y la ext
      return '${thumbnail.path}/portrait_small.${thumbnail.extension}';
      //return 'http://i.annihil.us/u/prod/marvel/i/mg/3/50/531771b4e8c60/portrait_uncanny.jpg';
    }
    return 'https://i.stack.imgur.com/GNhxO.png';
  }

  factory Result.fromJson(String str) => Result.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory Result.fromMap(Map<String, dynamic> json) => Result(
        id: json["id"],
        name: json["name"],
        description: json["description"],
        modified: json["modified"],
        thumbnail: Thumbnail.fromMap(json["thumbnail"]),
        resourceUri: json["resourceURI"],
        comics: Comics.fromMap(json["comics"]),
        series: Comics.fromMap(json["series"]),
        stories: Stories.fromMap(json["stories"]),
        events: Comics.fromMap(json["events"]),
        urls: List<Url>.from(json["urls"].map((x) => Url.fromMap(x))),
      );

  Map<String, dynamic> toMap() => {
        "id": id,
        "name": name,
        "description": description,
        "modified": modified,
        "thumbnail": thumbnail.toMap(),
        "resourceURI": resourceUri,
        "comics": comics.toMap(),
        "series": series.toMap(),
        "stories": stories.toMap(),
        "events": events.toMap(),
        "urls": List<dynamic>.from(urls.map((x) => x.toMap())),
      };
}

class Comics {
  int available;
  String collectionUri;
  List<ComicsItem> items;
  int returned;

  Comics({
    required this.available,
    required this.collectionUri,
    required this.items,
    required this.returned,
  });

  factory Comics.fromJson(String str) => Comics.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory Comics.fromMap(Map<String, dynamic> json) => Comics(
        available: json["available"],
        collectionUri: json["collectionURI"],
        items: List<ComicsItem>.from(
            json["items"].map((x) => ComicsItem.fromMap(x))),
        returned: json["returned"],
      );

  Map<String, dynamic> toMap() => {
        "available": available,
        "collectionURI": collectionUri,
        "items": List<dynamic>.from(items.map((x) => x.toMap())),
        "returned": returned,
      };
}

class ComicsItem {
  String resourceUri;
  String name;

  ComicsItem({
    required this.resourceUri,
    required this.name,
  });

  factory ComicsItem.fromJson(String str) =>
      ComicsItem.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory ComicsItem.fromMap(Map<String, dynamic> json) => ComicsItem(
        resourceUri: json["resourceURI"],
        name: json["name"],
      );

  Map<String, dynamic> toMap() => {
        "resourceURI": resourceUri,
        "name": name,
      };
}

class Stories {
  int available;
  String collectionUri;
  List<StoriesItem> items;
  int returned;

  Stories({
    required this.available,
    required this.collectionUri,
    required this.items,
    required this.returned,
  });

  factory Stories.fromJson(String str) => Stories.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory Stories.fromMap(Map<String, dynamic> json) => Stories(
        available: json["available"],
        collectionUri: json["collectionURI"],
        items: List<StoriesItem>.from(
            json["items"].map((x) => StoriesItem.fromMap(x))),
        returned: json["returned"],
      );

  Map<String, dynamic> toMap() => {
        "available": available,
        "collectionURI": collectionUri,
        "items": List<dynamic>.from(items.map((x) => x.toMap())),
        "returned": returned,
      };
}

class StoriesItem {
  String resourceUri;
  String name;
  String type;

  StoriesItem({
    required this.resourceUri,
    required this.name,
    required this.type,
  });

  factory StoriesItem.fromJson(String str) =>
      StoriesItem.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory StoriesItem.fromMap(Map<String, dynamic> json) => StoriesItem(
        resourceUri: json["resourceURI"],
        name: json["name"],
        type: json["type"],
      );

  Map<String, dynamic> toMap() => {
        "resourceURI": resourceUri,
        "name": name,
        "type": itemTypeValues.reverse[type],
      };
}

enum ItemType { COVER, EMPTY, INTERIOR_STORY, PINUP }

final itemTypeValues = EnumValues({
  "cover": ItemType.COVER,
  "": ItemType.EMPTY,
  "interiorStory": ItemType.INTERIOR_STORY,
  "pinup": ItemType.PINUP
});

class Thumbnail {
  String path;
  String extension;

  Thumbnail({
    required this.path,
    required this.extension,
  });

  factory Thumbnail.fromJson(String str) => Thumbnail.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory Thumbnail.fromMap(Map<String, dynamic> json) => Thumbnail(
        path: json["path"],
        extension: json["extension"],
      );

  Map<String, dynamic> toMap() => {
        "path": path,
        "extension": extensionValues.reverse[extension],
      };
}

enum Extension { JPG }

final extensionValues = EnumValues({"jpg": Extension.JPG});

class Url {
  String type;
  String url;

  Url({
    required this.type,
    required this.url,
  });

  factory Url.fromJson(String str) => Url.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory Url.fromMap(Map<String, dynamic> json) => Url(
        type: json["type"],
        url: json["url"],
      );

  Map<String, dynamic> toMap() => {
        "type": urlTypeValues.reverse[type],
        "url": url,
      };
}

enum UrlType { COMICLINK, DETAIL, WIKI }

final urlTypeValues = EnumValues({
  "comiclink": UrlType.COMICLINK,
  "detail": UrlType.DETAIL,
  "wiki": UrlType.WIKI
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
