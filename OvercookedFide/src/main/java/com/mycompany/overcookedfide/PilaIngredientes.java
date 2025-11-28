/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.overcookedfide;

public class PilaIngredientes {
    private Nodo<Ingrediente> tope;
    private int tamanio;
    
    public PilaIngredientes() {
        this.tope = null;
        this.tamanio = 0;
    }
    
    public void apilar(Ingrediente ingrediente) {
        Nodo<Ingrediente> nuevoNodo = new Nodo<>(ingrediente);
        nuevoNodo.setSiguiente(tope);
        tope = nuevoNodo;
        tamanio++;
    }
    
    public Ingrediente desapilar() {
        if (estaVacia()) {
            return null;
        }
        
        Ingrediente ingrediente = tope.getDato();
        tope = tope.getSiguiente();
        tamanio--;
        return ingrediente;
    }
    
    public Ingrediente verTope() {
        if (estaVacia()) {
            return null;
        }
        return tope.getDato();
    }
    
    public boolean estaVacia() {
        return tope == null;
    }
    
    public int getTamanio() {
        return tamanio;
    }
    
    public void limpiar() {
        tope = null;
        tamanio = 0;
    }
    
    public Ingrediente[] obtenerIngredientes() {
        Ingrediente[] ingredientes = new Ingrediente[tamanio];
        Nodo<Ingrediente> actual = tope;
        int i = tamanio - 1;
        
        while (actual != null) {
            ingredientes[i] = actual.getDato();
            actual = actual.getSiguiente();
            i--;
        }
        
        return ingredientes;
    }
    
    public boolean contiene(Ingrediente ingrediente) {
        Nodo<Ingrediente> actual = tope;
        while (actual != null) {
            if (actual.getDato() == ingrediente) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
}