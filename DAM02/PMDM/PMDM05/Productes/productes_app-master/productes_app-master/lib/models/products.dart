import 'dart:convert';
import 'package:productes_app/models/models.dart';

class Product {
    bool available;
    String name;
    String? picture;
    double price;
    String? id;

    Product({
        required this.available,
        required this.name,
        this.picture,
        required this.price,
        this.id, // Añadido a mano
    });

    factory Product.fromJson(String str) => Product.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory Product.fromMap(Map<String, dynamic> json) => Product(
        available: json["available"],
        name: json["name"],
        picture: json["picture"],
        price: json["price"].toDouble(),
    );

    Map<String, dynamic> toMap() => {
        "available": available,
        "name": name,
        "picture": picture,
        "price": price,
    };

    // Getter que devuelve una copia del propio Producto.
    Product getProduct() {
      Product product = new Product(available: this.available, name: this.name,picture: this.picture, id: this.id, price: this.price);
      return product;
    }

    Product getProductDartEdition() => Product(
      available: this.available, 
      name: this.name,
      picture: this.picture, 
      id: this.id, 
      price: this.price 
    );
}
