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
public class Espejo {
    
    private BufferedImage img;
    
    public Espejo(BufferedImage tmp){
//        img = new BufferedImage(tmp.getWidth(), tmp.getHeight(), tmp.getType());
        img = tmp;
    }
    
    public BufferedImage espejoHorizontal(){
        BufferedImage resultado = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j < img.getHeight(); j++){
                resultado.setRGB(i, j, img.getRGB(img.getWidth() - 1 - i, j));
            }
        }
        
        return resultado;
    }
    
    public BufferedImage espejoVertical(){
        BufferedImage resultado = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        
        for(int i = 0; i < img.getHeight(); i++){
            for(int j = 0; j < img.getWidth(); j++){
                resultado.setRGB(j, i, img.getRGB(j, img.getHeight() - 1 - i));
            }
        }
        
        return resultado;
    }
    
}
