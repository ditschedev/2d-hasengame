/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure;

import hasventure.display.Display;
import hasventure.gfx.Assets;
import hasventure.gfx.GameCamera;
import hasventure.input.KeyManager;
import hasventure.input.MouseManager;
import hasventure.states.GameOverState;
import hasventure.states.GameState;
import hasventure.states.IntroState;
import hasventure.states.MenuState;
import hasventure.states.PauseState;
import hasventure.states.State;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Tobias
 */
public class Game implements Runnable {

	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	public Thread thread;
        private long timer;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	public State gameState;
        private int level = 2;
        
	public State menuState;
        public State introState;
        public State pauseState;
        public State gameOver;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
                playSound("/music/bgmusic.mp3");
	}
        
        public static synchronized void playSound(final String url) {
            new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
              @Override
              public void run() {
                try {
                  Clip clip = AudioSystem.getClip();
                  AudioInputStream inputStream = AudioSystem.getAudioInputStream(Game.class.getResourceAsStream(url));
                  AudioFormat baseFormat = inputStream.getFormat();
                  AudioFormat decodeFormat = new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED,
				baseFormat.getSampleRate(),
				16,
				baseFormat.getChannels(),
				baseFormat.getChannels() * 2,
				baseFormat.getSampleRate(),
				false
			);
                  AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, inputStream);
                  clip.open(dais);
                  clip.loop(1000);
                  clip.start(); 
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                  System.err.println(e.getMessage());
                }
              }
            }).start();
        }
	
	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		gameState = new GameState(handler, this.level);
		menuState = new MenuState(handler);
                introState = new IntroState(handler);
                pauseState = new PauseState(handler);
                gameOver = new GameOverState(handler);
		State.setState(introState);
	}
	
	private void tick(){
		keyManager.tick();
		checkKey();
                if(State.paused) {
                    pauseState.tick();
		} else if(State.getState() != null)
			State.getState().tick();
	}
        
        private void checkKey(){
            if(this.keyManager.j){
                this.level = 2;
            }
        }
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw Here!
		
                if(State.getState() != null)
			State.getState().render(g);
		
		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	public void run(){
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		this.timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(this.timer >= 1000000000){
			//	System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				this.timer = 0;
			}
		}
		
		stop();
		
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public MouseManager getMouseManager(){
		return mouseManager;
	}
	
	public GameCamera getGameCamera(){
		return gameCamera;
	}
	
	public int getWidth(){
		return width;
	}
        
        public long getTimer(){
                return timer;
        }
	
	public int getHeight(){
		return height;
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
