/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editorimag;

import herramientas.Brillo_y_contraste;
import herramientas.Ecualizacion_histograma;
import imagen.Gestion_subventanas;
import imagen.Imagen;

/**
 *
 * @author jonay
 */
public class EditorImag {

    /**
     * @param args the command line arguments
     */
    
    public static int activa = 0;
    
    public static Gestion_subventanas gestor_img;
    
    public static boolean recortar = false;
    
    public static void main(String[] args) {
        gestor_img = new Gestion_subventanas();
        
        interfaz ventana = new interfaz();
        ventana.setVisible(true);
        
        
        
//        Brillo_y_contraste aa = new Brillo_y_contraste(); //Prueba
//        Ecualizacion_histograma bb = new Ecualizacion_histograma();
    }
    
}
