package CityBuilder.load;

import com.badlogic.gdx.Gdx;

public abstract class Data 
{
	
	protected int ScreenWidth = Gdx.graphics.getWidth();
	protected int ScreenHeight = Gdx.graphics.getHeight();
	
	protected int tileWidth = 64;
	protected int tileHeight = 64;
	
	protected int tileStartWidth = 0;
	protected int tileStartHeight = (24*64);
	
	protected int gridSizeWidth = 25;
	protected int gridSizeHeight = 25;
	protected int numberOfTiles = 625;

	protected int unavailable = 50;
	protected int available = 51;
	
}
