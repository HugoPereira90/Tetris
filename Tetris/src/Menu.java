import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Menu {

	private JFrame frame;
	private JLabel lBackground;
	private JPanel pBackground;
	private JLabel titre;
	private JLabel start;
	private JLabel quit;
	
	MouseHandler mHandler = new MouseHandler();
	
	public static void main(String[] args) {
		new Menu();
	}
	
	Menu(){
		
		pBackground = new JPanel();
		pBackground.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pBackground.setLayout(null);
		pBackground.setOpaque(false);
		
		titre = new JLabel("TETRIS",SwingConstants.CENTER);
		titre.setBounds(95, 30, 200, 50);
		titre.setFont(new Font("Impact", Font.BOLD, 60));
		titre.setForeground(Color.white);
		pBackground.add(titre);
		
		start = new JLabel("Start",SwingConstants.CENTER);
		start.setBounds(90, 150, 200, 50);
		start.setFont(new Font("Impact", Font.PLAIN, 40));
		start.setForeground(Color.white);
		pBackground.add(start);
		
		quit = new JLabel("Quit",SwingConstants.CENTER);
		quit.setBounds(90, 250, 200, 50);
		quit.setFont(new Font("Impact", Font.PLAIN, 40));
		quit.setForeground(Color.white);
		pBackground.add(quit);
		
		lBackground = new JLabel();
	    lBackground.setIcon(new ImageIcon("src/Images/bg.jpg"));
	    lBackground.setBounds(0, 0, 400, 600);
	    pBackground.add(lBackground);

	    
		frame = new JFrame();
		frame.setTitle("Menu");
		frame.setSize(400,600);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/logo.png"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    frame.add(pBackground);
	    frame.addMouseListener(mHandler);
	}
	
	public class MouseHandler implements MouseListener{
		
		Settings settings;
		
		@Override
		public void mouseClicked(MouseEvent e) {
//			System.out.println(e.getX() + " " + e.getY());
			if (e.getX() > 150 && e.getX() < 250 && e.getY() > 180 && e.getY() < 230){
				if(e.getButton()==MouseEvent.BUTTON1) {
					settings = new Settings();
					frame.setVisible(false);
				}
			} else if (e.getX() > 150 && e.getX() < 250 && e.getY() > 285 && e.getY() < 325){
				if(e.getButton()==MouseEvent.BUTTON1) {
					System.exit(0);
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
