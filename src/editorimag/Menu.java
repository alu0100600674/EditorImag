/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editorimag;

import static editorimag.EditorImag.activa;
import static editorimag.EditorImag.gestor_img;
import static editorimag.EditorImag.recortar;
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
    private JMenuItem Copia;
    private JMenuItem Recortar;
    private JMenuItem Propiedades;
    private JMenuItem Salir;
    
    //Elementos del menú Ver
//    private JMenu Ver;
//    private JCheckBoxMenuItem Ver_Barra_Botones;
//    private JCheckBoxMenuItem Ver_Barra_Estado;
    
    //Elementos del menú Operaciones lineales
    private JMenu Op_lineales;
    private JMenuItem Trans_por_tramos;
    private JMenuItem Brillo_Contraste;
    
    //Elementos del menú Operaciones no lineales
    private JMenu Op_no_lineales;
    private JMenuItem H_Ecualizacion;
    private JMenuItem Histograma;
    private JMenuItem Histograma_Ac;
    private JMenuItem Histograma_Ec;
    private JMenuItem H_Especificacion;
    private JMenuItem C_Gamma;
    private JMenuItem Perfil;
    private JMenuItem Sim_Dig_Imag;
    private JMenuItem Dif_Imags;
    
    //Elementos del menú Operaciones geométricas
    private JMenu Op_geometricas;
    private JMenu Espejo;
    private JMenuItem E_Vertical;
    private JMenuItem E_Horizontal;
    private JMenuItem Traspuesta;
    private JMenu Rotar;
    private JMenuItem R_90;
    private JMenuItem R_180;
    private JMenuItem R_270;
    private JMenuItem R_valor;
    private JMenuItem Escalar;
    
    //Elementos del menú Ayuda
    private JMenu Ayuda;
    private JMenuItem Acerca_de;
    
//    private Imagen i;
//    Gestion_subventanas gestor_img; //Usando gestor de subventanas.

    
    
    public Menu(){
        
        menuArchivo();
//        menuVer();
        menuOpLineales();
        menuOpNoLineales();
        menuOpGeometricas();
        menuAyuda();
        
//        gestor_img = new Gestion_subventanas(); //Usando gestor de subventanas.
        
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
        
        Copia = new JMenuItem("Copiar Imagen");
        Archivo.add(Copia);
        Copia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(01);
            }
        });
        
        Recortar = new JMenuItem("Recortar");
        Archivo.add(Recortar);
        Recortar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                recortar = true;
            }
        });
        
        
        Propiedades = new JMenuItem("Propiedades");
        Archivo.add(Propiedades);
        Propiedades.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                //System.out.println("Propiedades");
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
    
//    private void menuVer(){
//        Ver = new JMenu("Ver");
//        this.add(Ver);
//        
//        Ver_Barra_Botones = new JCheckBoxMenuItem("Barra de Botones");
//        Ver.add(Ver_Barra_Botones);
//        
//        Ver_Barra_Estado = new JCheckBoxMenuItem("Barra de Estado");
//        Ver.add(Ver_Barra_Estado);
//    }
    
    private void menuOpLineales(){
        Op_lineales = new JMenu("Operaciones lineales");
        this.add(Op_lineales);
        
        Brillo_Contraste = new JMenuItem("Brillo y Contraste");
        Op_lineales.add(Brillo_Contraste);
        Brillo_Contraste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(12);
                //System.out.println("Brillo y Constraste");
            }
        });
        
        Trans_por_tramos = new JMenuItem("Transformaciones por tramos");
        Op_lineales.add(Trans_por_tramos);
        Trans_por_tramos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(11);
            }
        });
        
    }
    
    private void menuOpNoLineales(){
        Op_no_lineales = new JMenu("Operaciones no lineales");
        this.add(Op_no_lineales);
        
//        H_Ecualizacion = new JMenuItem("Ecualización del Histograma");
//        Op_no_lineales.add(H_Ecualizacion);
//        H_Ecualizacion.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e){
//                gestor_img.getListaLan().get(activa).lanzar(21);
//            }
//        });
        
        Histograma = new JMenuItem("Histograma");
        Op_no_lineales.add(Histograma);
        Histograma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(211);
            }
        });
        
        Histograma_Ac = new JMenuItem("Histograma Acumulativo");
        Op_no_lineales.add(Histograma_Ac);
        Histograma_Ac.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(212);
            }
        });
        
        Histograma_Ec = new JMenuItem("Ecualizar histograma");
        Op_no_lineales.add(Histograma_Ec);
        Histograma_Ec.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(213);
            }
        });
        
        H_Especificacion = new JMenuItem("Especificación del Histograma");
        Op_no_lineales.add(H_Especificacion);
        H_Especificacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(22);
            }
        });
        
        C_Gamma = new JMenuItem("Correción Gamma");
        Op_no_lineales.add(C_Gamma);
        C_Gamma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(23);
            }
        });
        
        Perfil = new JMenuItem("Perfil");
//        Op_no_lineales.add(Perfil);
        Perfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Perfil (Image-Cross Section)");
            }
        });
        
        Sim_Dig_Imag = new JMenuItem("Simulación de la digitalización de una Imagen");
//        Op_no_lineales.add(Sim_Dig_Imag);
        Sim_Dig_Imag.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Simulación de la digitalización de una Imagen");
            }
        });
        
        Dif_Imags = new JMenuItem("Diferencia entre dos imágenes");
        Op_no_lineales.add(Dif_Imags);
        Dif_Imags.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(26);
            }
        });
        
    }
    
    private void menuOpGeometricas(){
        Op_geometricas = new JMenu("Operaciones geométricas");
        this.add(Op_geometricas);
        
        Espejo = new JMenu("Espejo");
        Op_geometricas.add(Espejo);
        
        E_Vertical = new JMenuItem("Vertical");
        Espejo.add(E_Vertical);
        E_Vertical.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(31);
            }
        });
        
        E_Horizontal = new JMenuItem("Horizontal");
        Espejo.add(E_Horizontal);
        E_Horizontal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(32);
            }
        });
        
        Traspuesta = new JMenuItem("Traspuesta");
        Op_geometricas.add(Traspuesta);
        Traspuesta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(33);
            }
        });
        
        Rotar = new JMenu("Rotar");
        Op_geometricas.add(Rotar);
        
        R_90 = new JMenuItem("90 grados");
        Rotar.add(R_90);
        R_90.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(34);
            }
        });
        
        R_180 = new JMenuItem("180 grados");
        Rotar.add(R_180);
        R_180.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(35);
            }
        });
        
        R_270 = new JMenuItem("270 grados");
        Rotar.add(R_270);
        R_270.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(36);
            }
        });
        
        R_valor = new JMenuItem("... grados");
        Rotar.add(R_valor);
        R_valor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(37);
            }
        });
        
        Escalar = new JMenuItem("Escalar");
        Op_geometricas.add(Escalar);
        Escalar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(38);
            }
        });
        
    }
    
    private void menuAyuda(){
        Ayuda = new JMenu("Ayuda");
        this.add(Ayuda);
        Acerca_de = new JMenuItem("Acerca de");
        Ayuda.add(Acerca_de);
        Acerca_de.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Acerca_de ad = new Acerca_de();
            }
        });
        
    }

}
