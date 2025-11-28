/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.overcookedfide;


/**
 * @author Neto
 */
public class ColaCircular {
    private Nodo<Orden> frente;
    private Nodo<Orden> fin;
    private int tamanio;
    private final int CAPACIDAD_MAXIMA = 3;
    
    public ColaCircular() {
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }
    
    public boolean encolar(Orden orden) {
        if (tamanio >= CAPACIDAD_MAXIMA) {
            return false; // No se pueden agregar más de 3 órdenes
        }
        
        Nodo<Orden> nuevoNodo = new Nodo<>(orden);
        
        if (estaVacia()) {
            frente = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.setSiguiente(nuevoNodo);
            fin = nuevoNodo;
        }
        tamanio++;
        return true;
    }
    
    public Orden desencolar() {
        if (estaVacia()) {
            return null;
        }
        
        Orden ordenDesencolada = frente.getDato();
        frente = frente.getSiguiente();
        
        if (frente == null) {
            fin = null;
        }
        
        tamanio--;
        return ordenDesencolada;
    }
    
    public Orden verFrente() {
        if (estaVacia()) {
            return null;
        }
        return frente.getDato();
    }
    
    public boolean estaVacia() {
        return frente == null;
    }
    
    public boolean estaLlena() {
        return tamanio >= CAPACIDAD_MAXIMA;
    }
    
    public int getTamanio() {
        return tamanio;
    }
    
    public Orden[] obtenerOrdenes() {
        Orden[] ordenes = new Orden[tamanio];
        Nodo<Orden> actual = frente;
        int i = 0;
        
        while (actual != null && i < tamanio) {
            ordenes[i] = actual.getDato();
            actual = actual.getSiguiente();
            i++;
        }
        
        return ordenes;
    }
}
