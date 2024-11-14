import '../model/Cotxe.dart';
import '../model/Moto.dart';

List<Cotxe> cotxes = [];
List<Moto> motos = [];
void main(){
  ompleixLlistes();
  
}

void ompleixLlistes(){
  Cotxe renault = Cotxe.complet(matricula: "123456M", marca: "Renault", model: "Megane", isLlogat: false, dni: "", quilometratge: 150.00);
  Cotxe seat = Cotxe.nomesMatricula(matricula: "123456Y");
  Cotxe bmw = Cotxe.nomesMatricula(matricula: "123456P");
  Cotxe fiat = Cotxe.nomesMatricula(matricula: "123456L");
  Cotxe opel = Cotxe.nomesMatricula(matricula: "123456J");

  Moto harleyDavidson = Moto.complet(matricula: "1234567L", marca: "Harley davidson", model: "Fat Bob", isLlogat: true, dni: "43122222R", quilometratge: 3000.00, cilindrada: 1900);
  Moto royalEnfield = Moto.nomesMatricula(matricula: "123456A");
  Moto triumph = Moto.nomesMatricula(matricula: "123456W");
  Moto honda = Moto.nomesMatricula(matricula: "123456R");
  Moto kawasaki = Moto.nomesMatricula(matricula: "123456T");

  cotxes.addAll([renault, seat, bmw, fiat, opel]);
  motos.addAll([harleyDavidson, royalEnfield, triumph, honda, kawasaki]);
}
