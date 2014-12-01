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

    private ArrayList<ArrayList<Integer>> valoresSinBrillo = new ArrayList<ArrayList<Integer>>();
//    private JSlider SBrillo = new JSlider();
//    private JSlider SContraste = new JSlider();

//    public interfaz ventana;
//    private Gestion_subventanas vOriginal;
//    public Subventana refSubact;
    private BufferedImage a;
    public Imagen imag;

    public JFrame panel;
//    private Scrollbar Brillo;
//    private Scrollbar Contraste;
//    private JButton Aceptar;

    // private int Brillo;
    //private int Contraste;
    //private BufferedImage mod;
    
    private BufferedImage img;
    
    private double brillo = 0.0;
    private double contraste = 0.0;
    
    private int datos[];
    
//    JSlider getBrillo() {
//        return SBrillo;
//    }
//
//    void setBrillo(JSlider a) {
//        SBrillo = a;
//    }
//
//    JSlider getContraste() {
//        return SContraste;
//    }
//
//    void setContraste(JSlider a) {
//        SContraste = a;
//    }

    public Brillo_y_contraste(BufferedImage tmp) {
        img = tmp;
        obtenerdatoshistograma();
        obtenerBrillo();
        obtenerContraste();
        

        // NUEVO !! --> TENEMOS LA IMAGEN, SU BRILLO Y CONTRASTE -> ORIGINALES!! (no actualizar !!)
//        getContentPane().setBackground(Color.WHITE);
//        setBounds(350, 50, 500, 300);
//        setLayout(null);
//        setAlwaysOnTop(true);
//        setResizable(false);
//        a = refSubact.getImagenActual();
//        //imag = Imagen.get
//        iniciarPanel(actual);
        controles2();
    }

