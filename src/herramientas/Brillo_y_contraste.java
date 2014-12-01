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

    private int datos[];
    private int vin[];
    private double brillo = 0.0;
    private double contraste = 0.0;

    private double n_brillo;
    private double n_contraste;

    public double get_brillo() {
        return brillo;
    }

    public void set_brillo(double b) {
        brillo = b;
    }

    public double get_contraste() {
        return contraste;
    }

    public void set_contraste(double c) {
        contraste = c;
    }

    public Brillo_y_contraste(BufferedImage tmp) {
        img = tmp;

        obtenerdatoshistograma();
        obtenerBrillo();
        obtenerContraste();

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
                n_contraste = (int) Dat_contraste.getValue();
                n_brillo = (int) Dat_brillo.getValue();
                dispose();
                gestor_img.anadirImagen(actualizarBrilloYContraste());
            }
        });

        panel.add(Brillo);
        panel.add(Dat_brillo);
        panel.add(Contraste);
        panel.add(Dat_contraste);
        panel.add(Aceptar);

        setVisible(true);
    }

    private void obtenerdatoshistograma() {
        datos = new int[256];

        //Inicializar datos a 0.
        for (int i = 0; i < 256; i++) {
            datos[i] = 0;
        }

        //Obtener datos de nivel de color.
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color c = new Color(img.getRGB(i, j));
                datos[c.getRed()] += 1;
            }
        }
    }

    public void obtenerBrillo() {
        double brill = 0.0;
        for (int i = 0; i < 256; i++) {
            brill += (datos[i] * i);
        }
        set_brillo(brill / (img.getWidth() * img.getHeight()));
    }

    public void obtenerContraste() {
        double contrast = 0.0;
        for (int i = 0; i < 256; i++) {
            contrast += (Math.pow(i - get_brillo(), 2) * datos[i]);
        }
        set_contraste(Math.sqrt(contrast / (img.getWidth() * img.getHeight())));
    }

    public BufferedImage actualizarBrilloYContraste() {
        BufferedImage resultado = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        System.out.println("c = " + get_contraste());
        System.out.println("b = " + get_brillo());
        System.out.println("n_c = " + n_contraste);
        System.out.println("n_b = " + n_brillo);

        double A = n_contraste / get_contraste();
        double B = n_brillo - A * get_brillo();
        int tmp = 0;
        vin = new int[256];

        System.out.println("A = " + A);
        System.out.println("B = " + B);

        for (int i = 0; i < 256; i++) {
            tmp = (int) (Math.round((A * i) + B));
            if (tmp < 0) {
                tmp = 0;
            } else if (tmp > 255) {
                tmp = 255;
            }
            vin[i] = tmp;

            System.out.println(i + " " + vin[i]);
        }

//        int valorNuevo = 0;
//        for (int i = 0; i < img.getWidth(); i++) {
//            for (int j = 0; j < img.getHeight(); j++) {
//                valorNuevo = new Color(img.getRGB(i, j)).getRed();
//                resultado.setRGB(i, j, new Color(valorNuevo, valorNuevo, valorNuevo).getRGB());
//            }
//        }
        
        for (int i = 0; i < resultado.getWidth(); i++) {
            for (int j = 0; j < resultado.getHeight(); j++) {
                int viejo = new Color(img.getRGB(i, j)).getRed();
                int nuevo = vin[viejo];
                resultado.setRGB(i, j, new Color(nuevo, nuevo, nuevo).getRGB());
            }
        }

        return resultado;
    }

}
