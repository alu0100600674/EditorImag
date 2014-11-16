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
            //Operaciones Lineales
            case 11:
                transfLinealesTramos();
                break;
            case 12:
                brilloYContraste();
                
                break;
            
            //Operaciones No Lineales
            case 21:
                ecualizacionHistograma();
                break;
            case 22:
                especificacionHistograma();
                break;
            case 23:
                correccionGamma();
                break;
            case 24:
                perfil();
                break;
            case 25:
                simulacionDigitalizacionImagen();
                break;
            case 26:
                diferenciaDosImagenes();
                break;
            
            //Operaciones Geométricas
                
        }
        
    }
    
    
    //Operaciones Lineales
    private void transfLinealesTramos(){
        
    }
    
    private void brilloYContraste(){
        
    }
    
    
    //Operaciones No Lineales
    private void ecualizacionHistograma(){
        Ecualizacion_histograma ech = new Ecualizacion_histograma(img);
    }
    
    private void especificacionHistograma(){
        
    }
    
    private void correccionGamma(){
        
    }
    
    private void perfil(){
        
    }
    
    private void simulacionDigitalizacionImagen(){
        
    }
    
    private void diferenciaDosImagenes(){
        
    }
    
    
    //Operaciones Geométricas
    
    
}
