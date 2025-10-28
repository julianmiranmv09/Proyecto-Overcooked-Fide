/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.overcookedfide;

/**
 *
 * @author Fabian
 */
public class Nodo {
    // Nodo para las ordenes de la cola 
    Orden dato;
    Nodo siguiente;

    public Nodo(Orden dato, Nodo siguiente) {
        this.dato = dato;
        this.siguiente = null;
    }
}
