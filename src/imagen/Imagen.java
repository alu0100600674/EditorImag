/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagen;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author jonay
 */
public class Imagen {
    
    
    public Imagen(){
        
    }
    
    public BufferedImage pasarAEscalaGrises(BufferedImage img){
        
        int red, green, blue;
        int gris;
        int rgb;
        
        BufferedImage imgGris = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j < img.getHeight(); j++){
                //Obtención de las componentes RGB
                rgb = img.getRGB(i, j);
                red = (rgb >> 16) & 0xff;
                green = (rgb >> 8) & 0xff;
                blue = (rgb) & 0xff;
                
                //Transformación a escala de grises por el sistema PAL
                gris = (int) ((0.222 * red) + (0.707 * green) + (0.071 * blue));
                imgGris.setRGB(i, j, new Color(gris, gris, gris).getRGB() );
            }
        }
        return imgGris;
    }
    
}
