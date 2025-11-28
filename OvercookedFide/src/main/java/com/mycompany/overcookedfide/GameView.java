/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.overcookedfide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Equipo
 */
public class GameView extends JFrame {
    private GameController controller;
    private JPanel panelPrincipal;
    private JPanel panelCinta;
    private JPanel panelOrdenes;
    private JPanel panelHamburguesa;
    private JPanel panelInfo;
    private JLabel lblTiempo;
    private JLabel lblPuntaje;
    private JButton btnIniciar;
    private JButton btnEntregar;
    private JButton btnDescartar;
    
    public GameView() {
        controller = new GameController(this);
        inicializarComponentes();
        configurarVentana();
    }
    
    private void inicializarComponentes() {
        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(255, 248, 220));
        
        // Panel superior - Información del juego
        panelInfo = crearPanelInfo();
        
        // Panel central - Cinta transportadora
        panelCinta = crearPanelCinta();
        
        // Panel izquierdo - Órdenes
        panelOrdenes = crearPanelOrdenes();
        
        // Panel derecho - Hamburguesa en preparación
        panelHamburguesa = crearPanelHamburguesa();
        
        // Agregar paneles al panel principal
        panelPrincipal.add(panelInfo, BorderLayout.NORTH);
        panelPrincipal.add(panelCinta, BorderLayout.CENTER);
        panelPrincipal.add(panelOrdenes, BorderLayout.WEST);
        panelPrincipal.add(panelHamburguesa, BorderLayout.EAST);
        
