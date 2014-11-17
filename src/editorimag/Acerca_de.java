/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editorimag;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jonay
 */
public class Acerca_de extends JFrame {

    private JLabel Titulo;
    private JLabel Version;
    private JLabel Desarrolladores;
    private JLabel Des1;
    private JLabel Des2;
    
    public Font letra = new Font(Font.SERIF, Font.BOLD, 15);
    public Font letra1 = new Font(Font.SERIF, Font.BOLD, 25);
    
    public Acerca_de(){
        setTitle("Acerca de");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,1));
        add(panel);
        
        Titulo = new JLabel         ("       EditorImag      ");
        Titulo.setFont(letra1);
        Version = new JLabel        ("   Versión: 0.1");
        Version.setFont(letra);
        
        
        Desarrolladores = new JLabel("   Desarrolladores:   ");
        Des1 = new JLabel           ("     - Iván García Campos ");
        Des2 = new JLabel           ("     - Jonay Suárez Armas ");
        Desarrolladores.setFont(letra);
        Des1.setFont(letra);
        Des2.setFont(letra);
        
        
        panel.add(Titulo);
        panel.add(Version);
        panel.add(Desarrolladores);
        panel.add(Des1);
        panel.add(Des2);
        
        setVisible(true);
    }
    
}
