/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.states;

import hasventure.Handler;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Tobias
 */
public class MenuState extends State {

	public MenuState(Handler handler){
		super(handler);
	}

	@Override
	public void tick() {
		if(handler.getMouseManager().isLeftPressed() && handler.getMouseManager().getMouseX() >= 600 && handler.getMouseManager().getMouseX() <= 800 && handler.getMouseManager().getMouseY() >= 600 && handler.getMouseManager().getMouseY() <= 660)
			State.setState(handler.getGame().gameState);
	}

	@Override
	public void render(Graphics g) {
	//	g.setColor(Color.RED);
	//	g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 8, 8);
                Font f = new Font("Avenir", Font.PLAIN, 54);
                g.setFont(f);
                g.drawString("HASVENTURE 2.0", 140, 100);
                g.setColor(Color.BLACK);
                g.fillRoundRect(600, 600, 200, 60, 3, 3);
                f = new Font("Verdana", Font.BOLD, 20);
                g.setFont(f);
                g.setColor(Color.WHITE);
                g.drawString("START GAME", 625, 640);
	}
	
}
