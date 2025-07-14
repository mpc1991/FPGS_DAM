package com.porcel.fils;

import com.porcel.Main;

public class Toc extends Thread{
    String msg = "toc";

    @Override
    public void run() {
        while(Main.repeticiones != 0) {
            Main.msg += msg;
            synchronized (Main.lock) {
                if (!Main.tocBool) {
                    try {
                        Main.lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println(msg);
                    Main.repeticiones--;
                    Main.ticBool = true;
                    Main.tacBool = false;
                    Main.tocBool = false;
                    Main.lock.notifyAll();
                }
            }
        }
    }
}
