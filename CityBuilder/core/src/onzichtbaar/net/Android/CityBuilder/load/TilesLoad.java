package onzichtbaar.net.Android.CityBuilder.load;

import java.util.ArrayList;

import onzichtbaar.net.Android.CityBuilder.objects.Tile;

public class TilesLoad extends Data
{
	private Tile[] tile = new Tile[105];
	public ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	public TilesLoad()
	{
		tile[0] = null;
		int x = 0;
		int y = 0;
		for( int i = 1; i < (numberOfTiles + 1); i++ )
		{
			
			tile[i] = new Tile( new Vector((-tileStartWidth + (tileWidth * x)), (tileStartHeight - (tileHeight * y))), wood, unavailable );
			
			if( (i % gridSize) == 0 )
			{
				x = x - gridSize;
				y++;
			}
			
			x++;
		}
		
		tile[7].setAttributes( wood, available );
		tile[8].setAttributes( wood, available );
		tile[9].setAttributes( wood, available );
		tile[12].setAttributes( wood, available );
		tile[13].setAttributes( town, available );
		tile[14].setAttributes( wood, available );
		tile[17].setAttributes( wood, available );
		tile[18].setAttributes( wood, available );
		tile[19].setAttributes( wood, available );
		
		for( int i = 0; i < (numberOfTiles + 1); i++ )
		{
			tiles.add(tile[i] );
		}
	}

	public ArrayList<Tile> getTiles()
	{
		return tiles;
	}
}
