/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.entities.items;

import hasventure.Handler;
import hasventure.entities.Entity;

/**
 *
 * @author Tobias
 */

public abstract class ItemEntity extends Entity {
    
	public final int HEAL_AMOUNT = 18; 
        
        protected int heal; 
        protected boolean isUsed;
        
	public ItemEntity(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, width, height);
                heal = HEAL_AMOUNT;
                isUsed = false;
	}
        
        public abstract void setUsed(boolean used);

}
