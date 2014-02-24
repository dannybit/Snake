package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import Panels.BoardPanel;
import Panels.GamePanel;


public class Snake {
	
	/* The minimum size that the snake needs to be at the beginning of the game */
	public static final int MIN_SIZE = 3;
	private SnakeNode first; // tail of snake
	private SnakeNode last; // head of snake
	private int size;
	private Directions direction;
	private BoardPanel board;
	private GamePanel gamePanel;
	private boolean appleEaten;
	
	public Snake(GamePanel gamePanel){
		this.gamePanel = gamePanel;
		this.board = gamePanel.getBoard();
		first = null;
		last = null;
		reset();
	}
	
	private void drawEyes(Graphics2D g2d){
		Point headPoint = last.getPoint();
		int x = headPoint.x;
		int y = headPoint.y;
		g2d.setColor(Color.RED);
		if (direction == Directions.RIGHT || direction == Directions.LEFT){
			g2d.fillOval((x * BoardPanel.TILE_SIZE) + BoardPanel.TILE_SIZE / 2, (y * BoardPanel.TILE_SIZE) +  BoardPanel.TILE_SIZE / 4, 5, 5);
			g2d.fillOval((x * BoardPanel.TILE_SIZE) + BoardPanel.TILE_SIZE / 2, (y * BoardPanel.TILE_SIZE) +  BoardPanel.TILE_SIZE / 2, 5, 5);
		}
		else {
			g2d.fillOval((x * BoardPanel.TILE_SIZE) + BoardPanel.TILE_SIZE / 4, (y * BoardPanel.TILE_SIZE) +  BoardPanel.TILE_SIZE / 2, 5, 5);
			g2d.fillOval((x * BoardPanel.TILE_SIZE) + BoardPanel.TILE_SIZE / 2, (y * BoardPanel.TILE_SIZE) +  BoardPanel.TILE_SIZE / 2, 5, 5);
		}
		
	}
	
	public void addNode(){
		/* set point to default because when move is called, this node will contain the current first point*/
		SnakeNode node = new SnakeNode(new Point(), first);
		first = node;
		size++;
	}
	
	private void startSnake(){
		
		first = new SnakeNode(new Point(BoardPanel.ROWS / 2  - (MIN_SIZE / 2), BoardPanel.COLUMNS / 2), null);
		SnakeNode curr = first;
		/* For each node, subtract i from the Snake x position */
		for (int i = 1; i < MIN_SIZE; i++){
			curr.setNext(new SnakeNode(new Point(first.getPoint().x + i, first.getPoint().y), null));
			if (first == curr)
				board.setTileType(TileTypes.SNAKEHEAD, curr.getPoint().x, curr.getPoint().y);
			else
				board.setTileType(TileTypes.SNAKEBODY, curr.getPoint().x, curr.getPoint().y);
			curr = curr.getNext();
			size++;
		}
		last = curr;
		direction = Directions.RIGHT;
	}
	
	public Directions getDirection(){
		return this.direction;
	}
	
	public void setDirection(Directions direction){
		this.direction = direction;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public void reset(){
		first = null;
		last = null;
		size = 0;
		startSnake();
	}
	
	
	@Override
	public String toString(){
		SnakeNode curr = first;
		String result = "";
		while (curr != null){
			result += curr.toString() + "\n";
			curr = curr.getNext();
		}
		return result;
		
	}
	
	public void move(){
		Point headPoint = new Point(last.getPoint());
		if (!gamePanel.directionQueue.isEmpty())
			direction = gamePanel.directionQueue.poll();
		switch (direction){
			case RIGHT:
				headPoint.x++;
				break;
			case LEFT:
				headPoint.x--;
				break;
			case UP:
				headPoint.y--;
				break;
			case DOWN:
				headPoint.y++;
				break;
		}
		if (checkWallCollision(headPoint) || checkBodyCollision(headPoint)){
			gamePanel.setGameOver(true);
			return;
		}
		
		if (checkAppleCollision(headPoint)){
			appleEaten = true;
			addNode();
			gamePanel.addScore(1);
		}
		SnakeNode curr = first;
		while (curr.getNext() != null){
			curr.setPoint(new Point(curr.getNext().getPoint()));
			curr = curr.getNext();
		}
		curr.setPoint(headPoint);
		if (appleEaten){
			gamePanel.getApple().generateRL(this);
			appleEaten = false;
		}
		gamePanel.directionQueue.clear();
	}
	

	private boolean checkWallCollision(Point head){
		if (head.x >= BoardPanel.ROWS || head.x < 0 || head.y >= BoardPanel.COLUMNS || head.y < 0){
			return true;
		}
		return false;
	}
	
	private boolean checkBodyCollision(Point head){
		SnakeNode curr = first.getNext();
		while (curr != null){
			if (head.equals(curr.getPoint()))
				return true;
			curr = curr.getNext();
		}
		return false;
	}
	
	private boolean checkAppleCollision(Point head){
		if (head.equals(gamePanel.getApple().getPos())){
			return true;
		}
		return false;
	}
	
	public void drawSnake(Graphics2D g2d){
		SnakeNode curr = first;
		while (curr != null){
			g2d.setColor(Color.GREEN);
			g2d.fillRect(curr.getPoint().x * BoardPanel.TILE_SIZE, curr.getPoint().y * BoardPanel.TILE_SIZE, BoardPanel.TILE_SIZE, BoardPanel.TILE_SIZE);
			g2d.setColor(Color.BLACK);
			g2d.drawRect(curr.getPoint().x * BoardPanel.TILE_SIZE, curr.getPoint().y * BoardPanel.TILE_SIZE, BoardPanel.TILE_SIZE, BoardPanel.TILE_SIZE);
			curr = curr.getNext();
		}
		drawEyes(g2d);
		
	}
	
	public SnakeNode getFirst(){
		return this.first;
	}

}

