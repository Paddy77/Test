package graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class TileSet
{
    private BufferedImage tilesImage = null;

    private BufferedImage entitiesImage = null;

    private Color backgroundColor;

    private HashMap<Integer,TileInfo> tileInfos = new HashMap<Integer,TileInfo>();


    public TileSet( String name )
        throws IOException
    {
        tilesImage = ImageIO.read( new File( name + "_tiles.jpg" ) );
        entitiesImage = ImageIO.read( new File( name + "_entities.jpg" ) );
        readTileInfo( name + ".dat" );
    }

    private void readTileInfo( String infoPath )
        throws IOException
    {
        File file = new File( infoPath );
        BufferedReader bufRdr = new BufferedReader( new FileReader( file ) );
        String line = null;
        line = bufRdr.readLine();
        StringTokenizer st = new StringTokenizer( line, "\t" );
        int red = Integer.parseInt( st.nextToken() );
        int green = Integer.parseInt( st.nextToken() );
        int blue = Integer.parseInt( st.nextToken() );
        backgroundColor = new Color( red, green, blue );
        while ( ( line = bufRdr.readLine() ) != null )
        {
            st = new StringTokenizer( line, "\t" );
            int id = Integer.parseInt( st.nextToken() );
            TileInfo tileInfo = new TileInfo();
            tileInfo.id = id;
            tileInfo.obstacle = Integer.parseInt( st.nextToken() );
            tileInfo.animation = Integer.parseInt( st.nextToken() );
            tileInfo.default_layer = Integer.parseInt( st.nextToken() );

            if ( true /*tileInfo.animation != 1 && tileInfo.animation != 5 */ )
            {
                tileInfo.x = Integer.parseInt( st.nextToken() );
                tileInfo.y = Integer.parseInt( st.nextToken() );
                tileInfo.width = Integer.parseInt( st.nextToken() );
                tileInfo.height = Integer.parseInt( st.nextToken() );
                tileInfo.width = tileInfo.width <= 0 ? 1 : tileInfo.width;
                tileInfo.height = tileInfo.height <= 0 ? 1 : tileInfo.height;
            }
            else
            {
                tileInfo.sequence = Integer.parseInt( st.nextToken() );
                tileInfo.width = Integer.parseInt( st.nextToken() );
                tileInfo.height = Integer.parseInt( st.nextToken() );
                tileInfo.x1 = Integer.parseInt( st.nextToken() );
                tileInfo.y1 = Integer.parseInt( st.nextToken() );
                tileInfo.x2 = Integer.parseInt( st.nextToken() );
                tileInfo.y2 = Integer.parseInt( st.nextToken() );
                tileInfo.x3 = Integer.parseInt( st.nextToken() );
                tileInfo.y3 = Integer.parseInt( st.nextToken() );
            }
            tileInfos.put( id, tileInfo );
        }
        bufRdr.close();
    }

    public BufferedImage getTile( int id )
    {
        TileInfo tileInfo = tileInfos.get( id );
        if ( tileInfo == null || tileInfo.width <= 0 || tileInfo.height <= 0 )
        {
            System.out.println( id );
        }
        return tilesImage.getSubimage( tileInfo.x, tileInfo.y, tileInfo.width, tileInfo.height );
    }

    public BufferedImage getTilesImage()
    {
        return tilesImage;
    }

    class TileInfo
    {
        int id;
        int obstacle;
        int animation;
        int default_layer;
        int x;
        int y;
        int width;
        int height;
        int sequence;
        int x1;
        int y1;
        int x2;
        int y2;
        int x3;
        int y3;
    }
}
