/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.states;

import hasventure.Handler;
import hasventure.worlds.World;
import java.awt.Graphics;

/**
 *
 * @author Tobias
 */
public class GameState extends State {
	
	private World world;
        private PauseState menu;
	
	public GameState(Handler handler){
		super(handler);
		world = new World(handler, "res/worlds/underground/1.lvl");
                menu = new PauseState(handler);
		handler.setWorld(world);
	}
	
	@Override
	public void tick() {
                if(!paused) {
                    world.tick();
                } else {
                    menu.tick();
                }
	}

	@Override
	public void render(Graphics g) {
                if(!paused) {
                    world.render(g);
                } else {
                    menu.render(g);
                }
	}

}
