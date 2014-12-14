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

    private ArrayList<Point> puntosEsp;
    private ArrayList<EcuacionRecta> ecuacionesRectas;
    private HashMap<Integer, Integer> tablaTransf;
    private double ALTO_PANELES;
    private double DESFACE_PUNTOS_PANEL;

    public ArrayList<EcuacionRecta> getEcuacionesRectas() {
        return ecuacionesRectas;
    }

    public void setEcuacionesRectas(ArrayList<EcuacionRecta> ecuaciones) {
        this.ecuacionesRectas = ecuaciones;
    }

    public ArrayList<Point> getPuntosEsp() {
        return puntosEsp;
    }

    public void setPuntosEsp(ArrayList<Point> puntosEsp) {
        this.puntosEsp = puntosEsp;
    }

    public Transf_lineales_tramos(BufferedImage tmp) {
        img = tmp;

        tablaTransf = new HashMap<Integer, Integer>();
        iniciarPuntos();

        controles();
    }

    public void controles() {
        setTitle("Insertar punto");
        setSize(300, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));
        add(panel);

        JLabel PuntoX = new JLabel(" Introduce la X");
        JLabel PuntoY = new JLabel(" Introduce la Y");

        final JTextField Dat_x = new JTextField();
        final JTextField Dat_y = new JTextField();

        JButton Anadir = new JButton("Añadir");
        JButton Aceptar = new JButton("Aceptar");

        Anadir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int x = Integer.parseInt(Dat_x.getText());
                int y = Integer.parseInt(Dat_y.getText());
                insertarPunto2(new Point(x, y));
                modificarEcuacionesRectas();
                rellenarTablaDeTransformacion();
            }
        });

        Aceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gestor_img.anadirImagen(crearImagenTransformada());
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

    public BufferedImage crearImagenTransformada() {
        BufferedImage resultado = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        if (resultado != null && tablaTransf != null && tablaTransf.size() > 0) {
            int argb = 0;
            for (int i = 0; i < resultado.getWidth(); ++i) {
                for (int j = 0; j < resultado.getHeight(); ++j) {
                    argb = tablaTransf.get(new Color(img.getRGB(i, j)).getRed());
                    resultado.setRGB(i, j, new Color(argb, argb, argb).getRGB());
                }
            }
        }
//        getPanelImResultado().setImagenTranf(getImagenNueva());

        return resultado;
    }

    // -------------------------------------------------------------------------
    public void rellenarTablaDeTransformacion() {
        int inicioRect = 0, finRect = 0;
        for (int i = 0; i < ecuacionesRectas.size(); ++i) {
            inicioRect = (int) ecuacionesRectas.get(i).getIni().getX();
            finRect = (int) ecuacionesRectas.get(i).getFin().getX();
            for (int j = inicioRect; j < finRect + 1; ++j) {
                tablaTransf.put(j, ecuacionesRectas.get(i).getRecta().get(j));
            }
        }
    }

    private void iniciarPuntos() {
        setPuntosEsp(new ArrayList<Point>());
        getPuntosEsp().add(new Point(0, 0));
        getPuntosEsp().add(new Point(255, 255));

        setEcuacionesRectas(new ArrayList<EcuacionRecta>());

        EcuacionRecta primera = new EcuacionRecta(getPuntosEsp().get(0), getPuntosEsp().get(1));
        primera.rellenarEcuacion();
        getEcuacionesRectas().add(primera);
    }

