/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editorimag;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;

/**
 *
 * @author jonay
 */
public class Barra_botones extends JToolBar{
    
    private Boton Abrir;
    private Boton Guardar;
    
    private Boton Prueba;
    
    public Barra_botones(){
        
        Abrir = new Boton("Abrir", new ImageIcon("src/images/abrir2.png") );
        this.add(Abrir);
        Guardar = new Boton("Guardar", new ImageIcon("src/images/guardar2.png") );
        this.add(Guardar);
        
        this.addSeparator();
        
        Prueba = new Boton("Prueba", new ImageIcon("src/images/abrir2.png") );
        this.add(Prueba);
        
        activarBotones();
        
    }
    
    private void activarBotones(){
        
        Abrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Abrir"); //Sustituir por función adecuada.
            }
        });
        
        Guardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Guardar"); //Sustituir por función adecuada.
            }
        });
        
    }
    
}
