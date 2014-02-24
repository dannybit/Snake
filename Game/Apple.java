package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;

import Panels.BoardPanel;
import Panels.GamePanel;


public class Apple {
	Random random;
	public Point pos;
	private GamePanel gamePanel;
	private BoardPanel boardPanel;
	
	public Apple(GamePanel gamePanel){
		this.gamePanel = gamePanel;
		this.boardPanel = gamePanel.getBoard();
		random = new Random();
		pos = new Point();
		generateRL(gamePanel.getSnake());
	}
	
	/*	Generates a random location for the apple that is not in the snake.
	 *  We could just take a random location until the apple is not in the snake, but this process could be slow and we have no guarantee that we might get it.
	 */
	public void generateRL(Snake snake){
		int index = random.nextInt(BoardPanel.ROWS * BoardPanel.COLUMNS - snake.getSize());
		int x = 0, y = 0;
		int xPos = 0;
		int yPos = 0;
		int counter = -1;
		boolean breakLoop = false;
		for ( x = 0; x < BoardPanel.ROWS; x++){
			for ( y = 0; y < BoardPanel.COLUMNS; y++){
				if (!inSnake(new Point(x, y), snake))
						counter++;
				if (counter == index){
					xPos = x;
					yPos = y;
					breakLoop = true;
					break;
				}
			}
			if (breakLoop)
				break;
		}
		pos.setLocation(xPos, yPos);
		
	}
	
	private boolean inSnake(Point p, Snake snake){
		SnakeNode curr = snake.getFirst();
		while (curr != null){
			if (curr.getPoint().equals(p))
				return true;
			curr = curr.getNext();
		}
		return false;
	}
	
	public void drawApple(Graphics2D g2d){
			g2d.setColor(Color.RED);
			g2d.fillRect(pos.x * BoardPanel.TILE_SIZE, pos.y * BoardPanel.TILE_SIZE, BoardPanel.TILE_SIZE, BoardPanel.TILE_SIZE);
			g2d.setColor(Color.ORANGE);
			g2d.drawRect(pos.x * BoardPanel.TILE_SIZE, pos.y * BoardPanel.TILE_SIZE , BoardPanel.TILE_SIZE, BoardPanel.TILE_SIZE);
	}
	
	public Point getPos(){
		return this.pos;
	}
	
}
