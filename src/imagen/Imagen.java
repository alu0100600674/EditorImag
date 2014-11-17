/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagen;

import java.awt.BorderLayout;
import herramientas.Ecualizacion_histograma;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jonay
 */
public class Imagen extends JFrame{
    
    private BufferedImage imgGris;
    //variables prueba
    public String nombre = "Sin nombre";
    public String formatoImg; //= new String("Desconocido");
    public int anchoImg = 0;
    public int altoImg = 0;
    public int min = -1;
    public int max = -1;
    
    
    public JFrame jf;
    
    //public Ecualizacion_histograma histo;
    private int datos[];
    private double brillo = 0.0;
    private double contraste = 0.0; 
    private double entropia = 0.0;
    
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
    
    public double get_entropia(){
        return entropia;
    }
    
    public void set_entropia(double e){
        entropia = e;
    }
        //letra especificada empezando la clase
    
    BufferedImage temp=null;     //nombre imagen
        
    
    //prueba letra
    public Font letra = new Font(Font.SERIF, Font.BOLD, 15);
    public Font letra1 = new Font(Font.SERIF, Font.BOLD, 25);
    
    public Imagen(int tam) throws IOException{
        abrirImagen(tam);
        //histo = new Ecualizacion_histograma(imgGris);
        //nombre = "sin nombre.jpg";
        formatoImg= obtenerFormato(nombre);
        anchoImg = imgGris.getWidth();
        altoImg = imgGris.getHeight();
        min = obtenerMinGris(imgGris);
        max =obtenerMaxGris(imgGris);
        obtenerdatoshistograma();
        obtenerBrillo();
        obtenerContraste();
        obtenerEntropia();
        /*se definiran cuando esten hechas las funciones
        entropia =
        brillo =
        contraste=*/

    }
    
    public BufferedImage getImg(){
        return imgGris;
    }
    
    public BufferedImage pasarAEscalaGrises(BufferedImage img){
        
        int red, green, blue;
        int gris;
        int rgb;
        
        imgGris = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j < img.getHeight(); j++){
                //Obtención de las componentes RGB
                rgb = img.getRGB(i, j);
                red = (rgb >> 16) & 0xff;
                green = (rgb >> 8) & 0xff;
                blue = (rgb) & 0xff;
                
                //Transformación a escala de grises por el sistema PAL
                gris = (int) ((0.222 * red) + (0.707 * green) + (0.071 * blue));
                imgGris.setRGB(i, j, new Color(gris, gris, gris).getRGB() );
            }
        }
        return imgGris;
    }
    
    private void obtenerdatoshistograma(){
        
        datos = new int[256];
        
        //Inicializar datos a 0.
        for(int i = 0; i < 256; i++){
            datos[i] = 0;
        }
        
        //Obtener datos de nivel de color.
        for(int i = 0; i < imgGris.getWidth(); i++){
            for(int j = 0; j < imgGris.getHeight(); j++){
                Color c = new Color(imgGris.getRGB(i, j));
                
                datos[c.getRed()] += 1;
//                System.out.println(i + "," + j);
//                System.out.println(img.getRGB(i, j));
//                System.out.println(c);
            }
        }
    }
    public void obtenerBrillo () {

        double brill = 0.0;
	for (int i = 0; i < 256; i++) {
		brill += (datos[i]*i);
	}
	set_brillo (brill / (imgGris.getWidth() * imgGris.getHeight()));
    }
	
    public void obtenerContraste () {
	double contrast = 0.0;
	for (int i = 0; i < 256; i++) {
            contrast += (Math.pow (i - get_brillo(), 2) * datos[i]); 

	}
	set_contraste(Math.sqrt(contrast / (imgGris.getWidth() * imgGris.getHeight())));
    }
    
    public void obtenerEntropia () {
		double prob = 0.0;
		double ent = 0.0;
		double size = imgGris.getWidth() * imgGris.getHeight();
		for (int i = 0; i < 256; ++i) {
			if (datos[i] != 0) {
				prob = datos[i] / size;
				ent += (-prob * (Math.log10(prob) / Math.log10(2)));
			}
		}
		set_entropia(ent);
    }
    
    private void pintarImagen(Graphics g, BufferedImage temp){
        g.drawImage(temp, 100, 100, null);
    }
    
    public void abrirImagen(int tam) throws IOException{
        
        
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Seleccione una imagen");
        FileNameExtensionFilter filtro_jpg = new FileNameExtensionFilter("JPG", "jpg");
        FileNameExtensionFilter filtro_png = new FileNameExtensionFilter("PNG", "png");
        FileNameExtensionFilter filtro_gif = new FileNameExtensionFilter("GIF", "gif");
        selector.setFileFilter(filtro_gif);
        selector.setFileFilter(filtro_png);
        selector.setFileFilter(filtro_jpg);
        selector.showOpenDialog(null);
       
        nombre = String.valueOf(selector.getSelectedFile());
        temp = ImageIO.read(selector.getSelectedFile());
        //System.out.println(selector.getSelectedFile());
        //nombre = selector.getSelectedFile();
        Subventana ventana = new Subventana(pasarAEscalaGrises(temp), tam);
        
    } 
    
    
    public void guardarImagen() throws FileNotFoundException, IOException{
        File selec = null;
        JFileChooser selector = new JFileChooser();
        selector.setApproveButtonText("Guardar");
        selector.setDialogTitle("Guarde la imagen con el nombre y extension que desee");
        FileNameExtensionFilter filtro_png = new FileNameExtensionFilter("PNG", "png");
        FileNameExtensionFilter filtro_jpg = new FileNameExtensionFilter("JPG", "jpg");
        FileNameExtensionFilter filtro_gif = new FileNameExtensionFilter("GIF", "gif");


        selector.setFileFilter(filtro_png);
        selector.setFileFilter(filtro_gif);
        selector.setFileFilter(filtro_jpg);

        selector.showOpenDialog(null);
        selec = selector.getSelectedFile();
        
        ImageIO.write(imgGris, "png", selec); //Guardando imagenes en PNG.
        ImageIO.write(imgGris, "jpg", selec); //Guardando imagenes en JPG.
        ImageIO.write(imgGris, "gif", selec); //Guardando imagenes en GIF.
        
        
    }
    
