/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.states;

import hasventure.Handler;
import hasventure.gfx.ImageLoader;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Tobias
 */
public class MenuState extends State {
        
        private Font f;
        
        private int alpha;
        private int ticks;
	
        private final int FADE_IN = 80;
        
	public MenuState(Handler handler){
		super(handler);
	}

	@Override
	public void tick() {
		if(handler.getMouseManager().isLeftPressed())
			State.setState(handler.getGame().gameState);
                ticks++;
		if(ticks < FADE_IN) {
			alpha = (int) (255 - 255 * (1.0 * ticks / FADE_IN));
			if(alpha < 0) alpha = 0;
		}
	}

	@Override
	public void render(Graphics g) {
            
            BufferedImage logo = ImageLoader.loadImage("/textures/menu/logo.png");
            BufferedImage text = ImageLoader.loadImage("/textures/menu/startit.png");
            BufferedImage text_green = ImageLoader.loadImage("/textures/menu/startit-green.png");
            
            g.drawImage(logo, (handler.getWidth() - 380)/2, (int) ((handler.getHeight()-90) / 2), 380, 90, null);
            
            g.drawImage(text, (int) ((handler.getWidth() - (380/1.3))/2), (int) ((handler.getHeight()-90) / 1.6), (int) (380/1.3), (int) (90/1.3), null);
            g.drawImage(text_green, (int) ((handler.getWidth() - (380/1.3))/2), (int) ((handler.getHeight()-90) / 1.6), (int) (380/1.3), (int) (90/1.3), null);
            
            g.setColor(new Color(0, 0, 0, alpha));
            g.fillRect(0, 0, handler.getWidth(),handler.getHeight());
	}
	
}
