//Retorna el número més gran

main() {
int a = 10;
int b = 11;
int c = 12;

int MAX = returnMax(a, b, c);
print("El número más alto és $MAX");
}

returnMax(int a, int b, int c) {
  int MAX = a;

  if (b > MAX){
    MAX = b;
  } 
  
  if (c > MAX) {
    MAX = c;
  }

  return MAX;
} 