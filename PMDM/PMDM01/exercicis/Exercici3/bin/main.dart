//Virifica si es un polidrome o no.

void main(){
String texto = "asa";
isPalindrom(texto);
}

isPalindrom (String frase) {
  String fraseFormateada = frase.toLowerCase().replaceAll(" ", "").trim();

  String reverse = fraseFormateada.split("").reversed.join("");

  if (fraseFormateada == reverse) {
    print("La cadena de texto es palídromo");
  } else {
    print("La cadena de texto no es palídromo");
  }
}