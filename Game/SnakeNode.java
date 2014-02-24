package Game;

import java.awt.Point;


public class SnakeNode {
	
	private Point point;
	private SnakeNode next;
	
	public SnakeNode(Point point, SnakeNode next){
		this.point = point;
		this.next = next;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public SnakeNode getNext() {
		return next;
	}

	public void setNext(SnakeNode next) {
		this.next = next;
	}
	
	@Override
	public String toString(){
		return "(" + point.x + "," + point.y + ")";
	}
	
}
