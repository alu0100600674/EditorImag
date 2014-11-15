/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editorimag;

import imagen.Gestion_subventanas;
import imagen.Imagen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author jonay
 */
public class Menu extends JMenuBar{
    
    //Elementos del menú Archivo
    private JMenu Archivo;
    private JMenuItem Abrir;
    private JMenuItem Guardar;
    private JMenuItem Propiedades;
    private JMenuItem Salir;
    
    //Elementos del menú Ver
    private JMenu Ver;
    private JCheckBoxMenuItem Ver_Barra_Botones;
    private JCheckBoxMenuItem Ver_Barra_Estado;
    
    //Elementos del menú Herramientas
    private JMenu Herramientas;
    private JMenuItem Brillo_Contraste;
    
    private JMenu Histograma;
    private JMenuItem H_Ecualizacion;
    private JMenuItem H_Especificacion;
    
    private JMenuItem C_Gamma;
    private JMenuItem Perfil;
    private JMenuItem Sim_Dig_Imag;
    private JMenuItem Dif_Imags;
    
    private JMenu Transformaciones;
    private JMenuItem T_rotacion;
    private JMenuItem T_escalado;
    
    //Elementos del menú Ayuda
    private JMenu Ayuda;
    private JMenuItem Acerca_de;
    
//    private Imagen i;
    Gestion_subventanas gestor_img; //Usando gestor de subventanas.

    
    
    public Menu(){
        
        menuArchivo();
        menuVer();
        menuHerramientas();
        menuAyuda();
        
        gestor_img = new Gestion_subventanas(); //Usando gestor de subventanas.
        
    }
    
    private void menuArchivo(){
        Archivo = new JMenu("Archivo");
        this.add(Archivo);
        
        Abrir = new JMenuItem("Abrir");
        Archivo.add(Abrir);
        //private Imagen i;
        Abrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    gestor_img.nuevaImagen();
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        Guardar = new JMenuItem("Guardar");
        Archivo.add(Guardar);
        Guardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    gestor_img.guardarImagen();
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        
        
        Propiedades = new JMenuItem("Propiedades");
        Archivo.add(Propiedades);
        Propiedades.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Propiedades");
                gestor_img.abrirPropiedades();
                //Imagen i = new Imagen();
//                i.propiedades();
                
            }
        });
        
        Salir = new JMenuItem("Salir");
        Archivo.add(Salir);
        Salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
                //System.out.println("Salir");
            }
        });
    }
    
    private void menuVer(){
        Ver = new JMenu("Ver");
        this.add(Ver);
        
        Ver_Barra_Botones = new JCheckBoxMenuItem("Barra de Botones");
        Ver.add(Ver_Barra_Botones);
        
        Ver_Barra_Estado = new JCheckBoxMenuItem("Barra de Estado");
        Ver.add(Ver_Barra_Estado);
    }
    
    private void menuHerramientas(){
        Herramientas = new JMenu("Herramientas");
        this.add(Herramientas);
        
        Brillo_Contraste = new JMenuItem("Brillo y Contraste");
        Herramientas.add(Brillo_Contraste);
        Brillo_Contraste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Brillo y Constraste");
            }
        });
        
        Histograma = new JMenu("Histograma");
        Herramientas.add(Histograma);
        
        H_Ecualizacion = new JMenuItem("Ecualización");
        Histograma.add(H_Ecualizacion);
        H_Ecualizacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Ecualización del Histograma");
            }
        });
        
        H_Especificacion = new JMenuItem("Especificación");
        Histograma.add(H_Especificacion);
        H_Especificacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Especificación del Histograma");
            }
        });
        
        C_Gamma = new JMenuItem("Correción Gamma");
        Herramientas.add(C_Gamma);
        C_Gamma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Correción Gamma");
            }
        });
        
        Perfil = new JMenuItem("Perfil");
        Herramientas.add(Perfil);
        Perfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Perfil (Image-Cross Section)");
            }
        });
        
        Sim_Dig_Imag = new JMenuItem("Simulación de la digitalización de una Imagen");
        Herramientas.add(Sim_Dig_Imag);
        Sim_Dig_Imag.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Simulación de la digitalización de una Imagen");
            }
        });
        
        Dif_Imags = new JMenuItem("Diferencia entre dos imágenes");
        Herramientas.add(Dif_Imags);
        Dif_Imags.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Diferencia entre dos imágenes");
            }
        });
        
        Transformaciones = new JMenu("Transformaciones");
        Herramientas.add(Transformaciones);
        
        T_rotacion = new JMenuItem("De rotación");
        Transformaciones.add(T_rotacion);
        
        T_escalado = new JMenuItem("De escalado");
        Transformaciones.add(T_escalado);
    }
    
    private void menuAyuda(){
        Ayuda = new JMenu("Ayuda");
        this.add(Ayuda);
        Acerca_de = new JMenuItem("Acerca de");
        Ayuda.add(Acerca_de);
    }
    
}
