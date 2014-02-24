package Panels;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayDeque;
import java.util.Queue;

import javax.swing.JPanel;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import Game.Apple;
import Game.Directions;
import Game.KeyBoard;
import Game.Snake;
   
public class GamePanel extends JPanel implements Runnable{
	/* Borderlayout that contains the game in the center and the sidepanel on top
	 *  also contains the logic of the game  */
	private static final long serialVersionUID = -4167903465205638138L;
	private BoardPanel boardPanel;
	private SidePanel  sidePanel;
	public Thread thread;
	private int score;
	private KeyBoard keyboard;
	private Snake snake;
	private Apple apple;
	
	private boolean running;
	private boolean gameOver;
	private boolean newGame;
	private boolean paused;
	
	private boolean twoPlayer = true;
	
	private Sound eatingSound;
	
	public Queue<Directions> directionQueue;
	
	public GamePanel(){
		setLayout(new BorderLayout());
		/* Panel where the game is rendered */
		boardPanel = new BoardPanel(this);
		add(boardPanel, BorderLayout.CENTER);
		/* Panel to render the score, time, lives */
		sidePanel = new SidePanel(this);
		add(sidePanel, BorderLayout.NORTH); // used to render score 
		thread = new Thread(this);
		directionQueue = new ArrayDeque<Directions>();
		this.snake = new Snake(this);
		apple = new Apple(this);
		keyboard = new KeyBoard(this);
		addKeyListener(keyboard);
		newGame = true;
		eatingSound = TinySound.loadSound(new File("res/adios.wav"));
		reset();
	}
	
	public BoardPanel getBoard(){
		return this.boardPanel;
	}
	
	public void reset(){
		score = 0;
		running = true;
		gameOver = false;
		snake.reset();
		apple.generateRL(snake);
	}
	
	@Override
	public void run(){
		requestFocusInWindow();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double ns = 1000000000.0 / 10.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		while (true){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1){
				updateGame();
				boardPanel.repaint();
				sidePanel.repaint();
				updates++;
				delta--;
			}
			frames++;
			if (System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				updates = 0;
				frames = 0;
		}
	}
	}
	
	public int getScore(){
		return this.score;
	}
	
	public void addScore(int i){
		this.score += i;
		eatingSound.play();
	}
	
	public void resetScore(){
		score = 0;
	}
	
	public boolean isRunning(){
		return this.running;
	}
	
	public boolean isGameOver(){
		return this.gameOver;
	}
	
	public void setGameOver(boolean gameOver){
		this.gameOver = gameOver;
	}
	
	public void setPaused(boolean paused){
		this.paused = paused;
	}
	
	public boolean isPaused(){
		return this.paused;
	}
	
	public Snake getSnake(){
		return this.snake;
	}
	
	public Apple getApple(){
		return this.apple;
	}
	
	public boolean isNewGame(){
		return this.newGame;
	}
	
	public void setNewGame(boolean newGame){
		this.newGame = newGame;
	}
	
	public void updateGame(){
		if (!gameOver && !newGame && !paused){
			snake.move();
		}
	}
	
}
