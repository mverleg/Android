package onzichtbaar.net.Android.CityBuilder.gameScreen;

import java.util.ArrayList;

import onzichtbaar.net.Android.CityBuilder.load.Skin_Setup;
import onzichtbaar.net.Android.CityBuilder.objects.Citizen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Renderer
{
	private int grass = 1;
	private int wood = 2;
	private int town = 3;
	private int sheep = 4;
	private int desert = 5;
	
	private  ArrayList<Citizen> citizens = new ArrayList<Citizen>();
	
	private int grey = 10;
	private int green = 11;
	private int blue = 12;
	
	public static final String LOG = Renderer.class.getSimpleName();

	private boolean touchedDown = false;
	private float touchX = 1200;
	private float touchY = 1200;
	
	private float distance = 0;
	
	private Stage stage;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Sprite sprite;
	
	private Skin skin;
	
	private float scaling_x = 0;
	private float scaling_y = 0;
	
	private Texture SquareTile;
	private Texture SquareTileGrass;
	private Texture SquareTileForest;
	private Texture SquareTileTown;
	private Texture SquareTileDesert;
	private Texture SquareTileSheep;
	private Texture SquareTileSelected;
	private Texture InfoBox;
	private Texture PopupBox;
	
	private TextureRegion SquareTileRegion;
	
	private boolean firstPress = true;
	private boolean firstPinch = true;
	
	private boolean[] tileTouch = new boolean[105];
	
	private float firstDistance = 0;
	private float firstX = 0;
	private float firstY = 0;
	
	private float offsetX = 0;
	private float offsetY = 0;
	
	private float zoomSpeed = 0;
	
	private float currentZoom = 10;
	
	private float currentX = 0;
	private float currentY = 0;
	
	private boolean down_pressed = false;
	private boolean up_pressed = false;
	private boolean info = false;
	private boolean drawCitizen = false;
	
	private TextField tileInfo;
	
	private Skin_Setup skin_setup;
	
	private GameScreen game;
	
	private Simulation simulation;
	
	private Image UserInterface;
	
	private TextureRegion SquareTileRegionSelected;
	private TextureRegion SquareTileRegionTown;
	private TextureRegion SquareTileRegionGrass;
	private TextureRegion SquareTileRegionWood;
	private TextureRegion SquareTileRegionSheep;
	private TextureRegion SquareTileRegionDesert;
	private TextureRegion region;
	
	public Renderer( GameScreen game, OrthographicCamera camera, Stage stage, SpriteBatch batch, ArrayList<Citizen> citizens )
	{
		this.citizens = citizens;
		this.game = game;
		SquareTile = new Texture( Gdx.files.internal( "SquareGreen.png" ));
		SquareTileTown = new Texture( Gdx.files.internal( "SquareTown.png" ));
		SquareTileSheep = new Texture( Gdx.files.internal( "SquareSheep.png" ));
		SquareTileDesert = new Texture( Gdx.files.internal( "SquareDesert.png" ));
		SquareTileGrass = new Texture( Gdx.files.internal( "SquareGrass.png" ));
		SquareTileForest = new Texture( Gdx.files.internal( "SquareForest.png" ));
		SquareTileSelected = new Texture( Gdx.files.internal( "SquareSelected.png" ));
		InfoBox = new Texture( Gdx.files.internal( "UITest.png" ));
		
		region = new TextureRegion(InfoBox, 0, 0, InfoBox.getWidth(), InfoBox.getHeight());
		SquareTileRegionSelected = new TextureRegion( SquareTileSelected, 0, 0, SquareTileSelected.getWidth(), SquareTileSelected.getHeight() );
		SquareTileRegionTown = new TextureRegion( SquareTileTown, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		SquareTileRegionGrass = new TextureRegion( SquareTileGrass, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		SquareTileRegionDesert = new TextureRegion( SquareTileDesert, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		SquareTileRegionWood = new TextureRegion( SquareTileForest, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		SquareTileRegionSheep = new TextureRegion( SquareTileSheep, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
		
		this.stage = stage;
		this.camera = camera;
		this.batch = batch;
		
		UserInterface = new Image(region);
		UserInterface.setPosition(530, 80);
		UserInterface.setScale(1f, 0.7f);
		UserInterface.setVisible( false );
		
		skin_setup = new Skin_Setup();
		skin = new Skin();
		skin = skin_setup.set_skin();
		
		tileInfo = new TextField( "", skin );
		tileInfo.setDisabled(true);
		tileInfo.setPosition( 600, 340 );
		tileInfo.setVisible( false );
		
		stage.addActor( UserInterface);
		stage.addActor( tileInfo );
		
		camera.zoom = currentZoom;

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		scaling_x = width/480;
		scaling_y = height/800;
		
	}
	
	 
	public void DrawImages( Simulation simulation )
	{
		this.simulation = simulation;
		this.touchedDown = simulation.getTouchedDown();
		this.touchX = simulation.getTouchX();
		this.touchY = simulation.getTouchY();
		this.down_pressed = simulation.getDown();
		this.up_pressed = simulation.getUp();
		this.distance = simulation.getDistance();
		this.tileTouch = simulation.getTileTouch();
		this.info = simulation.getInfo();
		
		MapScroll();
		MapZoom();
		
		camera.position.x = (currentX + offsetX);
		camera.position.y = (currentY - offsetY);
		
		simulation.updateScroll( camera.position.x, camera.position.y, camera.zoom );
		
		for( int i = 1; i < 10; i++ )
		{
			if( simulation.tiles.get(i).type == town )
			{
				batch.draw( SquareTileRegionTown, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
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
			
			if( simulation.tiles.get(i).colour == grey )
			{
			}
			else if( simulation.tiles.get(i).colour == green )
			{
				SquareTileRegion = new TextureRegion( SquareTile, 0, 0, SquareTile.getWidth(), SquareTile.getHeight() );
				batch.draw( SquareTileRegion, -SquareTile.getWidth()/2 + simulation.tiles.get(i).position.x, -SquareTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, SquareTile.getWidth(), SquareTile.getHeight(), 1, 1, 0, false);
			}
			
			
		}
		
		if( info )
		{
			UserInterface.setVisible( true );
			tileInfo.setVisible( true );
		}
		else
		{
			UserInterface.setVisible( false );
			tileInfo.setVisible( false );
		}
		
		for( int i = 0; i < simulation.tiles.size(); i++ )
		{
			if( tileTouch[i] )
			{
				displayInfoBox( simulation, i );
				batch.draw( SquareTileRegionSelected, (-SquareTileSelected.getWidth()/2 + simulation.tiles.get(i).position.x), (-SquareTileSelected.getHeight()/2 + simulation.tiles.get(i).position.y), 0, 0, SquareTileSelected.getWidth(), SquareTileSelected.getHeight(), 1, 1, 0, false);
			}
		}
		
	}
	
	private void displayInfoBox( Simulation simulation, int type )
	{
		if( simulation.tiles.get(type).type == grass )
		{
			tileInfo.setText( "grass" );
		}
		else if( simulation.tiles.get(type).type == wood )
		{
			tileInfo.setText( "wood" );
		}
		else if( simulation.tiles.get(type).type == town )
		{
			tileInfo.setText( "town" );
		}
		else if( simulation.tiles.get(type).type == sheep )
		{
			tileInfo.setText( "sheep" );
		}
		else if( simulation.tiles.get(type).type == desert )
		{
			tileInfo.setText( "desert" );
		}
		tileInfo.setVisible( true );
	}
	
	private void MapScroll()
	{
		if( touchedDown && firstPress )
		{
			firstPress = false;
			firstX = touchX;
			firstY = touchY;
		}
		else if( touchedDown && !firstPress )
		{
			offsetX = (firstX - touchX)*currentZoom;
			offsetY = (firstY - touchY)*currentZoom;
		}
		
		if( !touchedDown )
		{
			firstPress = true;
			currentX = (currentX + offsetX);
			currentY = (currentY - offsetY);
			offsetX = 0;
			offsetY = 0;
		}
		
	}
	
	private void MapZoom()
	{
		if( distance != 0 )
		{
			if( touchedDown && firstPinch )
			{
				firstPinch = false;
				
				firstDistance = distance;
			}
		}
		else
		{
			firstPinch = true;
		}
		
		
		if( touchedDown && !firstPinch )
		{
			zoomSpeed = firstDistance - distance;
			
			camera.zoom = (currentZoom + (zoomSpeed/100));
		}
		
		if( !touchedDown )
		{
			currentZoom = (currentZoom + (zoomSpeed/100));
			zoomSpeed = 0;
			firstPinch = true;
		}
		
		if( down_pressed )
		{
			camera.zoom += 1;
		}
		else if( up_pressed )
		{
			camera.zoom -= 1;
		}
	}
	
	public void dispose()
	{
		stage.clear();
	}
	
}