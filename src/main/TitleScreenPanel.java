package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class TitleScreenPanel extends JPanel{
	public JButton startButton, optionButton, loadButton;
	BufferedImage background;
	
	
	public TitleScreenPanel() {
		try {
		background = null;
		background = ImageIO.read(getClass().getResourceAsStream("/res/2dGameBackground.png"));
		} catch(Exception e) {
			e.printStackTrace();
		}
        this.setBackground(Color.BLACK);
        this.startButton = new JButton("Start New Game");
        this.add(startButton, null);
        this.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Welcome to the Game!", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        
        startButton.setFont(new Font("Arial", Font.BOLD, 27));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(Color.BLACK);
        this.optionButton = new JButton("Options");
        optionButton.setFont(new Font("Arial", Font.BOLD, 27));
        optionButton.setForeground(Color.WHITE);
        optionButton.setBackground(Color.BLACK);
        this.loadButton = new JButton("Load Saved Game");
        loadButton.setFont(new Font("Arial", Font.BOLD, 27));
        loadButton.setForeground(Color.WHITE);
        loadButton.setBackground(Color.BLACK);

        
        this.add(titleLabel, BorderLayout.CENTER);
        
        JPanel panelX = new JPanel();
        panelX.setBackground(Color.green);
        panelX.setLayout(new BoxLayout(panelX, BoxLayout.X_AXIS)); // Stack buttons vertically
        JPanel panelY = new JPanel();
        panelY.setBackground(Color.green);
        panelY.setLayout(new BoxLayout(panelY, BoxLayout.Y_AXIS)); // Stack buttons vertically
        JPanel panelX2 = new JPanel();
        panelX2.setBackground(Color.green);
        panelX2.setLayout(new BoxLayout(panelX2, BoxLayout.X_AXIS));
        
        
        panelX.add(Box.createRigidArea(new Dimension(100,0)));
        panelX2.add(Box.createRigidArea(new Dimension(200,0)));
        panelX2.add(startButton);
        panelY.add(panelX2);
        panelY.add(optionButton);
      //  panelY.add(loadButton);
        panelY.add(Box.createRigidArea(new Dimension(0,20)));
        panelX.add(panelY);
        this.add(panelX, BorderLayout.SOUTH);
	}
	 @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        // Draw the image, scaled to fill the entire panel
	        if (background != null) {
	            g.drawImage(background, 0, 0, getWidth(), getHeight() + 100, this);
	        }
	    }
	
}
