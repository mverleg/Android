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
	
	private int tileWidth = 64;
	private int tileHeight = 64;
	
	private Tile[] tile = new Tile[105];
	public ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	public TilesLoad()
	{
		tile[0] = null;
		int x = 0;
		int y = 0;
		for( int i = 1; i < 10; i++ )
		{
			
			tile[i] = new Tile( new Vector((-tileWidth + (tileWidth * x)), (tileHeight - (tileHeight * y))), wood, green );
			
			if( (i % 3) == 0 )
			{
				x = x - 3;
				y++;
			}
			
			x++;
		}
		
		tile[1].setAttributes( wood, grey );
		tile[2].setAttributes( wood, grey );
		tile[3].setAttributes( wood, grey );
		tile[4].setAttributes( wood, grey );
		tile[5].setAttributes( town, grey );
		tile[6].setAttributes( wood, grey );
		tile[7].setAttributes( wood, grey );
		tile[8].setAttributes( wood, grey );
		tile[9].setAttributes( wood, grey );
		
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
