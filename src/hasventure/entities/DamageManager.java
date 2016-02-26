/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.entities;

import hasventure.Handler;
import hasventure.entities.creatures.Enemy;
import hasventure.entities.creatures.Player;

/**
 *
 * @author Tobias
 */
public class DamageManager {
        private Handler handler;
	private Player player;
        private Enemy enemy;
        private int ticker;
        
    public DamageManager(Handler handler, Player player, Enemy enemy){
            this.handler = handler;
            this.player = player;
            this.enemy = enemy;
    }
    
    public void tick(){
                ticker++;
                if(ticker >= 100){
                     //   System.out.println("Taack");
                        ticker = 0;
                }
            
            if(!(player.getX() <= (enemy.getX()+40)) || !(player.getX() >= (enemy.getX()-40)))
                return;
            if(!(player.getY() <= (enemy.getY()+40)) || !(player.getY() >= (enemy.getY()-40)))
                return;
            if(handler.getKeyManager().atk) {
                enemy.setHealth((int) (enemy.getHealth() - player.getDamage()));
            }
    }
}
