/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herramientas;

import static java.awt.Adjustable.HORIZONTAL;
import java.awt.Scrollbar;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author jonay
 */
public class Brillo_y_contraste {
    
//    private Scrollbar Brillo;
//    private Scrollbar Contraste;
//    private JButton Aceptar;
    
    private int Brillo;
    private int Contraste;
    
    private BufferedImage mod;
    
    public Brillo_y_contraste(){
        controles();
        imagenResultado();
    }
    
    private void controles(){
        JFrame control = new JFrame();
//        JPanel panel = new JPanel();
//        control.add(panel);
        control.setTitle("Brillo y Contraste");
        control.setSize(300, 200);
        control.setVisible(true);
//        control.setResizable(false);
        
        Scrollbar Brillo = new Scrollbar(HORIZONTAL, 0, 0, 0, 255);
        Brillo.setSize(270, 20);
        
        Scrollbar Contraste = new Scrollbar(HORIZONTAL, 0, 0, 0, 255);
        Contraste.setSize(270, 20);
        
        JButton Aceptar = new JButton();
        Aceptar.setSize(100, 30);
        
        control.add(Brillo).setLocation(15, 30);
        control.add(Contraste).setLocation(15, 80);
        control.add(Aceptar).setLocation(200, 150);
    }
    
    private void imagenResultado(){
        JFrame resultado = new JFrame();
    }
    
}
