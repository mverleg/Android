package onzichtbaar.net.greaterThanFive6.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import onzichtbaar.net.greaterThanFive6.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Empire_Clicker";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new Main(), config);
	}
}
