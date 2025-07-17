/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea;


import javax.swing.*;
import java.awt.*;

/**
 *
 * @author MCCLA
 */

public class ExplosionAnimation {
    private int x, y; // Posición de la explosión
    private boolean visible; // Si la explosión está visible o no

    public ExplosionAnimation(int x, int y) {
        this.x = x;
        this.y = y;
        this.visible = true;
    }

    public void draw(Graphics g) {
        if (visible) {
            // Carga la imagen de la explosión
            Image explosionImage = new ImageIcon("data/Explosion.png").getImage();
            g.drawImage(explosionImage, x, y, null);
        }
    }

    public void hide() {
        visible = false;
    }
}
