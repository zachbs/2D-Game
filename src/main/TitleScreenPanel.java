package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class TitleScreenPanel extends JPanel{
	public JButton startButton, optionButton;
	
	
	public TitleScreenPanel() {
		
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Welcome to the Game!", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        this.startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 27));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(Color.BLACK);
        this.optionButton = new JButton("optionButton");
        optionButton.setFont(new Font("Arial", Font.BOLD, 27));
        optionButton.setForeground(Color.WHITE);
        optionButton.setBackground(Color.BLACK);

        
        this.add(titleLabel, BorderLayout.CENTER);
        
        JPanel panelX = new JPanel();
        panelX.setBackground(Color.BLACK);
        panelX.setLayout(new BoxLayout(panelX, BoxLayout.X_AXIS)); // Stack buttons vertically
        JPanel panelY = new JPanel();
        panelY.setBackground(Color.BLACK);
        panelY.setLayout(new BoxLayout(panelY, BoxLayout.Y_AXIS)); // Stack buttons vertically
       
        panelX.add(Box.createRigidArea(new Dimension(250,0)));
        panelY.add(startButton);
        panelY.add(optionButton);
        panelY.add(Box.createRigidArea(new Dimension(0,100)));
        panelX.add(panelY);
        this.add(panelX, BorderLayout.SOUTH);
	}
	
}
