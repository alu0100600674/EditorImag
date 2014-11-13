/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herramientas;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author jonay
 */
public class Ecualizacion_histograma {
    
//    private int size;
    private int[] datos;
    private BufferedImage img;
    
    public Ecualizacion_histograma(BufferedImage tmp){
        img = tmp;
        
        cogerDatos();
        histograma();
        histogramaAcumulativo();
        ecualizacion();
    }
    
    private void cogerDatos(){
//        size = img.getWidth() * img.getHeight();
        datos = new int[256];
        
        //Inicializar datos a 0.
        for(int i = 0; i < 256; i++){
            datos[i] = 0;
        }
        
        //Obtener datos de nivel de color.
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j < img.getHeight(); j++){
                datos[img.getRGB(i, j)] += 1;
            }
        }

    }
    
    private void histograma(){
        JFrame vHist = new JFrame("Histograma");
                
        JFreeChart graf_hist = null;
        
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        
        for(int i = 0; i < 256; i++){
            if(datos[i] != 0) //Mostrando solo los que son distintos de 0.
                data.addValue(datos[i], "Histograma frec. abs.", Integer.toString(i));
        }
        
        graf_hist = ChartFactory.createBarChart("Histograma de frecuencias absolutas", 
                "Nivel RGB", "Número de píxeles", data, PlotOrientation.VERTICAL, false, false, false);
        
        ChartPanel panel = new ChartPanel(graf_hist);
        vHist.getContentPane().add(panel);
        vHist.pack();
        vHist.setVisible(true);
        
    }
    
    private void histogramaAcumulativo(){
        JFrame vHist = new JFrame("Histograma");
                
        JFreeChart graf_hist = null;
        
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        
        int datosAc[] = new int[256];
        
        datosAc[0] = datos[0];
        for(int i = 1; i < 256; i++){
            datosAc[i] = datos[i] + datosAc[i-1];
        }
        
        for(int i = 0; i < 256; i++){
            if(datos[i] != 0) //Mostrando solo los que son distintos de 0.
                data.addValue(datos[i], "Histograma frec. abs. ac.", Integer.toString(i));
        }
        
        graf_hist = ChartFactory.createBarChart("Histograma de frecuencias absolutas acumuladas", 
                "Nivel RGB", "Número de píxeles", data, PlotOrientation.VERTICAL, false, false, false);
        
        ChartPanel panel = new ChartPanel(graf_hist);
        vHist.getContentPane().add(panel);
        vHist.pack();
        vHist.setVisible(true);
        
    }
    
    private void ecualizacion(){
        int[] datos_ec = new int[256];
        int datos_ac[] = new int[256];
        
        datos_ac[0] = datos[0];
        for(int i = 1; i < 256; i++){
            datos_ac[i] = datos[i] + datos_ac[i-1];
        }
        
        int size = img.getWidth() * img.getHeight();
        int m = 256; //2^8, Imágenes de 8 bits.
        
        for(int i = 0; i < 256; i++){
            datos_ec[0] = Math.max(0, Math.round( ((m / size) * datos_ac[i]) - 1 ));
        }
        
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        
        for(int i = 0; i < 256; i++){
//            if(datos_ec[i] != 0) //Mostrando solo los que son distintos de 0.
                data.addValue(datos_ac[i], "Histograma ec.", Integer.toString(i));
        }
        
    }
    
}
