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
public class Traspuesta {
    
    private BufferedImage img;
    
    public Traspuesta(BufferedImage tmp){
        img = tmp;
    }
    
    public BufferedImage trasponer(){
        BufferedImage resultado = new BufferedImage(img.getHeight(), img.getWidth(), img.getType());
        
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j < img.getHeight(); j++){
                resultado.setRGB(j, i, img.getRGB(i, j));
            }
        }
        
        return resultado;
    }
    
}
