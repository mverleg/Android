package cityBuilder.gameScreen;

import java.util.ArrayList;

import cityBuilder.gameScreen.buildings.Farm;
import cityBuilder.gameScreen.buildings.Road;
import cityBuilder.gameScreen.buildings.Warehouse;
import cityBuilder.gameScreen.buildings.Warehouse2;
import cityBuilder.gameScreen.buildings.WoodCutter;
import cityBuilder.load.Data;
import cityBuilder.load.Item;
import cityBuilder.load.build.buildActor;
import cityBuilder.load.build.buildInventory;
import cityBuilder.load.inventory.Inventory;
import cityBuilder.load.inventory.InventoryActor;
import cityBuilder.objects.Citizen;
import cityBuilder.objects.Tile;

import com.badlogic.gdx.Gdx;

public class Simulation extends Data {
	private Citizen[] citizen = new Citizen[100];

	public ArrayList<Tile> tiles = new ArrayList<Tile>();

	private ArrayList<Citizen> citizens = new ArrayList<Citizen>();
	private ArrayList<Farm> farms = new ArrayList<Farm>();
	private ArrayList<WoodCutter> woodcutters = new ArrayList<WoodCutter>();
	private ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();
	private ArrayList<Warehouse2> warehouses2 = new ArrayList<Warehouse2>();
	private ArrayList<Road> roads = new ArrayList<Road>();

	private float width = 0;
	private float height = 0;

	private float touchX = 0;
	private float touchY = 0;

	private float cameraX = 0;
	private float cameraY = 0;
	private float cameraZ = 1;

	private float distance = 0;

	private int pressed = 0;
	private int miningProgress = 0;

	private boolean infoboxTouch = false;
	private boolean touched_down = false;
	private boolean back_pressed = false;
	private boolean fast_press = false;
	private boolean down_pressed = false;
	private boolean up_pressed = false;
	private boolean scrolling = false;

	private boolean[] farmTiles = new boolean[626];
	private boolean BuildingFarm = false;
	private boolean BuildingWoodCutter = false;
	private boolean buildingWareHouse = false;
	private boolean buildingWarehouse2 = false;
	private boolean buildingRoad = false;

	private int selectedTile = -1;

	float touch_distance_x = 999;
	float touch_distance_y = 999;
	float sec = 0.0f;
	private boolean MiningSequence = false;

	public static final String LOG = Simulation.class.getSimpleName();

	private GameScreen game;

	private Inventory inventory;
	private InventoryActor inventoryActor;

	public Simulation(GameScreen game, Inventory inventory, InventoryActor inventoryActor, buildInventory builder, buildActor actor, ArrayList<Citizen> citizens, ArrayList<Tile> tiles ) {
		this.tiles = tiles;
		this.game = game;
		this.citizens = citizens;
		this.inventory = inventory;
		this.inventoryActor = inventoryActor;

		populate();
	}

	public void populate() {
		for( int i = 0; i < farmTiles.length; i++ )
		{
			farmTiles[i] = false;
		}
		Gdx.app.log(Simulation.LOG, "Game Screen populated");
	}

