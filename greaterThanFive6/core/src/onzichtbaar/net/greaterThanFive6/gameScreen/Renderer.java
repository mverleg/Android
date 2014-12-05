package onzichtbaar.net.greaterThanFive6.gameScreen;

import java.util.ArrayList;

import onzichtbaar.net.greaterThanFive6.load.Skin_Setup;
import onzichtbaar.net.greaterThanFive6.objects.Citizen;

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
	
	private Texture HexTile;
	private Texture HexTileGrass;
	private Texture HexTileForest;
	private Texture HexTileTown;
	private Texture HexTileDesert;
	private Texture HexTileSheep;
	private Texture HexTileSelected;
	private Texture InfoBox;
	private Texture PopupBox;
	
	private TextureRegion HexTileRegion;
	
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
	
	private TextField score;
	private TextField citizenInfo;
	private TextButton changeCitizens;
	private TextButton[] citizenButton = new TextButton[30];
	private ScrollPane scroll;
	
	private Skin_Setup skin_setup;
	
	private GameScreen game;
	
	private Simulation simulation;
	
	private Image UserInterface;
	
	private TextureRegion HexTileRegionSelected;
	private TextureRegion HexTileRegionTown;
	private TextureRegion HexTileRegionGrass;
	private TextureRegion HexTileRegionWood;
	private TextureRegion HexTileRegionSheep;
	private TextureRegion HexTileRegionDesert;
	private TextureRegion region;
	
	public Renderer( GameScreen game, OrthographicCamera camera, Stage stage, SpriteBatch batch, ArrayList<Citizen> citizens )
	{
		this.citizens = citizens;
		this.game = game;
		HexTile = new Texture( Gdx.files.internal( "hexagon_green.png" ));
		HexTileTown = new Texture( Gdx.files.internal( "hexagon_town.png" ));
		HexTileSheep = new Texture( Gdx.files.internal( "hexagon_sheep.png" ));
		HexTileDesert = new Texture( Gdx.files.internal( "hexagon_desert.png" ));
		HexTileGrass = new Texture( Gdx.files.internal( "hexagon_grass.png" ));
		HexTileForest = new Texture( Gdx.files.internal( "hexagon_forest.png" ));
		HexTileSelected = new Texture( Gdx.files.internal( "hexagon_paint_selected.png" ));
		InfoBox = new Texture( Gdx.files.internal( "UITest.png" ));
		
		region = new TextureRegion(InfoBox, 0, 0, InfoBox.getWidth(), InfoBox.getHeight());
		HexTileRegionSelected = new TextureRegion( HexTileSelected, 0, 0, HexTileSelected.getWidth(), HexTileSelected.getHeight() );
		HexTileRegionTown = new TextureRegion( HexTileTown, 0, 0, HexTile.getWidth(), HexTile.getHeight() );
		HexTileRegionGrass = new TextureRegion( HexTileGrass, 0, 0, HexTile.getWidth(), HexTile.getHeight() );
		HexTileRegionDesert = new TextureRegion( HexTileDesert, 0, 0, HexTile.getWidth(), HexTile.getHeight() );
		HexTileRegionWood = new TextureRegion( HexTileForest, 0, 0, HexTile.getWidth(), HexTile.getHeight() );
		HexTileRegionSheep = new TextureRegion( HexTileSheep, 0, 0, HexTile.getWidth(), HexTile.getHeight() );
		
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
		
		score = new TextField( "", skin );
		score.setDisabled(true);
		score.setPosition( 600, 340 );
		score.setVisible( false );
		
		citizenInfo = new TextField( "name: ", skin );
		citizenInfo.setDisabled( true );
		citizenInfo.setBounds(120, 300, 400, 30);
		citizenInfo.setVisible( false );
		
		changeCitizens = new TextButton( "Citizens", skin );
		changeCitizens.setPosition( 0, 0 );
		
		Table content = new Table( skin );
		for( int i = 1; i < citizens.size(); i++ )
		{
			citizenButton[i] = new TextButton( i + " " + citizens.get(i).name, skin );
			citizenButton[i].addListener(citizenSelectListener);
			citizenButton[i].setName( "" + i );
			content.add( citizenButton[i] ).row();
		}
		
		scroll = new ScrollPane(content, skin);
		scroll.setScrollingDisabled(false, false);
		scroll.setBounds(550, 150, 200, 200);
		scroll.setVisible( false );
		
		stage.addActor( UserInterface);
		stage.addActor( scroll );
		stage.addActor( score );
		stage.addActor( citizenInfo );
		stage.addActor( changeCitizens );
		
		camera.zoom = currentZoom;

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		scaling_x = width/480;
		scaling_y = height/800;
		
		changeCitizens.addListener( citizenClickListener );
	}
	
	public ClickListener citizenClickListener = new ClickListener() 
	{
		@Override
		public void clicked (InputEvent event, float x, float y) {
			scroll.setVisible( true );
			simulation.endGame( 1 );
			System.out.println( "citizens changed clicked" );
		}
		
	};
	
	public ClickListener citizenSelectListener = new ClickListener()
	{
		@Override
		public void clicked( InputEvent event, float x, float y )
		{
			int citizen = Integer.parseInt( event.getListenerActor().getName() );
			citizenInfo.setText( "Name: " + citizens.get(citizen).name );
			citizenInfo.setVisible( true );
		}
	};
	 
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
		
		for( int i = 1; i < 104; i++ )
		{
			if( simulation.tiles.get(i).type == town )
			{
				batch.draw( HexTileRegionTown, -HexTile.getWidth()/2 + simulation.tiles.get(i).position.x, -HexTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, HexTile.getWidth(), HexTile.getHeight(), 1, 1, 0, false);
			}
			else if( simulation.tiles.get(i).type == grass )
			{
				batch.draw( HexTileRegionGrass, -HexTile.getWidth()/2 + simulation.tiles.get(i).position.x, -HexTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, HexTile.getWidth(), HexTile.getHeight(), 1, 1, 0, false);
			}
			else if( simulation.tiles.get(i).type == desert )
			{
				batch.draw( HexTileRegionDesert, -HexTile.getWidth()/2 + simulation.tiles.get(i).position.x, -HexTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, HexTile.getWidth(), HexTile.getHeight(), 1, 1, 0, false);
			}
			else if( simulation.tiles.get(i).type == wood )
			{
				batch.draw( HexTileRegionWood, -HexTile.getWidth()/2 + simulation.tiles.get(i).position.x, -HexTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, HexTile.getWidth(), HexTile.getHeight(), 1, 1, 0, false);
			}
			else if( simulation.tiles.get(i).type == sheep )
			{
				batch.draw( HexTileRegionSheep, -HexTile.getWidth()/2 + simulation.tiles.get(i).position.x, -HexTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, HexTile.getWidth(), HexTile.getHeight(), 1, 1, 0, false);
			}
			
			if( simulation.tiles.get(i).colour == grey )
			{
			}
			else if( simulation.tiles.get(i).colour == green )
			{
				HexTileRegion = new TextureRegion( HexTile, 0, 0, HexTile.getWidth(), HexTile.getHeight() );
				batch.draw( HexTileRegion, -HexTile.getWidth()/2 + simulation.tiles.get(i).position.x, -HexTile.getHeight()/2 + simulation.tiles.get(i).position.y, 0, 0, HexTile.getWidth(), HexTile.getHeight(), 1, 1, 0, false);
			}
			
			
		}
		
		if( info )
		{
			UserInterface.setVisible( true );
			score.setVisible( true );
		}
		else
		{
			UserInterface.setVisible( false );
			score.setVisible( false );
		}
		
		for( int i = 0; i < simulation.tiles.size(); i++ )
		{
			if( tileTouch[i] )
			{
				displayInfoBox( simulation, i );
				batch.draw( HexTileRegionSelected, (-HexTileSelected.getWidth()/2 + simulation.tiles.get(i).position.x), (-HexTileSelected.getHeight()/2 + simulation.tiles.get(i).position.y), 0, 0, HexTileSelected.getWidth(), HexTileSelected.getHeight(), 1, 1, 0, false);
			}
		}
		
	}
	
	private void displayInfoBox( Simulation simulation, int type )
	{
		if( simulation.tiles.get(type).type == grass )
		{
			score.setText( "grass" );
		}
		else if( simulation.tiles.get(type).type == wood )
		{
			score.setText( "wood" );
		}
		else if( simulation.tiles.get(type).type == town )
		{
			score.setText( "town" );
		}
		else if( simulation.tiles.get(type).type == sheep )
		{
			score.setText( "sheep" );
		}
		else if( simulation.tiles.get(type).type == desert )
		{
			score.setText( "desert" );
		}
		score.setVisible( true );
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