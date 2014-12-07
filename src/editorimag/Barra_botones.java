/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editorimag;

import static editorimag.EditorImag.activa;
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
    
    private Boton Espejo_H;
    private Boton Espejo_V;
    
    private Boton Rotar_90;
    private Boton Rotar_180;
    private Boton Rotar_270;
    
    //Iconos
    private ImageIcon abrir = new ImageIcon("src/images/abrir2.png");
    private ImageIcon guardar = new ImageIcon("src/images/guardar2.png");
    
    
    
    public Barra_botones(){
        Abrir = new Boton("Abrir", abrir);
        this.add(Abrir);
        Guardar = new Boton("Guardar", guardar);
        this.add(Guardar);
        
        this.addSeparator();
        
        Espejo_H = new Boton("Espejo Horizontal", null);
        this.add(Espejo_H);
        Espejo_V = new Boton("Espejo Vertical", null);
        this.add(Espejo_V);
        
        this.addSeparator();
        
        Rotar_90 = new Boton("Rotar 90º", null);
        this.add(Rotar_90);
        Rotar_180 = new Boton("Rotar 180º", null);
        this.add(Rotar_180);
        Rotar_270 = new Boton("Rotar 270º", null);
        this.add(Rotar_270);
        
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
        
        Espejo_H.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(32);
            }
        });
        
        Espejo_V.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(31);
            }
        });
        
        Rotar_90.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(34);
            }
        });
        
        Rotar_180.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(35);
            }
        });
        
        Rotar_270.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gestor_img.getListaLan().get(activa).lanzar(36);
            }
        });
        
    }
    
}
