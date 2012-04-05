import graphics.TileSet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test
{

    public Test()
        throws Exception
    {
         JFrame window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setBounds(30, 30, 300, 300);
            window.getContentPane().add(new MyCanvas());
            window.setVisible(true);
    }

     class MyCanvas extends JComponent
     {
        BufferedImage img;

        MyCanvas()
            throws Exception
        {
            TileSet tileSet = new TileSet( "res/tileset0000_tiles.jpg", "res/tileset0000.dat" );
            img = tileSet.getTile( 2 );
        }

        public void paint( Graphics g )
        {
            Graphics2D g2 = ( Graphics2D ) g;
            g2.drawImage( img, 10, 10, this );
            g2.finalize();
        }
    }

        public static void main( String[] args )
                throws Exception
        {
            new Test();
        }
}
