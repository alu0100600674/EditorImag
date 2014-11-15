/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagen;

import java.awt.BorderLayout;
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
import java.time.Clock;
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
    
    //prueba letra
    public Font letra = new Font(Font.SERIF, Font.BOLD, 15);
    
    public Imagen(int tam) throws IOException{
        abrirImagen(tam);
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
    
    private void pintarImagen(Graphics g, BufferedImage temp){
        g.drawImage(temp, 100, 100, null);
    }
    
    public void abrirImagen(int tam) throws IOException{
        BufferedImage temp=null;
        
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Seleccione una imagen");
        FileNameExtensionFilter filtro_jpg = new FileNameExtensionFilter("JPG", "jpg");
        FileNameExtensionFilter filtro_png = new FileNameExtensionFilter("PNG", "png");
        FileNameExtensionFilter filtro_gif = new FileNameExtensionFilter("GIF", "gif");
        selector.setFileFilter(filtro_jpg);
        selector.setFileFilter(filtro_png);
        selector.setFileFilter(filtro_gif);
        selector.showOpenDialog(null);
        temp = ImageIO.read(selector.getSelectedFile());
        
        Subventana ventana = new Subventana(pasarAEscalaGrises(temp), tam);
        
    } 
    
    public void guardarImagen() throws FileNotFoundException, IOException{
        File selec = null;
        JFileChooser selector = new JFileChooser();
        selector.setApproveButtonText("Guardar");
        selector.setDialogTitle("Guarde la imagen con el nombre y extension que desee");
//        FileNameExtensionFilter filtro_jpg = new FileNameExtensionFilter("JPG", "jpg");
        FileNameExtensionFilter filtro_png = new FileNameExtensionFilter("PNG", "png");
//        FileNameExtensionFilter filtro_gif = new FileNameExtensionFilter("GIF", "gif");
//        selector.setFileFilter(filtro_jpg);
        selector.setFileFilter(filtro_png);
//        selector.setFileFilter(filtro_gif);
        selector.showOpenDialog(null);
        selec = selector.getSelectedFile();
        
        ImageIO.write(imgGris, "png", selec); //Guardando imagenes en PNG.
        
    }
    
    
    //variables prueba
        public String nombre = "Sin nombre";
	//public String formatoImg = new String("Desconocido");
	public int anchoImg = 0;
	public int altoImg = 0;
	public int min = -1;
	public int max = -1;
	public double entropia = 0.0;
	public double brillo = 0.0;
	public double contraste = 0.0;
        public JFrame jf;
        //letra especificada empezando la clase
    
//VENTANA PROPIEDADES
    public void propiedades(Graphics g) {
        
        System.out.print("hola");
        
                jf = new JFrame();
                //jf.getDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
		jf.setLayout(new BorderLayout());
                
                jf.setBounds(0, 0, imgGris.getWidth(), imgGris.getHeight());
		//setRefBufImg(imgGris.getRefImg());
	
		//Graphics2D gr = (Graphics2D) g;
		jf.setBackground(Color.WHITE);
                
		/*gr.fillRect(0, 0, imgGris.getWidth(), imgGris.getHeight());
		gr.setColor(Color.BLACK);
		gr.setFont(letra);
		gr.drawString("�Propiedades de la imagen:", 55, 35);
		
		gr.setColor(Color.DARK_GRAY);
		gr.drawString("Nombre: " + nombre, 20, 90);
		gr.drawString("Formato: " + obtenerFormato(nombre), 20, 120);
		gr.drawString("Dimensiones: [" + anchoImg + " X " + altoImg + "]", 20, 150);
		gr.drawString(" Ancho: " + anchoImg, 25, 180);
		gr.drawString(" Alto: " + altoImg, 25, 210);*/
                
                JLabel nombreJLabel = new JLabel("Nombre: " + nombre);
                JLabel FormatoJLabel = new JLabel("Formato: " + obtenerFormato(nombre));
                JLabel DimensionesJLabel = new JLabel("Dimensiones: "/* + anchoImg + "X " + altoImg + "]"*/);
                JLabel AnchoJLabel = new JLabel("Ancho: " /*+ anchoImg*/);
                JLabel AltoJLabel = new JLabel("Alto: " /*+ altoImg*/);
                
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
                jp.setLayout(new GridLayout(0,1)); 
                jp.add(nombreJLabel);
                jp.add(FormatoJLabel);
                jp.add(DimensionesJLabel);
                jp.add(AnchoJLabel);
                jp.add(AnchoJLabel);

                jf.add (aceptar, BorderLayout.SOUTH);
                
               // jp.paint(gr);
                jf.add(jp, BorderLayout.CENTER);
                       
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
            System.out.print("caca");
		String [] nombre_Punto_Formato = {"a.jpg"};
		if (nombreImagen.length() > 5){
			nombre_Punto_Formato = nombreImagen.split("\\.");
                        System.out.print(nombreImagen.split("\\."));
			return nombre_Punto_Formato[1];
		}System.out.print("pedo");
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
        
	
	// Manejadores de atributos
/*	
	public Font getLetra() { return letra; }
	public double getBrillo() { return brillo; }
	public void setBrillo(double brillo) { this.brillo = brillo; }
	public double getContraste() { return contraste; }
	public void setContraste(double contraste) { this.contraste = contraste; }
	
	public void setLetra(Font letra) { this.letra = letra; }
	
	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	
	public int getAnchoImg() { return anchoImg; }
	public void setAnchoImg(int anchoImg) { this.anchoImg = anchoImg; }
	
	public int getAltoImg() { return altoImg; }
	public void setAltoImg(int altoImg) { this.altoImg = altoImg; }
	
	public int getMin() { return min; }
	public void setMin(int min) { this.min = min; }
	
	public int getMax() { return max; }
	public void setMax(int max) { this.max = max; }
	
	public double getEntropia() { return entropia; }
	public void setEntropia(double entropia) { this.entropia = entropia; }
	
	public BufferedImage getRefBufImg() { return refBufImg; }
	public void setRefBufImg(BufferedImage refBufImg) { this.refBufImg = refBufImg; }
        */
    /*    
        setTitle("Propiedades de la imagen");
        setVisible(true);
        setSize(200, 300);
     */   
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
    
        
        
        
                
    } 
    

