import 'Comercial.dart';
import 'administratiu.dart';

main(){
  Administratiu maria = new Administratiu(1, "Maria", 2000, 15);
  Comercial aina = new Comercial(50, 2, 2, "Aina", 2000, 17);

  maria.souNet();
  aina.souNet();

  maria.imprimirNom();
  aina.imprimirNom();
}