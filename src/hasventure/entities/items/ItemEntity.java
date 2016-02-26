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
	
	public ItemEntity(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, width, height);
	}

}
