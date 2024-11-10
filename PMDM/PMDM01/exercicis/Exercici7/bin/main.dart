// Donada una cadena de diverses paraules, les torni a imprimir amb les paraules en ordre invers
// El meu nom és Jaume
// Jaume és nom meu El

main(){
  String a = "El meu nom és Jaume";
  String b = reverse(a);
  print(b);
}

reverse(String a){
  var b = a.split(' ');
  b = b.reversed.toList();

  return b.join(" ");
}