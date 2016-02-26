/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.worlds;

import hasventure.Handler;
import hasventure.entities.DamageManager;
import hasventure.entities.EntityManager;
import hasventure.entities.creatures.Enemy;
import hasventure.entities.creatures.EnemyAI;
import hasventure.entities.creatures.Player;
import hasventure.entities.items.Heart;
import hasventure.entities.items.ItemManager;
import hasventure.tiles.Tile;
import hasventure.utils.Utils;
import java.awt.Graphics;

/**
 *
 * @author Tobias
 */
public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	//Entities
	private EntityManager entityManager;
	private DamageManager dmgManager;
        private EnemyAI enemyAI;
        private ItemManager itemManager;
        
	public World(Handler handler, String path){
		this.handler = handler;
                Player player = new Player(handler, 100, 100);
		entityManager = new EntityManager(handler, player);
		// Temporary entity code!
		//entityManager.addEntity(new Tree(handler, 100, 250));
                Enemy badguy = new Enemy(handler, 100, 680);
                Heart h = new Heart(handler, 100, 250);
                entityManager.addEntity(badguy);
                entityManager.addEntity(h);
                dmgManager = new DamageManager(handler, player, badguy);
                enemyAI = new EnemyAI(handler, player, badguy);
                itemManager = new ItemManager(handler, h, player);
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public void tick(){
		entityManager.tick();
                dmgManager.tick();
                enemyAI.tick();
                itemManager.tick();
	}
	
	public void render(Graphics g){
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		//Entities
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.dirtTile;
		return t;
	}
	
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
}
