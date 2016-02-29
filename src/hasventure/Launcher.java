/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure;

import java.io.IOException;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

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
