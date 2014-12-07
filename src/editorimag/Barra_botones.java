/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editorimag;

import static editorimag.EditorImag.gestor_img;
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
    
    
    //Iconos
    private ImageIcon abrir = new ImageIcon("src/images/abrir2.png");
    private ImageIcon guardar = new ImageIcon("src/images/guardar2.png");
    
    
    
    public Barra_botones(){
        Abrir = new Boton("Abrir", abrir);
        this.add(Abrir);
        Guardar = new Boton("Guardar", guardar);
        this.add(Guardar);
        
        this.addSeparator();
        
        
        activarBotones();
        
    }
    
    private void escalarImagenes(){
        
    }
    
    private void activarBotones(){
        
        Abrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    gestor_img.nuevaImagen();
                } catch (IOException ex) {
                    Logger.getLogger(Barra_botones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Guardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    gestor_img.guardarImagen();
                } catch (IOException ex) {
                    Logger.getLogger(Barra_botones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    
}
