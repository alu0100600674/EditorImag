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
public class Lanzador {
    
    private BufferedImage img;
    
    public Lanzador(BufferedImage tmp){
        img = tmp;
    }
    
    public void lanzar(int opcion){
        
        switch(opcion){
            case 1:
                transfLinealesTramos();
                break;
            case 2:
                ecualizacionHistograma();
                break;
            case 3:
                especificacionHistograma();
                break;
            case 4:
                brilloYContraste();
                break;
            case 5:
                correccionGamma();
                break;
            case 6:
                diferenciaDosImagenes();
                break;
        }
        
    }
    
    private void transfLinealesTramos(){
        
    }
    
    private void ecualizacionHistograma(){
        Ecualizacion_histograma ech = new Ecualizacion_histograma(img);
    }
    
    private void especificacionHistograma(){
        
    }
    
    private void brilloYContraste(){
        
    }
    
    private void correccionGamma(){
        
    }
    
    private void diferenciaDosImagenes(){
        
    }
    
}
