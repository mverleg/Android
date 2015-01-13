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
	private Texture SquareTileDirt;
	private Texture SquareTileForest;
	private Texture SquareTileDesert;
	private Texture SquareTileSheep;
	private Texture SquareTileSelected;
	private Texture SquareUnavailable;
	private Texture SquareTileDwayne;
	private Texture SquareTileWallFull;
	private Texture SquareTileWallTriple;
	private Texture SquareTileWallDouble;
	private Texture SquareTileWallCorner;
	private Texture SquareTileWallSides;
	private Texture SquareTileWallSingle;
	private Texture SquareTileHouse;
	private Texture InfoBox;
	
	private TextureRegion SquareTileRegionSelected;
	private TextureRegion SquareTileRegionUnavailable;
	private TextureRegion SquareTileRegionWallFull;
	private TextureRegion SquareTileRegionWallTriple;
	private TextureRegion SquareTileRegionWallDouble;
	private TextureRegion SquareTileRegionWallCorner;
	private TextureRegion SquareTileRegionWallSides;
	private TextureRegion SquareTileRegionWallSingle;
	private TextureRegion SquareTileRegionHouse;
	private TextureRegion SquareTileRegionGrass;
	private TextureRegion SquareTileRegionDirt;
	private TextureRegion SquareTileRegionWood;
	private TextureRegion SquareTileRegionSheep;
	private TextureRegion SquareTileRegionDesert;
	private TextureRegion SquareTileRegionDwayne;
	private TextureRegion region;
	
	private boolean[] tileTouch = new boolean[105];
	
	private int rotate = 0;
	
	public DrawTiles()
	{		
		infoBoxDisplay = new DisplayInfoBox();
		
		SquareTile = new Texture( Gdx.files.internal( "images/SquareGreenSmall.png" ));
		SquareUnavailable = new Texture( Gdx.files.internal( "images/SquareGreySmall.png" ));
		SquareTileSheep = new Texture( Gdx.files.internal( "images/SquareSheep.png" ));
		SquareTileDesert = new Texture( Gdx.files.internal( "images/SquareDesert.png" ));
		SquareTileGrass = new Texture( Gdx.files.internal( "images/Grass.png" ));
		SquareTileDirt =  new Texture( Gdx.files.internal( "images/dirt.png" ));
		SquareTileForest = new Texture( Gdx.files.internal( "images/pinetree.png" ));
		SquareTileSelected = new Texture( Gdx.files.internal( "images/SquareSelectedSmall.png" ));
		SquareTileDwayne = new Texture( Gdx.files.internal( "images/Rock3.png" ));
		SquareTileWallFull = new Texture( Gdx.files.internal( "images/Village-wall1.png" ));
		SquareTileWallTriple = new Texture( Gdx.files.internal( "images/muur-noord2.png" ));
		SquareTileWallDouble = new Texture( Gdx.files.internal( "images/muur-hoek-linksboven.png" ));
		SquareTileWallCorner = new Texture( Gdx.files.internal( "images/muur-corner.png" ));
		SquareTileWallSides = new Texture( Gdx.files.internal( "images/muur-sides.png" ));
		SquareTileWallSingle = new Texture( Gdx.files.internal( "images/muur-single.png" ));
		SquareTileHouse = new Texture( Gdx.files.internal( "images/HouseBrown.png" ));
		InfoBox = new Texture( Gdx.files.internal( "images/UITest.png" ));
		
		SquareTileRegionSelected = new TextureRegion( SquareTileSelected, 0, 0, SquareTileSelected.getWidth(), SquareTileSelected.getHeight() );
		SquareTileRegionWallFull = new TextureRegion( SquareTileWallFull, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		SquareTileRegionWallTriple = new TextureRegion( SquareTileWallTriple, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		SquareTileRegionWallDouble = new TextureRegion( SquareTileWallDouble, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		SquareTileRegionWallCorner = new TextureRegion( SquareTileWallCorner, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		SquareTileRegionWallSides = new TextureRegion( SquareTileWallSides, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		SquareTileRegionWallSingle = new TextureRegion( SquareTileWallSingle, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		SquareTileRegionHouse = new TextureRegion( SquareTileHouse, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		SquareTileRegionGrass = new TextureRegion( SquareTileGrass, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		SquareTileRegionDirt = new TextureRegion( SquareTileDirt, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
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
			batch.draw( SquareTileRegionGrass, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, -90, false);
		
			if( simulation.tiles.get(i).type == town )
			{
				batch.draw( SquareTileRegionHouse, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, -90, false);
				//batch.draw( SquareTileRegionWall, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
			}
			else if( simulation.tiles.get(i).type == grass )
			{
			}
			else if( simulation.tiles.get(i).type == desert )
			{
				batch.draw( SquareTileRegionDirt, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, -90, false);
			}
			else if( simulation.tiles.get(i).type == wood )
			{
				batch.draw( SquareTileRegionWood, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, -90, false);
			}
			else if( simulation.tiles.get(i).type == sheep )
			{
				batch.draw( SquareTileRegionSheep, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, -90, false);
			}
			else if( simulation.tiles.get(i).type == dwayne )
			{
				batch.draw( SquareTileRegionDwayne, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, -90, false);
			}
			
			if( simulation.tiles.get(i).colour == available )
			{
			}
			else if( simulation.tiles.get(i).colour == unavailable )
			{
				SquareTileRegionUnavailable = new TextureRegion( SquareUnavailable, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
				batch.draw( SquareTileRegionUnavailable, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, -90, false);
			}
			
			if( simulation.tiles.get(i).wall )
			{
				boolean wallTop = false;
				boolean wallLeft = false;
				boolean wallRight = false;
				boolean wallBottom = false;
				boolean wallTopLeft = false;
				boolean wallTopRight = false;
				boolean wallBottomLeft = false;
				boolean wallBottomRight = false;
				
				//test to find a wall above the tile
				if( simulation.tiles.get(i - gridSizeWidth).wall )
				{
					wallTop = true;
				}
				
				if( simulation.tiles.get(i-(gridSizeWidth+1)).wall )
				{
					wallTopLeft = true;
				}
				
				if( simulation.tiles.get(i-(gridSizeWidth-1)).wall )
				{
					wallTopRight = true;
				}

				if( simulation.tiles.get(i - 1).wall )
				{
					wallLeft = true;
				}

				if( simulation.tiles.get(i + 1).wall )
				{
					wallRight = true;
				}
				
				if( simulation.tiles.get(i + gridSizeWidth).wall )
				{
					wallBottom = true;
				}
				
				if( simulation.tiles.get(i + (gridSizeWidth-1)).wall )
				{
					wallBottomLeft = true;
				}
				
				if( simulation.tiles.get(i + (gridSizeWidth+1)).wall )
				{
					wallBottomRight = true;
				}
				
				if( !wallTop && !wallLeft && !wallRight && !wallBottom )
				{	
					//no wall around it
					batch.draw( SquareTileRegionWallFull, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
				}
				else if( wallTop && !wallLeft && !wallRight && !wallBottom )
				{
					//only wall above
					batch.draw( SquareTileRegionWallTriple, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 90, false);
				}
				else if( !wallTop && !wallLeft && !wallRight && wallBottom )
				{
					//only wall below
					batch.draw( SquareTileRegionWallTriple, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, -90, false);
				}
				else if( !wallTop && wallLeft && !wallRight && !wallBottom )
				{
					//only wall to the left
					batch.draw( SquareTileRegionWallTriple, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 180, false);
				}
				else if( !wallTop && !wallLeft && wallRight && !wallBottom )
				{
					//only wall to the right
					batch.draw( SquareTileRegionWallTriple, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
				}
				else if( wallTop && wallLeft && !wallRight && !wallBottom )
				{
					//wall to the top and the left
					if( !wallTopLeft )
					{
						batch.draw( SquareTileRegionWallCorner, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 90, false);
					}
					batch.draw( SquareTileRegionWallDouble, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 90, false);
				}
				else if( wallTop && !wallLeft && wallRight && !wallBottom )
				{
					//wall to the top and the right
					if( !wallTopRight )
					{
						batch.draw( SquareTileRegionWallCorner, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
					}
					batch.draw( SquareTileRegionWallDouble, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
				}
				else if( wallTop && !wallLeft && !wallRight && wallBottom )
				{
					//wall to the top and the bottom
					batch.draw( SquareTileRegionWallSides, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 90, false);
				}
				else if( !wallTop && wallLeft && wallRight && !wallBottom )
				{
					//wall to the left and the right
					batch.draw( SquareTileRegionWallSides, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
				}
				else if( !wallTop && wallLeft && !wallRight && wallBottom )
				{
					//wall to the left and the bottom
					if( !wallBottomLeft )
					{
						batch.draw( SquareTileRegionWallCorner, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 180, false);
					}
					batch.draw( SquareTileRegionWallDouble, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 180, false);
				}
				else if( !wallTop && !wallLeft && wallRight && wallBottom )
				{
					//wall to the right and the bottom
					if( !wallBottomRight )
					{
						batch.draw( SquareTileRegionWallCorner, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, -90, false);
					}
					batch.draw( SquareTileRegionWallDouble, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, -90, false);
				}
				else if( wallTop && wallLeft && wallRight && !wallBottom )
				{
					//wall to the top, to the left and to the right
					if( !wallTopLeft )
					{
						batch.draw( SquareTileRegionWallCorner, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 90, false);
					}
					if( !wallTopRight )
					{
						batch.draw( SquareTileRegionWallCorner, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
					}
					batch.draw( SquareTileRegionWallSingle, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
				}
				else if( wallTop && wallLeft && !wallRight && wallBottom )
				{
					//wall to the top, to the left and to the bottom
					if( !wallTopLeft )
					{
						batch.draw( SquareTileRegionWallCorner, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 90, false);
					}
					if( !wallBottomLeft )
					{
						batch.draw( SquareTileRegionWallCorner, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 180, false);
					}
					batch.draw( SquareTileRegionWallSingle, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 90, false);
				}
				else if( !wallTop && wallLeft && wallRight && wallBottom )
				{
					//wall to the top, to the left and to the right
					if( !wallBottomRight )
					{
						batch.draw( SquareTileRegionWallCorner, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, -90, false);
					}
					if( !wallBottomLeft )
					{
						batch.draw( SquareTileRegionWallCorner, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 180, false);
					}
					batch.draw( SquareTileRegionWallSingle, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 180, false);
				}
				else if( wallTop && !wallLeft && wallRight && wallBottom )
				{
					//wall to the top, to the right and to the bottom
					if( !wallTopRight )
					{
						batch.draw( SquareTileRegionWallCorner, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
					}
					if( !wallBottomRight )
					{
						batch.draw( SquareTileRegionWallCorner, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, -90, false);
					}
					batch.draw( SquareTileRegionWallSingle, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, -90, false);
				}
				else if( wallTop && wallLeft && wallRight && wallBottom )
				{
					if( !wallTopLeft )
					{
						batch.draw( SquareTileRegionWallCorner, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 90, false);
					}
					if( !wallTopRight )
					{
						batch.draw( SquareTileRegionWallCorner, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
					}
					if( !wallBottomLeft )
					{
						batch.draw( SquareTileRegionWallCorner, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 180, false);
					}
					if( !wallBottomRight )
					{
						batch.draw( SquareTileRegionWallCorner, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, -90, false);
					}
					//walls everywhere
				}
				else
				{
					batch.draw( SquareTileRegionWallFull, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, SquareTile.getWidth()/2, SquareTile.getHeight()/2, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
				}
			}
		}
	}
	
	public void drawSelected( Simulation simulation, Batch batch, int tileNumber )
	{
		batch.draw( SquareTileRegionSelected, (-SquareTileSelected.getWidth()/2 + simulation.tiles.get(tileNumber).position.x), (-SquareTileSelected.getHeight()/2 + simulation.tiles.get(tileNumber).position.y), 0, 0, SquareTileSelected.getWidth(), SquareTileSelected.getHeight(), 1, 1, 0, false);
	}

}
