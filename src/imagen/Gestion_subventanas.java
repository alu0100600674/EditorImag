/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagen;

import static editorimag.EditorImag.activa;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author jonay
 */
public class Gestion_subventanas {
    
    private ArrayList<Imagen> img_list;
    
    private int Subv_actual;
    private int Subv_actual2;
    
    public void setSubvActual(int v){
        Subv_actual = v;
    }
    
    public ArrayList<Imagen> getListaImg(){
        return img_list;
    }
    
    public Gestion_subventanas(){
        img_list = new ArrayList<>();
    }
    
    public void nuevaImagen() throws IOException{
        int tam;
        if(img_list.size() == 0)
            tam = 0;
        else
            tam = img_list.size();
        
        img_list.add(new Imagen(tam));
    }
    
    public void guardarImagen() throws IOException{
        if(!img_list.isEmpty()){
            img_list.get(activa).guardarImagen(); //Guardar la imagen de la ventana actual.
        }
    }
    
    public void abrirPropiedades(){
        img_list.get(activa).propiedades(); 
    }
    
    public void buscarSubventanaActual(){
        
    }
    
}
