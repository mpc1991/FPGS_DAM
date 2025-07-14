//imprimeix tots els elements de la llista que siguin inferiors a 5

main() {
  var a = [1, 1,2,3,5,8, 13,21,34,55,89];
  _getMyList(a);
}

_getMyList(var a){
  for (int i in a) {
    if(i <= 5){
    print("El número en esta posicion es: $i");
    }
  }
}