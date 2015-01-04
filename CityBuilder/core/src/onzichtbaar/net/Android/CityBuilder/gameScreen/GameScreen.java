package onzichtbaar.net.Android.CityBuilder.gameScreen;

import java.util.ArrayList;

import onzichtbaar.net.Android.CityBuilder.load.Screen;
import onzichtbaar.net.Android.CityBuilder.objects.Citizen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen implements Screen
{
	public static final String LOG = Simulation.class.getSimpleName();
	
	private Simulation simulation;
	private Renderer renderer;
	
	private boolean game_finished = false;
	
	private ArrayList<Citizen> citizens = new ArrayList<Citizen>();
	
	int level;
	
	public GameScreen( Application app, OrthographicCamera camera, Stage stage, SpriteBatch batch, ArrayList<Citizen> citizens )
	{
		this.citizens = citizens;
		Gdx.app.log( LOG, "Creating opening screen" );
		
		simulation = new Simulation( this, citizens );
		renderer = new Renderer( this, camera, stage, batch, citizens );
		
	}

	@Override
	public void update(float delta, float touchX, float touchY, float width, float height, boolean touched_down, boolean fast_press, boolean back_pressed, boolean down_pressed, boolean enter_pressed, boolean up_pressed, float distance ) 
	{
		simulation.update( delta );
		simulation.variables( touchX, touchY, width, height, touched_down, fast_press, back_pressed, down_pressed, up_pressed, distance );
	}

	@Override
	public void dispose()
	{
		renderer.dispose();
	}

	public void Game_Finished( int level, ArrayList<Citizen> citizens )
	{
		game_finished = true;
		this.level = level;
		this.citizens = citizens;
	}

	@Override
	public boolean isDone()
	{
		return game_finished;
	}


	@Override
	public int level()
	{
		return level;
	}

	@Override
	public void render( Application app )
	{
		 renderer.DrawImages( simulation );
	}

	@Override
	public ArrayList<Citizen> citizens()
	{
		return citizens;
	}
	
	

}
