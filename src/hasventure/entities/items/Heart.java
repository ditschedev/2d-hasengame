/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.entities.items;

import hasventure.Handler;
import hasventure.gfx.Animation;
import hasventure.gfx.Assets;
import hasventure.tiles.Tile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Tobias
 */
public class Heart extends ItemEntity {
    
        private Animation updown;
        
        private float hx;
        private float hy;
    
	public Heart(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		updown = new Animation(183, Assets.heart);
                
                this.hx = x;
                this.hy = y;
                
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 0;
		bounds.height = 0;
	}

	@Override
	public void tick() {
		updown.tick();
	}
        
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
        
        private BufferedImage getCurrentAnimationFrame(){
                return updown.getCurrentFrame();
        }
        
        public float getX() {
            return hx;
        }

        public void setX(float hx) {
            this.hx = hx;
        }

        public float getY() {
            return hy;
        }

        public void setY(float hy) {
            this.hy = hy;
        }
}