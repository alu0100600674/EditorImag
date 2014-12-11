/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import editorimag.EditorImag;
import static editorimag.EditorImag.gestor_img;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
//import javafx.scene.control.RadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author jonay
 */
public class Rotacion {

    private BufferedImage img;
    private int rotacion;
    private int contadorFondo;

    public class Punto {

        private double x;
        private double y;

        public Punto(double x, double y) {
            setX(x);
            setY(y);
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }
        
        public Punto opuesto () {
            return new Punto (-getX(), -getY());
        }
    }
    
    public int getContadorFondo() {
        return contadorFondo;
    }

    public Rotacion(BufferedImage tmp) {
        img = tmp;
    }

    public BufferedImage rotar90() {
        BufferedImage resultado = new BufferedImage(img.getHeight(), img.getWidth(), img.getType());

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                resultado.setRGB(j, i, img.getRGB(img.getWidth() - 1 - i, j));
            }
        }

        return resultado;
    }

    public BufferedImage rotar180() {
        BufferedImage resultado = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                resultado.setRGB(j, i, img.getRGB(img.getWidth() - 1 - j, img.getHeight() - 1 - i));
            }
        }

        return resultado;
    }

    public BufferedImage rotar270() {
        BufferedImage resultado = new BufferedImage(img.getHeight(), img.getWidth(), img.getType());

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                resultado.setRGB(j, i, img.getRGB(i, img.getHeight() - 1 - j));
            }
        }

        return resultado;
    }

    public void rotar() {
        controles();

    }

    private BufferedImage rotar_x(int rot, int opcion) {
        BufferedImage resultado = null;

        if (rot >= 360) { //Si la rotación es mayor a 360º, convertir a una vuelta.
            rot -= 360;
        }

        if ((rot != 90) && (rot != 180) && (rot != 270) && (rot != 0)) {

            int ancho = img.getWidth();
            int alto = img.getHeight();
            contadorFondo = 0;
            double grados = Math.toRadians(rotacion);
            ArrayList<Punto> esquinas = rotarEsquina(grados);
            Punto dimension = anchoAlto(esquinas);
            resultado = new BufferedImage((int) dimension.getX() + 1, (int) dimension.getY() + 1, img.getType());
            
            int colorNuevo;
            int clr = -1;
            
            Punto oPrima = calcularIzquierdaSuperior(esquinas);
            Punto traslado;
            Punto mapInverso;

            if (opcion == 1) { //Rotar y pintar
                
//                for(int i = 0; i < dimension.getY(); i++){
//                    for(int j = 0; j < dimension.getX(); j++){
//                        traslado = trasladar(oPrima, new Punto((double) j, (double) i));
//                        mapInverso = transformacionInversa(grados, (int) traslado.getX(), (int) traslado.getY());
//                        if((mapInverso.getX() < ancho) && (mapInverso.getX() >= 0) && (mapInverso.getY() < alto) && (mapInverso.getY() >= 0)){
//                            colorNuevo = new Color( img.getRGB((int) mapInverso.getX(), (int) mapInverso.getY())).getRed();
//                            resultado.setRGB(j, i, new Color(colorNuevo, colorNuevo, colorNuevo).getRGB());
//                        }
//                        else {
//                            contadorFondo += 1;
//                        }
//                    }
//                }
                
                for(int i = 0; i < alto; i++){
                    for(int j = 0; j < ancho; j++){
                        mapInverso = transformacionDirecta(grados, j, i);
                        traslado = trasladar(oPrima.opuesto(), new Punto(mapInverso.getX(), mapInverso.getY()));
                        colorNuevo = new Color(img.getRGB(j, i)).getRed();
                        clr = new Color(resultado.getRGB((int) traslado.getX(), (int) traslado.getY())).getRed();
                        
                        if(clr == 0){
                            resultado.setRGB((int) traslado.getX(), (int) traslado.getY(), new Color(colorNuevo, colorNuevo, colorNuevo).getRGB());
                            contadorFondo += 1;
                        }
                    }
                }
                
            } else if (opcion == 2) { //Vecino más próximo
                
                for(int i = 0; i < dimension.getY(); i++){
                    for(int j = 0; j < dimension.getX(); j++){
                        traslado = trasladar(oPrima, new Punto((double) j, (double) i));
                        mapInverso = transformacionInversa(grados, (int) traslado.getX(), (int) traslado.getY());
                        if((mapInverso.getX() < ancho) && (mapInverso.getX() >= 0) && (mapInverso.getY() < alto) && (mapInverso.getY() >= 0)){
                            //colorNuevo = new Color( img.getRGB((int) mapInverso.getX(), (int) mapInverso.getY())).getRed();
                            colorNuevo = new Color(img.getRGB((int) vecinoMasCercano(mapInverso.getX(), mapInverso.getY()).getX(), (int) vecinoMasCercano(mapInverso.getX(), mapInverso.getY()).getY())).getRed();
                            resultado.setRGB(j, i, new Color(colorNuevo, colorNuevo, colorNuevo).getRGB());
                        }
                        else {
                            contadorFondo += 1;
                        }
                    }
                }
                
            } else if (opcion == 3) { //Bilineal
                
                for(int i = 0; i < dimension.getY(); i++){
                    for(int j = 0; j < dimension.getX(); j++){
                        traslado = trasladar(oPrima, new Punto((double) j, (double) i));
                        mapInverso = transformacionInversa(grados, (int) traslado.getX(), (int) traslado.getY());
                        if((mapInverso.getX() < ancho) && (mapInverso.getX() >= 0) && (mapInverso.getY() < alto) && (mapInverso.getY() >= 0)){
                            colorNuevo = bilineal(mapInverso.getX(), mapInverso.getY());
                            resultado.setRGB(j, i, new Color(colorNuevo, colorNuevo, colorNuevo).getRGB());
                        }
                        else {
                            contadorFondo += 1;
                        }
                    }
                }
                
            }
            
            System.out.println("fondo en rot " + contadorFondo);

        } else { //Si las rotaciones son de 90, 180 o 270; usar los métodos específicos para ello.
            switch (rot) {
                case 90:
                    resultado = rotar90();
                    break;
                case 180:
                    resultado = rotar180();
                    break;
                case 270:
                    resultado = rotar270();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "No se han hecho cambios respecto a la imagen original.");
                    break;
            }
//            return resultado;
        }
        return resultado;
    }

    private void controles() {
        final JFrame v = new JFrame();
        v.setTitle("Rotar");
        v.setSize(300, 300);
        v.setResizable(false);
        v.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));
        v.add(panel);

        final JRadioButton Normal = new JRadioButton("Rotar y pintar");
        final JRadioButton Vecino = new JRadioButton("Vecino más cercano");
        Vecino.setSelected(true);
        final JRadioButton Bilineal = new JRadioButton("Bilineal");
        ButtonGroup Grupo = new ButtonGroup();
        Grupo.add(Normal);
        Grupo.add(Vecino);
        Grupo.add(Bilineal);

        JLabel Rotar = new JLabel(" Introduce la rotación");
