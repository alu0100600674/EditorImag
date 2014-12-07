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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author jonay
 */
public class Escalado {
    
    private BufferedImage img;
    
    private int p_ancho;
    private int p_alto;
    
    public Escalado (BufferedImage tmp) {
        img = tmp;
        
        controles();
    }
    
    public void controles () {
        final JFrame v = new JFrame();
        v.setTitle("Rotar");
        v.setSize(300, 300);
        v.setResizable(false);
        v.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));
        v.add(panel);

        final JRadioButton Vecino = new JRadioButton("Vecino más cercano");
        final JRadioButton Bilineal = new JRadioButton("Bilineal");
        ButtonGroup Grupo = new ButtonGroup();
        Grupo.add(Vecino);
        Grupo.add(Bilineal);

        JLabel Ancho = new JLabel(" Introduce el ancho (%)");
        final JTextField Dat_ancho = new JTextField();
        JLabel Alto = new JLabel(" Introduce el alto (%)");
        final JTextField Dat_alto = new JTextField();

        JButton Aceptar = new JButton("Aceptar");

        Aceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int opcion = 0;
                if (Vecino.isSelected()) {
                    opcion = 1;
                } else if (Bilineal.isSelected()) {
                    opcion = 2;
                }
                p_ancho = Integer.parseInt(Dat_ancho.getText());
                p_alto = Integer.parseInt(Dat_alto.getText());
                v.dispose();
                
            }
        });

        panel.add(Vecino);
        panel.add(Bilineal);
        panel.add(Ancho);
        panel.add(Dat_ancho);
        panel.add(Alto);
        panel.add(Dat_alto);
        panel.add(Aceptar);

        v.setVisible(true);
    }
    
}
