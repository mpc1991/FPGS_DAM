// Dado un número, pintará una cuadricula de esa grandaria

void main() {
  int tamano = 3; // Tamaño de la cuadrícula (número de celdas por fila y columna)
  pintarCuadricula(tamano);
}

void pintarCuadricula(int tamano) {
  // Bucle para imprimir las filas de la cuadrícula
  for (int i = 0; i < tamano; i++) {
    // Imprimir la línea horizontal
    print(' ---' * tamano); 
    
    // Imprimir las celdas verticales
    for (int j = 0; j < tamano; j++) {
      //stdout.write('|   '); // Usamos stdout.write para evitar el salto de línea
    }
    print('|'); // Imprimir la última barra vertical y saltar a la siguiente línea
  }
  // Imprimir la última línea horizontal después del ciclo
  print(' ---' * tamano);
}