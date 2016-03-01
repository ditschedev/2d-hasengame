/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.entities.creatures;

import hasventure.Handler;
import hasventure.gfx.Animation;
import hasventure.gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Tobias
 */
public class Enemy extends Creature {
            
        private int ticker = 0;
        private float xA;
        private float yA;
        
	//Animations
	private Animation animDown, animUp, animLeft, animRight;
	
	public Enemy(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		this.xA = x;
                this.yA = y;
		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 20;
		bounds.height = 40;
                
		this.health = this.width;
                this.speed = 2.8f;
                this.damage = 10;
		//Animatons
		animDown = new Animation(500, Assets.zombie_down);
		animUp = new Animation(500, Assets.zombie_up);
		animLeft = new Animation(500, Assets.zombie_left);
		animRight = new Animation(500, Assets.zombie_right);
	}

	@Override
	public void tick() {
		//Animations
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
                ticker++;
                
                if(ticker >= 300){
                    //    System.out.println("Tick");
                        getMovement();
                        move(); 
                        ticker = 0;
                }
                
                if(this.getHealth() <= 0){
                    this.bounds.height = 0;
                    this.bounds.width = 0;
                    this.bounds.x = 0;
                    this.bounds.y = 0;
           //         System.out.println("Killed");
                }
                
                
		//handler.getGameCamera().centerOnEntity(this);
	}
        
        private void getMovement(){
            Random rand = new Random();
            int r = rand.nextInt(10);
            
            if(r == 0)
                return;
            
            xMove = 0;
            yMove = 0;
		
            if(r == 1)
                yMove = -speed;
            if(r == 2)
                yMove = speed;
            if(r == 3)
                xMove = -speed;
            if(r == 4)
                xMove = speed;
            
        }
        
        
	@Override
	public void render(Graphics g) {
                if(this.getHealth() <= 0)
                        return;
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		g.setColor(Color.red);
                g.fillRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), this.getHealth(), 8);
//		g.setColor(Color.red);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//				bounds.width, bounds.height);
	}
	
	private BufferedImage getCurrentAnimationFrame(){
		if(xMove < 0){
			return animLeft.getCurrentFrame();
		}else if(xMove > 0){
			return animRight.getCurrentFrame();
		}else if(yMove < 0){
			return animUp.getCurrentFrame();
		}else{
			return animDown.getCurrentFrame();
		}
	}

}
