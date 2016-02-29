/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.states;

import hasventure.Handler;
import hasventure.worlds.World;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.IOException;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author Tobias
 */
public class GameState extends State {
	
	private World world;
        private PauseState menu;
      //  private int lvl;
	
	public GameState(Handler handler){
		super(handler);
                this.lvl = 1;
		world = new World(handler, "res/worlds/underground/" + lvl + ".lvl");
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
                world.render(g);
                if(paused)
                    menu.render(g);
	}
        
        public int getLvl() {
            return lvl;
        }

        public void setLvl(int lvl) {
            GameState.lvl = lvl;
        }

}
