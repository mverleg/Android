package onzichtbaar.net.Android.CityBuilder.load;

import onzichtbaar.net.Android.CityBuilder.gameScreen.Simulation;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class TouchInput 
{
	private float firstX = 0;
	private float firstY = 0;
	
	private float touchX = 1700;
	private float touchY = 1700;
	
	private float offsetX = 60;
	private float offsetY = 0;
	
	private boolean firstPress = true;
	private boolean firstPinch = true;
	
	private boolean touchedDown = false;
	
	private float firstDistance = 0;
	private float zoomSpeed = 0;
	
	private float currentX = 0;
	private float currentY = 0;
	private float currentZoom = 0.5f;
	
	private boolean touchSideRight = false;
	private boolean touchSideLeft = false;
	private boolean touchUp = false;
	private boolean touchBottom = false;
	
	private boolean down_pressed = false;
	private boolean up_pressed = false;

	private float distance = 0;
	
	private OrthographicCamera camera;
	private Simulation simulation;
	
	public TouchInput()
	{
		
	}
	
	public void variables( OrthographicCamera camera, Simulation simulation )
	{
		this.camera = camera;
		this.simulation = simulation;
		
		this.touchedDown = simulation.getTouchedDown();
		this.touchX = simulation.getTouchX();
		this.touchY = simulation.getTouchY();
		this.down_pressed = simulation.getDown();
		this.up_pressed = simulation.getUp();
		this.distance = simulation.getDistance();
	}
	
	public void MapScroll()
	{
		
		if( touchedDown && firstPress )
		{
			firstPress = false;
			firstX = touchX;
			firstY = touchY;
		}
		else if( touchedDown && !firstPress )
		{
			if( ((currentX + offsetX) <= -280) && offsetX < 0 )
			{
				touchSideRight = true;
			}
			else if( ((currentX + offsetX) >= 280) && offsetX > 0 )
			{
				touchSideLeft = true;
			}
			else
			{
				if(!touchSideRight || !touchSideLeft )
				{
					offsetX = ((firstX - touchX)*currentZoom);
				}
			}
			
			if( ((currentY - offsetY) <= -150) && offsetY > 0 )
			{
				touchUp = true;
			}
			else if( ((currentY - offsetY) >= 150) && offsetY < 0 )
			{
				touchBottom = true;
			}
			else
			{
				offsetY = ((firstY - touchY)*currentZoom);
			}
		}
		
		if( !touchedDown )
		{
			firstPress = true;
			currentX = (currentX + offsetX);
			currentY = (currentY - offsetY);
			offsetX = 0;
			offsetY = 0;
			
			if( touchSideRight )
			{
				currentX = -280;
				touchSideRight = false;
			}
			else if( touchSideLeft )
			{
				currentX = 280;
				touchSideLeft = false;
			}
			
			if( touchUp )
			{
				currentY = -150;
				touchUp = false;
			}
			else if( touchBottom )
			{
				currentY = 150;
				touchBottom = false;
			}
		}
		
	}

	public void MapZoom()
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

			//Gdx.app.log( "Zoomtest", "zoomSpeed: " + zoomSpeed );

			zoomSpeed = firstDistance - distance;
			
			if( camera.zoom <= 0.2 && zoomSpeed <= 0 )
			{
				currentZoom = 0.2f;
				zoomSpeed = 0;
			}
			else if( camera.zoom >= 0.5 && zoomSpeed > 0)
			{
				currentZoom = 0.5f;
				zoomSpeed = 0;
			}
			else
			{
				camera.zoom = (currentZoom + (zoomSpeed/1000));
			}
		}
		
		if( !touchedDown )
		{
			currentZoom = (currentZoom + (zoomSpeed/1000));
			zoomSpeed = 0;
			firstPinch = true;
		}
		
		if( down_pressed )
		{
			if(camera.zoom >= 0.5f )
			{
				
			}
			else
			{
				camera.zoom += 0.1;
				currentZoom += 0.1;
			}
		}
		else if( up_pressed )
		{
			if(camera.zoom <= 0.2 )
			{
				
			}
			else
			{
				camera.zoom -= 0.1;
				currentZoom -= 0.1;
			}
		}
		

		camera.position.x = (currentX + offsetX);
		camera.position.y = (currentY - offsetY);
		
		simulation.updateScroll( camera.position.x, camera.position.y, camera.zoom );
	}
}
