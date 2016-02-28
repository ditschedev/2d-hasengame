/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure;

/**
 *
 * @author Tobias
 */
public class Launcher {

	public static void main(String[] args){
		Game game = new Game("Hasventure!", 256*2, 256*2);
		game.start();
	}
	
}
