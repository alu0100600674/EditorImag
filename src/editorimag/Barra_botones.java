/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editorimag;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;

/**
 *
 * @author jonay
 */
public class Barra_botones extends JToolBar{
    //modificados pero no pasaba nada sin el final
    private final Boton Abrir;
    private final Boton Guardar;
    
    private final Boton Prueba;
    
    public Menu a;
    
    
    
    
    
    
    public Barra_botones(){
        a = new Menu();
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
                try {
                    a.gestor_img.nuevaImagen();
                } catch (IOException ex) {
                    Logger.getLogger(Barra_botones.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //System.out.println("Abrir"); //Sustituir por función adecuada.
            }
        });
        
        Guardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    a.gestor_img.guardarImagen();
                    //System.out.println("Guardar"); //Sustituir por función adecuada.
                } catch (IOException ex) {
                    Logger.getLogger(Barra_botones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    
}
