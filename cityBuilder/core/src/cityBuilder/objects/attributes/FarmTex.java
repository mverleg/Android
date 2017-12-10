package cityBuilder.objects.attributes;

import cityBuilder.objects.Tile;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FarmTex {
	private Tile tile;
	private int position;
	private int rotation;
	private TextureRegion SquareTileRegionFarmBottomLeft;
	private TextureRegion SquareTileRegionFarmBottomRight;
	private TextureRegion SquareTileRegionFarmTopLeft;
	private TextureRegion SquareTileRegionFarmTopRight;

	public FarmTex( Tile tile, int position, int rotation, TextureAtlas atlas ) {
		this.tile = tile;
		this.position = position;
		this.rotation = rotation;
		SquareTileRegionFarmBottomLeft = atlas.findRegion("cubeLight");
		SquareTileRegionFarmTopLeft = atlas.findRegion("cubeLight");
		SquareTileRegionFarmTopRight = atlas.findRegion("cubeLight");
		SquareTileRegionFarmBottomRight = atlas.findRegion("cubeLight");
	}

	public void draw( Batch batch ) {
		if( position == 0 ) {
			//bottom left
			batch.draw( SquareTileRegionFarmBottomLeft, -32 + tile.getPosition().x , -32 + tile.getPosition().y, 32, 32, 64, 64, 1, 1, -(90 * rotation), false);
		} else if( position == 1 ) {
			//top left	
			batch.draw( SquareTileRegionFarmTopLeft, -32 + tile.getPosition().x , -32 + tile.getPosition().y, 32, 32, 64, 64, 1, 1, -(90 * rotation), false);
		} else if( position == 2 ) {
			//top right	
			batch.draw( SquareTileRegionFarmTopRight, -32 + tile.getPosition().x , -32 + tile.getPosition().y, 32, 32, 64, 64, 1, 1, -(90 * rotation), false);
		} else if( position == 3 ) {
			//bottom right	
			batch.draw( SquareTileRegionFarmBottomRight, -32 + tile.getPosition().x , -32 + tile.getPosition().y, 32, 32, 64, 64, 1, 1, -(90 * rotation), false);
		}
	}
}