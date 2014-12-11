/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import static editorimag.EditorImag.gestor_img;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
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

//    private int p_ancho;
//    private int p_alto;
    public Escalado(BufferedImage tmp) {
        img = tmp;

        controles();
    }

    public void controles() {
        final JFrame v = new JFrame();
        v.setTitle("Escalar");
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
                int p_ancho = Integer.parseInt(Dat_ancho.getText());
                int p_alto = Integer.parseInt(Dat_alto.getText());

                System.out.println("p_ancho " + p_ancho);
                System.out.println(" p_alto " + p_alto);
                System.out.println("---------------------");

                v.dispose();
                gestor_img.anadirImagen(escalar(p_ancho, p_alto, opcion));
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

    private BufferedImage escalar(int p_ancho, int p_alto, int opcion) {
        int ancho = img.getWidth();
        int alto = img.getWidth();

        System.out.println(" ancho1 " + ancho);
        System.out.println("  alto1 " + alto);

        p_ancho /= 100;
        p_alto /= 100;
        ancho *= p_ancho;
        alto *= p_alto;

        System.out.println("p_ancho " + p_ancho);
        System.out.println(" p_alto " + p_alto);
        System.out.println(" ancho2 " + ancho);
        System.out.println("  alto2 " + alto);

        BufferedImage resultado = new BufferedImage(ancho, alto, img.getType());
        int colorNuevo;
        int jI = 0;
        int iI = 0;

        if (opcion == 1) { //Vecino más cercano
            for (int i = 0; i < alto; i++) {
                for (int j = 0; j < ancho; j++) {
                    jI = (int) vecinoMasCercano(j / p_ancho, i / p_alto).getX();
                    iI = (int) vecinoMasCercano(j / p_ancho, i / p_alto).getY();
                    colorNuevo = new Color(img.getRGB(jI, iI)).getRed();
                    resultado.setRGB(j, i, new Color(colorNuevo, colorNuevo, colorNuevo).getRGB());
                }
            }
        } else if (opcion == 2) { //Bilineal
            for (int i = 0; i < alto; i++){
                for (int j = 0; j < ancho; j++){
                    colorNuevo = bilineal(j / p_ancho, i / p_alto);
                    resultado.setRGB(j, i, new Color(colorNuevo, colorNuevo, colorNuevo).getRGB());
                }
            }
        }

        return resultado;
    }

    public Point vecinoMasCercano(double j, double i) {
        double jI = Math.round(j);
        double iI = Math.round(i);
        int ancho = img.getWidth();
        int alto = img.getHeight();

        if (jI >= ancho) {
            jI = ancho - 1;
        }
        if (iI >= alto) {
            iI = alto - 1;
        }

        Point tmp = new Point((int) jI, (int) iI);
        return tmp;
    }

    public int bilineal(double j, double i) {
        int ancho = img.getWidth();
        int alto = img.getHeight();

        int A = new Color(img.getRGB((int) j, (int) i)).getRed();
        int B;
        int C;
        int D;

        if (j + 1 >= ancho) {
            B = new Color(img.getRGB((int) j, (int) i)).getRed();
            if(i + 1 >= alto) {
                C = new Color(img.getRGB((int) j, (int) i)).getRed();
                D = new Color(img.getRGB((int) j, (int) i)).getRed();
            } else {
                C = new Color(img.getRGB((int) j, (int) i + 1)).getRed();
                D = new Color(img.getRGB((int) j, (int) i + 1)).getRed();
            }
        } else {
            B = new Color(img.getRGB((int) j + 1, (int) i)).getRed();
            if (i + 1 >= alto) {
                C = new Color(img.getRGB((int) j, (int) i)).getRed();
                D = new Color(img.getRGB((int) j + 1, (int) i)).getRed();
            } else {
                C = new Color(img.getRGB((int) j, (int) i + 1)).getRed();
                D = new Color(img.getRGB((int) j + 1, (int) i + 1)).getRed();
            }
        }
        
        double p = j - (int) j;
        double q = i - (int) i;
        
        double Q = A + (B - A) * p;
        double R = C + (D - C) * q;
        int P = (int) (R + (Q - R) * q);
        
        return P;
    }

}
