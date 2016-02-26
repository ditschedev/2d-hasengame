/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.entities.creatures;

import hasventure.Handler;
import static sun.audio.AudioPlayer.player;

/**
 *
 * @author Tobias
 */
public class EnemyAI {
    
    // Initialize
    private Handler handler;
    private Player player;
    private Enemy enemy;
    
    //Ticker
    private int ticker;
    
    //Positions
    private float pX, pY, eX, eY;
    private boolean pLocked = false;
    
    public EnemyAI(Handler handler, Player player, Enemy enemy){
        this.handler = handler;
        this.player = player;
        this.enemy = enemy;
    }
    
    public void tick(){
            ticker++;
            if(pLocked){
                if(!(pX <= (eX+120) || pX >= (eX-120) ))
                    pLocked = false;
                if(!(pY <= (eY+120) || pY >= (eY-120) ))
                    pLocked = false;
                pX = player.getX();
                pY = player.getY();
                eX = enemy.getX();
                eY = enemy.getY();
                double dX = pX - eX;
                double dY = pY - eY;
                double direction = Math.atan(dY / dX);
                float speedX = 0;
                float speedY = 0;
                if(dX > 0)
                    speedX = enemy.speed;
                if(dX < 0)
                    speedX = -enemy.speed;
                
                if(dX > 0)
                    speedY = enemy.speed;
                if(dX < 0)
                    speedY = -enemy.speed;
                enemy.xMove = (float) (speedX * Math.cos(direction));
                enemy.yMove = (float) (speedY * Math.sin(direction));
                enemy.move();
                if(ticker >= 10 && enemy.health > 0){
                    if(!(enemy.getX() <= (player.getX()+40)) || !(enemy.getX() >= (player.getX()-40)))
                            return;
                    if(!(enemy.getY() <= (player.getY()+40)) || !(enemy.getY() >= (player.getY()-40)))
                            return;
                    player.setHealth((int) (player.getHealth() - enemy.getDamage()));
                    ticker = 0;
                }
                return;
            }
            
            if(ticker >= 100){
                pX = player.getX();
                pY = player.getY();
                eX = enemy.getX();
                eY = enemy.getY();
                if(!(pX <= (eX+120) || pX >= (eX-120) ))
                    return;
                if(!(pY <= (eY+120) || pY >= (eY-120) ))
                    return;
                pLocked = true;
                //System.out.println("Locked");
                ticker = 0;
            }
            
    }
}
