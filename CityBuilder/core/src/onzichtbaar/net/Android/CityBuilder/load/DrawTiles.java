package onzichtbaar.net.Android.CityBuilder.load;

import onzichtbaar.net.Android.CityBuilder.gameScreen.Simulation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class DrawTiles extends Data
{
	private DisplayInfoBox infoBoxDisplay;
	
	private Texture SquareTile;
	private Texture SquareTileGrass;
	private Texture SquareTileForest;
	private Texture SquareTileDesert;
	private Texture SquareTileSheep;
	private Texture SquareTileSelected;
	private Texture SquareUnavailable;
	private Texture SquareTileDwayne;
	private Texture SquareTileWall;
	private Texture SquareTileHouse;
	private Texture InfoBox;
	
	private TextureRegion SquareTileRegionSelected;
	private TextureRegion SquareTileRegionUnavailable;
	private TextureRegion SquareTileRegionWall;
	private TextureRegion SquareTileRegionHouse;
	private TextureRegion SquareTileRegionGrass;
	private TextureRegion SquareTileRegionWood;
	private TextureRegion SquareTileRegionSheep;
	private TextureRegion SquareTileRegionDesert;
	private TextureRegion SquareTileRegionDwayne;
	private TextureRegion region;
	
	private boolean[] tileTouch = new boolean[105];
	
	public DrawTiles()
	{		
		infoBoxDisplay = new DisplayInfoBox();
		
		SquareTile = new Texture( Gdx.files.internal( "SquareGreenSmall.png" ));
		SquareUnavailable = new Texture( Gdx.files.internal( "SquareGreySmall.png" ));
		SquareTileSheep = new Texture( Gdx.files.internal( "SquareSheep.png" ));
		SquareTileDesert = new Texture( Gdx.files.internal( "SquareDesert.png" ));
		SquareTileGrass = new Texture( Gdx.files.internal( "SquareGrass.png" ));
		SquareTileForest = new Texture( Gdx.files.internal( "Boom-top-down.png" ));
		SquareTileSelected = new Texture( Gdx.files.internal( "SquareSelectedSmall.png" ));
		SquareTileDwayne = new Texture( Gdx.files.internal( "Dwayne.png" ));
		SquareTileWall = new Texture( Gdx.files.internal( "Village-wall1.png" ));
		SquareTileHouse = new Texture( Gdx.files.internal( "HouseBrown.png" ));
		InfoBox = new Texture( Gdx.files.internal( "UITest.png" ));
		
		SquareTileRegionSelected = new TextureRegion( SquareTileSelected, 0, 0, SquareTileSelected.getWidth(), SquareTileSelected.getHeight() );
		SquareTileRegionWall = new TextureRegion( SquareTileWall, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		SquareTileRegionHouse = new TextureRegion( SquareTileHouse, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		SquareTileRegionGrass = new TextureRegion( SquareTileGrass, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		SquareTileRegionDesert = new TextureRegion( SquareTileDesert, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		SquareTileRegionWood = new TextureRegion( SquareTileForest, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		SquareTileRegionSheep = new TextureRegion( SquareTileSheep, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		SquareTileRegionDwayne = new TextureRegion( SquareTileDwayne, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		
		
		
		region = new TextureRegion(InfoBox, 0, 0, InfoBox.getWidth(), InfoBox.getHeight());
		
	}
	

	public void fillTiles( Simulation simulation, Batch batch )
	{
		tileTouch = simulation.getTileTouch();
		
		for( int i = 1; i < (numberOfTiles + 1); i++ )
		{
			if( simulation.tiles.get(i).type == town )
			{
				batch.draw( SquareTileRegionHouse, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
				//batch.draw( SquareTileRegionWall, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
			}
			else if( simulation.tiles.get(i).type == grass )
			{
				batch.draw( SquareTileRegionGrass, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
			}
			else if( simulation.tiles.get(i).type == desert )
			{
				batch.draw( SquareTileRegionDesert, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
			}
			else if( simulation.tiles.get(i).type == wood )
			{
				batch.draw( SquareTileRegionWood, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
			}
			else if( simulation.tiles.get(i).type == sheep )
			{
				batch.draw( SquareTileRegionSheep, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
			}
			else if( simulation.tiles.get(i).type == dwayne )
			{
				batch.draw( SquareTileRegionDwayne, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);	
			}
			
			if( simulation.tiles.get(i).colour == available )
			{
			}
			else if( simulation.tiles.get(i).colour == unavailable )
			{
				SquareTileRegionUnavailable = new TextureRegion( SquareUnavailable, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
				batch.draw( SquareTileRegionUnavailable, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
			}
			
			if( simulation.tiles.get(i).wall )
			{
				batch.draw( SquareTileRegionWall, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
			}
			
		}
	}
	
	public void drawSelected( Simulation simulation, Batch batch, int tileNumber )
	{
		batch.draw( SquareTileRegionSelected, (-SquareTileSelected.getWidth()/2 + simulation.tiles.get(tileNumber).position.x), (-SquareTileSelected.getHeight()/2 + simulation.tiles.get(tileNumber).position.y), 0, 0, SquareTileSelected.getWidth(), SquareTileSelected.getHeight(), 1, 1, 0, false);
	}

}
