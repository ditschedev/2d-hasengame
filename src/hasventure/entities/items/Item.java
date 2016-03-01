/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.entities.items;

import hasventure.Handler;
import hasventure.gfx.Assets;
import java.awt.Graphics;

/**
 *
 * @author tobias.dittmann
 */
public class Item extends ItemEntity{
    
    private boolean isUsed;
    
    private int ID;
    private float x;
    private float y;
    private int width;
    private int height;

    public Item(Handler handler, int id, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        this.isUsed = false;
        this.ID = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void setUsed(boolean used) {
        this.isUsed = true;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        if(isUsed)
            return;
        g.drawImage(Assets.items[ID],(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getxOffset()), width, height, null);
    }
    
}
