/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.states;

import hasventure.Handler;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Tobias
 */
public class GameOverState extends State{

    public GameOverState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        if(handler.getMouseManager().isLeftPressed())
			State.setState(handler.getGame().gameState);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(0,0,0,150));
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        g.setColor(Color.white);
        g.drawString("GAME OVER", handler.getWidth()/2, handler.getHeight()/2);
    }
    
}
