/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.entities.creatures;

import hasventure.Handler;
import hasventure.entities.Entity;
import hasventure.entities.EntityManager;
import hasventure.gfx.Animation;
import hasventure.gfx.Assets;
import hasventure.states.MenuState;
import hasventure.states.State;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobias
 */
public class Player extends Creature {
	
	//Animations
	private Animation animDown, animUp, animLeft, animRight;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 19;
		bounds.height = 19;
                
                this.health = this.width;
                this.setMaxHealth(health);
                this.damage = 1;
		
		//Animatons
		animDown = new Animation(100, Assets.player_down);
		animUp = new Animation(100, Assets.player_up);
		animLeft = new Animation(100, Assets.player_left);
		animRight = new Animation(100, Assets.player_right);
	}

	@Override
	public void tick() {
		//Animations
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
            try {
                //Movement
                getInput();
            } catch (InterruptedException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
		move();
		handler.getGameCamera().centerOnEntity(this);
                if(this.getHealth() <= 0){
                    System.out.println("DEAD");
                    State.setState(handler.getGame().gameOver);
                    this.setHealth(this.MAX_HEALTH);
                }
	}
	
	private void getInput() throws InterruptedException{
		xMove = 0;
		yMove = 0;
		EntityManager em = new EntityManager(handler, this);
                ArrayList<Entity> ents = em.getEntities();
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
                if(handler.getKeyManager().esc) {
                    handler.getGame().thread.join(100);
                    State.paused = true;
                }
                if(handler.getKeyManager().j){
                }
         /*       if(handler.getKeyManager().atk)
                    if(this.getX() == ents.get(0).getX() && this.getY() == ents.get(0).getY())
                        this.setHealth((int) (this.getHealth() - this.damage)); */
	}
                
	@Override 
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		//g.setColor(Color.green);
                //g.fillRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), this.getHealth(), 8);
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
