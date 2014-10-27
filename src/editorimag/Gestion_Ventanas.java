/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editorimag;

import imagen.PanelInfoColoresPixel;
import imagen.Subventana;
import imagen.Imagen;

/**
 *
 * @author johnspirit
 */
public class Gestion_Ventanas {
    //las imagenes estaran ya en escala de grises guardadas en getimagenGuardada()
    //Atributos
    private ArrayList<Subventana> subVentanas; //array de subventanas
    private Subventana refSubventanaActual; //subventana actual
    
    
    //a lo mejor se incluye una info de la imagen. Pensandolo
    
    //referencias
    private interfaz vp;
    private PanelInfoColoresPixel refPI;

    public Gestion_Ventanas() {
        this.ventana = new Subventana(pasarAEscalaGrises(getimagenGuardada()));
    }
    
    public interfaz getvp(){return vp;}
    public void setvp(interfaz inter){this.vp = inter;}
    
    public PanelInfoColoresPixel getrefPI(){ return refPI;}
    public void setrefPI(PanelInfoColoresPixel panel){ this.refPI = panel;}
    Subventana ventana;
}
