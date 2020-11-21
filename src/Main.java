import java.awt.Component;

import javax.swing.JFrame;

public class Main {
	
	public Main() {
		JFrame frame = new JFrame();							//establishes the window in which the game will be played
		GamePanel gamepanel = new GamePanel();					

		
		frame.add(gamepanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("The Amazing Wiggler");
		
		
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
	}
	
	public static void main(String[] args) {
		
		new Main();
		
	}

}
