/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herramientas;

import editorimag.interfaz;
import imagen.Subventana; //pruebas

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

/**
 *
 * @author jonay
 */
public class Brillo_y_contraste extends JDialog{
    
    
    private ArrayList<ArrayList<Integer>> valoresSinBrillo = new ArrayList <ArrayList<Integer>>();
    private JSlider SBrillo = new JSlider();
    private JSlider SContraste = new JSlider();
    
    private interfaz ventana;
    private Subventana vOriginal;
  
//    private Scrollbar Brillo;
//    private Scrollbar Contraste;
//    private JButton Aceptar;
    
   // private int Brillo;
    //private int Contraste;
    //private BufferedImage mod;
    JSlider getBrillo(){
        return SBrillo;
    }
    
    JSlider setBrillo(JSlider a){
        SBrillo= a;
    }
    
    JSlider getConstraste(){
        return SContraste;
    }
    
    JSlider setContraste(JSlider a){
        SContraste=a;
    }
    
    public Brillo_y_contraste() {	
    
	// NUEVO !! --> TENEMOS LA IMAGEN, SU BRILLO Y CONTRASTE -> ORIGINALES!! (no actualizar !!)
		
	getContentPane().setBackground(Color.WHITE);
	setBounds(350, 50, 500, 300);
	setLayout(null);
	setAlwaysOnTop(true);
	setResizable(false);
	iniciarPanel();
    }
    
    public iniciarPanel(){
        JLabel titulo = new JLabel("Ajustes Lineales (Brillo y Contraste)");
		titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		titulo.setBounds(50, 10, 400, 30);
		add (titulo);

		JLabel etBrillo = new JLabel("Brillo");
		etBrillo.setBounds(20, 100, 100, 30);
		etBrillo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		add (etBrillo);
		getBrillo().setBounds(80, 105, 400, 50);
		getBrillo().setValue((int) getRefVp().getGestorSubVentanas().getRefSubVentActual().getBrillo());
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
						if (!getRefVp().getGestorSubVentanas().getRefSubVentActual().getImgOriginal()) {
							actualizarBrillo(getSBrillo().getValue(), getSContraste().getValue());
							getRefVp().getGestorSubVentanas().getRefSubVentActual().repaint();
						}
					}
				} catch (Exception ex) {}
			}
		});
		add (getSBrillo());

		JLabel etContr = new JLabel("Contraste");
		etContr.setBounds(20, 180, 400, 50);
		etContr.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		add (etContr);
		getSContraste().setBounds(100, 180, 380, 50);
		getSContraste().setValue((int) getRefVp().getGestorSubVentanas().getRefSubVentActual().getContraste());
		getSContraste().setBackground(Color.WHITE);
		getSContraste().setMinimum(0);
		getSContraste().setMaximum(127);
		getSContraste().setPaintTicks(true);
		getSContraste().setPaintTrack(true);
		getSContraste().setMajorTickSpacing(20);
		getSContraste().setMinorTickSpacing(10);
		getSContraste().setPaintLabels(true);
		getSContraste().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					if (((JSlider) e.getSource()).getValueIsAdjusting()) {
						if (!getRefVp().getGestorSubVentanas().getRefSubVentActual().getImgOriginal()) {
							actualizarBrillo(getSBrillo().getValue(), getSContraste().getValue());
							getRefVp().getGestorSubVentanas().getRefSubVentActual().repaint();
						}
					}
				} catch (Exception ex) {}
			}
		});
		add (getSContraste());
		
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
    
    
    
    private void controles(){
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
    }
    
}


