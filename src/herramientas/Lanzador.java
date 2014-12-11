/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herramientas;

import java.awt.image.BufferedImage;
import static editorimag.EditorImag.gestor_img;

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
            //Pruebas
            case 01:
                herramientaDePrueba();
            
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
            case 211:
                histograma();
                break;
            case 212:
                histogramaAcumulativo();
                break;
            case 213:
                ecualizarHistograma();
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
            case 31:
                espejoVertical();
                break;
            case 32:
                espejoHorizontal();
                break;
            case 33:
                traspuesta();
                break;
            case 34:
                rotar90();
                break;
            case 35:
                rotar180();
                break;
            case 36:
                rotar270();
                break;
            case 37:
                rotar();
                break;
            case 38:
                escalar();
        }
        
    }
    
    private void herramientaDePrueba(){
        H_Prueba prueba = new H_Prueba(img);
        gestor_img.anadirImagen(prueba.guardarOtra());
    }
    
    //Operaciones Lineales
    private void transfLinealesTramos(){
        Transf_lineales_tramos a = new Transf_lineales_tramos(img);
        //gestor_img.añadirImagen(a.transformacion());
        //FALTA POR DEFINIR
    }
    
    private void brilloYContraste(){
        Brillo_y_contraste byc = new Brillo_y_contraste(img);
//        gestor_img.anadirImagen(byc.actualizarBrilloYContraste());
    }
    
    
    //Operaciones No Lineales
    private void ecualizacionHistograma(){
        Ecualizacion_histograma ech = new Ecualizacion_histograma(img);
    }
    
    private void histograma(){
        Ecualizacion_histograma ech1 = new Ecualizacion_histograma(img);
        ech1.histograma();
    }
    
    private void histogramaAcumulativo(){
        Ecualizacion_histograma ech2 = new Ecualizacion_histograma(img);
        ech2.histogramaAcumulativo();
    }
    
    private void ecualizarHistograma(){
        Ecualizacion_histograma ech3 = new Ecualizacion_histograma(img);
        gestor_img.anadirImagen(ech3.ecualizacion());
    }
    
    private void especificacionHistograma(){
       
    }
    
    private void correccionGamma(){
        Correccion_gamma cg = new Correccion_gamma(img);
    }
    
    private void perfil(){
        
    }
    
    private void simulacionDigitalizacionImagen(){
        
    }
    
    private void diferenciaDosImagenes(){
        Diferencia_dos_imagenes ddi = new Diferencia_dos_imagenes(img);
    }
    
    
    //Operaciones Geométricas
    private void espejoVertical(){
        Espejo e = new Espejo(img);
        gestor_img.anadirImagen(e.espejoVertical());
    }
    
    private void espejoHorizontal(){
        Espejo e = new Espejo(img);
        gestor_img.anadirImagen(e.espejoHorizontal());
    }
    
    private void traspuesta(){
        Traspuesta t = new Traspuesta(img);
        gestor_img.anadirImagen(t.trasponer());
    }
    
    private void rotar90(){
        Rotacion r = new Rotacion(img);
        gestor_img.anadirImagen(r.rotar90());
    }
    
    private void rotar180(){
        Rotacion r = new Rotacion(img);
        gestor_img.anadirImagen(r.rotar180());
    }
    
    private void rotar270(){
        Rotacion r = new Rotacion(img);
        gestor_img.anadirImagen(r.rotar270());
    }
    
    private void rotar(){
        Rotacion r = new Rotacion(img);
        r.rotar();
    }
    
    private void escalar(){
        Escalado e = new Escalado(img);
    }
    
}
