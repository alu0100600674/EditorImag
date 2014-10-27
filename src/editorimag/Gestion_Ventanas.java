/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editorimag;

import imagen.PanelInfoColoresPixel;
import imagen.Subventana;
import imagen.Imagen;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author johnspirit
 */
public class Gestion_Ventanas {
    //las imagenes estaran ya en escala de grises guardadas en getimagenGuardada()
    //Atributos
    private ArrayList<Subventana> subVentanas; //array de subventanas
    private Subventana refSubventanaActual; //subventana actual
    
    private Imagen a;
   
    //a lo mejor se incluye una info de la imagen. Pensandolo
    
    //referencias
    private interfaz vp;
    private PanelInfoColoresPixel refPI;

    
    public interfaz getvp(){return vp;}
    public void setvp(interfaz inter){this.vp = inter;}
    
    public PanelInfoColoresPixel getrefPI(){ return refPI;}
    public void setrefPI(PanelInfoColoresPixel panel){ this.refPI = panel;}
    
    //get y setters de atributos
    //array de ventanas
    public ArrayList<Subventana> getSubVentanas(){ return subVentanas;}
    public void setSubventanas(ArrayList<Subventana> ventanitas) { this.subVentanas = ventanitas;}
    
    //ventana actual
    public Subventana getrefSubventanaActual(){ return refSubventanaActual;}
    public void setrefSubventanaActual(Subventana ventanita){ this.refSubventanaActual = ventanita;}
    
    public interfaz getRefvp(){ return vp;}
    public void setRefVp (interfaz ventanaP){ this.vp = ventanaP;}
    
    public PanelInfoColoresPixel getRefPI(){ return refPI;}
    public void setRefPI(PanelInfoColoresPixel panel){ this.refPI = panel;}
    
    public Gestion_Ventanas(interfaz vp){
        setRefVp(vp);
        setRefPI(getRefvp().getPanel());//modificar por interfaz)
        setSubventanas(new ArrayList<Subventana>());
    }

    public void crearSubventana()
    {   //crear la subventana
        //BufferedImage temp;
        //temp = imagen.Imagen.
                
        Subventana vimagen = new Subventana(a.getimagenGuardada());
    }
    
    //Subventana ventana = new Subventana(pasarAEscalaGrises(temp));
    
    //public void crearSubventana(B)
}
