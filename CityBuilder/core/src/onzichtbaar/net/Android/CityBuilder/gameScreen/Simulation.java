package onzichtbaar.net.Android.CityBuilder.gameScreen;

import java.util.ArrayList;

import onzichtbaar.net.Android.CityBuilder.load.Data;
import onzichtbaar.net.Android.CityBuilder.load.TilesLoad;
import onzichtbaar.net.Android.CityBuilder.load.Vector;
import onzichtbaar.net.Android.CityBuilder.objects.Citizen;
import onzichtbaar.net.Android.CityBuilder.objects.Tile;

import com.badlogic.gdx.Gdx;

public class Simulation extends Data
{
		private Citizen[] citizen = new Citizen[100];
		
		public ArrayList<Tile> tiles = new ArrayList<Tile>( );
		
		private  ArrayList<Citizen> citizens = new ArrayList<Citizen>();
		
		private TilesLoad tileLoad;
		
		private float emma = 0;
		private float watson = 0;

		private float width = 0;
		private float height = 0;

		private float touchX = 0;
		private float touchY = 0;
    	
		private float cameraX = 0;
		private float cameraY = 0;
		private float cameraZ = 1;
		
    	private float distance = 0;
		
    	private int pressed = 0;
    	
		private boolean touched_down = false;
        private boolean back_pressed = false;
        private boolean fast_press = false;
        private boolean down_pressed = false;
        private boolean up_pressed = false;
        private boolean scrolling = false;
        
        private boolean[] tileSelected = new boolean[60001];
        private boolean[] firstTouchTile = new boolean[60001];
        private boolean tileTouched = false;
        private boolean touchedBox = true;
    	
        float touch_distance_x = 999;
        float touch_distance_y = 999;
        
        private boolean startMining = false;
		
		public static final String LOG = Simulation.class.getSimpleName();
                
        private GameScreen game;
        
        public Simulation( GameScreen game, ArrayList<Citizen> citizens )
        {
        		this.game = game;
        		this.citizens = citizens;

            	tileLoad = new TilesLoad();
                populate( );
        }

        public void populate( )
        {
        	for( int m = 1; m < tiles.size(); m++ )
        	{
        		firstTouchTile[m] = true;
        	}
        	
        	tiles = tileLoad.getTiles();
        	
        	Gdx.app.log( Simulation.LOG, "Game Screen populated" );
        }
        
        public void variables( float touchX, float touchY, float width, float height, boolean touched_down, boolean fast_press, boolean back_pressed, boolean down_pressed, boolean up_pressed, float distance ) 
        {
    		this.width = width;
    		this.height = height;
    		
    		this.touchX = touchX;
    		this.touchY = touchY;
    		
    		this.touched_down = touched_down;
    		this.back_pressed = back_pressed;
    		this.fast_press = fast_press;
    		this.down_pressed = down_pressed;
    		this.up_pressed = up_pressed;
    		this.distance = distance;
        }

        public void update( float delta )
        {
        	checkTouch();
        	TileTouch();
        }
        
        public void updateScroll( float cameraX, float cameraY, float cameraZ )
        {
        	this.cameraX = cameraX;
        	this.cameraY = cameraY;
        	this.cameraZ = cameraZ;
        }
        
        public void isScrolling( boolean scrolling )
        {
        	this.scrolling = scrolling;
        }
        
        public void endGame( int i )
        {
			game.Game_Finished( i, citizens );
        }
        
        private void checkTouch()
        {
        	touch_distance_x = ((touchX-(ScreenWidth/2))*cameraZ + cameraX);
        	touch_distance_y = (((touchY-(ScreenHeight/2))*cameraZ - cameraY)*-1); 	
        }
        
        public void BuildWall( boolean build, int selectedTile )
        {
        	if( build )
        	{
        		if( game.Wood >= 50 && game.Stone >= 100 )
        		{
    				game.Wood = game.Wood - 50;
    				game.Stone = game.Stone - 100;
    				tiles.get(selectedTile).setWall( true );
        		}
        		else
        		{
        			//not enough resources
        		}
        	}
        	else if( !build )
        	{
				game.Wood = game.Wood + 50;
				game.Stone = game.Stone + 100;
				tiles.get(selectedTile).setWall( false );
        	}
        }
        
        private void TileTouch()
        {
        	if( !touched_down )
        	{
        		pressed = 0;
        	}
        	
        	if( tileTouched )
        	{
        		if( touched_down )
        		{
        			pressed ++;
        			
        			if( pressed >= 20 )
        			{
        				if( !scrolling )
        				{
        					startMining = true;
        				}
        			}
        			else
        			{
        				startMining = false;
        			}
        			
        			for( int i = 1; i < tiles.size(); i++ )
                	{
        				tileSelected[i] = false;
        			      
                	}
        			tileTouched = false;
        		}
        	}
        	
        	if( fast_press )
        	{
        		for( int k = 1; k < tiles.size(); k++ )
            	{
            		if(( tiles.get(k).position.distance( new Vector( touch_distance_x, touch_distance_y )) < ((tileWidth/2)+1) ) && !tileTouched )
            		{
        				tileSelected[k] = true;
            			tileTouched = true;
            		}
            	}
        	}
        	
        }
        
        
        public boolean getTouchedDown()
        {
        	return touched_down;
        }
        
        public float getTouchX()
        {
        	return touchX;
        }
        
        public float getTouchY()
        {
        	return touchY;
        }
        
        public boolean getDown()
        {
        	return down_pressed;
        }
        
        public boolean getUp()
        {
        	return up_pressed;
        }
        
        public float getDistance()
        {
        	return distance;
        }
        
        public boolean[] getTileTouch()
        {
        	return tileSelected;
        }
        
        public boolean getMining()
        {
        	return startMining;
        }
}
