/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editorimag;

import static editorimag.EditorImag.activa;
import static editorimag.EditorImag.recortar;
import static editorimag.EditorImag.gestor_img;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;

/**
 *
 * @author jonay
 */
public class Barra_botones extends JToolBar {

    //modificados pero no pasaba nada sin el final

    private final Boton Abrir;
    private final Boton Guardar;
    
    private Boton Recortar;

    private Boton Espejo_H;
    private Boton Espejo_V;

    private Boton Rotar_90;
    private Boton Rotar_180;
    private Boton Rotar_270;
    private Boton Rotar;

    //Iconos
    private int tam = 30;
    private ImageIcon abrir = new ImageIcon("src/images/abrir.png");
    private ImageIcon guardar = new ImageIcon("src/images/guardar.png");
    private ImageIcon recortari = new ImageIcon("src/images/recortar.png");
    private ImageIcon espejoH = new ImageIcon("src/images/espejoH.png");
    private ImageIcon espejoV = new ImageIcon("src/images/espejoV.png");
    private ImageIcon rotar90 = new ImageIcon("src/images/rotar90.png");
    private ImageIcon rotar180 = new ImageIcon("src/images/rotar180.png");
    private ImageIcon rotar270 = new ImageIcon("src/images/rotar270.png");
    private ImageIcon rotar = new ImageIcon("src/images/rotar.png");

    public Barra_botones() {
        escalarImagenes();

        Abrir = new Boton("Abrir", abrir);
        this.add(Abrir);
        Guardar = new Boton("Guardar", guardar);
        this.add(Guardar);

        this.addSeparator();
        
        Recortar = new Boton("Recortar", recortari);
        this.add(Recortar);
        
        this.addSeparator();

        Espejo_H = new Boton("Espejo Horizontal", espejoH);
        this.add(Espejo_H);
        Espejo_V = new Boton("Espejo Vertical", espejoV);
        this.add(Espejo_V);

        this.addSeparator();

        Rotar_90 = new Boton("Rotar 90º", rotar90);
        this.add(Rotar_90);
        Rotar_180 = new Boton("Rotar 180º", rotar180);
        this.add(Rotar_180);
        Rotar_270 = new Boton("Rotar 270º", rotar270);
        this.add(Rotar_270);
        Rotar = new Boton("Rotar", rotar);
        this.add(Rotar);

        activarBotones();

    }

    private void escalarImagenes() {
        abrir = new ImageIcon(abrir.getImage().getScaledInstance(tam, tam, Image.SCALE_DEFAULT));
        guardar = new ImageIcon(guardar.getImage().getScaledInstance(tam, tam, Image.SCALE_DEFAULT));
        recortari = new ImageIcon(recortari.getImage().getScaledInstance(tam, tam, Image.SCALE_DEFAULT));
        espejoH = new ImageIcon(espejoH.getImage().getScaledInstance(tam, tam, Image.SCALE_DEFAULT));
        espejoV = new ImageIcon(espejoV.getImage().getScaledInstance(tam, tam, Image.SCALE_DEFAULT));
        rotar90 = new ImageIcon(rotar90.getImage().getScaledInstance(tam, tam, Image.SCALE_DEFAULT));
        rotar180 = new ImageIcon(rotar180.getImage().getScaledInstance(tam, tam, Image.SCALE_DEFAULT));
        rotar270 = new ImageIcon(rotar270.getImage().getScaledInstance(tam, tam, Image.SCALE_DEFAULT));
        rotar = new ImageIcon(rotar.getImage().getScaledInstance(tam, tam, Image.SCALE_DEFAULT));
    }

    private void activarBotones() {

        Abrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    gestor_img.nuevaImagen();
                } catch (IOException ex) {
                    Logger.getLogger(Barra_botones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Guardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    gestor_img.guardarImagen();
                } catch (IOException ex) {
                    Logger.getLogger(Barra_botones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Recortar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recortar = true;
            }
        });

        Espejo_H.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestor_img.getListaLan().get(activa).lanzar(32);
            }
        });

        Espejo_V.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestor_img.getListaLan().get(activa).lanzar(31);
            }
        });

        Rotar_90.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestor_img.getListaLan().get(activa).lanzar(34);
            }
        });

        Rotar_180.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestor_img.getListaLan().get(activa).lanzar(35);
            }
        });

        Rotar_270.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestor_img.getListaLan().get(activa).lanzar(36);
            }
        });
        
        Rotar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestor_img.getListaLan().get(activa).lanzar(37);
            }
        });

    }

}
