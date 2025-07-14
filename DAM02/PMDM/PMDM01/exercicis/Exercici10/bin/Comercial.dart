import "Treballador.dart";

class Comercial extends Treballador{
  int ventes;
  double comisio;

  Comercial(this.ventes, this.comisio, int id, String nom, double sou, int retencio) 
    :super(id, nom, sou, retencio){ 

  }
}