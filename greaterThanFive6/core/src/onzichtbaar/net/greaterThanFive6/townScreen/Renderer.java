package onzichtbaar.net.greaterThanFive6.townScreen;

import java.util.ArrayList;

import onzichtbaar.net.greaterThanFive6.load.Skin_Setup;
import onzichtbaar.net.greaterThanFive6.objects.Citizen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Renderer
{

    public ArrayList<Citizen> citizens = new ArrayList<Citizen>();
    
	public static final String LOG = Renderer.class.getSimpleName();

	private int citizenNumber = 0;
	
	private Texture InfoBox;
	private Texture ScrollBackground;
	private Texture PeopleBackground;
	
	private Stage stage;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Sprite sprite;
	
	private float currentZoom = 2;
	private TextField citizenName;
	private TextField citizenAge;
	private TextField citizenHappiness;
	private TextField citizenProfession;
	
	private TextButton goBack;
	private TextButton changeName;
	private TextButton[] citizenButton = new TextButton[30];
	private ScrollPane scroll;

	private List list;
	private String[] citizenNames = new String[5];
	private String[] phone_data;
	
	private Skin skin;
	private Skin_Setup skin_setup;
	
	private Table container;
	
	private TextureRegion InfoBoxRegion;
	private TextureRegion ScrollRegion;
	private TextureRegion PeopleRegion;
	
	private TownScreen game;
	private Simulation simulation;
	private Window window;
	
	public Renderer( final TownScreen game, Simulation simulation, OrthographicCamera camera, Stage stage, SpriteBatch batch, ArrayList<Citizen> citizens )
	{
		this.game = game;
		this.simulation = simulation;
		this.citizens = citizens;
		
		InfoBox = new Texture( Gdx.files.internal( "townOverview.png" ));
		ScrollBackground = new Texture( Gdx.files.internal( "background.png" ));
		PeopleBackground = new Texture( Gdx.files.internal( "background.png" ));
		
		InfoBoxRegion = new TextureRegion( InfoBox, 0, 0, InfoBox.getWidth(), InfoBox.getHeight() );
		ScrollRegion = new TextureRegion( ScrollBackground, 0, 0, InfoBox.getWidth(), InfoBox.getHeight() );
		PeopleRegion = new TextureRegion( PeopleBackground, 0, 0, InfoBox.getWidth(), InfoBox.getHeight() );
		
		this.stage = stage;
		this.camera = camera;
		this.batch = batch;

		camera.position.x = 0;
		camera.position.y = 0;
		camera.zoom = 1;

		skin_setup = new Skin_Setup();
		skin = new Skin();
		skin = skin_setup.set_skin();
		
		goBack = new TextButton( "Back to Map", skin );
		goBack.setDisabled( true );
		goBack.setPosition( 40, 400 );
		goBack.setVisible( true );
		
		changeName = new TextButton( "change name", skin );
		changeName.setDisabled( true );
		changeName.setPosition( 400, 350);
		changeName.setVisible( false );
		
		citizenName = new TextField( "\"name\"", skin );
		citizenName.setDisabled( true );
		citizenName.setBounds(30, 340, 400, 40);
		citizenName.setVisible( false );
		
		citizenAge = new TextField( "Age: ", skin );
		citizenAge.setDisabled( true );
		citizenAge.setBounds( 30, 300, 400, 40);
		citizenAge.setVisible( false );
		
		citizenHappiness = new TextField( "Happiness: ", skin );
		citizenHappiness.setDisabled( true );
		citizenHappiness.setBounds( 30, 260, 400, 40);
		citizenHappiness.setVisible( false );
		
		citizenProfession = new TextField( "Profession: ", skin );
		citizenProfession.setDisabled( true );
		citizenProfession.setBounds( 30, 220, 400, 40 );
		citizenProfession.setVisible( false );
		
		stage.addActor( goBack );
		stage.addActor( citizenName );
		stage.addActor( changeName );
		stage.addActor( citizenAge );
		stage.addActor( citizenHappiness );
		stage.addActor( citizenProfession );
		
		//List list = new List( null, skin );
		list = new List( skin );
		
		Table content = new Table( skin );
		
		for( int i = 1; i < citizens.size(); i++ )
		{
			citizenButton[i] = new TextButton( i + " " + citizens.get(i).name, skin );
			citizenButton[i].addListener(levelClickListener);
			citizenButton[i].setName("button " + i + " " + citizens.get(i).name );
			content.add( citizenButton[i] ).row();
		}
		
		scroll = new ScrollPane(content, skin);
		scroll.setScrollingDisabled(false, false);
		scroll.setBounds(600, 100, 200, 300);
		
		stage.addActor(scroll);
		
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		
		goBack.addListener( goBackListener );
		
		changeName.addListener(new InputListener() 
		{
	        @Override
	        public boolean touchDown( InputEvent event, float x, float y, int pointer, int button )
	        {
				return true;
	        }
	        
	        @Override
	        public void touchUp( InputEvent event, float x, float y, int pointer, int button )
	        {
	        	citizenName.setDisabled( false );	
	        	changeName.setVisible( false );
	        	System.out.println("change name");
	        }
		});
		
	}
	
	public InputListener goBackListener = new InputListener()
	{
		@Override
        public boolean touchDown( InputEvent event, float x, float y, int pointer, int button )
        {
        	goBack.setVisible( false );
			return true;
        }
        
        @Override
        public void touchUp( InputEvent event, float x, float y, int pointer, int button )
        {
        	game.Game_Finished(0, citizens);
        }
	};
	
	private void changeNamePress()
	{
		citizens.get(citizenNumber).setName( citizenName.getText() );
    	citizenName.setDisabled( true );
    	changeName.setVisible( true );
    	game.Game_Finished(1, citizens);
	}
	
	public ClickListener levelClickListener = new ClickListener() 
	{
		@Override
		public void clicked (InputEvent event, float x, float y) {
			
        	String[] splitted;
        	splitted = event.getListenerActor().getName().split(" ");
        	citizenNumber = Integer.parseInt( splitted[1] );
			System.out.println("Click: " + citizens.get(citizenNumber).name );
			
			citizenName.setText( citizens.get(citizenNumber).name );
			citizenName.setVisible( true );
			citizenAge.setVisible( true );
			citizenHappiness.setVisible( true );
			citizenProfession.setVisible( true );
			citizenAge.setText( "Age: " + citizens.get(citizenNumber).age );
			citizenHappiness.setText( "Happiness: " + citizens.get( citizenNumber ).happiness );
			citizenProfession.setText( "Profession: " + citizens.get( citizenNumber ).profession );
			changeName.setVisible( true );
		}
		
	};
	
	public void DrawImages()
	{
		if( simulation.enterPressed() )
		{
			changeNamePress();
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		batch.draw( InfoBoxRegion, -400, -240, 0, 0, 800, 480, 1, 1, 0, false);
		
		batch.draw( ScrollRegion, 200, -200, 0, 0, 200, 400, 1, 1, 0, false);
	}
	
	public void dispose()
	{
		stage.clear();
	}
	
}