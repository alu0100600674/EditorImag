/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herramientas;

import editorimag.EditorImag;
import static editorimag.EditorImag.gestor_img;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    
    public void rotar(){
        controles();

    }
    
    private BufferedImage rotar_x(int rot){
        BufferedImage resultado = null;
        
        if(rot >= 360){ //Si la rotación es mayor a 360º, convertir a una vuelta.
            rot -= 360;
        }
        
        if((rot != 90) && (rot != 180) && (rot != 270) && (rot != 0)){
            
        }
        else{
            switch(rot){
                case 90:
                    resultado = rotar90();
                    break;
                case 180:
                    resultado = rotar180();
                    break;
                case 270:
                    resultado = rotar270();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "No se han hecho cambios respecto a la imagen original.");
                    break;
            }
//            return resultado;
        }
        return resultado;
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
                gestor_img.anadirImagen(rotar_x(rotacion));
            }
        });

        panel.add(Rotar);
        panel.add(Dat_rotar);
        panel.add(Aceptar);

        v.setVisible(true);
        
    }
    
}
