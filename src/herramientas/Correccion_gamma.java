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
import javax.swing.JTextField;

/**
 *
 * @author jonay
 */
public class Correccion_gamma extends JFrame {
    
    private BufferedImage img;
    
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
//        final JSpinner Dat_gamma = new JSpinner();
        
        final JTextField Dat_gamma = new JTextField();
        
        JButton Aceptar = new JButton("Aceptar");

        Aceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
//                n_gamma = (int) Dat_gamma.getValue();
                n_gamma = Double.parseDouble(Dat_gamma.getText());
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
    
}
