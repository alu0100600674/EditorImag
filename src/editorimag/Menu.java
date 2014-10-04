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
    
    //Elementos del menú Ayuda
    private JMenu Ayuda;
    private JMenuItem Acerca_de;
    
    
    public Menu(){
        
        //Menú Archivo
        Archivo = new JMenu("Archivo");
        this.add(Archivo);
        Abrir = new JMenuItem("Abrir");
        Archivo.add(Abrir);
        Guardar = new JMenuItem("Guardar");
        Archivo.add(Guardar);
        Salir = new JMenuItem("Salir");
        Archivo.add(Salir);
        
        //Menú Herramientas
        Herramientas = new JMenu("Herramientas");
        this.add(Herramientas);
        
        //Menú Ayuda
        Ayuda = new JMenu("Ayuda");
        this.add(Ayuda);
        Acerca_de = new JMenuItem("Acerca de");
        Ayuda.add(Acerca_de);
        
    }
    
}
