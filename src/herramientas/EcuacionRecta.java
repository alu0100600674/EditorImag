/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herramientas;

import java.awt.Point;
import java.util.HashMap;

public class EcuacionRecta {

	private HashMap<Integer, Integer> recta;
	int indiceIni = 0;
	int indiceFin = 0;
	private Point ini;
	private Point fin;
	
	public HashMap<Integer, Integer> getRecta() { return recta; }
	public void setRecta(HashMap<Integer, Integer> recta) { this.recta = recta; }

	public int getIndiceIni() { return indiceIni; }
	public void setIndiceIni(int indiceIni) { this.indiceIni = indiceIni; }
	
	public int getIndiceFin() { return indiceFin; }
	public void setIndiceFin(int indiceFin) { this.indiceFin = indiceFin; }
	
	public Point getIni() { return ini;	}
	public void setIni(Point ini) { this.ini = ini; }
	
	public Point getFin() { return fin;	}
	public void setFin(Point fin) { this.fin = fin;	}
	
	public EcuacionRecta(Point ini, Point fin) { 
		setRecta(new HashMap<Integer, Integer>()); 
		
		setIni(ini);
		setFin(fin);
		
		setIndiceIni((int) Math.round(getIni().getX()));
		setIndiceFin((int) Math.round(getFin().getX()));
	}
	
	public double calcularPendiente () {	
		if (getFin().getX() == getIni().getX())
			return 1.0f;
		return ((getFin().getY() - getIni().getY()) / (getFin().getX() - getIni().getX()));
	}
	
	public void rellenarEcuacion () {
		double m = calcularPendiente();
		
		int n = (int) Math.round (getIni().getY() - (m * (getIni().getX())));
		for (int x = getIndiceIni(); x < getIndiceFin() + 1; ++x) {
			getRecta().put(x, (int) Math.round(m * x + n));
		}
	}

}
