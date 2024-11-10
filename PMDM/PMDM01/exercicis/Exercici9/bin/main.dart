// Dado un número, pintará una cuadricula de esa grandaria

// adaptado de chatGPT
void main() {
  int tamano = 10; // Tamaño de la cuadrícula (número de celdas por fila y columna)
  pintarCuadricula(tamano);
}

void pintarCuadricula(int tamano) {
  // Bucle para imprimir las filas de la cuadrícula
  for (int i = 0; i < tamano; i++) {
    // Imprimir la línea horizontal
    print(' ---' * tamano); 
    
    // Imprimir las celdas verticales
    for (int j = 0; j < tamano; j++) {
      print('|   ' * (tamano + 1)); // Usamos stdout.write para evitar el salto de línea
    }
    //print('|'); // Imprimir la última barra vertical y saltar a la siguiente línea
  }
  // Imprimir la última línea horizontal después del ciclo
  print(' ---' * tamano);
}

// Solución ejercicio.
void pintaTaula(int nombreCubs) {
  // Elements de disseny bàsics
  String linies = " ---";
  String columnes = "|   ";

  // Bucle per anar pintant les files i columnes en una sola passada
  // Es pot fer també amb diversos bucles anidats
  for (var i = 0; i < nombreCubs; i++) {
    print(linies * nombreCubs);
    print(columnes * (nombreCubs + 1));
  }

  // Afegim la darrera linia d'abaix
  print("${linies * nombreCubs}\n");
}