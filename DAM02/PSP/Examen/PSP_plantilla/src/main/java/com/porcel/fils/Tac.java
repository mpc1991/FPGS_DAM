package com.porcel.fils;

import com.porcel.Main;

public class Tac extends Thread{
    String msg = "tac";

    @Override
    public void run() {
        while(Main.repeticiones != 0) {
            Main.msg += msg;
            synchronized (Main.lock) {
                if (!Main.tacBool) {
                    try {
                        Main.lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println(msg);
                    Main.repeticiones--;
                    Main.ticBool = false;
                    Main.tacBool = false;
                    Main.tocBool = true;
                    Main.lock.notifyAll();
                }
            }
        }
    }
}
