/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imagen;

import static editorimag.EditorImag.activa;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author jonay
 */
public class Subventana extends JFrame {
    
    //gestion de la subventana activa
    private BufferedImage tmp;
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
        });
        
        setVisible(true);
        
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
        
        
        
    public void paint(Graphics g){
        infoRGB(g);
        g.drawImage(tmp, 5, 30, this);
        
    }
    
}



