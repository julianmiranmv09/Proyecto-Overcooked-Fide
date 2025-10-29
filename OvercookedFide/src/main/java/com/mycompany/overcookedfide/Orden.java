/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.overcookedfide;

/**
 *
 * @author Fabian
 */
public class Orden {
    /// Así se vería la hamburguesa "Los tipos de hamburguesa y losmpuntos"
    private String tipo;
    private int puntos;

    public Orden(String tipo, int puntos) {
        this.tipo = tipo;
        this.puntos = puntos;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPuntos() {
        return puntos;
    }

    public String toString() {
        return tipo + " (" + puntos + "pts)";
    }
    
}