//VENTANA PROPIEDADES
    public void propiedades(Graphics g) {
        
                
                setTitle("Propiedades");
                jf = new JFrame();
              
                jf.setDefaultCloseOperation(EXIT_ON_CLOSE);   
		jf.setLayout(new BorderLayout());
                
                jf.setBounds(0, 0, 400, 300);
		
		jf.setBackground(Color.WHITE);
                
                
                
                //transformacion ruta a nombre
                File f = new File (nombre);
                nombre = f.getName();
                
                JLabel titleJLabel = new JLabel("Propiedades");
                JLabel nombreJLabel = new JLabel("Nombre: " + nombre);
                JLabel FormatoJLabel = new JLabel("Formato: " + formatoImg);
                JLabel DimensionesJLabel = new JLabel("Dimensiones: [" + anchoImg + "X " + altoImg + "]");
                JLabel AnchoJLabel = new JLabel("Ancho: " + anchoImg);
                JLabel AltoJLabel = new JLabel("Alto: " + altoImg);
                JLabel minJLabel = new JLabel("Minimo: " + min);
                JLabel maxJLabel = new JLabel("Maximo: " + max);
                JLabel EntropiaJLabel = new JLabel("Entropia: " + entropia);
                JLabel BrilloJLabel = new JLabel ("Brillo: " + brillo);
                JLabel ContrasteJLabel = new JLabel ("Contraste: " + contraste);
                
                
                JButton aceptar = new JButton("Aceptar");
		aceptar.setBounds(300, 410, 100, 30);
		aceptar.setBackground(Color.WHITE);
		aceptar.setBorder(new LineBorder(Color.BLACK, 2));
		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
                            jf.dispose();
			}
		});
		JPanel jp = new JPanel();
                jp.setLayout(new GridLayout(6,2)); 
                //jp.setFont(letra1);
                jp.add(titleJLabel).setFont(letra1);
                //jp.setFont(letra);
                jp.add(nombreJLabel);
                jp.add(FormatoJLabel);
                jp.add(DimensionesJLabel);
                jp.add(AnchoJLabel);
                jp.add(AnchoJLabel);
                jp.add(minJLabel);
                jp.add(maxJLabel);
                jp.add(EntropiaJLabel);
                jp.add(BrilloJLabel);
                jp.add(ContrasteJLabel);

                jf.add (aceptar, BorderLayout.SOUTH);
                
               // jp.paint(gr);
                jf.add(jp, BorderLayout.CENTER);
                jf.add(titleJLabel, BorderLayout.BEFORE_FIRST_LINE);
                jf.setVisible(true);
		
	/*	gr.setColor(Color.GRAY);
		gr.drawString("Valor MIN = " + getMin(), 20, 240);
		gr.drawString("Valor MAX = " + getMax(), 20, 270);
		
		gr.setColor(Color.DARK_GRAY);
		gr.drawString("Entrop�a = " + (float) getEntropia(), 20, 300);
		gr.drawString("Brillo = " + (float) getBrillo(), 20, 330);
		gr.drawString("Contraste = " + (float) getContraste(), 20, 360);
		*/
    }
	
	public String obtenerFormato(String nombreImagen) {
           // System.out.print("caca");
		String [] nombre_Punto_Formato = {"a.jpg"};
		if (nombreImagen.length() > 5){
			nombre_Punto_Formato = nombreImagen.split("\\.");
                        //System.out.print(nombreImagen.split("\\."));
			return nombre_Punto_Formato[1];
		}//System.out.print("pedo");
		return "UNKNOWN FORMAT";
	}
        
        public int obtenerMinGris (BufferedImage bf) {
		int temp = Integer.MAX_VALUE;
		for (int i = 0; i < bf.getWidth(); ++i)
			for (int j = 0; j < bf.getHeight(); ++j)
				if (new Color (bf.getRGB(i, j)).getRed() < temp)
					temp = new Color (bf.getRGB(i, j)).getRed();
		return temp;
	}
	
	public int obtenerMaxGris (BufferedImage bf) {
		int temp = Integer.MIN_VALUE;
		for (int i = 0; i < bf.getWidth(); ++i)
			for (int j = 0; j < bf.getHeight(); ++j)
				if (new Color (bf.getRGB(i, j)).getRed() > temp)
					temp = new Color (bf.getRGB(i, j)).getRed();
		return temp;
	}
    } 
    

