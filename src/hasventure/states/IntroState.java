/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.states;

import hasventure.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author Tobias
 */
public class IntroState extends State{
    
    private BufferedImage logo;
	
    private int alpha;
    private int ticks;
	
    private final int FADE_IN = 160;
    private final int LENGTH = 160;
    private final int FADE_OUT = 160;
    
    public IntroState(Handler handler){
        super(handler);
        ticks = 0;
        try {
            logo = ImageIO.read(getClass().getResourceAsStream("/textures/logo.png"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {
        handleInput();
		ticks++;
		if(ticks < FADE_IN) {
			alpha = (int) (255 - 255 * (1.0 * ticks / FADE_IN));
			if(alpha < 0) alpha = 0;
		}
		if(ticks > FADE_IN + LENGTH) {
			alpha = (int) (255 * (1.0 * ticks - FADE_IN - LENGTH) / FADE_OUT);
			if(alpha > 255) alpha = 255;
		}
		if(ticks > FADE_IN + LENGTH + FADE_OUT) {
			State.setState(handler.getGame().menuState);
		}
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        g.drawImage(logo, (handler.getWidth() - 128) / 2, (handler.getHeight() - 128) / 2, 128, 128, null);
        g.setColor(new Color(0, 0, 0, alpha));
        g.fillRect(0, 0, handler.getWidth(),handler.getHeight());
    }
    
    public void handleInput() {
        if(handler.getKeyManager().enter) {
            State.setState(handler.getGame().menuState);
        }
    }
    
}