        add(panelPrincipal);
    }
    
    private JPanel crearPanelInfo() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        panel.setBackground(new Color(255, 228, 181));
        panel.setBorder(BorderFactory.createTitledBorder("Información del Juego"));
        
        lblTiempo = new JLabel("Tiempo: 05:00");
        lblTiempo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTiempo.setForeground(new Color(139, 69, 19));
        
        lblPuntaje = new JLabel("Puntaje: 0");
        lblPuntaje.setFont(new Font("Arial", Font.BOLD, 24));
        lblPuntaje.setForeground(new Color(0, 100, 0));
        
        btnIniciar = new JButton("Iniciar Juego");
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 16));
        btnIniciar.setBackground(new Color(34, 139, 34));
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setFocusPainted(false);
        btnIniciar.addActionListener(e -> controller.iniciarJuego());
        
        panel.add(lblTiempo);
        panel.add(lblPuntaje);
        panel.add(btnIniciar);
        
        return panel;
    }
    
    private JPanel crearPanelCinta() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 220));
        panel.setBorder(BorderFactory.createTitledBorder("Cinta Transportadora"));
        
        JPanel panelBotones = new JPanel(new GridLayout(2, 5, 10, 10));
        panelBotones.setBackground(new Color(245, 245, 220));
        
        // Fila de botones "Tomar"
        for (int i = 0; i < 5; i++) {
            final int posicion = i;
            JButton btnTomar = new JButton("Tomar");
            btnTomar.setBackground(new Color(100, 149, 237));
            btnTomar.setForeground(Color.WHITE);
            btnTomar.addActionListener(e -> controller.tomarIngrediente(posicion));
            panelBotones.add(btnTomar);
        }
        
        // Fila de botones "Tirar"
        for (int i = 0; i < 5; i++) {
            final int posicion = i;
            JButton btnTirar = new JButton("Tirar");
            btnTirar.setBackground(new Color(220, 20, 60));
            btnTirar.setForeground(Color.WHITE);
            btnTirar.addActionListener(e -> controller.tirarIngrediente(posicion));
            panelBotones.add(btnTirar);
        }
        
        panel.add(panelBotones);
        
        return panel;
    }
    
    private JPanel crearPanelOrdenes() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(250, 400));
        panel.setBackground(new Color(255, 239, 213));
        panel.setBorder(BorderFactory.createTitledBorder("Órdenes Pendientes"));
        
        return panel;
    }
    
    private JPanel crearPanelHamburguesa() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setPreferredSize(new Dimension(250, 400));
        panel.setBackground(new Color(255, 239, 213));
        panel.setBorder(BorderFactory.createTitledBorder("Hamburguesa en Preparación"));
        
        JPanel panelBotones = new JPanel(new GridLayout(2, 1, 5, 5));
        panelBotones.setBackground(new Color(255, 239, 213));
        
        btnEntregar = new JButton("Entregar Hamburguesa");
        btnEntregar.setBackground(new Color(34, 139, 34));
        btnEntregar.setForeground(Color.WHITE);
        btnEntregar.addActionListener(e -> {
            if (controller.entregarHamburguesa()) {
                JOptionPane.showMessageDialog(this, "¡Hamburguesa entregada correctamente!");
            } else {
                JOptionPane.showMessageDialog(this, "La hamburguesa no coincide con la orden", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnDescartar = new JButton("Descartar Hamburguesa");
        btnDescartar.setBackground(new Color(220, 20, 60));
        btnDescartar.setForeground(Color.WHITE);
        btnDescartar.addActionListener(e -> controller.descartarHamburguesa());
        
        panelBotones.add(btnEntregar);
        panelBotones.add(btnDescartar);
        
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void configurarVentana() {
        setTitle("OverCooked-Fide");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    public void actualizar() {
        lblTiempo.setText("Tiempo: " + controller.getTiempoFormateado());
        lblPuntaje.setText("Puntaje: " + controller.getPuntajeTotal());
        
        actualizarCinta();
        actualizarOrdenes();
        actualizarHamburguesa();
    }
    
    private void actualizarCinta() {
        panelCinta.removeAll();
        
        JPanel panelIngredientes = new JPanel(new GridLayout(1, 5, 10, 10));
        panelIngredientes.setBackground(new Color(245, 245, 220));
        panelIngredientes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        Ingrediente[] cinta = controller.getCinta().getCinta();
        for (int i = 0; i < cinta.length; i++) {
            JLabel lblIngrediente = new JLabel();
            lblIngrediente.setHorizontalAlignment(SwingConstants.CENTER);
            lblIngrediente.setFont(new Font("Arial", Font.BOLD, 14));
            lblIngrediente.setOpaque(true);
            lblIngrediente.setBackground(Color.WHITE);
            lblIngrediente.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            lblIngrediente.setPreferredSize(new Dimension(100, 80));
            
            if (cinta[i] != null) {
                lblIngrediente.setText("<html><center>" + cinta[i].name() + 
                    "<br>Pos: " + i + "</center></html>");
                lblIngrediente.setBackground(getColorIngrediente(cinta[i]));
            } else {
                lblIngrediente.setText("Vacío");
                lblIngrediente.setBackground(Color.LIGHT_GRAY);
            }
            
            panelIngredientes.add(lblIngrediente);
        }
        
        JPanel panelBotones = new JPanel(new GridLayout(2, 5, 10, 10));
        panelBotones.setBackground(new Color(245, 245, 220));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        for (int i = 0; i < 5; i++) {
            final int posicion = i;
            JButton btnTomar = new JButton("Tomar");
            btnTomar.setBackground(new Color(100, 149, 237));
            btnTomar.setForeground(Color.WHITE);
            btnTomar.setFont(new Font("Arial", Font.BOLD, 12));
            btnTomar.addActionListener(e -> controller.tomarIngrediente(posicion));
            panelBotones.add(btnTomar);
        }
        
        for (int i = 0; i < 5; i++) {
            final int posicion = i;
            JButton btnTirar = new JButton("Tirar");
            btnTirar.setBackground(new Color(220, 20, 60));
            btnTirar.setForeground(Color.WHITE);
            btnTirar.setFont(new Font("Arial", Font.BOLD, 12));
            btnTirar.addActionListener(e -> controller.tirarIngrediente(posicion));
            panelBotones.add(btnTirar);
        }
        
        panelCinta.setLayout(new BorderLayout(10, 10));
        panelCinta.setBorder(BorderFactory.createTitledBorder("Cinta Transportadora"));
        panelCinta.setBackground(new Color(245, 245, 220));
        panelCinta.add(panelIngredientes, BorderLayout.CENTER);
        panelCinta.add(panelBotones, BorderLayout.SOUTH);
        
        panelCinta.revalidate();
        panelCinta.repaint();
    }
    
    private void actualizarOrdenes() {
        panelOrdenes.removeAll();
        panelOrdenes.setLayout(new BoxLayout(panelOrdenes, BoxLayout.Y_AXIS));
        panelOrdenes.setBorder(BorderFactory.createTitledBorder("Órdenes Pendientes"));
        
        Orden[] ordenes = controller.getColaOrdenes().obtenerOrdenes();
        
        for (int i = 0; i < ordenes.length; i++) {
            JPanel panelOrden = new JPanel();
            panelOrden.setLayout(new BoxLayout(panelOrden, BoxLayout.Y_AXIS));
            panelOrden.setBackground(i == 0 ? new Color(144, 238, 144) : Color.WHITE);
            panelOrden.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            panelOrden.setMaximumSize(new Dimension(230, 120));
            
            JLabel lblTitulo = new JLabel((i == 0 ? "→ " : "") + ordenes[i].getTipo());
            lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
            lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            JLabel lblPuntos = new JLabel("Puntos: " + ordenes[i].getPuntos());
            lblPuntos.setFont(new Font("Arial", Font.PLAIN, 12));
            lblPuntos.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            TipoHamburguesa tipo = TipoHamburguesa.valueOf(ordenes[i].getTipo());
            JTextArea txtIngredientes = new JTextArea();
            txtIngredientes.setEditable(false);
            txtIngredientes.setBackground(panelOrden.getBackground());
            txtIngredientes.setFont(new Font("Arial", Font.PLAIN, 11));
            
            StringBuilder sb = new StringBuilder("Ingredientes:\n");
            for (Ingrediente ing : tipo.getRequeridos()) {
                sb.append("• ").append(ing.name()).append("\n");
            }
            txtIngredientes.setText(sb.toString());
            txtIngredientes.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            panelOrden.add(Box.createVerticalStrut(5));
            panelOrden.add(lblTitulo);
            panelOrden.add(lblPuntos);
            panelOrden.add(txtIngredientes);
            panelOrden.add(Box.createVerticalStrut(5));
            
            panelOrdenes.add(panelOrden);
            panelOrdenes.add(Box.createVerticalStrut(10));
        }
        
        panelOrdenes.revalidate();
        panelOrdenes.repaint();
    }
    
    private void actualizarHamburguesa() {
        panelHamburguesa.removeAll();
        panelHamburguesa.setLayout(new BorderLayout(10, 10));
        panelHamburguesa.setBorder(BorderFactory.createTitledBorder("Hamburguesa en Preparación"));
        
        JPanel panelIngredientes = new JPanel();
        panelIngredientes.setLayout(new BoxLayout(panelIngredientes, BoxLayout.Y_AXIS));
        panelIngredientes.setBackground(new Color(255, 239, 213));
        
        Ingrediente[] ingredientes = controller.getHamburguesaEnPreparacion().obtenerIngredientes();
        
        for (Ingrediente ing : ingredientes) {
            JLabel lblIng = new JLabel("  " + ing.name());
            lblIng.setFont(new Font("Arial", Font.BOLD, 14));
            lblIng.setOpaque(true);
            lblIng.setBackground(getColorIngrediente(ing));
            lblIng.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            lblIng.setMaximumSize(new Dimension(230, 40));
            panelIngredientes.add(lblIng);
            panelIngredientes.add(Box.createVerticalStrut(5));
        }
        
        JPanel panelBotones = new JPanel(new GridLayout(2, 1, 5, 5));
        panelBotones.setBackground(new Color(255, 239, 213));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        btnEntregar = new JButton("Entregar Hamburguesa");
        btnEntregar.setBackground(new Color(34, 139, 34));
        btnEntregar.setForeground(Color.WHITE);
        btnEntregar.setFont(new Font("Arial", Font.BOLD, 12));
        btnEntregar.addActionListener(e -> {
            if (controller.entregarHamburguesa()) {
                JOptionPane.showMessageDialog(this, "¡Hamburguesa entregada correctamente!");
            } else {
                JOptionPane.showMessageDialog(this, "La hamburguesa no coincide con la orden", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnDescartar = new JButton("Descartar Hamburguesa");
        btnDescartar.setBackground(new Color(220, 20, 60));
        btnDescartar.setForeground(Color.WHITE);
        btnDescartar.setFont(new Font("Arial", Font.BOLD, 12));
        btnDescartar.addActionListener(e -> controller.descartarHamburguesa());
        
        panelBotones.add(btnEntregar);
        panelBotones.add(btnDescartar);
        
        JScrollPane scroll = new JScrollPane(panelIngredientes);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        panelHamburguesa.add(scroll, BorderLayout.CENTER);
        panelHamburguesa.add(panelBotones, BorderLayout.SOUTH);
        
        panelHamburguesa.revalidate();
        panelHamburguesa.repaint();
    }
    
    private Color getColorIngrediente(Ingrediente ing) {
        switch (ing) {
            case PAN: return new Color(255, 228, 181);
            case CARNE: return new Color(165, 42, 42);
            case QUESO: return new Color(255, 215, 0);
            case LECHUGA: return new Color(144, 238, 144);
            default: return Color.WHITE;
        }
    }
    
    public void mostrarFinJuego(int puntajeFinal) {
        String mensaje = "¡Juego terminado!\n\nPuntaje final: " + puntajeFinal + " puntos";
        JOptionPane.showMessageDialog(this, mensaje, "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
        btnIniciar.setEnabled(true);
    }
}