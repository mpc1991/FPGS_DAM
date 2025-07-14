// Genera una contraseña segura de longitud especificada con carácteres alfanuméricos.

import 'dart:math';

void main() {
  int longitud = 12;
  String pass = generarContrasena(longitud);
  print("Contraseña generada: $pass");
}

generarContrasena(int longitud){
  const caracters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  String pass = "";

  Random random = Random(); // instancia de Random

  for (int i = 0; i < longitud; i++) {
    int index = random.nextInt(caracters.length);
    pass += caracters[index];
  }

  return pass;
}


