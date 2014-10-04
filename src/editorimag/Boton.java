/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editorimag;

import javax.swing.JButton;

/**
 *
 * @author jonay
 */
public class Boton extends JButton{
    
    private int tamano = 20;
    
    public Boton(String nombre, String icono){
        
        this.setText(nombre);
        this.setIcon(null);
        
    }
    
}
