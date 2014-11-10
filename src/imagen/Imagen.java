/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jonay
 */
public class Imagen extends JFrame{
    
    private BufferedImage imgGris;
    
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
    
    //VENTANA PROPIEDADES
    //variables
    private Font letra = new Font(Font.SANS_SERIF, Font.BOLD, 20);
    private String nombre = new String("Sin nombre");
    private String formatoImg = new String("Desconocido");
    private int anchoImg = 0;
    private int altoImg = 0;
    private int min = -1;
    private int max = -1;
    private double entropia = 0.0;
    private double brillo = 0.0;    
    private double contraste = 0.0;
	
    private BufferedImage refBufImg;
    
    
    
    public void propiedades() {

	// Atributos -- referencias
	private interfaz refVp;
	private BufferedImage refImg;
	
	// Atributos de informaci�n
	private PanelVentanaInfo texto;
	
	// Manejadores de atributos
	public VentanaPrincipal getRefVp() { return refVp; }
	public void setRefVp(VentanaPrincipal refVp) { this.refVp = refVp; }
	
	public BufferedImage getRefImg() { return refImg; }
	public void setRefImg(BufferedImage refImg) { this.refImg = refImg; }
	
	public PanelVentanaInfo getTexto() { return texto; }
	public void setTexto(PanelVentanaInfo texto) { this.texto = texto; }


	public VentanaInfoImagen (VentanaPrincipal vp) {
		super ();
		setRefVp(vp);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(400, 50, 600, 500);
		setLayout(null);
		setAlwaysOnTop(true);
		setResizable(false);
		setTexto(new PanelVentanaInfo(this));
		
		add (getTexto());
		
	}
	
	public BufferedImage copiarBufferOriginal (BufferedImage original) {
		BufferedImage copia = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
		for (int i = 0; i < original.getWidth(); ++i) {
			for (int j = 0; j < original.getHeight(); ++j) {
				copia.setRGB(i, j, original.getRGB(i, j));
			}
		}
		return copia;
	}
	
	public void actualizar () {
		getTexto().setNombre(getRefVp().getGestorSubVentanas().getRefSubVentActual().getTitle());
		getTexto().setFormatoImg(getTexto().obtenerFormato(getRefVp().getGestorSubVentanas().getRefSubVentActual().getTitle()));
		getTexto().setAnchoImg(getRefImg().getWidth());
		getTexto().setAltoImg(getRefImg().getHeight());

		getTexto().setMin(obtenerMinGris(getRefVp().getGestorSubVentanas().getRefSubVentActual().getRefBufImg()));
		getTexto().setMax(obtenerMaxGris(getRefVp().getGestorSubVentanas().getRefSubVentActual().getRefBufImg()));
		
		getRefVp().getGestorSubVentanas().getRefSubVentActual().obtenerEntropia();
		getTexto().setEntropia(getRefVp().getGestorSubVentanas().getRefSubVentActual().getEntropia());
		
		getTexto().setBrillo(getRefVp().getGestorSubVentanas().getRefSubVentActual().getBrillo());
		getTexto().setContraste(getRefVp().getGestorSubVentanas().getRefSubVentActual().getContraste());
		getTexto().setRefBufImg(copiarBufferOriginal(getRefVp().getGestorSubVentanas().getRefSubVentActual().getRefBufImg()));
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
        
    /*    
        setTitle("Propiedades de la imagen");
        setVisible(true);
        setSize(200, 300);
     */   
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
        
        
        
                
    } 
    

