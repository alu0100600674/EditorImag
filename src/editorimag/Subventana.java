/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editorimag;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author jonay
 */
public class Subventana extends JFrame {
    
    public Subventana() {
        setTitle("Subventana"); //Cambiar el nombre por el de la función o lo que sea.
        setVisible(true);
        setSize(400,300);
        
        JPanel panel = new JPanel();
        
            
    }
    
    public void paint(Graphics g){ //Prueba de dibujar una línea.
        g.drawLine(30, 30, 30, 30);
    }
    
}
