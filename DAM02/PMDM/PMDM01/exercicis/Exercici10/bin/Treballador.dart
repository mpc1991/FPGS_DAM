abstract class Treballador{

  late int id;
  late String nom;
  late double sou;
  late int retencio;

  Treballador(this.id, this.nom, this.sou, this.retencio){

  }

  imprimirNom(){
    print(nom);
  }

  souNet() {
    double souNet = sou * (retencio* 0.01);
    souNet = sou - souNet;
    print(souNet);
  }
}