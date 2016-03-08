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
import hasventure.entities.items.Item;
import hasventure.entities.items.ItemManager;
import hasventure.gfx.Animation;
import hasventure.gfx.Assets;
import hasventure.tiles.Tile;
import hasventure.utils.Utils;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Tobias
 */
public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
        
        private Animation updown;
        
	//Entities
	private EntityManager entityManager;
	private DamageManager dmgManager;
        private EnemyAI enemyAI;
        private ItemManager itemManager;
        private Player player;
        
        private ArrayList<Enemy> enemies;
        
	public World(Handler handler, String path){
		this.handler = handler;
                
                enemies = new ArrayList<Enemy>();
                
                player = new Player(handler, 100, 100);
		entityManager = new EntityManager(handler, player);
                
                addEnemys();
                putToEntities();
                addDamages();
                setAI();
                
                Heart h = new Heart(handler, 420, 850);
                Item sword = new Item(handler, 1, 100, 180, 32, 32);
                entityManager.addEntity(sword);
                entityManager.addEntity(h);
                itemManager = new ItemManager(handler, h, player);
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
        
        private void addEnemys(){
                Enemy e;
                e = new Enemy(handler, 100, 480);
                enemies.add(e);
                e = new Enemy(handler, 150, 480);
                enemies.add(e);
        }
        
        private void putToEntities(){
                for (int i = 0; i < enemies.size(); i++) {
                    entityManager.addEntity(enemies.get(i));
                }
        }
        
        private void addDamages(){
                for (int i = 0; i < enemies.size(); i++) {
                    dmgManager = new DamageManager(handler, player, enemies.get(i));
                }
        }
        
        private void setAI(){
                for (int i = 0; i < enemies.size(); i++) {
                    enemyAI = new EnemyAI(handler, player, enemies.get(i));
                }
        }
	
	public void tick(){
		entityManager.tick();
                dmgManager.tick();
                enemyAI.tick();
                itemManager.tick();
                updown.tick();
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
                g.setColor(Color.white);
                g.setFont(new Font("Arial", Font.BOLD, 21));
                g.drawString("LVL: 1", 10, 20);
                g.setColor(new Color(0,0,0,200));
                g.fillRect(0, 256*2-48, 256*2, 48);
                g.setColor(new Color(255,255,255,180));
                g.setFont(new Font("Arial", Font.BOLD, 21));
                g.drawString("INV", 256*2-206, 256*2-16);
                g.drawRect(256*2-40, 256*2-40, 32, 32);
                g.drawRect(256*2-80, 256*2-40, 32, 32);
                g.drawRect(256*2-120, 256*2-40, 32, 32);
                g.drawRect(256*2-160, 256*2-40, 32, 32);
                
                //draw players items
                if(true)
                    g.drawImage(Assets.items[0], 256*2-36, 256*2-36, 26, 26, null);
                if(true)
                    g.drawImage(Assets.items[1], 256*2-76, 256*2-36, 26, 26, null);
                if(true)
                    g.drawImage(Assets.items[2], 256*2-116, 256*36, 26, 26, null);
                if(true)
                    g.drawImage(Assets.items[3], 256*2-156, 256*2-36, 26, 26, null);
                
                g.setColor(new Color(46, 204, 113));
                if(player.getHealth() < player.getMaxHealth() * 0.5)
                    g.setColor(new Color(243, 156, 18));
                if(player.getHealth() < player.getMaxHealth() * 0.2)
                    g.setColor(new Color(231, 76, 60));
                updown = new Animation(100, Assets.heart);
                g.drawImage(getCurrentAnimationFrame(), 20, 256*2-40, 32, 32, null);
                g.fillRect(60, 256*2-29, (int) (player.getHealth()*1.3), 10);
	}
        
        private BufferedImage getCurrentAnimationFrame(){
                return updown.getCurrentFrame();
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
