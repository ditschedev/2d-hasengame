/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.entities.items;

import hasventure.Handler;
import hasventure.entities.creatures.Player;
import hasventure.gfx.Animation;
import hasventure.gfx.Assets;

/**
 *
 * @author Tobias
 */
public class ItemManager {
    
    //Defintions
    private Handler handler;
    private ItemEntity item;
    private Player player;
    private Animation spark;
    
    public ItemManager(Handler handler, ItemEntity item, Player player){
        this.handler = handler;
        this.item = item;
        this.player = player;
        this.spark = new Animation(160, Assets.sparks);
    }
    
    public void tick(){
            
            if(item.isUsed)
                    return;
            if(!(player.getX() <= (item.getX()+32)) || !(player.getX() >= (item.getX()-32)))
                    return;
            if(!(player.getY() <= (item.getY()+32)) || !(player.getY() >= (item.getY()-32)))
                    return;
            if(player.getMaxHealth() < player.getHealth())
                player.setHealth(player.getMaxHealth());
            
            player.setHealth(player.getMaxHealth());
            spark.tick();
            item.setUsed(true);
         //   System.out.println("Healed");
    }
}
