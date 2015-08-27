package CityBuilder.objects.attributes;

import CityBuilder.objects.Tile;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WoodCutterTex
{
	private Tile tile;
	private int position = 0;
	
	public WoodCutterTex( Tile tile, int position )
	{
		this.tile = tile;
		this.position = position;
	}
	
	public void draw( Batch batch, TextureAtlas atlas )
	{
		TextureRegion SquareTileRegionWoodCutter = atlas.findRegion("farmbuilding1"); 
		if( position == 0 )
		{
			//bottom left
			SquareTileRegionWoodCutter = atlas.findRegion("farmbuilding1");
		}
		else if( position == 1 )
		{
			//top left
			SquareTileRegionWoodCutter = atlas.findRegion("farmbuilding2");
		}
		else if( position == 2 )
		{
			//top right
			SquareTileRegionWoodCutter = atlas.findRegion("farmbuilding3");
		}
		else if( position == 3 )
		{
			//bottom right
			SquareTileRegionWoodCutter = atlas.findRegion("farmbuilding4");
		}
		batch.draw( SquareTileRegionWoodCutter, -32 + tile.getPosition().x , -32 + tile.getPosition().y, 32, 32, 64, 64, 1, 1, -90, false);
		
		
	
	}
}