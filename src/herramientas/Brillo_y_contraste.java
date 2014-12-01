/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import static editorimag.EditorImag.gestor_img;
import editorimag.interfaz;
import imagen.Gestion_subventanas;
import imagen.Subventana; //pruebas
import imagen.Imagen;

import static java.awt.Adjustable.HORIZONTAL;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Brillo_y_contraste extends JDialog {

    private BufferedImage img;

    public Brillo_y_contraste(BufferedImage tmp) {
        img = tmp;

        controles();
    }

    public void controles() {
        setTitle("Brillo y Contraste");
        setSize(300, 250);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        add(panel);

        JLabel Brillo = new JLabel(" Introduce el brillo");
        final JSpinner Dat_brillo = new JSpinner();
        JLabel Contraste = new JLabel(" Introduce el contraste");
        final JSpinner Dat_contraste = new JSpinner();
        JButton Aceptar = new JButton("Aceptar");

        Aceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                dispose();
            }
        });

        panel.add(Brillo);
        panel.add(Dat_brillo);
        panel.add(Contraste);
        panel.add(Dat_contraste);
        panel.add(Aceptar);

        setVisible(true);
    }

}
