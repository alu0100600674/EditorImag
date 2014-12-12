/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import static editorimag.EditorImag.gestor_img;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author jonay
 */
public class Diferencia_dos_imagenes {

    private BufferedImage img1;
    private BufferedImage img2;
    
    private int n_umbral;

    public Diferencia_dos_imagenes(BufferedImage tmp1) {
        img1 = tmp1;

        elegirImagenDos();
    }
    
    private void controles(){
        final JFrame v = new JFrame();
        v.setTitle("Dif. imágenes");
        v.setSize(300, 150);
        v.setResizable(false);
        v.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        v.add(panel);

        JLabel Umbral = new JLabel(" Introduce el umbral");
//        final JSpinner Dat_gamma = new JSpinner();
        
        final JTextField Dat_umbral = new JTextField();
        
        JButton Aceptar = new JButton("Aceptar");

        Aceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                n_umbral = Integer.parseInt(Dat_umbral.getText());
                v.dispose();
                gestor_img.anadirImagenColor(mapaDeCambios());
            }
        });

        panel.add(Umbral);
        panel.add(Dat_umbral);
        panel.add(Aceptar);

        v.setVisible(true);
    }

    private boolean comprobarDimensiones() {
        if ((img1.getWidth() == img2.getWidth()) && (img1.getHeight() == img2.getHeight())) {
            return true;
        } else {
            return false;
        }
    }

    private void ventanaError() {
        final JFrame error = new JFrame();
        error.setTitle("Error");
        error.setSize(400, 100);
        error.setResizable(false);
        error.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        error.add(panel);

        JLabel Error = new JLabel("  Las dimensiones de las imágenes deben ser iguales.");
        JButton Aceptar = new JButton("Aceptar");

        Aceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                error.dispose();
            }
        });

        panel.add(Error);
        panel.add(Aceptar);

        error.setVisible(true);
    }

    private void elegirImagenDos() {
        final JFrame elegir = new JFrame();
        elegir.setTitle("Elige imagen para comparar");
        elegir.setSize(300, 150);
        elegir.setResizable(false);
        elegir.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        elegir.add(panel);

        JLabel Imagen = new JLabel("Elige la segunda imagen:");
        final JSpinner Dat_imagen = new JSpinner();
        JButton Aceptar = new JButton("Aceptar");

        Aceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int i = (int) Dat_imagen.getValue();
                elegir.dispose();
                img2 = gestor_img.getBufferedImage(i);
//                guardarImg1();
//                guardarImg2();

                if (comprobarDimensiones()) {
                    gestor_img.anadirImagen(resta());
                    controles();
//                    gestor_img.anadirImagenColor(mapaDeCambios());
                } else {
                    ventanaError();
                }

            }
        });

        panel.add(Imagen);
        panel.add(Dat_imagen);
        panel.add(Aceptar);

        elegir.setVisible(true);
    }

    private void guardarImg1() {
        BufferedImage r1 = new BufferedImage(img1.getWidth(), img1.getHeight(), img1.getType());
        for (int i = 0; i < img1.getWidth(); i++) {
            for (int j = 0; j < img1.getHeight(); j++) {
                r1.setRGB(i, j, img1.getRGB(i, j));
            }
        }
        gestor_img.anadirImagen(r1);
    }

    private void guardarImg2() {
        BufferedImage r2 = new BufferedImage(img2.getWidth(), img2.getHeight(), img2.getType());
        for (int i = 0; i < img2.getWidth(); i++) {
            for (int j = 0; j < img2.getHeight(); j++) {
                r2.setRGB(i, j, img2.getRGB(i, j));
            }
        }
        gestor_img.anadirImagen(r2);
    }

    private BufferedImage resta() {
        BufferedImage resultado = new BufferedImage(img1.getWidth(), img1.getHeight(), img1.getType());

        int tmp = 0;
        for (int i = 0; i < img1.getWidth(); i++) {
            for (int j = 0; j < img1.getHeight(); j++) {
                tmp = Math.abs(new Color(img1.getRGB(i, j)).getRed() - new Color(img2.getRGB(i, j)).getRed());
                resultado.setRGB(i, j, new Color(tmp, tmp, tmp).getRGB());
            }
        }

        return resultado;
    }

    public HashMap<Integer, Integer> tablaTransformacion(int uCambio) {
        // Si el valor es mayor que cambio entonces -1
        HashMap<Integer, Integer> tabla = new HashMap<>();
        int tmp = 0;
        for (int i = 0; i < 256; i++) {
            if (i > uCambio) {
                tmp = -1;
            } else {
                tmp = i;
            }
            tabla.put(i, tmp);
        }
        return tabla;
    }

    private BufferedImage mapaDeCambios() {
        BufferedImage resultado = new BufferedImage(img1.getWidth(), img1.getHeight(), img1.getType());

        HashMap<Integer, Integer> tabla = tablaTransformacion(n_umbral);
        int tmp = 0;
        for (int i = 0; i < img1.getWidth(); i++) {
            for (int j = 0; j < img1.getHeight(); j++) {
                tmp = tabla.get(Math.abs(new Color(img1.getRGB(i, j)).getRed() - new Color(img2.getRGB(i, j)).getRed()));
                if (tmp == -1) {
                    resultado.setRGB(i, j, Color.RED.getRGB()); // Cambia

                } else {
                    resultado.setRGB(i, j, img1.getRGB(i, j)); // No Cambia
                }
            }
        }

        return resultado;
    }

}
