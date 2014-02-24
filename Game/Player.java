package Game;

import Panels.GamePanel;

public class Player {
	private Snake snake;
	private int score;
	private int number;
	
	public Player(GamePanel gamePanel, int number){
		snake = new Snake(gamePanel);
		score = 0;
		this.number = number;
	}
}
