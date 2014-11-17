/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package herramientas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
    
    
    public int get_datos(int i){
            return datos[i];
    }
    
    public Ecualizacion_histograma(BufferedImage tmp){
        img = tmp;
        
        cogerDatos();
        controles();
//        histograma();
//        histogramaAcumulativo();
//        ecualizacion();
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
                Color c = new Color(img.getRGB(i, j));
                
                datos[c.getRed()] += 1;
//                System.out.println(i + "," + j);
//                System.out.println(img.getRGB(i, j));
//                System.out.println(c);
            }
        }

    }
    
    private void histograma(){
        JFrame vHist = new JFrame("Histograma");
                
        JFreeChart graf_hist = null;
        
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        
        for(int i = 0; i < 256; i++){
//            if(datos[i] != 0) //Mostrando solo los que son distintos de 0.
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
        JFrame vHist = new JFrame("Histograma Acumulado");
                
        JFreeChart graf_hist = null;
        
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        
        int datosAc[] = new int[256];
        
        for(int i = 0; i < 256; i++){
            datosAc[i] = 0;
        }
        
        datosAc[0] = datos[0];
        for(int i = 1; i < 256; i++){
            datosAc[i] = datos[i] + datosAc[i-1];
        }
        
        for(int i = 0; i < 256; i++){
//            if(datos[i] != 0) //Mostrando solo los que son distintos de 0.
                data.addValue(datosAc[i], "Histograma frec. abs. ac.", Integer.toString(i));
        }
        
        graf_hist = ChartFactory.createBarChart("Histograma de frecuencias absolutas acumuladas", 
                "Nivel RGB", "Número de píxeles", data, PlotOrientation.VERTICAL, false, false, false);
        
        ChartPanel panel = new ChartPanel(graf_hist);
        vHist.getContentPane().add(panel);
        vHist.pack();
        vHist.setVisible(true);
        
    }
    
    private void ecualizacion(){
        JFrame vHist = new JFrame("Histograma Ecualizado");
                
        JFreeChart graf_hist = null;
        
        int[] datos_ec = new int[256];
        int datos_ac[] = new int[256];
        
        datos_ac[0] = datos[0];
        for(int i = 1; i < 256; i++){
            datos_ac[i] = datos[i] + datos_ac[i-1];
        }
        
        int size = img.getWidth() * img.getHeight();
        int m = 256; //2^8, Imágenes de 8 bits.
        
        for(int i = 0; i < 256; i++){
//            datos_ec[i] = Math.max(0, Math.round( (  (m / size) * datos_ac[i])) - 1 ));
            
            datos_ec[i] = Math.max(0, (Math.round((m / size) * datos_ac[i]) - 1));
            System.out.println((Math.round((m / size) * datos_ac[i]) - 1));
            System.out.println(datos_ec[i]);
        }
        
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        
        for(int i = 0; i < 256; i++){
//            if(datos_ec[i] != 0) //Mostrando solo los que son distintos de 0.
                data.addValue(datos_ec[i], "Histograma ec.", Integer.toString(i));
        }
        
        graf_hist = ChartFactory.createBarChart("Histograma ecualizado", 
                "Nivel RGB", "Número de píxeles", data, PlotOrientation.VERTICAL, false, false, false);
        
        ChartPanel panel = new ChartPanel(graf_hist);
        vHist.getContentPane().add(panel);
        vHist.pack();
        vHist.setVisible(true);
        
    }
    
    private void controles(){
        JFrame v = new JFrame();
        v.setSize(220, 120);
        v.setVisible(true);
        v.setTitle("Controles");
        v.setResizable(false);
        
        JPanel panel = new JPanel();
        v.add(panel);
        
        JButton Hist = new JButton("Histograma");
        Hist.setSize(220, 30);
        panel.add(Hist);
        JButton Hist_ac = new JButton("Histograma Acumulativo");
        panel.add(Hist_ac);
        JButton Hist_ec = new JButton("Histograma Ecualizado");
        panel.add(Hist_ec);
        
        Hist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                histograma();
            }
        });
        
        Hist_ac.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                histogramaAcumulativo();
            }
        });
        
        Hist_ec.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ecualizacion();
            }
        });
        
    }
    
}
