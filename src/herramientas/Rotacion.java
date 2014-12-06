/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herramientas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jonay
 */
public class Rotacion {
    
    private BufferedImage img;
    private int rotacion;
    
    public Rotacion(BufferedImage tmp){
        img = tmp;
    }
    
    public BufferedImage rotar90(){
        BufferedImage resultado = new BufferedImage(img.getHeight(), img.getWidth(), img.getType());
        
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j < img.getHeight(); j++){
                resultado.setRGB(j, i, img.getRGB(img.getWidth() - 1 - i, j));
            }
        }
        
        return resultado;
    }
    
    public BufferedImage rotar180(){
        BufferedImage resultado = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        
        for(int i = 0; i < img.getHeight(); i++){
            for(int j = 0; j < img.getWidth(); j++){
                resultado.setRGB(j, i, img.getRGB(img.getWidth() - 1 - j, img.getHeight() - 1 - i));
            }
        }
        
        return resultado;
    }
    
    public BufferedImage rotar270(){
        BufferedImage resultado = new BufferedImage(img.getHeight(), img.getWidth(), img.getType());
        
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j < img.getHeight(); j++){
                resultado.setRGB(j, i, img.getRGB(i, img.getHeight() - 1 - j));
            }
        }
        
        return resultado;
    }
    
    public BufferedImage rotar(){
        controles();
        
        if(rotacion >= 360){ //Si la rotación es mayor a 360º, convertir a una vuelta.
            rotacion -= 360;
        }
        
        if((rotacion != 90) && (rotacion != 180) && (rotacion != 270) && (rotacion != 0)){
            
        }
        else{
            
        }
        
        return null; //Cambiar cuando se haga este método.
    }
    
    private void controles(){
        final JFrame v = new JFrame();
        v.setTitle("Rotar");
        v.setSize(300, 150);
        v.setResizable(false);
        v.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        v.add(panel);

        JLabel Rotar = new JLabel(" Introduce la rotación");
//        final JSpinner Dat_gamma = new JSpinner();
        
        final JTextField Dat_rotar = new JTextField();
        
        JButton Aceptar = new JButton("Aceptar");

        Aceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                rotacion = Integer.parseInt(Dat_rotar.getText());
                v.dispose();
            }
        });

        panel.add(Rotar);
        panel.add(Dat_rotar);
        panel.add(Aceptar);

        v.setVisible(true);
        
    }
    
}
