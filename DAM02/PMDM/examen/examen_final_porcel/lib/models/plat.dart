import 'dart:convert';

class Plat {
    String id;
    String nom;
    String descripcio;
    String foto;
    String alergens;
    String tipus;

    Plat({
        required this.id,
        required this.nom,
        required this.descripcio,
        required this.foto,
        required this.alergens,
        required this.tipus,
    });

    factory Plat.fromJson(String str) => Plat.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory Plat.fromMap(Map<String, dynamic> json) => Plat(
        id: json["id"],
        nom: json["nom"],
        descripcio: json["descripcio"],
        foto: json["foto"],
        alergens: json["alergens"],
        tipus: json["tipus"],
    );

    Map<String, dynamic> toMap() => {
        "id": id,
        "nom": nom,
        "descripcio": descripcio,
        "foto": foto,
        "alergens": alergens,
        "tipus": tipus,
    };

    Plat getPlat(){ // Añadido a mano
      Plat plat = new Plat(descripcio: this.descripcio, foto: this.foto, 
      nom: this.nom, alergens: this.alergens, tipus: this.tipus, id: this.id) ;

      return plat;
    }
    
}
