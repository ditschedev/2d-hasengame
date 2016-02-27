/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.states;

import hasventure.Handler;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobias
 */
public class PauseState extends State{
    public PauseState(Handler handler){
        super(handler);
    }

    @Override
    public void tick() {
        try {
            handleInput();
        } catch (InterruptedException ex) {
            Logger.getLogger(PauseState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawString("paused", 40, 30);
		
        g.drawString("arrow", 12, 76);
        g.drawString("keys", 16, 84);
        g.drawString(": move", 52, 80);

        g.drawString("space", 12, 96);
        g.drawString(": action", 52, 96);

        g.drawString("F1:", 36, 112);
        g.drawString("return", 68, 108);
        g.drawString("to menu", 68, 116);
    }
    
    public void handleInput() throws InterruptedException {
        if(handler.getKeyManager().esc) {
                if(State.paused){
                    State.setPaused(false);
                    handler.getGame().thread.join(100);
                }
        }
    }
}
