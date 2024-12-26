package net.porcel.porcel_cifre_macia_psp02_exercicis.exercici5;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.porcel.porcel_cifre_macia_psp02_exercicis.exercici5.dto.TicTacThread;

public class Exercici5 {
    private int n = 10;
    private Object lock = new Object();
    public Exercici5(){}
    
    public void inicialice(){
    TicTacThread fil = new TicTacThread("TIC", lock, n);
    TicTacThread fil2 = new TicTacThread("TAC", lock, n);
    
    fil.setPriority(Thread.MAX_PRIORITY);
    fil2.setPriority(Thread.MIN_PRIORITY);
    
    fil.start();
    fil2.start();

        try {
            fil.join();
            fil2.join();
            
            System.out.println("Programa finalitzat");
        } catch (InterruptedException ex) {
            Logger.getLogger(Exercici5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