//    public void iniciarPanel(BufferedImage tmp) {
//
//        setTitle("Valores de brillo y contraste");
//        panel = new JFrame();
//
////                jf.setDefaultCloseOperation(EXIT_ON_CLOSE);   
//        //panel.setLayout(new BorderLayout());
//        panel.setBounds(0, 0, 400, 300);
//
//        panel.setBackground(Color.WHITE);
//        JLabel titulo = new JLabel("Ajustes Lineales (Brillo y Contraste)");
//        titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
//        titulo.setBounds(50, 10, 400, 30);
//        panel.add(titulo);
//
//        JLabel etBrillo = new JLabel("Brillo");
//        etBrillo.setBounds(20, 100, 100, 30);
//        etBrillo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
//        panel.add(etBrillo);
//        getBrillo().setBounds(80, 105, 400, 50);
//        //getBrillo().setValue((int) refSubact.getBrillo());
//        //getRefVp().getGestorSubVentanas().getRefSubVentActual().getBrillo());
//        getBrillo().setBackground(Color.WHITE);
//        getBrillo().setMinimum(0);
//        getBrillo().setMaximum(255);
//        getBrillo().setPaintTicks(true);
//        getBrillo().setPaintTrack(true);
//        getBrillo().setMajorTickSpacing(30);
//        getBrillo().setMinorTickSpacing(10);
//        getBrillo().setPaintLabels(true);
//        getBrillo().addChangeListener(new ChangeListener() {
//
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                try {
//                    if (((JSlider) e.getSource()).getValueIsAdjusting()) {
//
//                        actualizarBrillo(getBrillo().getValue(), getContraste().getValue());
//                        repaint();
//                    }
//                } catch (Exception ex) {
//                }
//            }
//        });
//        panel.add(getBrillo());
//
//        JLabel etContr = new JLabel("Contraste");
//        etContr.setBounds(20, 180, 400, 50);
//        etContr.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
//        panel.add(etContr);
//        getContraste().setBounds(100, 180, 380, 50);
//        //getContraste().setValue((int)refSubact.get_contraste());
//        getContraste().setBackground(Color.WHITE);
//        getContraste().setMinimum(0);
//        getContraste().setMaximum(127);
//        getContraste().setPaintTicks(true);
//        getContraste().setPaintTrack(true);
//        getContraste().setMajorTickSpacing(20);
//        getContraste().setMinorTickSpacing(10);
//        getContraste().setPaintLabels(true);
//        getContraste().addChangeListener(new ChangeListener() {
//
//            @Override
//            public void stateChanged(ChangeEvent e) {
//
//                if (((JSlider) e.getSource()).getValueIsAdjusting()) {
//
//                    actualizarBrillo(getBrillo().getValue(), getContraste().getValue());
//                    repaint();
//                }
//
//            }
//        });
//        add(getContraste());
//
//        JButton aceptar = new JButton("Aceptar");
//        aceptar.setBounds(200, 240, 100, 20);
//        aceptar.setBackground(Color.WHITE);
//        aceptar.setBorder(new LineBorder(Color.BLACK, 2));
//        aceptar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                dispose();
//            }
//        });
//        add(aceptar);
//    }

    public HashMap<Integer, Integer> tablaTransformacionBrillo(double brillo, double contraste) {
        HashMap<Integer, Integer> tabla = new HashMap<Integer, Integer>();
        double A = contraste / imag.get_contraste();
        double B = brillo - A * imag.get_brillo();
        int temp = 0;
        /*vout = A*vin + B*/
        for (int i = 0; i < 256; ++i) {
            temp = (int) (Math.round(((A * i) + B)));
            if (temp < 0) {
                temp = 0;
            }
            if (temp > 255) {
                temp = 255;
            }
            tabla.put(i, temp);
        }

        return tabla;
    }

    public BufferedImage actualizarBrillo(double brillo, double contraste) {
        BufferedImage resultado = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        
        System.out.println("111111111111");

        HashMap<Integer, Integer> tabla = tablaTransformacionBrillo(get_brillo(), get_contraste());
        int valorNuevo = 0;
        for (int i = 0; i < 256; ++i) {
            for (int j = 0; j < 256; ++j) {
                System.out.println("22222222222222222222");

                valorNuevo = tabla.get(new Color(img.getRGB(i, j)).getRed());
System.out.println("333333333333333333");
                //getRefVp().getGestorSubVentanas().getRefSubVentActual().getRefBufImg().setRGB(i, j, new Color (
                //						valorNuevo, valorNuevo, valorNuevo).getRGB());
                resultado.setRGB(i, j, new Color(valorNuevo, valorNuevo, valorNuevo).getRGB());
                System.out.println("4444444444444444");
            }
        }
        //getRefVp().getGestorSubVentanas().getRefSubVentActual().obtenerHistograma(getRefVp().getGestorSubVentanas().getRefSubVentActual().getRefBufImg());
//        System.out.println("22222222222222222222");
        return resultado;

    }

    public void controles2() {
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
                gestor_img.anadirImagen(actualizarBrillo(get_brillo(), get_contraste()));
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
    
    public double get_brillo(){
        return brillo;
    }
    
    public void set_brillo(double b){
        brillo=b;
    }
   
    public double get_contraste(){
        return contraste;
    }
    
    public void set_contraste(double b){
        contraste = b;
    }
    
    public void obtenerBrillo () {

        double brill = 0.0;
	for (int i = 0; i < 256; i++) {
		brill += (datos[i]*i);
	}
	set_brillo (brill / (img.getWidth() * img.getHeight()));
    }
	
    public void obtenerContraste () {
	double contrast = 0.0;
	for (int i = 0; i < 256; i++) {
            contrast += (Math.pow (i - get_brillo(), 2) * datos[i]); 

	}
	set_contraste(Math.sqrt(contrast / (img.getWidth() * img.getHeight())));
    }
    
    private void obtenerdatoshistograma(){
        
        datos = new int[256];
        
        //Inicializar datos a 0.
        for(int i = 0; i < 256; i++){
            datos[i] = 0;
        }
        
        //Obtener datos de nivel de color.
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j < img.getHeight(); j++){
                Color c = new Color(img.getRGB(i, j));
                
                datos[c.getRed()] += 1;
//                System.out.println(i + "," + j);
//                System.out.println(img.getRGB(i, j));
//                System.out.println(c);
            }
        }
    }

}
