package com.porcel;

import com.porcel.fils.Nodo;
import com.porcel.fils.Tac;
import com.porcel.fils.Tic;
import com.porcel.fils.Toc;
import java.util.concurrent.Semaphore;

public class Main {
    public static String msg = "";
    public static Semaphore semaphore = new Semaphore(1);
    public static Object lock = new Object();
    public static int repeticiones = 3*30;

    public static boolean ticBool = true;
    public static boolean tacBool = false;
    public static boolean tocBool = false;

    public static void main(String[] args) throws InterruptedException {

    }

    public void Tictactoc() throws InterruptedException {
        Tic tic = new Tic();
        Tac tac = new Tac();
        Toc toc = new Toc();

        tic.start();
        tac.start();
        toc.start();
        tic.join();
        tac.join();
        toc.join();

        System.out.println(msg);
    }
    public void Anillo() throws InterruptedException {
        Nodo n1 = new Nodo("nodo1",50,51, true);
        Nodo n2 = new Nodo("nodo2",51,52, false);
        Nodo n3 = new Nodo("nodo3",52,50, false);

        n1.start();
        n2.start();
        n3.start();
        n1.join();
        n2.join();
        n3.join();
    }
}