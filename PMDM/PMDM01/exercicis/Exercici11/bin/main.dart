main () {
  int hora = 1;
  int minut = 49;
  int segon = 11;

formatTime(hora, minut, segon);
}

formatTime(int hora, int minut, int segon) {
  //print("$hora:$minut:$segon");
  String formatHora = hora.toString().padLeft(2, '0');
  String formatMinut = minut.toString().padLeft(2, '0');
  String formatSegon = segon.toString().padLeft(2,"0");

  print("$formatHora:$formatMinut:$formatSegon");
}