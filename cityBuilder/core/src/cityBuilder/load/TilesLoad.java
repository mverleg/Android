package cityBuilder.load;

import java.util.ArrayList;

import cityBuilder.objects.Tile;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TilesLoad extends Data
{
	private Tile[][] tile = new Tile[gridSizeWidth][gridSizeHeight];

	public ArrayList<Tile> tiles = new ArrayList<Tile>();

	public TilesLoad(TextureAtlas atlas)
	{

		for( int x = 0; x < gridSizeWidth; x++ )
		{
			for( int y = 0; y < gridSizeHeight; y++ )
			{
				tile[x][y] = new Tile( new Vector((-tileStartWidth + (tileWidth * y)), (tileStartHeight - (tileHeight * x))), atlas);
			}
		}

		setAttributes attributes = new setAttributes(tile);
		attributes.attributes();

		for( int i = 0; i < gridSizeWidth; i++ )
		{
			for( int j = 0; j < gridSizeHeight; j++ )
			{
				tiles.add( attributes.getTile(i, j) );
			}
		}
	}

	public ArrayList<Tile> getTiles()
	{
		return tiles;
	}
}