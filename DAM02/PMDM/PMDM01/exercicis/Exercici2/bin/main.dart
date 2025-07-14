// retorna una llista que contingui només els elements comuns entre ells, fora duplicar

void main(){
  List<int> a=[1, 1,2,3,5,8, 13,21,34,55,89];
  var b=[1,2,3,4,5,6,7,8,9, 10, 11, 12, 13];
  myList(a, b);
}

myList(var a, List b){
  
  Set<int> coincidencias = {}; // Con set evitamos duplicidades

  for (int numero in a){
    if (b.contains(numero) && !coincidencias.contains(numero)){ // O verificamos que no esté en la lista o aqui usamos Set en vez de List
      coincidencias.add(numero);
    }
  }

  print("coincidencias $coincidencias");
}