/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import static editorimag.EditorImag.gestor_img;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import imagen.Imagen;
import java.awt.GridLayout;

public class Transf_lineales_tramos extends JFrame {
    
    private BufferedImage img;
    
    public Transf_lineales_tramos(BufferedImage tmp) {
        img = tmp;
        
        controles();
    }
    
    public void controles(){
        setTitle("Insertar punto");
        setSize(300, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));
        add(panel);

        JLabel PuntoX = new JLabel(" Introduce la X");
        JLabel PuntoY = new JLabel(" Introduce la Y");
        
        JTextField Dat_x = new JTextField();
        JTextField Dat_y = new JTextField();
        
        JButton Anadir = new JButton("Añadir");
        JButton Aceptar = new JButton("Aceptar");
        
        Anadir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                
            }
        });

        Aceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                
                dispose();
            }
        });

        panel.add(PuntoX);
        panel.add(Dat_x);
        panel.add(PuntoY);
        panel.add(Dat_y);
        panel.add(Anadir);
        panel.add(Aceptar);

        setVisible(true);
    }
    
}
