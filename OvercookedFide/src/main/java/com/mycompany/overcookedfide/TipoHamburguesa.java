package com.mycompany.overcookedfide;

public enum TipoHamburguesa {
    CARNE(new Ingrediente[]{Ingrediente.PAN, Ingrediente.CARNE}, 5),
    QUESO(new Ingrediente[]{Ingrediente.PAN, Ingrediente.CARNE, Ingrediente.QUESO}, 10),
    CLASICA(new Ingrediente[]{Ingrediente.PAN, Ingrediente.CARNE, Ingrediente.LECHUGA, Ingrediente.QUESO}, 15);

    private final Ingrediente[] requeridos; // ingredientes necesarios para esta hamburguesa
    private final int puntos; // puntos que da al completarla

    // Constructor del enum
    TipoHamburguesa(Ingrediente[] requeridos, int puntos) {
        this.requeridos = requeridos;
        this.puntos = puntos;
    }

    // Devuelve los ingredientes que se necesitan
    public Ingrediente[] getRequeridos() {
        return requeridos;
    }

    // Devuelve los puntos que da esta hamburguesa
    public int getPuntos() {
        return puntos;
    }
}