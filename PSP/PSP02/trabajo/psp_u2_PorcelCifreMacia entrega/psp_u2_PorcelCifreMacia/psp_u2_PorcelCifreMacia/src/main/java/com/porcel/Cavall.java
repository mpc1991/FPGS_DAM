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
    private static boolean menjador = true;
    private int contadorBuffs;
    private int contadorDebuffs;

    private static boolean correr;

    public Cavall(String nom, int metrosCarrera, Semaphore semaphore, int idCavall) {
        this.nom = nom;
        this.velocitat = 50;
        this.metrosCarrera = metrosCarrera;
        Cavall.semaphore = semaphore;
        this.idCavall = idCavall;
        this.reportado = false;
        this.restado = false;
        this.contadorBuffs = 0;
        this.contadorDebuffs = 0;
    }

    public void run() {
        while (MonitorCavall.getTotalCavalls() > 0 && !Cavall.interrupted()) {
            synchronized (lock) {
                contador++;
                verificaPodio();
                if (contador == TOTAL_CAVALLS) {
                    contador = 0;
                    lock.notifyAll();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        correr = false;
                        this.interrupt();
                    }
                }
            }

            contadorBuffs++;
            if (contadorBuffs >= 5 && metrosCarrera > 0 && velocitat >= VELOCITAT_MIN) {
                if (menjador && velocitat == VELOCITAT_MAX) {
                    try {
                        semaphore.acquire();
                        if (extras.tiraMoneda()) {
                            menjador = false;
                            extras.menjador(this);
                            contadorBuffs = 4;
                        }
                        semaphore.release();
                    } catch (InterruptedException e) {
                        correr = false;
                        this.interrupt();
                    }
                } else if (velocitat == VELOCITAT_MIN) {
                    if (extras.tiraMoneda()) {
                        velocitat = extras.burstATOPE(this);
                        contadorBuffs = 0;
                    }
                } else if (velocitat <= VELOCITAT_MAX / 3) {
                    if (extras.tiraMoneda()) {
                        velocitat = extras.burstMitja(this);
                        contadorBuffs = 2;
                    }
                } else if (velocitat <= VELOCITAT_MAX / 2) {
                    if (extras.tiraMoneda()) {
                        velocitat = extras.burstPetit(this);
                        contadorBuffs = 3;
                    }
                }
            } else if (metrosCarrera <= 0) {
                velocitat = 0;
            } else {
                velocitat = calculVelocidat(velocitat); // calcular la velocitat
            }

            contadorDebuffs++;
            if (metrosCarrera > 0 && contadorDebuffs >= 5) {
                try {
                    semaphore.acquire();
                    if (extras.tiraMoneda()) {
                        extras.disaster(this);
                    }
                } catch (InterruptedException e) {
                    correr = false;
                    this.interrupt();
                } finally {
                    semaphore.release();
                }
            }

            metrosCarrera = metrosCarrera - velocitat; // calcular els metres faltants restant la velocitat

            try {
                semaphore.acquire();
                Carrera.notificarPasada(this);
                semaphore.release();
            } catch (InterruptedException e) {
                correr = false;
                this.interrupt();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            synchronized (lock) {
                contador++;
                if (reportado && !restado && metrosCarrera <= 0) {
                    MonitorCavall.restarTotalCavalls();
                    restado = true;
                }
                if (contador == TOTAL_CAVALLS) {
                    contador = 0;
                    menjador = true;
                    lock.notifyAll();
                } else {
                    try {
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
    }

    public void verificaPodio() {
        if (!reportado && metrosCarrera <= 0) {
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

    public static void setMenjador(boolean menjador) {
        Cavall.menjador = menjador;
    }

    public void setVelocitat(int velocitat) {
        this.velocitat = velocitat;
    }

    public void setContadorDebuffs(int i) {
        contadorDebuffs = i;
    }

    public int getContadorDebuffs() {
        return contadorDebuffs;
    }

    public int getVELOCITAT_MIN() {
        return VELOCITAT_MIN;
    }
}
