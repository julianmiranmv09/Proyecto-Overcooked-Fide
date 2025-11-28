/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.overcookedfide;
import java.util.Random;


public class CintaTransportadora {
    private Ingrediente[] cinta;
    private final int CAPACIDAD = 5;
    private Random random = new Random();

    public CintaTransportadora(){
        cinta = new Ingrediente[CAPACIDAD];
        rellenarCinta();
    }
  
    private void rellenarCinta(){
        for(int i = 0; i < CAPACIDAD; i++){
            cinta[i] = generarIngrediente();
        }
    }

    private Ingrediente generarIngrediente(){
        int n = random.nextInt(4);

        switch (n){
            case 0: return Ingrediente.PAN;
            case 1: return Ingrediente.CARNE;
            case 2: return Ingrediente.QUESO;
            default: return Ingrediente.LECHUGA;
        }
    }

    public Ingrediente tomarIngrediente(int pos){
        if (pos < 0 || pos >= CAPACIDAD) return null;
        if (cinta[pos] == null) return null;

        Ingrediente tomado = cinta[pos];
        cinta[pos] = null;

        compactar();
        verificarRelleno();
        desplazarIzquierda();

        return tomado;
    }

    public void tirarIngrediente(int pos){
        if (pos < 0 || pos >= CAPACIDAD) return;
        if (cinta[pos] == null) return;
        
        cinta[pos] = null;
        compactar();
        verificarRelleno();
        desplazarIzquierda();
    }

    private void compactar(){
        // Mover todos los ingredientes no nulos hacia la izquierda
        int writePos = 0;
        for (int i = 0; i < CAPACIDAD; i++){
            if (cinta[i] != null){
                cinta[writePos] = cinta[i];
                if (writePos != i){
                    cinta[i] = null;
                }
                writePos++;
            }
        }
    }

    private void verificarRelleno(){
        int count = contarIngredientes();
        if (count <= 3){
            // Rellenar las posiciones vacías
            for (int i = 0; i < CAPACIDAD; i++){
                if (cinta[i] == null){
                    cinta[i] = generarIngrediente();
                }
            }
        }
    }

    private void desplazarIzquierda(){
        // Implementación circular: el primer elemento pasa al final
        Ingrediente primero = cinta[0];
        for (int i = 0; i < CAPACIDAD - 1; i++){
            cinta[i] = cinta[i + 1];
        }
        cinta[CAPACIDAD - 1] = primero;
    }

    private int contarIngredientes(){
        int count = 0;
        for (int i = 0; i < CAPACIDAD; i++){
            if (cinta[i] != null){
                count++;
            }
        }
        return count;
    }

    public Ingrediente[] getCinta(){
        return cinta;
    }

    public int getCapacidad(){
        return CAPACIDAD;
    }

    public void imprimirCinta(){
        System.out.print("[ ");
        for (int i = 0; i < CAPACIDAD; i++) {
            if (cinta[i] != null){
                System.out.print(cinta[i] + " ");
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println("]");
    }
}
