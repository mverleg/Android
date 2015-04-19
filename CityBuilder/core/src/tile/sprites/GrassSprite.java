package tile.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import tile.TileSprite;

public class GrassSprite extends TileSprite {

	private static final GrassSprite instance = new GrassSprite();
 
	protected GrassSprite() {
		standard = new Sprite(new Texture("images/grass.png"));
	}
 
	public static GrassSprite getInstance() {
		return instance;
	}
	
}