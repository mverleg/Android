package cityBuilder.load;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import cityBuilder.enums.TileType;
import cityBuilder.objects.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class setAttributes extends Data
{
	FileHandle layout;
	BufferedReader br;

	public setAttributes() {
	}

	public void setAttributesTileArray(Tile[][] tiles) {
		for( int x = 0; x < gridSizeWidth; x++ ) {
			for( int y = 0; y < gridSizeHeight; y++ ) {
				tiles[x][y].setAttributes( TileType.grass, available, 0, 2000, 0 );
			}
		}

		layout = Gdx.files.internal("mapLayout/IsoAnno.csv");
		br = new BufferedReader(layout.reader());

		String line = null;
		try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int height = 0;
		while( line != null ) {
			String[] items = line.split(",");
			for (int width = 0; width < items.length; width++) {
				if( Integer.parseInt(items[width]) == 0 ) {
					tiles[height][width].setAttributes( TileType.grass, available, 0, 2000, 0 );
				} else if( Integer.parseInt(items[width]) == 1 ) {
					tiles[height][width].setAttributes( TileType.water, available, 0, 2000, 0 );
				}
			}

			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			height++;
		}
	}
}
