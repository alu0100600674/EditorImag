/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagen;

import java.awt.Color;
import editorimag.Subventana;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jonay
 */
public class Imagen {
    
    
    public Imagen() throws IOException{
        abrirImagen();
    }
    
    public BufferedImage pasarAEscalaGrises(BufferedImage img){
        
        int red, green, blue;
        int gris;
        int rgb;
        
        BufferedImage imgGris = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        
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
    
    public void abrirImagen() throws IOException{
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
        
        //if ()
       /* int opcion = selector.showOpenDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION)*/
        
        Subventana ventana = new Subventana(pasarAEscalaGrises(temp));
//        ventana.repaint();
    } 
    
    public void guardarImagen() throws FileNotFoundException{
        /*BufferedImage temp=null;
        File selec = null;
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Guarde la imagen con el nombre y extension que desee");
        FileNameExtensionFilter filtro_jpg = new FileNameExtensionFilter("JPG", "jpg");
        FileNameExtensionFilter filtro_png = new FileNameExtensionFilter("PNG", "png");
        FileNameExtensionFilter filtro_gif = new FileNameExtensionFilter("GIF", "gif");
        selector.setFileFilter(filtro_jpg);
        selector.setFileFilter(filtro_png);
        selector.setFileFilter(filtro_gif);
        selector.showOpenDialog(null);
        selec = selector.getSelectedFile();
        try ( //selec = new File(selector.getSelectedFile());
                FileOutputStream fos = new FileOutputStream(selec)) {
            byte[] array;
            array = new byte[1000];
            int leido = is.read(array);
            while(leido>0){
                fos.write(array,, 0, leido);
                leido= is.read(array);
            }
            is.close();
        }*/
    }
    //VENTANA PROPIEDADES
    public void propiedades() {
        
    /*    
        setTitle("Propiedades de la imagen");
        setVisible(true);
        setSize(200, 300);
     */   
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
        
        
        
                
    } 
    

