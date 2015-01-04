package onzichtbaar.net.Android.CityBuilder.objects;

import onzichtbaar.net.Android.CityBuilder.load.Vector;

public class Tile 
{
	public int type;
	public int colour;
	public final Vector position = new Vector( );
	
	public Tile( Vector position, int type, int colour )
    {
            this.position.set( position );
            this.type = type;
            this.colour = colour;
    }

}
