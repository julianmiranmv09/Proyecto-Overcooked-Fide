/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.overcookedfide;

/**
 *
 * @author Neto
 */
public class Lista<T> {
    private Nodo<T> cabeza;
    private int tamano;
    
    public Lista() {
        this.cabeza = null;
        this.tamano = 0;
    }
    
    public void agregar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        tamano++;
    }
    
    public boolean contiene(T dato) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
    
    public void limpiar() {
        cabeza = null;
        tamano = 0;
    }
    
    public boolean estaVacia() {
        return cabeza == null;
    }
    
    public int getTamanio() {
        return tamano;
    }
    
    public T obtenerEnPosicion(int posicion) {
        if (posicion < 0 || posicion >= tamano) {
            return null;
        }
        Nodo<T> actual = cabeza;
        for (int i = 0; i < posicion; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }
}