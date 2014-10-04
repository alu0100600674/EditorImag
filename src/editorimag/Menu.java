/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editorimag;

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
    private JMenuItem Salir;
    
    //Elementos del menú Herramientas
    private JMenu Herramientas;
    private JMenu Transformaciones;
    private JMenuItem T_rotacion;
    private JMenuItem T_escalado;
    
    //Elementos del menú Ayuda
    private JMenu Ayuda;
    private JMenuItem Acerca_de;
    
    
    public Menu(){
        
        menuArchivo();
        menuHerramientas();
        menuAyuda();
        
    }
    
    private void menuArchivo(){
        Archivo = new JMenu("Archivo");
        this.add(Archivo);
        Abrir = new JMenuItem("Abrir");
        Archivo.add(Abrir);
        Guardar = new JMenuItem("Guardar");
        Archivo.add(Guardar);
        Salir = new JMenuItem("Salir");
        Archivo.add(Salir);
    }
    
    private void menuHerramientas(){
        Herramientas = new JMenu("Herramientas");
        this.add(Herramientas);
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
