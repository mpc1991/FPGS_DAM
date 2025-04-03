import 'package:flutter/material.dart';

class ProductImage extends StatelessWidget {
  final String? url; 

  // Constructor que recibe la URL de la imagen.
  const ProductImage(
    this.url, {
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.only(right: 10, left: 10, top: 10),
      child: Container(
        decoration: _buildBoxDecoration(),
        width: double.infinity,
        height: 450,
        child: Opacity( // Aplicamos opacidad a la imagen para darle que se vean los botones
          opacity: 0.9,
          child: ClipRRect( // Redondear las esquinas del contenedor.
            borderRadius: BorderRadius.only(
                topLeft: Radius.circular(25), topRight: Radius.circular(25)),
            child: url == null          // if null
                ? Image.asset(          // null
                    'assets/no-image.png',
                    fit: BoxFit.cover,
                  )
                : FadeInImage(          // not null
                    placeholder: AssetImage('assets/jar-loading.gif'),
                    image: NetworkImage(url!),
                    fit: BoxFit.cover),
          ),
        ),
      ),
    );
  }

  // Función para construir la decoración del contenedor de la imagen (bordes redondeados y sombra).
  BoxDecoration _buildBoxDecoration() => BoxDecoration(
          color: Colors.black,
          borderRadius: BorderRadius.only(
              topLeft: Radius.circular(25), topRight: Radius.circular(25)),
          boxShadow: [
            BoxShadow(
              color: Colors.black12,
              blurRadius: 10,
              offset: Offset(0, 5),
            )
          ]);
}
