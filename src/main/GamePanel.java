package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.EntitySetter;
import entity.Gun;
import entity.Player;
import object.ObjectManager;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	//Screen Settings 
	final int originalTileSize = 16; //16x16
	final int scale = 3;
	final public int tileSize = originalTileSize * scale; // 48x48
	final public int maxScreenCol = 16;
	final public int maxScreenRow = 12;
	final public int screenWidth = maxScreenCol * tileSize; // 768
	final public int screenHeight = maxScreenRow * tileSize; // 576
	
	// World Settings
	final public int maxWorldColumn = 50;
	final public int maxWorldRow = 50;
	final public int maxWorldWidth = maxWorldColumn * tileSize;
	final public int maxWorldHeight = maxWorldRow * tileSize;
	public int fpsCount = 0;
	public Boolean gameStatePlay = true;
	
	
	// Start new game or load old one;
	public boolean loadGame = false;
	
	Thread gameThread;
	public KeyHandler keyH = new KeyHandler();
	public MouseHandler mouseH = new MouseHandler();
	
	public Player player = new Player(this);
	public EntitySetter entSet = new EntitySetter(this,player);
	Sound music = new Sound();
	Sound SE = new Sound();
	public ObjectManager objMan = new ObjectManager(this);
	Gun gun = new Gun(this);
	
	TileManager tileManager = new TileManager(this,player);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public InventoryManager invMan = new InventoryManager(this);
	public UI ui = new UI(this);
	public Saver saver = new Saver(this);
	Loader loader = new Loader(this);
	// FPS
	int fps = 60;
	
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.addMouseListener(mouseH);
		this.setFocusable(true);
		
		
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	public void setUpGame() {
		this.playMusic(0);
		if (loadGame == true) {
			loader.loadGame();
			
		} else {
			invMan.setUpImages();
			player.loadInventory();
		}
	}
	
	@Override
	public void run() {
		double drawInterval = 1000000000/fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		boolean disablePause = false;
		while (gameThread != null) {
			
			currentTime = System.nanoTime();
			timer += (currentTime - lastTime);
			delta += (currentTime - lastTime)/drawInterval;
			lastTime = currentTime;
			
			if (delta >= 1) {
				if (disablePause != true && keyH.escapePressed && ui.battleScreenOn == false) {
					if (gameStatePlay == true) {
					gameStatePlay = false;
					
					ui.showMessage("Game Paused");
					} else if (ui.gameOver != true){
						gameStatePlay = true;
					}
					disablePause = true;
				} else if (keyH.escapePressed == false) {
					disablePause = false;
				}
				// update character location
				update();
			
			
				// draw redraw the screen
				repaint();
				delta--;
				drawCount++;
				delta = Math.min(delta, 2);
				
			}
			if (timer >= 1000000000) {
				if (drawCount > 60) {
					drawCount = 60;
				}
				fpsCount = drawCount;
				//System.out.println("Fps" + drawCount);
				//System.out.println(" player (x,y) = (" + player.worldX  + "," + player.worldY + ")" );
				//System.out.println("WorldX, screenx = " + player.worldX + ", " + player.screenX);
				drawCount = 0;
				timer = 0;
				
			}
		}
		
	}
	
	public void update() {
		if (gameStatePlay && invMan.inventoryOn == false) {
			player.update(0);
			gun.update();
			entSet.update();
			
		} 
		if (gameStatePlay) {
			invMan.update();
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		Graphics2D g2 = (Graphics2D)g;
		if (ui.battleScreenOn != true && ui.gameOver != true) {
			tileManager.draw(g2);
			
			objMan.draw(g2);
			player.draw(g2);
			entSet.draw(g2);
			gun.draw(g2);
			ui.draw(g2);
			invMan.draw(g2);
			
			
		} else {
			ui.draw(g2);
		}
		g2.dispose();
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.start();
		music.loop();
		
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE (int i) {
		SE.setFile(i);
		SE.start();
	}
}
