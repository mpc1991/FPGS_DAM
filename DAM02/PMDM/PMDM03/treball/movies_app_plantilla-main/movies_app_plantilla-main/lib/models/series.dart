import 'package:movies_app/models/modelsSeries.dart';

class Series {
    int offset;
    int limit;
    int total;
    int count;
    List<Result> results;

    Series({
        required this.offset,
        required this.limit,
        required this.total,
        required this.count,
        required this.results,
    });

    factory Series.fromJson(String str) => Series.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory Series.fromMap(Map<String, dynamic> json) => Series(
        offset: json["offset"],
        limit: json["limit"],
        total: json["total"],
        count: json["count"],
        results: List<Result>.from(json["results"].map((x) => Result.fromMap(x))),
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
    String title;
    String? description;
    String resourceUri;
    List<Url> urls;
    int startYear;
    int endYear;
    String rating;
    String? type;
    String modified;
    Thumbnail thumbnail;
    Creators creators;
    Characters characters;
    Stories stories;
    Characters comics;
    Characters events;
    dynamic next;
    dynamic previous;

    Result({
        required this.id,
        required this.title,
        required this.description,
        required this.resourceUri,
        required this.urls,
        required this.startYear,
        required this.endYear,
        required this.rating,
        required this.type,
        required this.modified,
        required this.thumbnail,
        required this.creators,
        required this.characters,
        required this.stories,
        required this.comics,
        required this.events,
        required this.next,
        required this.previous,
    });

    get getFullImagePath {
      print('estoy en el geter de series');
    if (this.thumbnail != null) {
      // Obtener el path y la ext
      return '${thumbnail.path}/portrait_uncanny.${thumbnail.extension}';
    }
    return 'https://i.stack.imgur.com/GNhxO.png';
  }

    factory Result.fromJson(String str) => Result.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory Result.fromMap(Map<String, dynamic> json) => Result(
        id: json["id"],
        title: json["title"],
        description: json["description"],
        resourceUri: json["resourceURI"],
        urls: List<Url>.from(json["urls"].map((x) => Url.fromMap(x))),
        startYear: json["startYear"],
        endYear: json["endYear"],
        rating: json["rating"],
        type: json["type"],
        modified: json["modified"],
        thumbnail: Thumbnail.fromMap(json["thumbnail"]),
        creators: Creators.fromMap(json["creators"]),
        characters: Characters.fromMap(json["characters"]),
        stories: Stories.fromMap(json["stories"]),
        comics: Characters.fromMap(json["comics"]),
        events: Characters.fromMap(json["events"]),
        next: json["next"],
        previous: json["previous"],
    );

    Map<String, dynamic> toMap() => {
        "id": id,
        "title": title,
        "description": description,
        "resourceURI": resourceUri,
        "urls": List<dynamic>.from(urls.map((x) => x.toMap())),
        "startYear": startYear,
        "endYear": endYear,
        "rating": ratingValues.reverse[rating],
        "type": type,
        "modified": modified,
        "thumbnail": thumbnail.toMap(),
        "creators": creators.toMap(),
        "characters": characters.toMap(),
        "stories": stories.toMap(),
        "comics": comics.toMap(),
        "events": events.toMap(),
        "next": next,
        "previous": previous,
    };
}

class Characters {
    int available;
    String collectionUri;
    List<CharactersItem> items;
    int returned;

    Characters({
        required this.available,
        required this.collectionUri,
        required this.items,
        required this.returned,
    });

    factory Characters.fromJson(String str) => Characters.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory Characters.fromMap(Map<String, dynamic> json) => Characters(
        available: json["available"],
        collectionUri: json["collectionURI"],
        items: List<CharactersItem>.from(json["items"].map((x) => CharactersItem.fromMap(x))),
        returned: json["returned"],
    );

    Map<String, dynamic> toMap() => {
        "available": available,
        "collectionURI": collectionUri,
        "items": List<dynamic>.from(items.map((x) => x.toMap())),
        "returned": returned,
    };
}

class CharactersItem {
    String resourceUri;
    String name;

    CharactersItem({
        required this.resourceUri,
        required this.name,
    });

    factory CharactersItem.fromJson(String str) => CharactersItem.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory CharactersItem.fromMap(Map<String, dynamic> json) => CharactersItem(
        resourceUri: json["resourceURI"],
        name: json["name"],
    );

    Map<String, dynamic> toMap() => {
        "resourceURI": resourceUri,
        "name": name,
    };
}

class Creators {
    int available;
    String collectionUri;
    List<CreatorsItem> items;
    int returned;

    Creators({
        required this.available,
        required this.collectionUri,
        required this.items,
        required this.returned,
    });

    factory Creators.fromJson(String str) => Creators.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory Creators.fromMap(Map<String, dynamic> json) => Creators(
        available: json["available"],
        collectionUri: json["collectionURI"],
        items: List<CreatorsItem>.from(json["items"].map((x) => CreatorsItem.fromMap(x))),
        returned: json["returned"],
    );

    Map<String, dynamic> toMap() => {
        "available": available,
        "collectionURI": collectionUri,
        "items": List<dynamic>.from(items.map((x) => x.toMap())),
        "returned": returned,
    };
}

class CreatorsItem {
    String resourceUri;
    String name;
    String role;

    CreatorsItem({
        required this.resourceUri,
        required this.name,
        required this.role,
    });

    factory CreatorsItem.fromJson(String str) => CreatorsItem.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory CreatorsItem.fromMap(Map<String, dynamic> json) => CreatorsItem(
        resourceUri: json["resourceURI"],
        name: json["name"],
        role: json["role"],
    );

    Map<String, dynamic> toMap() => {
        "resourceURI": resourceUri,
        "name": name,
        "role": role,
    };
}

enum Rating {
    EMPTY,
    RATED_T,
    RATING_RATED_T
}

final ratingValues = EnumValues({
    "": Rating.EMPTY,
    "Rated T": Rating.RATED_T,
    "Rated T+": Rating.RATING_RATED_T
});

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
        items: List<StoriesItem>.from(json["items"].map((x) => StoriesItem.fromMap(x))),
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

    factory StoriesItem.fromJson(String str) => StoriesItem.fromMap(json.decode(str));

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

enum ItemType {
    COVER,
    INTERIOR_STORY
}

final itemTypeValues = EnumValues({
    "cover": ItemType.COVER,
    "interiorStory": ItemType.INTERIOR_STORY
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

enum Extension {
    JPG
}

final extensionValues = EnumValues({
    "jpg": Extension.JPG
});

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

enum UrlType {
    DETAIL
}

final urlTypeValues = EnumValues({
    "detail": UrlType.DETAIL
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
