import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Settings {

	private JFrame frame;
	private JLabel lBackground;
	private JPanel pBackground;
	private JLabel title;
	private JLabel level;
	private JLabel up;
	private JLabel down;
	private JLabel game;
	private JLabel back;
	MouseHandler mHandler = new MouseHandler();
	
	int lvl = 1;
	
	Settings(){
		
		pBackground = new JPanel();
		pBackground.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pBackground.setLayout(null);
		pBackground.setOpaque(false);
		
	    title = new JLabel("Select the difficulty",SwingConstants.CENTER);
		title.setBounds(70, 30, 250, 50);
		title.setFont(new Font("Impact", Font.PLAIN, 30));
		title.setForeground(Color.white);
		pBackground.add(title);
		
		level = new JLabel("1",SwingConstants.CENTER);
		level.setBounds(85, 130, 200, 50);
		level.setFont(new Font("Impact", Font.PLAIN, 40));
		level.setForeground(Color.orange);
		pBackground.add(level);
		
		up = new JLabel(">",SwingConstants.CENTER);
		up.setBounds(135, 130, 200, 50);
		up.setFont(new Font("Impact", Font.PLAIN, 40));
		up.setForeground(Color.white);
		pBackground.add(up);
		
		down = new JLabel("<",SwingConstants.CENTER);
		down.setBounds(35, 130, 200, 50);
		down.setFont(new Font("Impact", Font.PLAIN, 40));
		down.setForeground(Color.white);
		pBackground.add(down);
		
		game = new JLabel("Launch",SwingConstants.CENTER);
		game.setBounds(90, 200, 200, 50);
		game.setFont(new Font("Impact", Font.PLAIN, 40));
		game.setForeground(Color.white);
		pBackground.add(game);
		
		back = new JLabel("Back",SwingConstants.CENTER);
		back.setBounds(90, 280, 200, 50);
		back.setFont(new Font("Impact", Font.PLAIN, 30));
		back.setForeground(Color.white);
		pBackground.add(back);
		
		lBackground = new JLabel();
	    lBackground.setIcon(new ImageIcon("src/Images/bg.jpg"));
	    lBackground.setBounds(0, 0, 400, 600);
	    pBackground.add(lBackground);
		
		frame = new JFrame();
		frame.setTitle("Settings");
		frame.setSize(400,600);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/logo.png"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    frame.add(pBackground);
	    frame.addMouseListener(mHandler);
		
	}
	
public class MouseHandler implements MouseListener{
		
		Menu menu;
		Game game;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getX() > 230 && e.getX() < 250 && e.getY() > 170 && e.getY() < 195){
				if(e.getButton()==MouseEvent.BUTTON1) {
					if (lvl < 5) {
						lvl++;
						level.setText("" + lvl);
					}	
				}
			} else if (e.getX() > 130 && e.getX() < 150 && e.getY() > 170 && e.getY() < 195){
				if(e.getButton()==MouseEvent.BUTTON1) {
					if (lvl > 1) {
						lvl--;
						level.setText("" + lvl);
					}	
				}
				
			} else if (e.getX() > 140 && e.getX() < 260 && e.getY() > 240 && e.getY() < 270){
				if(e.getButton()==MouseEvent.BUTTON1) {
					frame.setVisible(false);
					game = new Game(lvl);
				}
			} else if (e.getX() > 165 && e.getX() < 230 && e.getY() > 320 && e.getY() < 350){
				if(e.getButton()==MouseEvent.BUTTON1) {
					menu = new Menu();
					frame.setVisible(false);
				}
			}	
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
	
}