//    public void insertarPunto(Point q, Boolean desfacePunto) {
//        Point p = null;
//        if (desfacePunto) {
//            p = new Point((int) Math.round(q.getX() - DESFACE_PUNTOS_PANEL),
//                    (int) Math.round(ALTO_PANELES - DESFACE_PUNTOS_PANEL - q.getY() - 1));
//        } else {
//            p = q;
//        }
//
//        System.out.println("Punto [" + p.getX() + " , " + p.getY() + "]");
//        if (getPanelTramos().comprobarLimite(p)) {
//
//            // Comprobar si coincide en x con alg�n punto (menos 1� y �ltimo)
//            int coincideX = buscarCoincidenteEnX(p);
//
//            // si -> intercambiar y del punto (menos 1� y �ltimo)
//            if (coincideX != 0 && coincideX != getPuntosEsp().size() - 1) {
//                if (coincideX != -1) {
//                    getPuntosEsp().get(coincideX).setLocation(getPuntosEsp().get(coincideX).getX(), p.getY());
//
//                } else {
//                    int puntoAnterior = buscarAnteriorPunto(p); // Comprobar entre que par de puntos est� la x
//                    ArrayList<Point> puntosOrdenados = insertarPuntoOrdenado(p, puntoAnterior);
//
//                    // Hacer que �ste sea el nuevo vector de puntos de la clase
//                    getPuntosEsp().clear();
//                    setPuntosEsp(puntosOrdenados);
//                }
//
//            } else if (coincideX == 0) { // Si coincide X del primer punto -> cambiamos su Y
//                getPuntosEsp().get(0).setLocation(getPuntosEsp().get(0).getX(), p.getY());
//                modificarEcuacionesRectas();
//                rellenarTablaDeTransformacion();
//                crearImagenTransformada();
//
//            } else if (coincideX == getPuntosEsp().size() - 1) { // Si coincide X del �ltimo punto -> cambiamos su Y
//                getPuntosEsp().get(getPuntosEsp().size() - 1).setLocation(getPuntosEsp().get(getPuntosEsp().size() - 1).getX(), p.getY());
//                modificarEcuacionesRectas();
//                rellenarTablaDeTransformacion();
//                crearImagenTransformada();
//            }
//        }
////        getPanelImResultado().repaint();
//    }

    public void insertarPunto2(Point q) {
        Point p = new Point((int) Math.round(q.getX()), (int) Math.round(q.getY() - 1));

        int coincideX = buscarCoincidenteEnX(p);

        // si -> intercambiar y del punto (menos 1� y �ltimo)
        if (coincideX != 0 && coincideX != getPuntosEsp().size() - 1) {
            if (coincideX != -1) {
                getPuntosEsp().get(coincideX).setLocation(getPuntosEsp().get(coincideX).getX(), p.getY());

            } else {
                int puntoAnterior = buscarAnteriorPunto(p); // Comprobar entre que par de puntos est� la x
                ArrayList<Point> puntosOrdenados = insertarPuntoOrdenado(p, puntoAnterior);

                // Hacer que �ste sea el nuevo vector de puntos de la clase
                getPuntosEsp().clear();
                setPuntosEsp(puntosOrdenados);
            }

        } else if (coincideX == 0) { // Si coincide X del primer punto -> cambiamos su Y
            getPuntosEsp().get(0).setLocation(getPuntosEsp().get(0).getX(), p.getY());
            modificarEcuacionesRectas();
            rellenarTablaDeTransformacion();
//            crearImagenTransformada();

        } else if (coincideX == getPuntosEsp().size() - 1) { // Si coincide X del �ltimo punto -> cambiamos su Y
            getPuntosEsp().get(getPuntosEsp().size() - 1).setLocation(getPuntosEsp().get(getPuntosEsp().size() - 1).getX(), p.getY());
            modificarEcuacionesRectas();
            rellenarTablaDeTransformacion();
//            crearImagenTransformada();
        }
    }

    public int buscarCoincidenteEnX(Point p) {
        int DIST_X = 5;
        for (int i = 0; i < getPuntosEsp().size(); ++i) {
            if (Math.abs(p.getX() - getPuntosEsp().get(i).getX()) <= DIST_X) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Point> insertarPuntoOrdenado(Point p, int puntoAnterior) {
        ArrayList<Point> puntosTemp = new ArrayList<Point>();

        for (int i = 0; i <= puntoAnterior; ++i) // Metemos los puntos anteriores al nuevo que vamos a insertar (inclu�do el anterior)
        {
            puntosTemp.add(getPuntosEsp().get(i));
        }

        puntosTemp.add(new Point((int) p.getX(), (int) p.getY())); // Meter punto nuevo en el vector

        // Meter resto de puntos
        for (int i = puntoAnterior + 1; i < getPuntosEsp().size(); ++i) {
            puntosTemp.add(getPuntosEsp().get(i));
        }

        return puntosTemp;
    }

    public int buscarAnteriorPunto(Point p) {
        int anterior = 0;
        for (int i = 1; i < getPuntosEsp().size() - 1; ++i) {
            if (p.getX() > getPuntosEsp().get(i).getX()) {
                anterior = i;
            }
        }
        return anterior;
    }

    public void modificarEcuacionesRectas() {
        if (!getEcuacionesRectas().isEmpty()) {
            getEcuacionesRectas().clear();
        }

        for (int i = 0; i < getPuntosEsp().size() - 1; ++i) {
            Point a = getPuntosEsp().get(i);

            Point b = getPuntosEsp().get(i + 1);
            EcuacionRecta ec = new EcuacionRecta(a, b);
            ec.rellenarEcuacion();
            getEcuacionesRectas().add(ec);
        }

    }

}
