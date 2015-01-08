package onzichtbaar.net.Android.CityBuilder.gameScreen;

import java.util.ArrayList;

import onzichtbaar.net.Android.CityBuilder.load.Data;
import onzichtbaar.net.Android.CityBuilder.load.DisplayInfoBox;
import onzichtbaar.net.Android.CityBuilder.load.DrawTiles;
import onzichtbaar.net.Android.CityBuilder.load.Skin_Setup;
import onzichtbaar.net.Android.CityBuilder.load.TouchInput;
import onzichtbaar.net.Android.CityBuilder.objects.Citizen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Renderer extends Data
{
	private  ArrayList<Citizen> citizens = new ArrayList<Citizen>();
	
	public static final String LOG = Renderer.class.getSimpleName();

	private DisplayInfoBox infoBoxDisplay;
	private TouchInput inputHandler;
	private DrawTiles drawTiles;
	
	private Stage stage;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private Skin skin;
	private int selectedTile = 1;
	private boolean hasWall = false;
	
	private float scaling_x = 0;
	private float scaling_y = 0;
	
	private Texture InfoBox;
	
	private TextButton woodButton;
	
	private TextField tileInfo;
	private TextField levelText;
	private TextField resourceWood;
	private TextField resourceStone;
	private TextField resourceFood;
	private TextField resourceGold;
	
	private Skin_Setup skin_setup;
	
	private GameScreen game;
	
	private Image UserInterface;
	private Simulation simulation;
	private TextureRegion region;
	
	private int Wood = 0;
	private int Stone = 0;
	private int Food = 0;
	private int Gold = 0;
	
	public Renderer( GameScreen game, OrthographicCamera camera, Stage stage, SpriteBatch batch, ArrayList<Citizen> citizens )
	{
		this.citizens = citizens;
		this.game = game;

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		scaling_x = width/ScreenHeight;
		scaling_y = height/ScreenWidth;

		InfoBox = new Texture( Gdx.files.internal( "UITest.png" ));
		
		region = new TextureRegion(InfoBox, 0, 0, InfoBox.getWidth(), InfoBox.getHeight());

		infoBoxDisplay = new DisplayInfoBox();
		inputHandler = new TouchInput();
		drawTiles = new DrawTiles();
		
		this.stage = stage;
		this.camera = camera;
		this.batch = batch;

		camera.zoom = 0.5f;
		
		drawStage();
	}
	
	public void DrawImages( Simulation simulation )
	{
		this.simulation = simulation;
		inputHandler.variables( camera, simulation );
		inputHandler.MapScroll();
		inputHandler.MapZoom();
		
		drawTiles.fillTiles( simulation, batch );
		
		boolean[] tileTouch = simulation.getTileTouch();
		for( int i = 0; i < simulation.tiles.size(); i++ )
		{
			if( tileTouch[i] )
			{
				if( simulation.tiles.get(i).wall )
				{
					woodButton.setText( "Remove wall" );
				}
				else
				{
					woodButton.setText( "Build wall" );
				}
				
				if( simulation.tiles.get(i).colour == available )
				{
					woodButton.setVisible( true );
				}
				else
				{
					woodButton.setVisible( false );
				}
				
				infoBoxDisplay.displayInfoBox(tileInfo, simulation, i);
				drawTiles.drawSelected( simulation, batch, i );
				
				selectedTile = i;
				hasWall = simulation.tiles.get(i).wall;
			}
		}
		
	}
	

	private void drawStage()
	{
		UserInterface = new Image(region);
		UserInterface.setBounds(880, 80, 400, 600);
		UserInterface.setVisible( true );
		
		skin_setup = new Skin_Setup();
		skin = new Skin();
		skin = skin_setup.set_skin();
		
		tileInfo = new TextField( "", skin );
		tileInfo.setDisabled(true);
		tileInfo.setBounds( 1030, 530, 300, 20 );
		tileInfo.setVisible( false );
		
		levelText = new TextField( "Level 1", skin );
		levelText.setDisabled(true);
		levelText.setBounds(30, 680, 300, 20 );
		levelText.setVisible( true );
		
		resourceWood = new TextField( "Wood: ", skin );
		resourceStone = new TextField( "Stone: ", skin );
		resourceFood = new TextField( "Food: ", skin );
		resourceGold = new TextField( "Gold: ", skin );
		resourceWood.setDisabled( true );
		resourceStone.setDisabled( true );
		resourceFood.setDisabled( true );
		resourceGold.setDisabled( true );
		resourceWood.setBounds( 150, 680, 300, 20 );
		resourceStone.setBounds( 350, 680, 300, 20 );
		resourceFood.setBounds( 550, 680, 300, 20 );
		resourceGold.setBounds( 750, 680, 300, 20 );
		resourceWood.setVisible( true );
		resourceStone.setVisible( true );
		resourceFood.setVisible( true );
		resourceGold.setVisible( true );
		
		woodButton = new TextButton("Build Wall", skin );
		woodButton.setBounds(980, 220, 200, 50 );
		woodButton.setVisible( false );
		woodButton.addListener(woodButtonListener);
		
		stage.addActor( UserInterface);
		stage.addActor( tileInfo );
		stage.addActor( levelText );
		stage.addActor( resourceWood );
		stage.addActor( resourceStone );
		stage.addActor( resourceFood );
		stage.addActor( resourceGold );
		stage.addActor( woodButton );
	}
	
	public ClickListener woodButtonListener = new ClickListener() 
	{
		@Override
		public void clicked (InputEvent event, float x, float y) 
		{
			if( hasWall )
			{
				Wood = Wood + 50;
				Stone = Stone + 100;
				simulation.tiles.get(selectedTile).setWall( false );
			}
			else
			{
				Wood = Wood - 50;
				Stone = Stone - 100;
				simulation.tiles.get(selectedTile).setWall( true );
			}
			resourceWood.setText( "Wood: " + Wood );
			resourceStone.setText( "Stone: " + Stone );
			resourceFood.setText( "Food: " + Food );
			resourceGold.setText( "Gold: " + Gold );
		}
		
	};
	
	public void dispose()
	{
		stage.clear();
	}
	
}