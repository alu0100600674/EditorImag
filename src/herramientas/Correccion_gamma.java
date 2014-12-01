/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herramientas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

/**
 *
 * @author jonay
 */
public class Correccion_gamma extends JFrame {
    
    private BufferedImage img;
    
    public Correccion_gamma(BufferedImage tmp){
        img = tmp;
        
        controles();
    }
    
    public void controles(){
        setTitle("Brillo y Contraste");
        setSize(300, 150);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        add(panel);

        JLabel Gamma = new JLabel(" Introduce el gamma");
        final JSpinner Dat_gamma = new JSpinner();
        JButton Aceptar = new JButton("Aceptar");

        Aceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });

        panel.add(Gamma);
        panel.add(Dat_gamma);
        panel.add(Aceptar);

        setVisible(true);
    }
    
    public BufferedImage gamma(){
        BufferedImage resultado = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        
        
        
        return resultado;
    }
    
}
