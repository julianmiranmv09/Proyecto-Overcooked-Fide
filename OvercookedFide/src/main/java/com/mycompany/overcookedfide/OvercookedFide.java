/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.overcookedfide;

import javax.swing.SwingUtilities;

/**
 * @author julia
 */
public class OvercookedFide {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameView ventana = new GameView();
            ventana.setVisible(true);
        });
    }
}