	public void variables(float touchX, float touchY, float width, float height, boolean touched_down, boolean fast_press, boolean back_pressed, boolean down_pressed, boolean up_pressed, float distance) {
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

	public void update(float delta) {
		checkTouch();

		//every second updates
		sec = (sec + delta);
		if( sec >= 1.00000f )
		{
			updateBuildings();
			sec = (sec - 1.00000f);
		}
	}

	private void updateBuildings()
	{
		for( int i = 0; i < farms.size(); i++ ) {
			farms.get(i).update();
		}

		for( int i = 0; i < woodcutters.size(); i++ ) {
			woodcutters.get(i).update();
		}

		for( int i = 0; i < warehouses.size(); i++ ) {
			warehouses.get(i).update();
		}
	}

	public void BuildFarm()
	{
		BuildingFarm = true;
	}

	public void BuildWoodCutter()
	{
		BuildingWoodCutter = true;
	}

	public void BuildWarehouse() {
		buildingWareHouse = true;
	}

	public void buildWarehouse2() {
		buildingWarehouse2 = true;
	}

	public void buildRoad() {
		buildingRoad = true;
	}

	public void BuildingConfirmation( int building, int rotation, int selectedTile )
	{
		//set all tiles that occupy the farm

		if( selectedTile == (gridSizeWidth-1) )
		{
			selectedTile = (selectedTile+(gridSizeWidth-1));
		}
		if(selectedTile < gridSizeWidth )
		{
			selectedTile = (selectedTile + gridSizeWidth );
		}
		else if( (selectedTile+1) % gridSizeWidth == 0)
		{
			selectedTile = (selectedTile-1);
		}

		if( building == 0 )
		{
			Farm farm = new Farm(selectedTile);
			farm.buildFarm(tiles, selectedTile, rotation);
			farms.add(farm);
			inventory.takeItem( "farm" );
			BuildingFarm = false;
		} else if( building == 1 ) {
			Warehouse warehouse = new Warehouse(selectedTile);
			warehouse.buildWarehouse(tiles, selectedTile, rotation);
			inventory.takeItem( "fishershut" );
			warehouses.add(warehouse);
			buildingWareHouse = false;
		} else if( building == 2 ) {
			WoodCutter woodcutter = new WoodCutter(selectedTile);
			woodcutter.buildWoodcutter(tiles, selectedTile, rotation);
			inventory.takeItem( "woodcutter" );
			woodcutters.add(woodcutter);
			BuildingWoodCutter = false;
		} else if( building == 3) {
			// this is a tree.
		} else if( building == 4) {
			Warehouse2 warehouse2 = new Warehouse2(selectedTile);
			warehouse2.buildWarehouse2(tiles, selectedTile, rotation);
			inventory.takeItem( "brick" );
			warehouses2.add(warehouse2);
			buildingWarehouse2 = false;
		}
	}

	public void BuildingConfirmationRoad( int building, int rotation,  ArrayList<Tile> roadSelected )
	{
  		if( building == 5) {

  			for (Tile tile : roadSelected) {
				tile.setOccupied(6, 0, rotation);
				checkRoads();
				// build road
				inventory.takeItem("road");

				Road road = new Road(selectedTile);
				roads.add(road);
			}
			buildingRoad = false;
		}
	}

	private void checkRoads() {
		// this is called after a road is placed, it will check all roads and if there are adjacent
		// road than they will be given an indication using the buildingposition variable
		for (Road road : roads) {

		}
	}

	public void updateScroll(float cameraX, float cameraY, float cameraZ) {
		this.cameraX = cameraX;
		this.cameraY = cameraY;
		this.cameraZ = cameraZ;
	}

	public void isScrolling(boolean scrolling) {
		this.scrolling = scrolling;
	}

	public void endGame(int i) {
		game.Game_Finished(i, citizens);
	}

	private void checkTouch()
	{
		//no touch detection in the panel sections
		if( !(touchX > 950 ))
		{
			touch_distance_x = ((touchX - (ScreenWidth / 2)) * cameraZ + cameraX);
			touch_distance_y = (((touchY - (ScreenHeight / 2)) * cameraZ - cameraY) * -1);
		}
	}

	public boolean touchedInfobox( float tileX, float tileY, float tileWidth, float tileHeight )
	{
		return ((touchX >= tileX) && (touchX <= (tileX + tileWidth))) && (((720 - touchY) > tileY) && ((720 - touchY) <= (tileY + tileHeight)));
	}

	public int TileTouch()
	{
		int tileX = Math.round((touch_distance_x)/64);
		int tileY = Math.round((touch_distance_y)/64);
		tileY = ((tileY-24)*-1);

		if (touched_down)
		{
			selectedTile = ((tileY*gridSizeHeight)+tileX);

			if( scrolling )
			{
				pressed = 0;
				selectedTile = -1;
				miningProgress = 0;
				MiningSequence = false;
			}

			pressed++;
		}
		else
		{
			miningProgress = 0;
			MiningSequence = false;
			pressed = 0;
		}

		return selectedTile;
	}

	public boolean getTouchedDown() {
		return touched_down;
	}

	public float getTouchX() {
		return touchX;
	}

	public float getTouchY() {
		return touchY;
	}

	public boolean getDown() {
		return down_pressed;
	}

	public boolean getUp() {
		return up_pressed;
	}

	public float getDistance() {
		return distance;
	}

	public boolean getMining() {
		return MiningSequence;
	}

	public int getMiningProgress() {
		return miningProgress;
	}

	public boolean getBuildingFarm()
	{
		return BuildingFarm;
	}

	public boolean getBuildingWoodCutter()
	{
		return BuildingWoodCutter;
	}

	public boolean getBuildingWarehouse() {
		return buildingWareHouse;
	}

	public boolean getBuildingWarehouse2() { return buildingWarehouse2; }

	public boolean getBuildingRoad() {
		return buildingRoad;
	}

	public int getRoadSize() {
		return inventory.checkInventory(Item.road);
	}
}