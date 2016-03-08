/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Tobias
 */
public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage dirt, grass, stone, tree, rock, chest, ladder, brick, PLAYER_STAY_UP
                                    ,PLAYER_STAY_DOWN
                                    , PLAYER_STAY_LEFT
                                    , PLAYER_STAY_RIGHT;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
        public static BufferedImage[] heart, sparks;
        public static BufferedImage[] items;
	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		SpriteSheet heartsheet = new SpriteSheet(ImageLoader.loadImage("/textures/hearts.png"));
                SpriteSheet sparksheet = new SpriteSheet(ImageLoader.loadImage("/textures/sparkle.gif"));
                SpriteSheet itemsheet = new SpriteSheet(ImageLoader.loadImage("/textures/items.png"));
                SpriteSheet player = new SpriteSheet(ImageLoader.loadImage("/textures/player/player.png"));
                
		player_down = new BufferedImage[3];
		player_up = new BufferedImage[3];
		player_left = new BufferedImage[3];
		player_right = new BufferedImage[3];
		
		player_down[0] = player.crop(width*2, 0, width, height);
                PLAYER_STAY_UP = player.crop(width*2, 0, width, height);
		player_down[1] = player.crop(width*2, height, width, height);
                player_down[2] = player.crop(width*2, height*2, width, height);
		player_up[0] = player.crop(width *3, 0, width, height);
                PLAYER_STAY_DOWN = player.crop(width *3, 0, width, height);
		player_up[1] = player.crop(width*3, height, width, height);
                player_up[2] = player.crop(width*3, height*2, width, height);
		player_right[0] = player.crop(0, 0, width, height);
                PLAYER_STAY_RIGHT = player.crop(0, 0, width, height);
		player_right[1] = player.crop(0, height, width, height);
                player_right[2] = player.crop(0, height*2, width, height);
		player_left[0] = player.crop(width, 0, width, height);
                PLAYER_STAY_LEFT = player.crop(width, 0, width, height);
		player_left[1] = player.crop(width, height, width, height);
                player_left[2] = player.crop(width, height*2, width, height);
		
		zombie_down = new BufferedImage[2];
		zombie_up = new BufferedImage[2];
		zombie_left = new BufferedImage[2];
		zombie_right = new BufferedImage[2];
		
		zombie_down[0] = sheet.crop(width * 4, height * 2, width, height);
		zombie_down[1] = sheet.crop(width * 5, height * 2, width, height);
		zombie_up[0] = sheet.crop(width * 6, height * 2, width, height);
		zombie_up[1] = sheet.crop(width * 7, height * 2, width, height);
		zombie_right[0] = sheet.crop(width * 4, height * 3, width, height);
		zombie_right[1] = sheet.crop(width * 5, height * 3, width, height);
		zombie_left[0] = sheet.crop(width * 6, height * 3, width, height);
		zombie_left[1] = sheet.crop(width * 7, height * 3, width, height);
                
                heart = new BufferedImage[4];
                
                heart[0] = heartsheet.crop(0, 0, width, height);
		heart[1] = heartsheet.crop(width, 0, width, height);
                heart[2] = heartsheet.crop(width * 2, 0, width, height);
                heart[3] = heartsheet.crop(width * 3, 0, width, height);
                
                sparks = new BufferedImage[4];
                
                sparks[0] = sparksheet.crop(0, 0, 16, 16);
		sparks[1] = sparksheet.crop(16, 0, 16, 16);
                sparks[2] = sparksheet.crop(16 * 2, 0, 16, 16);
                sparks[3] = sparksheet.crop(16 * 3, 0, 16, 16);
                
                items = new BufferedImage[4];
                
                items[0] = itemsheet.crop(0, 0, width, width);
                items[1] = itemsheet.crop(width, 0, width, width);
                items[2] = itemsheet.crop(width*2, 0, width, width);
                items[3] = itemsheet.crop(width*3, 0, width, width);
                
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(0, height * 5, width, height);
		stone = sheet.crop(width * 3, 0, width, height);
		tree = sheet.crop(0, 0, width, height * 2);
		rock = sheet.crop(0, height * 2, width, height);
                chest = sheet.crop(0, height * 3, width, height);
                ladder = sheet.crop(0, height * 5, width, height);
                brick = sheet.crop(0, height * 4, width, height);
	}
	
}
