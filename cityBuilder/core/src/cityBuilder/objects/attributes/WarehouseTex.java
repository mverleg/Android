package cityBuilder.objects.attributes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cityBuilder.objects.Tile;

/**
 * Created by User on 10/29/2017.
 */

public class WarehouseTex {
    private Tile tile;
    private int position;
    private int rotation;
    private TextureRegion SquareTileRegionFarmBottomLeft;
    private TextureRegion SquareTileRegionFarmBottomRight;

    public WarehouseTex(Tile tile, int position, int rotation, TextureAtlas atlas ) {
        this.tile = tile;
        this.position = position;
        this.rotation = rotation;
        SquareTileRegionFarmBottomLeft = atlas.findRegion("cubeLight");
        SquareTileRegionFarmBottomRight = atlas.findRegion("cubeDark");
    }

    public void draw( Batch batch ) {
        if (position == 0) {
            // top left
            batch.draw(SquareTileRegionFarmBottomLeft, -32 + tile.getPosition().x, -32 + tile.getPosition().y, 32, 32, 64, 64, 1, 1, -(90 * rotation), false);
        } else if (position == 1) {
            // top
            batch.draw(SquareTileRegionFarmBottomLeft, -32 + tile.getPosition().x, -32 + tile.getPosition().y, 32, 32, 64, 64, 1, 1, -(90 * rotation), false);
        } else if (position == 2) {
            // top right
            batch.draw(SquareTileRegionFarmBottomLeft, -32 + tile.getPosition().x, -32 + tile.getPosition().y, 32, 32, 64, 64, 1, 1, -(90 * rotation), false);
        } else if (position == 3) {
            // bottom left
            batch.draw(SquareTileRegionFarmBottomLeft, -32 + tile.getPosition().x, -32 + tile.getPosition().y, 32, 32, 64, 64, 1, 1, -(90 * rotation), false);
        } else if (position == 4) {
            // bottom
            batch.draw(SquareTileRegionFarmBottomLeft, -32 + tile.getPosition().x, -32 + tile.getPosition().y, 32, 32, 64, 64, 1, 1, -(90 * rotation), false);
        } else if (position == 5) {
            // bottom right
            batch.draw(SquareTileRegionFarmBottomLeft, -32 + tile.getPosition().x, -32 + tile.getPosition().y, 32, 32, 64, 64, 1, 1, -(90 * rotation), false);
        } else if (position == 6) {
            // shore
            batch.draw(SquareTileRegionFarmBottomRight, -32 + tile.getPosition().x, -32 + tile.getPosition().y, 32, 32, 64, 64, 1, 1, -(90 * rotation), false);
        } else if (position == 7) {
            // shore
            batch.draw(SquareTileRegionFarmBottomRight, -32 + tile.getPosition().x, -32 + tile.getPosition().y, 32, 32, 64, 64, 1, 1, -(90 * rotation), false);
        } else if (position == 8) {
            // shore
            batch.draw(SquareTileRegionFarmBottomRight, -32 + tile.getPosition().x, -32 + tile.getPosition().y, 32, 32, 64, 64, 1, 1, -(90 * rotation), false);
        }
    }
}
