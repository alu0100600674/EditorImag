/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herramientas;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author jonay
 */
public class Rotacion {
    
    public class Punto {
		private double x, y;

		public Punto (double x, double y) { setX(x); setY(y); }

		public double getX() { return x; }

		public void setX(double x) { this.x = x; }

		public double getY() { return y; }

		public void setY(double y) { this.y = y; }
	}
    
    private BufferedImage img;
    public int contadorDeFondo;
    
    public Rotacion(BufferedImage tmp){
        img = tmp;
    }
    
    public BufferedImage rotar90(){
        BufferedImage resultado = new BufferedImage(img.getHeight(), img.getWidth(), img.getType());
        
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j < img.getHeight(); j++){
                resultado.setRGB(j, i, img.getRGB(img.getWidth() - 1 - i, j));
            }
        }
        
        return resultado;
    }
    
    public BufferedImage rotar180(){
        BufferedImage resultado = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        
        for(int i = 0; i < img.getHeight(); i++){
            for(int j = 0; j < img.getWidth(); j++){
                resultado.setRGB(j, i, img.getRGB(img.getWidth() - 1 - j, img.getHeight() - 1 - i));
            }
        }
        
        return resultado;
    }
    
    public BufferedImage rotar270(){
        BufferedImage resultado = new BufferedImage(img.getHeight(), img.getWidth(), img.getType());
        
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j < img.getHeight(); j++){
                resultado.setRGB(j, i, img.getRGB(i, img.getHeight() - 1 - j));
            }
        }
        
        return resultado;
    }
    
    public BufferedImage rotar(){
        return null; //Cambiar cuando se haga este método.
    }
    

    public BufferedImage rotar (double grados, int opc) {
		int ancho = img.getWidth();
		int alto = img.getHeight();
		int tipo = img.getType();
		contadorDeFondo=0;
		grados = Math.toRadians(grados);
		ArrayList<Punto> esquinas = rotarEsquina(grados); 
		Punto dimension = anchoAlto(esquinas);
		BufferedImage temp = new BufferedImage((int)dimension.getX() + 1, (int)dimension.getY() + 1, tipo);

		int colorNuevo;

		Punto oPrima = calcularIzquierdaSuperior(esquinas);
		Punto traslado;
		Punto mapInverso;

		if (opc == 0) {		//rotar y pintar
			for (int i = 0; i <= dimension.getY(); ++i)
				for (int j = 0; j <= dimension.getX(); ++j) {
					traslado = trasladar(oPrima, new Punto ((double)j, (double)i));
					mapInverso = transformacionInversa(grados, (int)traslado.getX(), (int)traslado.getY());
					if ((mapInverso.getX() < ancho) && (mapInverso.getX() >= 0) && (mapInverso.getY() < alto) && (mapInverso.getY() >= 0)) {
						colorNuevo = new Color(img.getRGB((int)mapInverso.getX(), (int)mapInverso.getY())).getRed();
						temp.setRGB(j, i, new Color (colorNuevo, colorNuevo, colorNuevo).getRGB());
					}
					else
						contadorDeFondo++;
				}
		}
		else if (opc == 1) {		//rotar e interpolar con vecino mas proximo
			for (int i = 0; i <= dimension.getY(); ++i)
				for (int j = 0; j <= dimension.getX(); ++j) {
					traslado = trasladar(oPrima, new Punto ((double)j, (double)i));
					mapInverso = transformacionInversa(grados, (int)traslado.getX(), (int)traslado.getY());
					if ((mapInverso.getX() < ancho) && (mapInverso.getX() >= 0) && (mapInverso.getY() < alto) && (mapInverso.getY() >= 0)) { 
						colorNuevo = new Color(img.getRGB((int)vecinoMasCercano(mapInverso.getX(), mapInverso.getY()).getX(), (int)vecinoMasCercano(mapInverso.getX(), mapInverso.getY()).getY())).getRed();
						temp.setRGB(j, i, new Color (colorNuevo, colorNuevo, colorNuevo).getRGB());
					}
					else 
						contadorDeFondo++;
				}
		}

		else if (opc == 2) {		//rotar e interpolacion bilineal
			for (int i = 0; i <= dimension.getY(); ++i)
				for (int j = 0; j <= dimension.getX(); ++j) {
					traslado = trasladar(oPrima, new Punto ((double)j, (double)i));
					mapInverso = transformacionInversa(grados, (int)traslado.getX(), (int)traslado.getY());
					if ((mapInverso.getX() < ancho) && (mapInverso.getX() >= 0) && (mapInverso.getY() < alto) && (mapInverso.getY() >= 0)) {
						colorNuevo = interBiLineal(mapInverso.getX(), mapInverso.getY());
						temp.setRGB(j, i, new Color (colorNuevo, colorNuevo, colorNuevo).getRGB());
					}
					else
						contadorDeFondo++;
				}
		}


		return temp;
	}
    
    public ArrayList<Punto> rotarEsquina (double grados) {
		int ancho = img.getWidth();
		int alto = img.getHeight();

		ArrayList<Punto> temp = new ArrayList<Punto>();
		temp.add(transformacionDirecta(grados, 0, 0));
		temp.add(transformacionDirecta(grados, 0, alto - 1));
		temp.add(transformacionDirecta(grados, ancho - 1, 0));
		temp.add(transformacionDirecta(grados, ancho - 1, alto - 1));
		return temp;
	}
    
    public Point vecinoMasCercano (double j, double i) {
		double jI = Math.round(j);
		double iI = Math.round(i);
		int ancho = img.getWidth();
		int alto = img.getHeight();

		if (jI >= ancho)
			jI = ancho - 1;
		if (iI >= alto)
			iI = alto - 1;
		Point temp = new Point((int)jI, (int)iI);
		return temp;
	}
    
    public int interBiLineal (double j, double i) {
		int ancho = img.getWidth();
		int alto = img.getHeight();

		int A = new Color(img.getRGB((int)j,(int)i)).getRed();
		int B;
		int C;
		int D;

		if (j + 1 >= ancho) {
			B = new Color(img.getRGB((int)j,(int)i)).getRed();
			if (i + 1 >= alto) {
				C = new Color(img.getRGB((int)j,(int)i)).getRed();	
				D = new Color(img.getRGB((int)j,(int)i)).getRed();
			} else {
				C = new Color(img.getRGB((int)j,(int)i + 1)).getRed();
				D = new Color(img.getRGB((int)j,(int)i + 1)).getRed();
			}
		} else {
			B = new Color(img.getRGB((int)j + 1,(int)i)).getRed();
			if (i + 1 >= alto) {
				C = new Color(img.getRGB((int)j,(int)i)).getRed();	
				D = new Color(img.getRGB((int)j + 1,(int)i)).getRed();
			} else {
				C = new Color(img.getRGB((int)j,(int)i + 1)).getRed();
				D = new Color(img.getRGB((int)j + 1,(int)i + 1)).getRed();
			}
		}

		double p = j - (int)j;
		double q = i - (int)i;

		double Q = A + (B - A) * p;
		double R = C + (D - C) * p;
		int P = (int)(R + (Q - R) * q);

		return P;
	}
    
    public Punto transformacionDirecta (double grados, int x, int y) {
		return new Punto((Math.cos(grados) * x - Math.sin(grados) * y),(Math.sin(grados) * x + Math.cos(grados) * y));
	}
    
    public Punto calcularIzquierdaSuperior (ArrayList<Punto> p) {
		double iz = Double.MAX_VALUE, arriba = Double.MAX_VALUE;
		for (int i = 0; i < p.size(); ++i) {
			if (iz >= p.get(i).getX())
				iz = p.get(i).getX();
			if (arriba >= p.get(i).getY())
				arriba = p.get(i).getY();
		}
		return new Punto (Math.abs(iz), Math.abs(arriba));
	}
	
	public Punto trasladar (Punto o, Punto p) {
		return new Punto (p.getX() - o.getX(), p.getY() - o.getY());
	}
	
	public Punto anchoAlto (ArrayList<Punto> p) {
		double iz = Double.MAX_VALUE, der = -Double.MAX_VALUE, arriba = Double.MAX_VALUE, abajo = -Double.MAX_VALUE;

		for (int i = 0; i < p.size(); ++i) {
			if (iz >= p.get(i).getX())
				iz = p.get(i).getX();
			if (der <= p.get(i).getX())
				der = p.get(i).getX();
			if (arriba >= p.get(i).getY())
				arriba = p.get(i).getY();
			if (abajo <= p.get(i).getY())
				abajo = p.get(i).getY();
		}
		return new Punto((Math.abs(der) + Math.abs(iz)), (Math.abs(abajo) + Math.abs(arriba)));
	}

	public Punto transformacionInversa (double grados, int x, int y) {
		return new Punto((Math.cos(grados) * x + Math.sin(grados) * y),(-Math.sin(grados) * x + Math.cos(grados) * y));
	}
    
    
    
}
