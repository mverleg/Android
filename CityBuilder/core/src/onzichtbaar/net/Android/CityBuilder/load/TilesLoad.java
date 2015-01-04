package onzichtbaar.net.Android.CityBuilder.load;

import java.util.ArrayList;

import onzichtbaar.net.Android.CityBuilder.objects.Tile;

public class TilesLoad 
{

	private int grass = 1;
	private int wood = 2;
	private int town = 3;
	private int sheep = 4;
	private int desert = 5;
	
	private int grey = 10;
	private int green = 11;
	private int blue = 12;
	
	private Tile[] tile = new Tile[105];
	public ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	public TilesLoad()
	{
		tile[0] = null;
		int x = 0;
		int y = 0;
		for( int i = 1; i < 10; i++ )
		{
			
			tile[i] = new Tile( new Vector((-512 + (512 * x)), (512 - (512 * y))), wood, green );
			
			if( (i % 3) == 0 )
			{
				x = x - 3;
				y++;
			}
			
			x++;
		}
		
		tile[1].setColour(grey);
		tile[1].setType(grass);
		tile[2].setColour(grey);
		tile[2].setType(grass);
		tile[3].setColour(grey);
		tile[3].setType(grass);
		tile[4].setColour(grey);
		tile[4].setType(grass);
		tile[5].setColour(green);
		tile[5].setType(town);
		tile[6].setColour(grey);
		tile[6].setType(grass);
		tile[7].setColour(grey);
		tile[7].setType(grass);
		tile[8].setColour(grey);
		tile[8].setType(grass);
		tile[9].setColour(grey);
		tile[9].setType(grass);
		
		for( int i = 0; i < 10; i++ )
		{
			tiles.add(tile[i] );
		}
	}

	public ArrayList<Tile> getTiles()
	{
		return tiles;
	}
}
