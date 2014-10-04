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
    
    private JButton Abrir;
    private JButton Guardar;
    
    public Barra_botones(){
        
        Abrir = new JButton("Abrir");
        this.add(Abrir);
        Guardar = new JButton("Guardar");
        this.add(Guardar);
        
    }
    
}
