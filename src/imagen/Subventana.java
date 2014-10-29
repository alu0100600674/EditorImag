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
//        setTitle("Subventana"); //Cambiar el nombre por el de la función o lo que sea.
        setTitle("Imagen " + (tam + 1)); //Cambiar el nombre por el de la función o lo que sea.
        setVisible(true);
//        setSize(400,300);
        setSize(tmp2.getWidth()+10, tmp2.getHeight()+35);
        setResizable(false);
        
        tmp = tmp2;
        
        JPanel panel = new JPanel();
        this.repaint();
        
        //Asignar un número de subventana
        num_subv = tam;
        
        addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e){
                activa = num_subv;
            }
        });
        
    }
    
    public void paint(Graphics g){ //Prueba de dibujar una línea.
//        g.drawLine(30, 30, 30, 30);
        g.drawImage(tmp, 5, 30, this);
    }
    
}
