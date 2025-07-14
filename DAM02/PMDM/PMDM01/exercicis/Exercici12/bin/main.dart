// Att al profesor: por favor no tengas en cuenta este ejercicio a la hora de puntuar.

// Ejercicio invalido, está completamente sacado de chatGPT y me cuesta entenderlo..
// Guardo el ejercicio para mi por si se presenta la ocasión de tener que hacer algo similar usarlo como apuntes/referencia.

void main() {
  int limite = 44;
  List<int> primos = generaPrimers(limite);
  print("Números primos hasta $limite:");
  for (var primo in primos) {
    print(primo);
  }

  print("\nParejas de números primos con diferencia de 2:");
  generaParelles(primos);
}

List<int> generaPrimers(int limite) {
  List<int> primos = [];
  for (int i = 2; i <= limite; i++) {
    if (esPrimo(i)) {
      primos.add(i);
    }
  }
  return primos;
}

bool esPrimo(int numero) {
  if (numero < 2) return false;
  for (int i = 2; i <= numero ~/ 2; i++) {
    if (numero % i == 0) return false;
  }
  return true;
}

void generaParelles(List<int> primos) {
  for (int i = 0; i < primos.length - 1; i++) {
    if (primos[i + 1] - primos[i] == 2) {
      print("${primos[i]} i ${primos[i + 1]}");
    }
  }
}
