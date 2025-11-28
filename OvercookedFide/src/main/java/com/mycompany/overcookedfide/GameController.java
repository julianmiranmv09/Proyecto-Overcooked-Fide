/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.overcookedfide;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Neto
 */
public class GameController {
    private CintaTransportadora cinta;
    private ColaCircular colaOrdenes;
    private PilaIngredientes hamburguesaEnPreparacion;
    private int puntajeTotal;
    private int tiempoRestante; // en segundos
    private Timer timerJuego;
    private Timer timerOrdenes;
    private boolean juegoActivo;
    private Random random;
    private GameView vista;
    
    private final int TIEMPO_TOTAL = 300; // 5 minutos = 300 segundos
    private final int INTERVALO_ORDENES = 20; // segundos
    
    public GameController(GameView vista) {
        this.vista = vista;
        this.cinta = new CintaTransportadora();
        this.colaOrdenes = new ColaCircular();
        this.hamburguesaEnPreparacion = new PilaIngredientes();
        this.puntajeTotal = 0;
        this.tiempoRestante = TIEMPO_TOTAL;
        this.juegoActivo = false;
        this.random = new Random();
    }
    
    public void iniciarJuego() {
        juegoActivo = true;
        puntajeTotal = 0;
        tiempoRestante = TIEMPO_TOTAL;
        hamburguesaEnPreparacion.limpiar();
        
        // Generar primera orden
        generarOrden();
        
        // Timer para el tiempo del juego (cuenta regresiva cada segundo)
        timerJuego = new Timer();
        timerJuego.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tiempoRestante--;
                actualizarVista();
                
                if (tiempoRestante <= 0) {
                    finalizarJuego();
                }
            }
        }, 1000, 1000);
        
        // Timer para generar órdenes cada 20 segundos
        timerOrdenes = new Timer();
        timerOrdenes.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (juegoActivo) {
                    generarOrden();
                }
            }
        }, INTERVALO_ORDENES * 1000, INTERVALO_ORDENES * 1000);
        
        actualizarVista();
    }
    
    private void generarOrden() {
        if (!colaOrdenes.estaLlena()) {
            TipoHamburguesa tipo = TipoHamburguesa.values()[random.nextInt(3)];
            Orden nuevaOrden = new Orden(tipo.name(), tipo.getPuntos());
            colaOrdenes.encolar(nuevaOrden);
            actualizarVista();
        }
    }
    
    public void tomarIngrediente(int posicion) {
        if (!juegoActivo) return;
        
        Ingrediente ingrediente = cinta.tomarIngrediente(posicion);
        if (ingrediente != null) {
            hamburguesaEnPreparacion.apilar(ingrediente);
            actualizarVista();
        }
    }
    
    public void tirarIngrediente(int posicion) {
        if (!juegoActivo) return;
        
        cinta.tirarIngrediente(posicion);
        actualizarVista();
    }
    
    public boolean entregarHamburguesa() {
        if (!juegoActivo || colaOrdenes.estaVacia()) return false;
        
        Orden ordenActual = colaOrdenes.verFrente();
        TipoHamburguesa tipoRequerido = obtenerTipoHamburguesa(ordenActual.getTipo());
        
        if (verificarHamburguesa(tipoRequerido)) {
            puntajeTotal += ordenActual.getPuntos();
            colaOrdenes.desencolar();
            hamburguesaEnPreparacion.limpiar();
            actualizarVista();
            return true;
        }
        
        return false;
    }
    
    private boolean verificarHamburguesa(TipoHamburguesa tipo) {
        if (tipo == null) return false;
        
        Ingrediente[] requeridos = tipo.getRequeridos();
        Ingrediente[] preparados = hamburguesaEnPreparacion.obtenerIngredientes();
        
        if (requeridos.length != preparados.length) return false;
        
        // Verificar que todos los ingredientes requeridos estén presentes
        for (Ingrediente req : requeridos) {
            boolean encontrado = false;
            for (Ingrediente prep : preparados) {
                if (req == prep) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) return false;
        }
        
        return true;
    }
    
    private TipoHamburguesa obtenerTipoHamburguesa(String nombre) {
        try {
            return TipoHamburguesa.valueOf(nombre);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    public void descartarHamburguesa() {
        hamburguesaEnPreparacion.limpiar();
        actualizarVista();
    }
    
    private void finalizarJuego() {
        juegoActivo = false;
        if (timerJuego != null) {
            timerJuego.cancel();
        }
        if (timerOrdenes != null) {
            timerOrdenes.cancel();
        }
        actualizarVista();
        vista.mostrarFinJuego(puntajeTotal);
    }
    
    private void actualizarVista() {
        if (vista != null) {
            vista.actualizar();
        }
    }
    
    // Getters para la vista
    public CintaTransportadora getCinta() {
        return cinta;
    }
    
    public ColaCircular getColaOrdenes() {
        return colaOrdenes;
    }
    
    public PilaIngredientes getHamburguesaEnPreparacion() {
        return hamburguesaEnPreparacion;
    }
    
    public int getPuntajeTotal() {
        return puntajeTotal;
    }
    
    public int getTiempoRestante() {
        return tiempoRestante;
    }
    
    public boolean isJuegoActivo() {
        return juegoActivo;
    }
    
    public String getTiempoFormateado() {
        int minutos = tiempoRestante / 60;
        int segundos = tiempoRestante % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }
}
