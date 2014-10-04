/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editorimag;

import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 *
 * @author jonay
 */
public class Barra_botones extends JToolBar{
    
    private Boton Abrir;
    private Boton Guardar;
    private Boton prueba;
    
    public Barra_botones(){
        
        Abrir = new Boton("Abrir", "Icono");
        this.add(Abrir);
        Guardar = new Boton("Guardar", "Icono");
        this.add(Guardar);
        
    }
    
}
