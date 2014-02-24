package Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class SidePanel extends JPanel {
	
	private GamePanel gamePanel;
	Font scoreFont = new Font("SansSerif", Font.BOLD, 20);
	
	public SidePanel(GamePanel gamePanel){
		this.gamePanel = gamePanel;
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(BoardPanel.WIDTH,BoardPanel.TILE_SIZE * 2 ));
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setColor(Color.WHITE);
		g2d.setFont(scoreFont);
		FontMetrics metrics = g2d.getFontMetrics(scoreFont);
		int fontHeight = metrics.getHeight();
		g2d.drawString("Score: " + gamePanel.getScore(), 10, BoardPanel.TILE_SIZE);

	}
}
