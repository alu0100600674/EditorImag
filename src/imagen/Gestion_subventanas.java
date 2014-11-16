/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagen;

import static editorimag.EditorImag.activa;
import java.awt.Graphics;
import herramientas.Lanzador;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author jonay
 */
public class Gestion_subventanas {
    
    private ArrayList<Imagen> img_list;
    private ArrayList<Lanzador> lan_list;
    
    private int Subv_actual;
    private int Subv_actual2;
    
    public void setSubvActual(int v){
        Subv_actual = v;
    }
    
    public int getSubvActual(){
        return Subv_actual;
    }
            
    
    public ArrayList<Imagen> getListaImg(){
        return img_list;
    }
    
    public ArrayList<Lanzador> getListaLan(){
        return lan_list;
    }
    
    public Gestion_subventanas(){
        img_list = new ArrayList<>();
        lan_list = new ArrayList<>();
    }
    
    public void nuevaImagen() throws IOException{
        int tam;
        if(img_list.size() == 0)
            tam = 0;
        else
            tam = img_list.size();
        
        img_list.add(new Imagen(tam));
        
//        Lanzador l = new Lanzador(img_list.get(tam).getImg());
        
        lan_list.add(new Lanzador(img_list.get(tam).getImg()));
    }
    
    public void guardarImagen() throws IOException{
        if(!img_list.isEmpty()){
            img_list.get(activa).guardarImagen(); //Guardar la imagen de la ventana actual.
        }
    }
    
    public void abrirPropiedades(){
        img_list.get(activa).propiedades(img_list.get(activa).getGraphics());
        //System.out.print("hola");
    }
    
    public void buscarSubventanaActual(){
        
    }
    
}
