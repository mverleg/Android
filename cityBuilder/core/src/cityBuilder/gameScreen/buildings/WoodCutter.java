package cityBuilder.gameScreen.buildings;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

import cityBuilder.load.Building;
import cityBuilder.load.Data;
import cityBuilder.objects.Tile;

public class WoodCutter extends Data implements Building {

	Tile[] woodcutterTiles;
	private int TileNumber;
	private int rotation;
	private TextureRegion SquareTileRegionWoodCutterBottomLeft;
	private TextureRegion SquareTileRegionWoodCutterBottomRight;
	private TextureRegion SquareTileRegionWoodCutterTopLeft;
	private TextureRegion SquareTileRegionWoodCutterTopRight;

	public WoodCutter(int TileNumber, int rotation, TextureAtlas atlas)
	{
		woodcutterTiles = new Tile[4];
		this.TileNumber = TileNumber;
		this.rotation = rotation;
		SquareTileRegionWoodCutterBottomLeft = atlas.findRegion("cubeDark");
		SquareTileRegionWoodCutterTopLeft = atlas.findRegion("cubeDark");
		SquareTileRegionWoodCutterTopRight = atlas.findRegion("cubeDark");
		SquareTileRegionWoodCutterBottomRight = atlas.findRegion("cubeDark");
	}

	@Override
	public void render(Batch batch, int position, float x, float y) {
		if( position == 0 ) {
			//bottom left
			batch.draw( SquareTileRegionWoodCutterBottomLeft, -32 + x , -32 + y, 32, 32, 64, 64, 1, 1, -(90 * rotation), false);
		} else if( position == 1 ) {
			//top left
			batch.draw( SquareTileRegionWoodCutterTopLeft, -32 + x , -32 + y, 32, 32, 64, 64, 1, 1, -(90 * rotation), false);
		} else if( position == 2 ) {
			//top right
			batch.draw( SquareTileRegionWoodCutterTopRight, -32 + x , -32 + y, 32, 32, 64, 64, 1, 1, -(90 * rotation), false);
		} else if( position == 3 ) {
			//bottom right
			batch.draw( SquareTileRegionWoodCutterBottomRight, -32 + x , -32 + y, 32, 32, 64, 64, 1, 1, -(90 * rotation), false);
		}
	}

	public void update()
	{
	}

	@Override
	public void buildBuilding(ArrayList<Tile> tiles, int selectedTile, int rotation) {
		tiles.get(selectedTile).setOccupiedWoodCutter(0, this);
		tiles.get(selectedTile-gridSizeWidth).setOccupiedWoodCutter(1, this);
		tiles.get(selectedTile-(gridSizeWidth-1)).setOccupiedWoodCutter(2, this);
		tiles.get(selectedTile+1).setOccupiedWoodCutter(3, this);

		woodcutterTiles[0] = tiles.get(selectedTile);
		woodcutterTiles[1] = tiles.get(selectedTile-gridSizeWidth);
		woodcutterTiles[2] = tiles.get(selectedTile-(gridSizeWidth-1));
		woodcutterTiles[3] = tiles.get(selectedTile+1);
	}
}