// A partir d'una llista, fes una nova que inclogui nomes els elements parells.

main() {
  List a=[1, 4, 9, 16, 25, 36, 49, 64, 81, 100];

  var b = getParells(a);
  print(b);
}

getParells(a) {
  List<int> parells = [];

  for (int i in a){
    if (i % 2 == 0) {
      parells.add(i);
    }
  }
  return parells;
}

