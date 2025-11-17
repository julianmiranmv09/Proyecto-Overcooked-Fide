/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.overcookedfide;
import java.util.Random;
/**
 *
 * @author Sofi
 */
public class CintaTransportadora {
  private Ingrediente[] cinta;
  private dinal int CAPACIDAD = 5;
  private Random random = new Random(),

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
      case 0: return Ingrediente.Pan;
      case 1: return Ingrediente.Carne;
      case 2: return Ingrediente.Queso;
      default: return Ingrediente.Lechuga;
    }
  }

  public Ingrediente tomarIngrediente(int pos){
    if (pos < 0 || pos >= cinta.getTamanio()) return null;

    Ingrediente tomado = remover(pos);

    compactar();
    verificarRelleno();
    desplazarIzquierda();

    return tomado;
  }

  public void tirarIngrediente(int pos){
    if (pos < 0 || pos >= cinta.getTamanio()) return;
    remover(pos);
    compactar();
    verificarRelleno();
  }

  private Ingrediente remover(int pos){
    Lista<Ingrediente> nueva = new Lista<>();
    Ingrediente eliminado = null;

    for (int i = 0; i < cinta.getTamanio(); i++){
      Ingrediente actual = cinta.obtenerEnPosicion(i);
      if (i == pos){
        eliminado = actual;
      }else {
        nueva.agregar(actual);
      }
    }
    cinta = nueva;
    return eliminado;
  }

  private void compactar(){
  }

  private void verificarRelleno(){
    if (cinta.getTamanio() <= 3){
      rellenarCinta();
    }
  }

  private void desplazarIzquierda(){
    if (cinta.getTamanio() == 0) return;

    Ingrediente primero = cinta.obtenerEnPosicion(0);
    remover(0);
    cinta.agregar(primero);
  }

  public void imprimirCinta(){
    System.out.print("[ ");
    for (int i = 0; i < cinta.getTamanio(); i++) {
      System.out.print(cinta.obtenerEnPosicion(i) + " ");
    }
    System.out.println("]");
  }
}
