import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game{

	private GameArray grid;
	public JFrame frame;
	private JPanel pBackground;
	
	Game(int level){
		
		pBackground = new JPanel();
		pBackground.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pBackground.setLayout(null);
		pBackground.setOpaque(false);

		frame = new JFrame();
		frame.setTitle("Game");
		frame.setSize(600,640);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/logo.png"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    
	    grid = new GameArray(level);
	    frame.addKeyListener(grid);
		frame.add(grid);
		frame.setVisible(true);
		
	}
	
}
