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

/**
 *
 * @author jonay
 */
public class Especificacion_histograma {

    private BufferedImage img1;
    private BufferedImage img2;

    int datos1[]; //Datos histograma img1
    int datos2[]; //Datos histograma img2
    int datosAc1[]; //Datos histograma acumulado img1;
    int datosAc2[]; //Datos histograma acumulado img2;

    public Especificacion_histograma(BufferedImage tmp) {
        img1 = tmp;
        
        elegirImagenDos();
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
                cogerDatos();
                gestor_img.anadirImagen(especificacionH());
            }
        });

        panel.add(Imagen);
        panel.add(Dat_imagen);
        panel.add(Aceptar);

        elegir.setVisible(true);
    }

    private void cogerDatos() {
        datos1 = new int[256];
        datos2 = new int[256];
        datosAc1 = new int[256];
        datosAc2 = new int[256];

        //Inicializar datos a 0.
        for (int i = 0; i < 256; i++) {
            datos1[i] = 0;
            datos2[i] = 0;
            datosAc1[i] = 0;
            datosAc2[i] = 0;
        }

        //Obtener datos de nivel de color para la img1
        for (int i = 0; i < img1.getWidth(); i++) {
            for (int j = 0; j < img1.getHeight(); j++) {
                Color c = new Color(img1.getRGB(i, j));
                datos1[c.getRed()] += 1;
            }
        }

        //Obtener datos de nivel de color para la img2
        for (int i = 0; i < img2.getWidth(); i++) {
            for (int j = 0; j < img2.getHeight(); j++) {
                Color c = new Color(img2.getRGB(i, j));
                datos2[c.getRed()] += 1;
            }
        }
        
        //Obtener datos de los histogramas acumulados
        datosAc1[0] = datos1[0];
        datosAc2[0] = datos2[0];
        for (int i = 1; i < 256; i++) {
            datosAc1[i] = datos1[i] + datosAc1[i - 1];
            datosAc2[i] = datos2[i] + datosAc2[i - 1];
        }
        
    }
    
    private void mostrarDatos() {
        System.out.println("datos1");
        for(int i = 0; i < 256; i++){
            System.out.println(i + " " + datos1[i]);
        }
        
        System.out.println("datos2");
        for(int i = 0; i < 256; i++){
            System.out.println(i + " " + datos2[i]);
        }
    }
    
    public BufferedImage especificacionH() {
        BufferedImage resultado = new BufferedImage(img1.getWidth(), img1.getHeight(), img1.getType());
        HashMap<Integer, Integer> tabla = tablaTransformacion(datosAc1, datosAc2);
        int temp = 0;
        for (int i = 0; i < resultado.getWidth(); ++i) {
            for (int j = 0; j < resultado.getHeight(); ++j) {
                temp = tabla.get(new Color(img1.getRGB(i, j)).getRed());
                resultado.setRGB(i, j, new Color(temp, temp, temp).getRGB());
            }
        }
        return resultado;
    }

    public HashMap<Integer, Integer> tablaTransformacion(int[] histograma1, int[] histograma2) {
        HashMap<Integer, Integer> tabla = new HashMap<Integer, Integer>();
        double[] histograma1N = normalizar(histograma1);
        double[] histograma2N = normalizar(histograma2);

        for (int i = 0; i < histograma1N.length; ++i) {
            tabla.put(i, buscarMasCercano(histograma1N[i], histograma2N));
        }
        return tabla;
    }

    public int buscarMasCercano(double frecuenciaBuscada, double[] h2) {
        double maximo = 99999.0;
        double minimo = 99999.0;
        int mejorSolucionSuperior = 0;
        int mejorSolucionInferior = 0;

        for (int i = 0; i < h2.length; ++i) {
            double diferenciaSuperior = h2[i] - frecuenciaBuscada;
            if (diferenciaSuperior >= 0) {
                if (maximo > diferenciaSuperior) {
                    maximo = diferenciaSuperior;
                    mejorSolucionSuperior = i;
                }
            }

            double diferenciaInferior = frecuenciaBuscada - h2[i];
            if (diferenciaInferior > 0) {
                if (minimo > diferenciaInferior) {
                    minimo = diferenciaInferior;
                    mejorSolucionInferior = i;
                }
            }

        }
        if (minimo < maximo) {
            return mejorSolucionInferior;
        } else {
            return mejorSolucionSuperior;
        }
    }

    public double[] normalizar(int[] valores) {
        double pixelesTotales = (double) valores[valores.length - 1];
        double[] nuevo = new double[valores.length];
        for (int i = 0; i < valores.length; ++i) {
            nuevo[i] = ((double) valores[i]) / pixelesTotales;
        }
        return nuevo;
    }

    public void mostrarValoresNormalizados(double[] normalizados) {
        for (int i = 0; i < normalizados.length; ++i) {
            System.out.println(normalizados[i]);
        }
    }

}
