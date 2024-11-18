import '../model/Client.dart';
import '../model/Cotxe.dart';
import '../model/Moto.dart';
import '../model/Vehicle.dart';

List<Cotxe> cotxes = [];
List<Moto> motos = [];
List<Client> clients = [];

void main() {
  creaVehiculs();
  creaClients();

  cotxes[0].llogar(clients[1].dni);
  print(cotxes[0]
      .toString()); //Mostram la informació del cotxe per saber si està llogat

  motos[0].llogar(clients[1].dni);
  print(motos[0]
      .toString()); //Mostram la informació de la moto per saber si està llogada

  motos[1].llogar(clients[0].dni);
  print(motos[1]
      .toString()); //Mostram la informació de la moto per saber si està llogada
  print("");

  comptarCotxesLlogats(cotxes);
  comptarMotosLlogades(motos);
  print("");

  mesQuilometres(cotxes);
  mesQuilometres(motos);
  print("");

  cotxes[0].retornar();
  motos[0].retornar();
  motos[1].retornar();
  print(cotxes[0]);
  print(motos[0]);
  print(motos[1]);
  
}

void creaVehiculs() {
  // Creamos 5 coches
  Cotxe renault = Cotxe.complet(
      matricula: "123456M",
      marca: "Renault",
      model: "Megane",
      isLlogat: false,
      dni: "",
      quilometratge: 150.00);
  Cotxe seat = Cotxe.nomesMatricula(matricula: "123456Y");
  Cotxe bmw = Cotxe.nomesMatricula(matricula: "123456P");
  Cotxe fiat = Cotxe.nomesMatricula(
      matricula: "123456L",
      marca: "Fiat",
      model: "Punto",
      isLlogat: false,
      dni: "",
      quilometratge: 1000000.00);
  Cotxe opel = Cotxe.nomesMatricula(matricula: "123456J");

  // Creamos 5 motos
  Moto harleyDavidson = Moto.complet(
      matricula: "1234567L",
      marca: "Harley davidson",
      model: "Fat Bob",
      isLlogat: false,
      dni: "",
      quilometratge: 3000.00,
      cilindrada: 1900);
  Moto royalEnfield = Moto.nomesMatricula(matricula: "123456A");
  Moto triumph = Moto.nomesMatricula(
      matricula: "123456W",
      marca: "Triumph",
      model: "Roadster",
      isLlogat: false,
      dni: "",
      quilometratge: 40000.00,
      cilindrada: 900);
  Moto honda = Moto.nomesMatricula(matricula: "123456R");
  Moto kawasaki = Moto.nomesMatricula(matricula: "123456T");

  // Añadimos los vehiculos a sus correspondientes listas
  cotxes.addAll([renault, seat, bmw, fiat, opel]);
  motos.addAll([harleyDavidson, royalEnfield, triumph, honda, kawasaki]);
}

void creaClients() {
  // Creamos los clientes
  Client macia = new Client(
      dni: "12345678R",
      nom: "Macia",
      llinatges: "Porcel",
      correu: "maciaporcel@paucasesnovescifp.cat",
      telefon: 123123123,
      targetaCredit: 123123123123);
  Client guillem = Client(dni: "123123123S", nom: "Guillem");

  // Añadimos los clientes a la lista
  clients.addAll([macia, guillem]);
}

void comptarCotxesLlogats(List<Cotxe> cotxes) {
  int count = 0;
  int countTotal = 0;

  // iteramos sobre todos los coches de la lista
  for (var cotxe in cotxes) {
    countTotal++;
    if (cotxe.isLlogat) {
      // Si el coche està alquilado, sumamos 1 en el contador
      count++;
    }
  }
  print("Hi ha $count cotxes llogats de un total de $countTotal.");
}

void comptarMotosLlogades(List<Moto> motos) {
  int count = 0;
  int countTotal = 0;

  for (var moto in motos) {
    countTotal++;
    if (moto.isLlogat) {
      count++;
    }
  }
  print("Hi ha $count motos llogades de un total de $countTotal.");
}

void mesQuilometres(List<Vehicle> llista) {
  var mesQuilometres = llista[0]; // Inicializamos con el primer coche

  // iteramos sobre todos los cotches de la lista
  for (var a in llista) {
    if (mesQuilometres.compareTo(a) < 0) {
      mesQuilometres = a;
    }
  }
  print("El vehicle amb més quilometres es: $mesQuilometres");
}