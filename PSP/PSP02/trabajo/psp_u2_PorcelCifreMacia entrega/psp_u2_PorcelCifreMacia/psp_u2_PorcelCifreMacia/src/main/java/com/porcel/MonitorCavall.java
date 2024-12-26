package com.porcel;

public class MonitorCavall {
    private static int totalCavalls = 0;

    public static void setTotalCavalls(int totalCavalls) {
        MonitorCavall.totalCavalls = totalCavalls;
    }

    public static int getTotalCavalls() {
        return totalCavalls;
    }

    public static void restarTotalCavalls() {
        totalCavalls--;
    }
}
