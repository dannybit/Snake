package Panels;

import java.awt.BorderLayout;

import kuusisto.tinysound.TinySound;

import javax.swing.JPanel;


public class MainPanel extends JPanel {
	/* CardLayout panel that switches between panels */
	
	private static final long serialVersionUID = -3382263755770733986L;
	
	private StartMenu startMenu; // the start menu that contains different options such as Single Player, Multiplayer, Options etc
	private GamePanel gamePanel; // BorderLayout panel that contains the game and the side panel used to render score etc
	
	public MainPanel(){
		setLayout(new BorderLayout());
		TinySound.init();
		gamePanel = new GamePanel();
		add(gamePanel);
		/*
		setLayout(new CardLayout());
		startMenu = new StartMenu(this);
		gamePanel = new GamePanel();
		add(startMenu, "startMenu");
		add(gamePanel, "gamePanel");
		*/
	}
	
	/* Returns the BorderLayout panel that contains the game and side panel */
	public GamePanel getGamePanel(){
		return this.gamePanel;
	}
	
}
