package com.porcel.fils;
/*
TICTACTOC, la idea es que cuando se
ejecute imprima por pantalla la secuencia TIC– TAC– TOC, de tal forma que
cada hilo cuando se ejecuta debe imprimir por pantalla la palabra TIC, TAC o
TOC. Una vez el hilo haya impreso por pantalla la palabra correspondiente debe
concatenar la palabra dentro de una variable compartida entre todos. De modo
que el programa principal una vez que se hayan ejecutado todos los hilos debe
imprimir esta variable por pantalla.

 1,5 puntos
Escribe con java o pseudocódigo todas las clases que consideres apropiadas.
Ten cuenta que:
La ejecución del programa debe ser siempre la secuencia TIC– TAC– TOC– TIC–TAC–TOC–TIC–TAC–TOCperolavariablequeimprimeelprograma
principal una vez han finalizado todos los hilos no tiene por qué seguir la
secuencia indicada anteriormente, podría ser por ejemplo:
tictactoctoctictactactoc o bien tictictictactoctoctoctictac
*/

import com.porcel.Main;

public class Tic extends Thread {
    String msg = "tic";


    @Override
    public void run() {
        while(Main.repeticiones != 0) {
            Main.msg += msg;
            synchronized (Main.lock) {
                if (!Main.ticBool) {
                    try {
                        Main.lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println(msg);
                    Main.repeticiones--;
                    Main.ticBool = false;
                    Main.tacBool = true;
                    Main.tocBool = false;
                    Main.lock.notifyAll();
                }
            }
        }

    }
}
