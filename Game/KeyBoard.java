package Game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Panels.BoardPanel;
import Panels.GamePanel;

public class KeyBoard implements KeyListener{
	
	private int key;
	private BoardPanel board;
	private GamePanel gamePanel;
	private Snake snake;
	
	public KeyBoard(GamePanel gamePanel){
		this.gamePanel = gamePanel;
		this.board = gamePanel.getBoard();
		snake = gamePanel.getSnake();
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		switch (e.getKeyCode()){
			
			case KeyEvent.VK_ENTER:
				if (gamePanel.isNewGame()){
					gamePanel.setNewGame(false);
					
				}
				if (gamePanel.isGameOver()){
					gamePanel.reset();
				}
				break;
			
			case KeyEvent.VK_P:
				if (gamePanel.isPaused()){
					System.out.println("isPaused");
					gamePanel.setPaused(false);
				}
				else {
					if (!gamePanel.isGameOver() && !gamePanel.isNewGame())
						gamePanel.setPaused(true);
				}
				break;
			case KeyEvent.VK_A:
				if (gamePanel.isRunning() && !gamePanel.isGameOver()){
					if (snake.getDirection() != Directions.RIGHT && snake.getDirection() != Directions.LEFT)
						gamePanel.directionQueue.offer(Directions.LEFT);
				}
				break;
			case KeyEvent.VK_D:
				if (gamePanel.isRunning() && !gamePanel.isGameOver()){
					if (snake.getDirection() != Directions.LEFT && snake.getDirection() != Directions.RIGHT)
						gamePanel.directionQueue.offer(Directions.RIGHT);
					}
					break;

			case KeyEvent.VK_W:
				if (gamePanel.isRunning() && !gamePanel.isGameOver()){
					if (snake.getDirection() != Directions.DOWN && snake.getDirection() != Directions.UP)
						gamePanel.directionQueue.offer(Directions.UP);
					}
				break;
				
			case KeyEvent.VK_S:
				if (gamePanel.isRunning() && !gamePanel.isGameOver()){
					if (snake.getDirection() != Directions.UP && snake.getDirection() != Directions.DOWN)
						gamePanel.directionQueue.offer(Directions.DOWN);
					}
				break;
			}
	}
	@Override
	public void keyReleased(KeyEvent e){
		key = e.getKeyCode();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	

}
