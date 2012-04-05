package graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Map
{
    TileSet tileSet;

    private BufferedImage mapImage = null;

    int width;
    int height;
    int world;
    int floor;
    int x;
    int y;
    int smallKeysVariable;
    int tilesetId;
    String musicId;


    public Map( String map, TileSet tileSet )
            throws IOException
    {
        this.tileSet = tileSet;
        readMapInfo( map );
    }

    private void readMapInfo( String map )
            throws IOException
    {
        File file = new File( map );
        BufferedReader bufRdr = new BufferedReader( new FileReader( file ) );
        String line = null;
        line = bufRdr.readLine();
        StringTokenizer st = new StringTokenizer( line, "\t" );
        width = Integer.parseInt( st.nextToken() );
        height = Integer.parseInt( st.nextToken() );
        world = Integer.parseInt( st.nextToken() );
        floor = Integer.parseInt( st.nextToken() );
        x = Integer.parseInt( st.nextToken() );
        y = Integer.parseInt( st.nextToken() );
        smallKeysVariable = Integer.parseInt( st.nextToken() );
        tilesetId = Integer.parseInt( st.nextToken() );
        String musicId = st.nextToken();

        mapImage = new BufferedImage( width, height, tileSet.getTilesImage().getType() );
        Graphics2D g2d = mapImage.createGraphics();

        while ( ( line = bufRdr.readLine() ) != null )
        {
            try
            {

                st = new StringTokenizer( line, "\t" );
                int type = Integer.parseInt( st.nextToken() );
                int layer = Integer.parseInt( st.nextToken() );
                int x = Integer.parseInt( st.nextToken() );
                int y = Integer.parseInt( st.nextToken() );
                int foo = Integer.parseInt( st.nextToken() );
                int bar = Integer.parseInt( st.nextToken() );
                int tileId = Integer.parseInt( st.nextToken() );

                g2d.drawImage( tileSet.getTile( tileId ), null, x, y );
            }
            catch ( Exception e )
            {
            }
        }
        bufRdr.close();
    }

    public BufferedImage getMapImage()
    {
        return mapImage;
    }
}
