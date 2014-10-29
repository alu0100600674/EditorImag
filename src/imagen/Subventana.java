/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagen;

import static editorimag.EditorImag.activa;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author jonay
 */
public class Subventana extends JFrame {
    
    private BufferedImage tmp;
    private int num_subv;
    private int num_subv2;
    
    public int getNumSubv(){
        return num_subv;
    }
    
    public void setSubv(int v){
        num_subv2 = v;
    }
    
    public Subventana(BufferedImage tmp2, int tam) {
        setTitle("Imagen " + (tam + 1)); 
        setVisible(true);
        setSize(tmp2.getWidth()+10, tmp2.getHeight()+35); //Tamaño de la ventana según la imagen.
        setResizable(false);
        
        tmp = tmp2;
        
        JPanel panel = new JPanel();
        this.repaint();
        
        num_subv = tam; //Asignar un número de subventana
        
        addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e){
                activa = num_subv;
            }
        });
        
    }
    
    public void paint(Graphics g){
        g.drawImage(tmp, 5, 30, this);
    }
    
}
