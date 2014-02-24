package Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

import java.io.File;

import javax.swing.JPanel;

import Game.TileTypes;

public class BoardPanel extends JPanel {

	private static final long serialVersionUID = -2494630259882239237L;
	

	/* size snake body, snake head, apple */
	public static final int TILE_SIZE = 30;
	
	/* we want the rows and columns to be odd so we can center the snake at the beginning of the game */
	public static final int ROWS = 17; 
	public static final int COLUMNS = 17;
	
	/* We use an array to optimize the random generation for the apple */
	private TileTypes tiles[] = new TileTypes[ROWS * COLUMNS];
	
	/* We need the GamePanel instance to get the snake and apple and handle the logic of the game (GameOver, NewGame) */
	public GamePanel gamePanel;
	
	public static final int WIDTH = ROWS * TILE_SIZE;
	public static final int HEIGHT = COLUMNS * TILE_SIZE;
	
	private Font bigMessageFont = new Font("SansSerif", Font.BOLD, 30);
	private Font smallMessageFont = new Font("SansSerif", Font.BOLD, 15);

	private String startingMessage = "Press ENTER to start the game";
	
	private String gameOverMessage = "Game Over ahahah";
	
	private String continueMessage = "Press ENTER to continue";
	
	private String pauseMessage = "Game is Paused";
	private String continuePauseMessage = "Press P to unpause";
	
	public BoardPanel(GamePanel gamePanel){
		this.gamePanel = gamePanel;
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	
	public void setTileType(TileTypes tile, int x, int y){
		tiles[ROWS * y + x] = tile;
	}
	
	public TileTypes getTileType(int x, int y){
		return tiles[ROWS * y + x];
	}
	
	/* Get the width and height of the message depending on the font,  */
	private void renderMessage(String message, Font font, Color color, Graphics2D g2d){
		FontMetrics metrics = g2d.getFontMetrics(font);
		int messageWidth =  metrics.stringWidth(message);
		int messageHeight = metrics.getHeight();
		g2d.setColor(color);
		g2d.setFont(font);
		if (font.equals(bigMessageFont))
			g2d.drawString(message, WIDTH / 2 - messageWidth/2, HEIGHT / 2 - messageHeight);
		else if (font.equals(smallMessageFont))
			g2d.drawString(message, WIDTH / 2 - messageWidth/2, HEIGHT / 2);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		drawBoard(g2d);
		for (int x = 0; x < ROWS; x++)
			for (int y = 0; y < COLUMNS; y++)
				setTileType(null,x,y);
		gamePanel.getSnake().drawSnake(g2d);
		gamePanel.getApple().drawApple(g2d);
		
		/* If the player is starting the game we want to give a welcome message */
		if (gamePanel.isNewGame()){
			renderMessage(startingMessage, bigMessageFont, Color.WHITE, g2d);
			renderMessage(continueMessage, smallMessageFont, Color.WHITE, g2d);
		}
		if (gamePanel.isGameOver()){
			renderMessage(gameOverMessage, bigMessageFont, Color.WHITE, g2d);
			renderMessage(continueMessage, smallMessageFont, Color.WHITE, g2d);
		}
		
		if (gamePanel.isPaused()){
			renderMessage(pauseMessage, bigMessageFont, Color.WHITE, g2d);
			renderMessage(continuePauseMessage, smallMessageFont, Color.WHITE, g2d);
		}
	}
	
	public void drawBoard(Graphics2D g2d){
		/* Fill the background black */
		g2d.setColor(new Color(33, 32, 61));
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		/* Draws the grid */
		g2d.setColor(Color.GRAY);
		for (int i = 1; i < ROWS; i++){
			g2d.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, COLUMNS * TILE_SIZE); 
		}
		
		for (int i = 1; i < COLUMNS; i++){
			g2d.drawLine(0, i * TILE_SIZE, ROWS * TILE_SIZE, i * TILE_SIZE);
		}
		
	}
	
}
