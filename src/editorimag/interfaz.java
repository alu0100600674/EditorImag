/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editorimag;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author jonay
 */
public class interfaz extends JFrame {
    
    public interfaz(){
        setTitle("EditorImag");
//        setLayout(null);
        //setVisible(true);
        setSize(800, 85);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setResizable(false);
//        setLocationRelativeTo(null);
        
        Menu menu_principal = new Menu();
        setJMenuBar(menu_principal);
        
        Barra_botones barra_botones = new Barra_botones();
        getContentPane().add(barra_botones, BorderLayout.NORTH);
        
       
    }
    
}
