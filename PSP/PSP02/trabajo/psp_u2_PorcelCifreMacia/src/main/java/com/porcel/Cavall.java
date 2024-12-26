package com.porcel;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cavall extends Thread {
    private String nom;
    private int idCavall;
    private int velocitat;
    private int metrosCarrera;
    private static Semaphore semaphore;

    private static int TOTAL_CAVALLS;

    private final int VELOCITAT_MAX = 70;
    private final int VELOCITAT_MIN = 15;
    private static Object lock = new Object();

    private boolean reportado;
    private boolean restado;
    private static int contador = 0;

    private static boolean correr;


    public Cavall(String nom, int metrosCarrera, Semaphore semaphore, int idCavall) {
        this.nom = nom;
        this.velocitat = 50;
        this.metrosCarrera = metrosCarrera;
        Cavall.semaphore = semaphore;
        this.idCavall = idCavall;
        this.reportado = false;
        this.restado = false;
        //this.correr = true;
    }

    public void run() {
        while (MonitorCavall.getTotalCavalls() > 0 && !Cavall.interrupted()) {
            System.out.println("iniciant" + nom);
            synchronized (lock) {
                contador++;
                System.out.println("contador" + contador);
                if (contador == TOTAL_CAVALLS) {
                    System.out.println("soy el ultimo entrando en verificarpodio" + nom);
                    verificaPodio();
                    System.out.println("soy el ultimo saliendo de verificar podio" + nom);

                    contador = 0;
                    System.out.println("soy el ultimo voy a notificar de verificar podio" + nom);
                    lock.notifyAll();
                    System.out.println("soy el ultimo voy a salir de notificar de verificar podio" + nom);
                } else {
                    try {
                        System.out.println("entrando en semaforo waitpodio" + nom);
                        System.out.println("tengo el semaforo waitpodio" + nom);//-------------------------
                        verificaPodio();
                        System.out.println("voy a soltar el semaforo waitpodio" + nom);
                        System.out.println("soltado el semaforo waitpodio" + nom);
                        System.out.println("saliendo en semaforo waitpodio" + nom);
                        System.out.println("entrando en waitpodio" + nom);
                        lock.wait();
                        System.out.println("saliendo de waitpodio" + nom);
                    } catch (InterruptedException e) {
                        correr = false;
                        this.interrupt();
                    }
                }
            }

            System.out.println("saliendo de verificarpodio" + nom);

            if (metrosCarrera <= 0) {
                //velocitat = 0;
            } else {
                velocitat = calculVelocidat(velocitat); // calcular la velocitat
                metrosCarrera = metrosCarrera - velocitat; // calcular els metres faltants restant la velocitat
            }

            try {
                semaphore.acquire();
                System.out.println("entrando en notificarpasada" + nom);
                Carrera.notificarPasada(this);

                System.out.println("saliendo en notificarpasada" + nom);
                semaphore.release();
            } catch (InterruptedException e) {
                correr = false;
                this.interrupt();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            synchronized (lock) {
                contador++;
                if (contador == TOTAL_CAVALLS) {
                    if (reportado && !restado && metrosCarrera <= 0) {
                        restado = true;
                        MonitorCavall.restatTotalCavalls();
                    }
                    contador = 0;
                    lock.notifyAll();
                } else {
                    try {
                        if (reportado && !restado && metrosCarrera <= 0) {
                            restado = true;
                            MonitorCavall.restatTotalCavalls();
                        }
                        lock.wait();
                    } catch (InterruptedException e) {
                        correr = false;
                        this.interrupt();
                    }
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                correr = false;
                this.interrupt();
            }
        }
        System.out.println("saliendo de Cavall.while");
    }

    public void verificaPodio() {
        if (!reportado && metrosCarrera <= 0) {
            System.out.println("verificarpodio dentro IF" + nom);
            try {
                Carrera.sumaGonyador(this);
                reportado = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getMetrosFaltants() {
        return metrosCarrera;
    }

    public String getNom() {
        return nom;
    }

    public int getVelocitat() {
        return velocitat;
    }

    public int getIdCavall() {
        return idCavall;
    }

    public static void setTOTAL_CAVALLS(int TOTAL_CAVALLS) {
        Cavall.TOTAL_CAVALLS = TOTAL_CAVALLS;
    }

    public int calculVelocidat(int velocitat) {
        Random random = new Random();
        int velocitatAux = random.nextInt(11) - 5;
        velocitat = velocitat + velocitatAux; // sumam la velocitat random -5 +5

        if (velocitat > VELOCITAT_MAX) {
            velocitat = VELOCITAT_MAX; // si supuremaos la velocidad máxima, igualamos velocidad actual a velocidad máxima
        } else if (velocitat < VELOCITAT_MIN) {
            velocitat = VELOCITAT_MIN; // si supuremaos la velocidad mínima, igualamos velocidad actual a velocidad mínima
        }
        return velocitat;
    }

    public static void setCorrer(boolean correr) {
        Cavall.correr = correr;
    }
}
