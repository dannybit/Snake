package Panels;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartMenu extends JPanel implements ActionListener {
	/* The start menu of the game, it uses BoxLayout */
	private static final long serialVersionUID = -1409362852562569344L;
	
	JButton bSinglePlayer, bMultiPlayer, bHelp;
	MainPanel mainPanel;
	CardLayout cl;
	
	public StartMenu(MainPanel mainPanel){
		/* Takes the panel that uses CardLayout so we can call the CardLayout methods */
		this.mainPanel = mainPanel;
		cl = (CardLayout) mainPanel.getLayout();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.BLACK);
		/* The top image on the start screen */
		ImagePanel image = new ImagePanel();
		
		/* sets up the buttons */
		bSinglePlayer = new JButton("SINGLEPLAYER");
		bSinglePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		bSinglePlayer.setPreferredSize(new Dimension(200, 50));
		bSinglePlayer.setMaximumSize(new Dimension(200, 50));
		bSinglePlayer.setBackground(Color.GREEN);
		bSinglePlayer.setFont(new Font("Serif", Font.BOLD, 14));
		bSinglePlayer.addActionListener(this);
		
		bMultiPlayer = new JButton("MULTIPLAYER");
		bMultiPlayer.setBackground(Color.GREEN);
		bMultiPlayer.setPreferredSize(new Dimension(200, 50));
		bMultiPlayer.setMaximumSize(new Dimension(200, 50));
		bMultiPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		bMultiPlayer.setFont(new Font("Serif", Font.BOLD, 14));
		
		bHelp = new JButton("HELP");
		bHelp.setBackground(Color.GREEN);
		bHelp.setPreferredSize(new Dimension(200, 50));
		bHelp.setMaximumSize(new Dimension(200, 50));
		bHelp.setAlignmentX(Component.CENTER_ALIGNMENT);
		bHelp.setFont(new Font("Serif", Font.BOLD, 14));
		
		/* Adds the buttons with rigid area for the space between them */
		add(Box.createRigidArea(new Dimension(300, 50)));
		add(image);
		add(Box.createRigidArea(new Dimension(300, 25)));
		add(bSinglePlayer);
		add(Box.createRigidArea(new Dimension(300, 25)));
		add(bMultiPlayer);
		add(Box.createRigidArea(new Dimension(300, 25)));
		add(bHelp);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bSinglePlayer){
			cl.next(mainPanel);
			mainPanel.getGamePanel().thread.start();
		} 
		
	}
	
	
}
