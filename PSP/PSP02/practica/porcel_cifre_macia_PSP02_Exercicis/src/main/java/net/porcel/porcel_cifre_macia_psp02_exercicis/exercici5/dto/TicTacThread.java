package net.porcel.porcel_cifre_macia_psp02_exercicis.exercici5.dto;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TicTacThread extends Thread {

    private String message;
    private int repetitions;
    Object lock;

    public TicTacThread(String message, Object lock, int repetitions) {
        this.message = message;
        this.lock = lock;
        this.repetitions = repetitions;
    }

    public void run() {
        for (int i = 0; i < repetitions; i++) {
            synchronized (lock) {

                try {
                    System.out.println(message);

                    Thread.sleep(200);
                    lock.notify();
                    
                    if (i < repetitions - 1) { // Evitar esperar al final y quedarse bloqueada la ejecución
                        lock.wait();
                    }

                } catch (InterruptedException ex) {
                    Logger.getLogger(TicTacThread.class.getName()).log(Level.SEVERE, null, ex);
                    break;
                }
            }
        }
    }
}
