package net.porcel.porcel_cifre_macia_psp02_exercicis.exercici6;

import net.porcel.porcel_cifre_macia_psp02_exercicis.exercici6.dto.MostrarNombresThread;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exercici6 {

    int cuantitatFils = 2;
    String interrumpir = "";
    Scanner sc = new Scanner(System.in);

    public Exercici6() {
    }

    public void inicialice() {
        MostrarNombresThread mnt1 = new MostrarNombresThread("Fil: 1", 5);
        MostrarNombresThread mnt2 = new MostrarNombresThread("Fil: 2");

        mnt1.start();
        mnt2.start();

        while (true) {
            if (mnt1.isAlive()) {
                System.out.println("Interrumpir?");
                interrumpir = sc.nextLine();
            }
            
            if (interrumpir.equalsIgnoreCase("si")) {
                mnt1.interrupt();
                //break;
            }
            
            while (!mnt1.isAlive()) {
                mnt2.interrupt();
                break;
            }
        }
    }
}
