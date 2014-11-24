/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herramientas;

import editorimag.interfaz;
import imagen.Gestion_subventanas;
import imagen.Subventana; //pruebas
import imagen.Imagen;

import static java.awt.Adjustable.HORIZONTAL;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Brillo_y_contraste extends JDialog{
    
    
    private ArrayList<ArrayList<Integer>> valoresSinBrillo = new ArrayList <ArrayList<Integer>>();
    private JSlider SBrillo = new JSlider();
    private JSlider SContraste = new JSlider();
    
    public interfaz ventana;
    private Gestion_subventanas vOriginal;
    public Subventana refSubact;
    private BufferedImage a;
    public Imagen imag;
   
    public JFrame panel;
//    private Scrollbar Brillo;
//    private Scrollbar Contraste;
//    private JButton Aceptar;
    
   // private int Brillo;
    //private int Contraste;
    //private BufferedImage mod;
    
    JSlider getBrillo(){
        return SBrillo;
    }
    
    void setBrillo(JSlider a){
        SBrillo= a;
    }
    
    JSlider getContraste(){
        return SContraste;
    }
    
    void setContraste(JSlider a){
        SContraste=a;
    }
    
    public Brillo_y_contraste(BufferedImage actual) {	
    
	// NUEVO !! --> TENEMOS LA IMAGEN, SU BRILLO Y CONTRASTE -> ORIGINALES!! (no actualizar !!)
		
	getContentPane().setBackground(Color.WHITE);
	setBounds(350, 50, 500, 300);
	setLayout(null);
	setAlwaysOnTop(true);
	setResizable(false);
        a = refSubact.getImagenActual();
        //imag = Imagen.get
	iniciarPanel(actual);
    }
    
    public void iniciarPanel(BufferedImage tmp){
        
        setTitle("Valores de brillo y contraste");
        panel = new JFrame();
              
//                jf.setDefaultCloseOperation(EXIT_ON_CLOSE);   
        //panel.setLayout(new BorderLayout());
                
        panel.setBounds(0, 0, 400, 300);
		
        panel.setBackground(Color.WHITE);
        JLabel titulo = new JLabel("Ajustes Lineales (Brillo y Contraste)");
		titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		titulo.setBounds(50, 10, 400, 30);
		panel.add(titulo);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   

		JLabel etBrillo = new JLabel("Brillo");
		etBrillo.setBounds(20, 100, 100, 30);
		etBrillo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		panel.add(etBrillo);
		getBrillo().setBounds(80, 105, 400, 50);
		//getBrillo().setValue((int) refSubact.getBrillo());
                //getRefVp().getGestorSubVentanas().getRefSubVentActual().getBrillo());
		getBrillo().setBackground(Color.WHITE);
		getBrillo().setMinimum(0);
		getBrillo().setMaximum(255);
		getBrillo().setPaintTicks(true);
		getBrillo().setPaintTrack(true);
		getBrillo().setMajorTickSpacing(30);
		getBrillo().setMinorTickSpacing(10);
		getBrillo().setPaintLabels(true);
		getBrillo().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					if (((JSlider) e.getSource()).getValueIsAdjusting()) {
					
						actualizarBrillo(getBrillo().getValue(), getContraste().getValue());
							repaint();					
					}
				} catch (Exception ex) {}
			}
		});
		panel.add(getBrillo());

		JLabel etContr = new JLabel("Contraste");
		etContr.setBounds(20, 180, 400, 50);
		etContr.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		panel.add (etContr);
		getContraste().setBounds(100, 180, 380, 50);
		//getContraste().setValue((int)refSubact.get_contraste());
		getContraste().setBackground(Color.WHITE);
		getContraste().setMinimum(0);
		getContraste().setMaximum(127);
		getContraste().setPaintTicks(true);
		getContraste().setPaintTrack(true);
		getContraste().setMajorTickSpacing(20);
		getContraste().setMinorTickSpacing(10);
		getContraste().setPaintLabels(true);
		getContraste().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				
					if (((JSlider) e.getSource()).getValueIsAdjusting()) {
						
							actualizarBrillo(getBrillo().getValue(), getContraste().getValue());
							repaint();
						}
					
                       
			}
		});
		add (getContraste());
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.setBounds(200, 240, 100, 20);
		aceptar.setBackground(Color.WHITE);
		aceptar.setBorder(new LineBorder(Color.BLACK, 2));
		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		add (aceptar);    
    }
    
    
    
    
 /*   private void controles(){
        JFrame control = new JFrame();
        JPanel panel = new JPanel();
        control.add(panel);
        
        control.setTitle("Brillo y Contraste");
        control.setSize(300, 200);
        control.setVisible(true);
        control.setResizable(false);
        
        JLabel L_Brillo = new JLabel("Brillo");
        Scrollbar Brillo = new Scrollbar(HORIZONTAL, 0, 0, 0, 255);
        Brillo.setSize(270, 20);
        
        JLabel L_Contraste = new JLabel("Contraste");
        Scrollbar Contraste = new Scrollbar(HORIZONTAL, 0, 0, 0, 255);
        Contraste.setSize(270, 20);
        
        JButton Aceptar = new JButton("Aceptar");
        Aceptar.setSize(100, 30);
        
        panel.add(L_Brillo).setLocation(30, 15);
        panel.add(Brillo).setLocation(15, 30);
        panel.add(L_Contraste);
        panel.add(Contraste).setLocation(15, 80);
        panel.add(Aceptar).setLocation(200, 150);
    }
    
    private void imagenResultado(){
        JFrame resultado = new JFrame();
    }*/
    
//}

	public HashMap <Integer, Integer> tablaTransformacionBrillo (double brillo, double contraste) {
		HashMap <Integer, Integer> tabla = new HashMap <Integer, Integer>();
		double A = contraste / imag.get_contraste();
		double B = brillo - A * imag.get_brillo();
		int temp = 0;
		/*vout = A*vin + B*/
		for (int i = 0; i < 256; ++i) {
			temp = (int)(Math.round (((A * i) + B)));
			if (temp < 0)
				temp = 0;
			if (temp > 255)
				temp = 255;
			tabla.put (i, temp);
		}

		return tabla;
	}



	public void actualizarBrillo (double brillo, double contraste) {
		HashMap <Integer, Integer> tabla = tablaTransformacionBrillo (brillo, contraste);
		int valorNuevo = 0;
		for (int i = 0; i < 256; ++i) {
			for (int j = 0; j < 256; ++j) {
				
				valorNuevo = tabla.get(new Color (refSubact.getImagenActual().getRGB(i, j)).getRed());
			
				//getRefVp().getGestorSubVentanas().getRefSubVentActual().getRefBufImg().setRGB(i, j, new Color (
		//						valorNuevo, valorNuevo, valorNuevo).getRGB());
			}
		}
		//getRefVp().getGestorSubVentanas().getRefSubVentActual().obtenerHistograma(getRefVp().getGestorSubVentanas().getRefSubVentActual().getRefBufImg());

	}
}
