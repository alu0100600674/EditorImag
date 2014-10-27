/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagen;

import java.awt.Font;
import javax.swing.JPanel;

/**
 *
 * @author johnspirit
 */
public class PanelInfoColoresPixel extends JPanel{
    //Atributos de la ventana info
    private int x = 30;
    private int y;
    private int ancho = 0;
    private int alto = 40;
    private Font letra = new Font(Font.SANS_SERIF, Font.BOLD, 16);
    
    private int r = 0;
    private int g = 0;
    private int b = 0;
    
    private int posX = 0;
    private int posY = 0;
    
    public int getX(){ return x;}
    public void setX(int x) {this.x = x;}
    
    public int getY(){ return y;}
    public void setY(int y) {this.y = y;}
    
    public int getAncho(){ return ancho;}
    public void setAncho(int anch) { this.ancho = anch;}
    
    public int getAlto(){ return alto;}
    public void setAlto(int alt) {this.alto = alt;}
    
    public Font getletra(){ return letra;}
    public void setletra(Font let){ this.letra = let;}
    
    public int getr(){ return r;}
    public void setr( int valor){ this.r = valor;}
    
    public int getg(){ return g;}
    public void setg( int valor){ this.g = valor;}
    
    public int getb(){ return b;}
    public void setb( int valor){ this.b = valor;}
    
    public int getPosx(){ return PosX;}
    public void setPosx( int x){ this.posX = x;}
    
    public int getPosy(){ return PosY;}
    public void setPosy( int y){this.posY = y;}
    
    
            
    
    
}
