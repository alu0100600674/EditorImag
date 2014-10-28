/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagen;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author jonay
 */
public class Gestion_subventanas {
    
    private ArrayList<Imagen> img_list;
    
    public Gestion_subventanas(){
        img_list = new ArrayList<>();
    }
    
    public void nuevaImagen() throws IOException{
        img_list.add(new Imagen());
    }
    
    public void guardarImagen() throws IOException{
        if(!img_list.isEmpty()){
            //Buscar la ventana actual.
            
            //Guardar sobre la imagen actual.
            img_list.get(0).guardarImagen(); //Cambiar el 0 por la ventana actual.
        }
    }
    
    public void buscarSubventanaActual(){
        
    }
    
}
