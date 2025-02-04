package main;

import java.awt.CardLayout;
import java.io.File;

import javax.swing.JFrame;

import javax.swing.JPanel;



public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2d Adventure");
		CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // Title screen panel
        TitleScreenPanel titlePanel = new TitleScreenPanel();
		GamePanel gamePanel = new GamePanel();
	    // Add panels to the CardLayout
        mainPanel.add(titlePanel, "TitleScreen");
        mainPanel.add(gamePanel, "GamePanel");

        // Button action to switch to the game panel
        titlePanel.startButton.addActionListener(e -> {
        	cardLayout.show(mainPanel, "GamePanel"); 
        	gamePanel.requestFocusInWindow();
        	gamePanel.setUpGame();
        	gamePanel.startGameThread();
        	
        });
        
        titlePanel.loadButton.addActionListener(e -> {
        	String path = "/Users/zbsmi/CSCI 1301/2d Game/src/maps/save.txt";
        	File file = new File(path);
        	if (file.exists()) {
        	cardLayout.show(mainPanel, "GamePanel"); 
        	gamePanel.loadGame = true;
        	gamePanel.requestFocusInWindow();
        	gamePanel.setUpGame();
        	gamePanel.startGameThread();
        	}
        });
        
        

        // Add the main panel to the frame
        window.add(mainPanel);
        window.setVisible(true);
		//window.add(gamePanel);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		
	}

}
