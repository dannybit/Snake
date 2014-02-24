package Panels;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	
	public static final String TITLE = "Snake";
	
	public static void createAndShowGUI(){
		JFrame frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainPanel panel = new MainPanel(); // CardLayout panel that switches between the start menu and other panels
		frame.setContentPane(panel);
		frame.setResizable(false);
		frame.pack();
		/*  call pack() before setLocationRelativeTo(null). 
		 * pack() computes the size of the window, 
		 * which is required to perform the centering calculation correctly
		 */
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		panel.getGamePanel().thread.start();
	}
	
	public static void main(String[] args) {
		/* Construct this GUI on the Event Dispatch Thread */
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
				public void run() {
	                createAndShowGUI();
	            }
	        });
	
		
		
	}

}
