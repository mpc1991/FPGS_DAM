// Genera nombres aleatoris entre 1 i 1000 i comproba si es primari o no

import "dart:math";
main(){
  var a = getRandom();

  if (isPrime(a)){
    print("El número és primo");
  } else {
    print("El número no és primo");
  }
}

getRandom () {
  var num = Random().nextInt(1000) +1; // +1 par aque empiece desde el 1 y no desde el 0.
  print("Número generado = $num");
  return num;
}

bool isPrime(int n) { //https://pub.dev/documentation/mobile/latest/prime_dart/isPrime.html
  for (int i = 2; i <= n / 2; i++) {
    if (n % i == 0) {
      return false;
    }
  }
  return true;
}