//        final JSpinner Dat_gamma = new JSpinner();

        final JTextField Dat_rotar = new JTextField();

        JButton Aceptar = new JButton("Aceptar");

        Aceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int opcion = 0;
                if (Normal.isSelected()) {
                    opcion = 1;
                } else if (Vecino.isSelected()) {
                    opcion = 2;
                } else if (Bilineal.isSelected()) {
                    opcion = 3;
                }
                rotacion = Integer.parseInt(Dat_rotar.getText());
                rotacion = -rotacion;
                v.dispose();
//                gestor_img.anadirImagen(rotar_x(rotacion, opcion));
                gestor_img.anadirImagenRotada(rotar_x(rotacion, opcion), contadorFondo);
            }
        });

        panel.add(Normal);
        panel.add(Vecino);
        panel.add(Bilineal);
        panel.add(Rotar);
        panel.add(Dat_rotar);
        panel.add(Aceptar);

        v.setVisible(true);

    }

    public ArrayList<Punto> rotarEsquina(double grados) {
        int ancho = img.getWidth();
        int alto = img.getHeight();

        ArrayList<Punto> tmp = new ArrayList<Punto>();
        tmp.add(transformacionDirecta(grados, 0, 0));
        tmp.add(transformacionDirecta(grados, 0, alto - 1));
        tmp.add(transformacionDirecta(grados, ancho - 1, 0));
        tmp.add(transformacionDirecta(grados, ancho - 1, alto - 1));

        return tmp;
    }

    public Punto transformacionDirecta(double grados, int x, int y) {
        return new Punto((Math.cos(grados) * x - Math.sin(grados) * y), (Math.sin(grados) * x + Math.cos(grados) * y));
//        return new Punto((Math.cos(grados) * x + Math.sin(grados) * y), (-Math.sin(grados) * x + Math.cos(grados) * y));
    }

    public Punto transformacionInversa(double grados, int x, int y) {
        return new Punto((Math.cos(grados) * x + Math.sin(grados) * y), (-Math.sin(grados) * x + Math.cos(grados) * y)); //REVISAR, está mal.
//        return new Punto((Math.cos(grados) * x - Math.sin(grados) * y), (Math.sin(grados) * x + Math.cos(grados) * y)); 
    }

    public Punto trasladar(Punto o, Punto p) {
        return new Punto(p.getX() - o.getX(), p.getY() - o.getY());
    }

    public Punto anchoAlto(ArrayList<Punto> p) {
        double iz = Double.MAX_VALUE;
        double der = -Double.MAX_VALUE;
        double arriba = Double.MAX_VALUE;
        double abajo = -Double.MAX_VALUE;

        for (int i = 0; i < p.size(); i++) {
            if (iz >= p.get(i).getX()) {
                iz = p.get(i).getX();
            }
            if (der <= p.get(i).getX()) {
                der = p.get(i).getX();
            }
            if (arriba >= p.get(i).getY()) {
                arriba = p.get(i).getY();
            }
            if (abajo <= p.get(i).getY()) {
                abajo = p.get(i).getY();
            }
        }
        return new Punto((Math.abs(der) + Math.abs(iz)), (Math.abs(abajo) + Math.abs(arriba)));
    }

    public Punto calcularIzquierdaSuperior(ArrayList<Punto> p) {
        double iz = Double.MAX_VALUE;
        double arriba = Double.MAX_VALUE;
        
        for(int i = 0; i < p.size(); i++){
            if (iz >= p.get(i).getX()) {
                iz = p.get(i).getX();
            }
            if (arriba >= p.get(i).getY()) {
                arriba = p.get(i).getY();
            }
        }
        return new Punto(Math.abs(iz), Math.abs(arriba));
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
