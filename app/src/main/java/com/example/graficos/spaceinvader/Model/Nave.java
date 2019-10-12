package com.example.graficos.spaceinvader.Model;

public class Nave {
private int vidas=3;
int count;
private boolean moviendo;

    public Nave(int vidas, int count, boolean moviendo) {
        this.vidas = vidas;
        this.count = count;
        this.moviendo = moviendo;
    }

    public Nave() {

    }
}
