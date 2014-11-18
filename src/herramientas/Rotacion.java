/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herramientas;

import java.awt.image.BufferedImage;

/**
 *
 * @author jonay
 */
public class Rotacion {
    
    private BufferedImage img;
    
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
        return null; //Cambiar cuando se haga este método.
    }
    
}
