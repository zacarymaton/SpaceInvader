package com.example.graficos.spaceinvader.Model;

public class Asteroide {
    public static int cantAsteroides;
    public static boolean estadoAsteroide = false;
    public static int[] arrayAsteroides;

    public Asteroide() {
    }

    public static int getCantAsteroides() {
        return cantAsteroides;
    }

    public static void setCantAsteroides(int cantAsteroides) {
        Asteroide.cantAsteroides = cantAsteroides;
    }

    public static boolean isEstadoAsteroide() {
        return estadoAsteroide;
    }

    public static void setEstadoAsteroide(boolean estadoAsteroide) {
        Asteroide.estadoAsteroide = estadoAsteroide;
    }

    public static int[] getArrayAsteroides() {
        return arrayAsteroides;
    }

    public static void setArrayAsteroides(int[] arrayAsteroides) {
        Asteroide.arrayAsteroides = arrayAsteroides;
    }
}
