package com.porcel;

import java.util.Random;

public class extras {

    public static boolean tiraMoneda() {
        return Math.random() > 0.5;
    }

    public static void menjador(Cavall cavall) {
        System.out.println(cavall.getNom() + " s'ha aturat a menjar");
        cavall.setVelocitat(0);
    }

    public static int burstPetit(Cavall cavall) {
        System.out.println(cavall.getNom() + " està avançant! velocitat +5");
        return cavall.getVelocitat() + 5;
    }

    public static int burstMitja(Cavall cavall) {
        System.out.println(cavall.getNom() + " està aixecant el cap! velocitat +10");
        return cavall.getVelocitat() + 10;
    }

    public static int burstATOPE(Cavall cavall) {
        System.out.println(cavall.getNom() + " ¡Està rompent limits! velocitat *5");
        return cavall.getVelocitat() * 5;
    }

    public static void disaster(Cavall cavall) {
        Random random = new Random();
        int disaster = random.nextInt(10);

        switch (disaster) {
            case 0:
                maximumDisaster(cavall);
                break;
            case 1:
                mediumDisaster(cavall);
                break;
            case 2:
                minimumDisaster(cavall);
                break;
            default:
                // No passa res
                break;
        }
    }

    private static void maximumDisaster(Cavall cavall) {
        if (cavall.getVelocitat() >= 35) {
            System.out.println(cavall.getNom() + " Un clot a la pista redueix la velocitat -20");
            cavall.setVelocitat(cavall.getVelocitat() - 20);
            if (cavall.getVelocitat() < cavall.getVELOCITAT_MIN()) {
                cavall.setVelocitat(cavall.getVELOCITAT_MIN());
            }
            cavall.setContadorDebuffs(cavall.getContadorDebuffs() - 5);
        }
    }

    private static void mediumDisaster(Cavall cavall) {
        if (cavall.getVelocitat() >= 25) {
            System.out.println(cavall.getNom() + " El rebuig del puclic redueix la velocitat -10");
            cavall.setVelocitat(cavall.getVelocitat() - 10);
            if (cavall.getVelocitat() < cavall.getVELOCITAT_MIN()) {
                cavall.setVelocitat(cavall.getVELOCITAT_MIN());
            }
            cavall.setContadorDebuffs(cavall.getContadorDebuffs() - 3);
        }
    }

    private static void minimumDisaster(Cavall cavall) {
        if (cavall.getVelocitat() >= 20) {
            System.out.println(cavall.getNom() + " Es distreu, velocitat -5");
            cavall.setVelocitat(cavall.getVelocitat() - 5);
            if (cavall.getVelocitat() < cavall.getVELOCITAT_MIN()) {
                cavall.setVelocitat(cavall.getVELOCITAT_MIN());
            }
            cavall.setContadorDebuffs(cavall.getContadorDebuffs() - 2);
        }
    }

}
