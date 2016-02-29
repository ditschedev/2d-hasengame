/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.states;

import hasventure.Handler;
import hasventure.gfx.ImageLoader;
import java.awt.Color;
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
        g.setColor(new Color(255,255,255,125));
        g.fillRect(0, 0, 512, 512);
        g.drawImage(ImageLoader.loadImage("/textures/menu/paused.png"), (512/2)-(128/2), (512/2)-20, 128, 40, null);
    }
    
    public void handleInput() throws InterruptedException {
        if(handler.getKeyManager().esc) {
                if(State.paused){
                    handler.getGame().thread.join(100);
                    State.setPaused(false);
                }
        }
    }
}
