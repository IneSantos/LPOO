package pacman.menus;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class Gifpaneel extends JPanel{
    private ImageIcon gif, animatedGif;

    public Gifpaneel() {
        gif = new ImageIcon( "pacman.gif" );
        animatedGif = new ImageIcon( "/Pacman/src/images/pacman.gif" );
    }       

    public void paintComponent( Graphics g ){
        super.paintComponent( g );

        gif.paintIcon( this, g, 100, 100 );
        animatedGif.paintIcon ( this, g, 250, 100 );
    }
}