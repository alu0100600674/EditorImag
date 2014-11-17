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
public class H_Prueba {
    
    private BufferedImage img;
    
    public H_Prueba(BufferedImage tmp){
        img = tmp;
    }
    
    public BufferedImage guardarOtra(){
        BufferedImage resultado = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j < img.getHeight(); j++){
                resultado.setRGB(i, j, img.getRGB(i, j));
            }
        }
        
        return resultado;
    }
    
}
