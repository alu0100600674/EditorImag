/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagen;


import editorimag.EditorImag;
import herramientas.Ecualizacion_histograma;
import static editorimag.EditorImag.activa;
import static editorimag.EditorImag.gestor_img;
import static editorimag.EditorImag.recortar;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import sun.java2d.loops.DrawRect;

/**
 *
 * @author jonay
 */
public class Subventana extends JFrame {
    
    //gestion de la subventana activa
    
    private BufferedImage tmp; //referencia imagen actual
    private int num_subv;
    private int num_subv2;
    
    //pintando componentes
    private Font letra = new Font(Font.SERIF, Font.BOLD, 15);
	
    private int r = 0;  
    private int g = 0;
    private int b = 0;
	
    private int posX = 0;
    private int posY = 0;
    
    private int alto = 30; //gestion de posicion del mouse
    
    private int iniX = 0;
    private int iniY = 0;
    private int finX = 0;
    private int finY = 0;

    public BufferedImage getImagenActual(){
        return tmp;
    }
    
    public int getNumSubv(){
        return num_subv;
    }
    
    public void setSubv(int v){
        num_subv2 = v;
    }
    
    //control del panel RGB
    public Font getLetra() { return letra; }
    public void setLetra(Font letra) { this.letra = letra; }
    
    public int getR() { return r; }
    public void setR(int r) { this.r = r; }
	
    public int getG() { return g; }
    public void setG(int g) { this.g = g; }
	
    public int getB() { return b; }
    public void setB(int b) { this.b = b; }
	
    public int getPosX() { return posX; }
    public void setPosX(int posX) { this.posX = posX; }
	
    public int getPosY() { return posY; }
    public void setPosY(int posY) { this.posY = posY; }
    
    public int getAlto() { return alto; }
    public void setAlto(int alto) { this.alto = alto; }
   
    //haciendo subventana
    public Subventana(BufferedImage tmp2, int tam) {
        setTitle("Imagen " + (tam + 1)); 
        //caso imagen pequeña, no se ve panel pixel RGB

        if (tmp2.getWidth()<220)
            setSize(350+10, tmp2.getHeight()+35);
        else
            setSize(tmp2.getWidth()+10, tmp2.getHeight()+35); //Tamaño de la ventana según la imagen.
        
        setResizable(false);
        tmp = tmp2;
        
        JPanel panel = new JPanel();
        this.repaint();
        
        num_subv = tam; //Asignar un número de subventana
        
        addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e){
                activa = num_subv;
            }
           
        });
        
        addMouseMotionListener(new MouseMotionAdapter(){
        
            @Override
            public void mouseMoved(MouseEvent r) {
	
		setR(new Color(tmp.getRGB(r.getX(), r.getY())).getRed());
		setG(new Color(tmp.getRGB(r.getX(), r.getY())).getGreen());
		setB(new Color(tmp.getRGB(r.getX(), r.getY())).getBlue());

		setPosX(r.getX());
                setPosY(r.getY());
                //System.out.println(r.getX() + "," + r.getY());
                repaint();
			
            }
            
            public void mouseDragged(MouseEvent r){
//                System.out.println("dragged");
                pintarCuadrito();
            }
            
            public void pintarCuadrito () {
                getGraphics().drawRect(iniX - 5, iniY - 30, finX - iniX, finY - iniY);
            }
        });
        
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                
            }

            @Override
            public void mousePressed(MouseEvent me) {
                iniX = me.getX() - 5;
                iniY = me.getY() - 30;
//                System.out.println("Pressed" +  iniX + " " + iniY);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                finX = me.getX() - 5;
                finY = me.getY() - 30;
                
                if(finX < iniX){
                    int aux = finX;
                    finX = iniX;
                    iniX = aux;
                }
                if(finY < iniY){
                    int aux = finY;
                    finY = iniY;
                    iniY = aux;
                }
//                System.out.println("Released" + finX + " " + finY);
                if(recortar){
                    gestor_img.anadirImagen(recorte());
                    recortar = false;
                }
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                
            }

            @Override
            public void mouseExited(MouseEvent me) {
                
            }
            
        });
        
        setVisible(true);
        
    }
    
    public BufferedImage recorte(){
        BufferedImage resultado = new BufferedImage(finX - iniX, finY - iniY, tmp.getType());
//        BufferedImage resultado = new BufferedImage(200, 200, tmp.getType());
//        System.out.println("CACAAAAA");
        int i_ = 0;
        int j_ = 0;
//        System.out.println("Dim img nueva " + (finX - iniX + 1) + "x" + (finY - iniY + 1));
        for(int i = iniX; i < finX; i++){
            for(int j = iniY; j < finY; j++){
                resultado.setRGB(i_, j_, tmp.getRGB(i, j));
//                System.out.println(i + " " + j);
//                System.out.println("----------");
//                System.out.println("i,j " + i_ + " " + j_);
                j_ += 1;
                
            }
            i_ += 1;
            j_ = 0;
        }
        
        return resultado;
    }
    
    public BufferedImage copia(){
        BufferedImage resultado = new BufferedImage(tmp.getWidth(), tmp.getHeight(), tmp.getType());
        
        for(int i = 0; i < tmp.getWidth(); i++){
            for(int j = 0; j < tmp.getHeight(); j++){
                resultado.setRGB(i, j, tmp.getRGB(i, j));
            }
        }
        
        return resultado;
    }
    
    public void infoRGB(Graphics gr){
         
	gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, this.getWidth(), this.getHeight());
		
	gr.setFont(getLetra());
        
        
        gr.setColor(Color.RED);
	gr.drawString("R ["+ getR() +"]", 10, 20);
        gr.setColor(Color.GREEN);
	gr.drawString("G ["+ getG() +"]", 80, 20);
        gr.setColor(Color.BLUE);
	gr.drawString("B ["+ getB() +"]", 150, 20);
		
        gr.setColor(Color.BLACK);
        
        int a = getPosX()-5;
        int b = getPosY()-30;
        
        if ((a<0) || (b<0))
            a=0;
        if (b<0)
            b=0;      
        
        gr.drawString("Pixel [" + a + ", " + b + "]", 220, 20);
//	gr.drawString("Pixel [" + (getPosX()-5) + ", " + (getPosY()-30) + "]", 220, 20);
    }       
 
    public void PanelSubVentana () {
		
		if (tmp != null)
			setBounds(0, 0, tmp.getWidth() - 20, tmp.getHeight() - 20);
		else
			setBounds(0, 0, 200, 400);

	}

    protected void paintComponent (Graphics gr) {
		Graphics2D g2 = (Graphics2D) gr;
		g2.setColor (Color.WHITE);
		if (tmp == null) {
			g2.drawRect(0, 0, getWidth(), getHeight());

		} else {
			g2.drawImage(tmp, 0, 0, this);
		}
		
	}
        
      
 /*   public final void obtenerBrillo () {
		// Media de las frecuencias de los valores del histograma
        double brill = 0.0;
	for (int i = 0; i < 256; i++) {
		brill += (histo.get_datos(i)*i);
	}
	set_brillo (brill / (tmp.getWidth() * tmp.getHeight()));
    }
	
    public final void obtenerContraste () {
		
	double contrast = 0.0;
	for (int i = 0; i < 256; i++) {
            contrast += (Math.pow (i - get_brillo(), 2) * histo.get_datos(i)); 

	}
	set_contraste(Math.sqrt(contrast / tmp.getWidth() * tmp.getHeight()));
}*/
        
    public void paint(Graphics g){
        infoRGB(g);
        g.drawImage(tmp, 5, 30, this);
        
    }
    
}



