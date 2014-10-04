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
    
    //Elementos del menú
    private JMenu Archivo;
    private JMenuItem Abrir;
    private JMenuItem Guardar;
    private JMenuItem Salir;
    
    
    public Menu(){
        Archivo = new JMenu("Archivo");
        Abrir = new JMenuItem();
        Archivo.add(Abrir);
        Guardar = new JMenuItem();
        Archivo.add(Guardar);
        Salir = new JMenuItem();
        Archivo.add(Salir);
        
    }
    
}
