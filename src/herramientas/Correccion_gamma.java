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
import java.util.ArrayList;
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
public class Correccion_gamma extends JFrame {
    
    private BufferedImage img;
    
    private double trans[];
    double n_gamma;
    
    private ArrayList<ArrayList<Integer>> valoresAcotados = new ArrayList<ArrayList<Integer>>();
    
    public Correccion_gamma(BufferedImage tmp){
        img = tmp;
        
        controles();
    }
    
    public void controles(){
        setTitle("Correción Gamma");
        setSize(300, 150);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        add(panel);

        JLabel Gamma = new JLabel(" Introduce el gamma");
        final JSpinner Dat_gamma = new JSpinner();
        JButton Aceptar = new JButton("Aceptar");

        Aceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                n_gamma = (int) Dat_gamma.getValue();
                dispose();
                gestor_img.anadirImagen(gamma());
            }
        });

        panel.add(Gamma);
        panel.add(Dat_gamma);
        panel.add(Aceptar);

        setVisible(true);
    }
    
    public BufferedImage gamma(){
        BufferedImage resultado = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        
        //Inicializar valores.
        valoresAcotados.clear();
        for(int i = 0; i < img.getWidth(); i++){
            ArrayList<Integer> dummy = new ArrayList<Integer>();
            for(int j = 0; j < img.getHeight(); j++){
                int rojo = new Color(img.getRGB(i, j)).getRed();
                dummy.add(rojo);
            }
            valoresAcotados.add(dummy);
        }
        
        //Tabla de transformación
        HashMap<Integer, Double> tabla = new HashMap<>();
        for(int i = 0; i < 256; i++){
            tabla.put(i, Math.pow(i/255.0, n_gamma) * 255.0);
        }
        
        //Actualizar Gamma.
        double color = 0.0;
        for(int i = 0; i < valoresAcotados.size(); i++){
            for(int j = 0; j < valoresAcotados.get(i).size(); j++){
                color = tabla.get(valoresAcotados.get(i).get(j));
                resultado.setRGB(i, j, new Color( (int) Math.round(color), (int) Math.round(color), (int) Math.round(color)).getRGB());
            }
        }
        
        return resultado;
    }
    

    
//    public void actualizarGamma (double gamma) {
//		double color = 0.0;
//		HashMap<Integer, Double> tabla = tablaDeTransformacion(gamma);
//		for (int i = 0; i < getValoresAcotados().size(); ++i) {
//			for (int j = 0; j < getValoresAcotados().get(i).size(); ++j) {
//				color = tabla.get(getValoresAcotados().get(i).get(j));
//				getRefVp().getGestorSubVentanas().getRefSubVentActual().getRefBufImg().setRGB(i, j,
//						new Color ((int) Math.round(color), (int) Math.round(color), (int) Math.round(color)).getRGB());
//			}
//		}
//		getRefVp().getGestorSubVentanas().getRefSubVentActual().obtenerHistograma(getRefVp().getGestorSubVentanas().getRefSubVentActual().getRefBufImg());
//	}
    
    
    
}
