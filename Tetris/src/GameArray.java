
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;


public class GameArray extends JPanel implements KeyListener {
	
	private JLabel lScore;

	
	Color[][] background = new Color[GRID_HEIGHT][GRID_WIDTH];
	Color[][] nextBackground = new Color[40][10];
	
	public static final int GRID_WIDTH = 10;
	public static final int GRID_HEIGHT = 20;
	public static final int BLOCK_SIZE = 30;
	private Timer loop;

	int level;
	int score = 0; 
	
	boolean pause = false;
	
	Piece block;
	Piece nextBlock;
	Piece[] blocks;
	
	Random rand;
	
	GameArray(){
		
	}
	
	GameArray(int level){
		
			this.level =level;
			
			rand = new Random();
			
			selectRandomBlock();

		    loop = new Timer(1000/level, new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent ae) {
		          		        	
		        	if(pause == false) {
			        	if(isGameOver() == false) {
				            if(isBlockFalling()) {
					            repaint();
				            } else {
				            	blockIntoBackground();
				    			removeLine();
				    			selectRandomBlock();
				    			repaint();
				            }
			        	}
		        	}
		        }
		    });
		    loop.start();
		
	}
	
	protected void paintComponent(Graphics g) {
		
		Font fonte = new Font("TimesRoman ",Font.BOLD,30);
		Font fonte1 = new Font("TimesRoman ",Font.BOLD,100);
		
		super.paintComponent(g);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		
		if (pause == true) {
		    g.setColor(Color.white);
			g.setFont(fonte1);
			g.drawString("Pause", 150, 150);
			
		    g.setColor(Color.white);
			g.setFont(fonte);
			g.drawString("Resume (press p)", 175, 300);
			
		    g.setColor(Color.white);
			g.setFont(fonte);
			g.drawString("Restart (press r)", 175, 400);
			
			g.setColor(Color.white);
			g.setFont(fonte);
			g.drawString("Quit (press q)", 175, 500);
			
		} else if (isGameOver()) {
		 	g.setColor(Color.white);
			g.setFont(fonte1);
			g.drawString("Game Over", 25, 150);
			
			g.setColor(Color.white);
			g.setFont(fonte);
			g.drawString("Score :", 175, 225);
		    g.drawString(String.valueOf(score), 300, 225);
				
			g.setColor(Color.white);
			g.setFont(fonte);
			g.drawString("Restart (press r)", 175, 400);
			
			g.setColor(Color.white);
			g.setFont(fonte);
			g.drawString("Quit (press q)", 175, 500);
		} else {
			block.render(g, BLOCK_SIZE, block.getX(), block.getY());
			
			for(int row = 0; row < background.length; row++) {
				for(int col = 0; col < background[row].length; col++) {
					if(background[row][col] != null) {
						g.setColor(background[row][col]);
						g.fillRect(col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
					}
				}
			}
			
			//draw the grid
			g.setColor(Color.white);
			for(int row = 0; row < GRID_HEIGHT + 1; row++) {
				g.drawLine(0, BLOCK_SIZE * row, BLOCK_SIZE * GRID_WIDTH, BLOCK_SIZE * row);
			}
			for(int col = 0; col < GRID_WIDTH + 1; col++) {
				g.drawLine(col * BLOCK_SIZE, 0, col * BLOCK_SIZE, BLOCK_SIZE * GRID_HEIGHT);
			}
			
			//draw the score
			g.setColor(Color.white);
			g.setFont(fonte);
			g.drawString("Score :", 325, 150);
		    g.drawString(String.valueOf(score), 450, 150);
		    
		    //draw the level
		    g.setColor(Color.white);
			g.setFont(fonte);
			g.drawString("Level :", 325, 200);
		    g.drawString(String.valueOf(level), 450, 200);
			
		  //draw the pause
		    g.setColor(Color.white);
			g.setFont(fonte);
			g.drawString("Pause (press p)", 325, 400);
			
			//draw the restart
		    g.setColor(Color.white);
			g.setFont(fonte);
			g.drawString("Restart (press r)", 325, 450);
			
			//draw the quit
			g.setColor(Color.white);
			g.setFont(fonte);
			g.drawString("Quit (press q)", 325, 500);
			
			nextBlock.render(g, 15, 31 , 2);
			
			for(int row = 0; row < nextBackground.length; row++) {
				for(int col = 0; col < nextBackground[row].length; col++) {
					if(nextBackground[row][col] != null) {
						g.setColor(nextBackground[row][col]);
						g.fillRect(col * 15, row * 15, 15, 15);
					}
				}
			}
			
			g.setColor(Color.white);
			for(int row = 0; row < 5 + 1; row++) {
				g.drawLine(450, (15 * row)+15, (15*4)+450, (15 * row)+15);
			}
			for(int col = 0; col < 4 + 1; col++) {
				g.drawLine((col * 15)+450, 15 , (col * 15)+450, (15*5)+15);
			}
			
			g.setColor(Color.white);
			g.setFont(fonte);
			g.drawString("Next :", 350, 65);
		}
		
	}
	
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	
	public boolean isBlockFalling() {
		
		if (hitBottom() == false) {
			return false;
		}
		
		block.pieceFalling();
		repaint();
		
		return true;
	}
	
	void selectRandomBlock() {
		
		blocks = new Piece[] {
				new IPiece(),
				new JPiece(),
				new LPiece(),
				new SquarePiece(),
				new SPiece(),
				new TPiece(),
				new ZPiece() };
		
		int min = 0;
		int max = 7;
		
		int value = rand.nextInt(min + max) + min;
		
		if(block == null) {
			block = blocks[value];
		} else {
			block = nextBlock;
		}
		
		value = rand.nextInt(min + max) + min;
		
		nextBlock = blocks[value];
	}
	
	public boolean hitBottom() {
		if((block.getY()+block.getHeight())== GRID_HEIGHT) {
			return false;
		}
		
		//blocks overlay
		int[][]shape = block.getPieceShape();
		int h = block.getHeight();
		int w = block.getWidth();
		
		for(int c = 0; c<w; c++) {
			for(int r = h-1; r>=0; r--) {
				if(shape[r][c]!=0) {
					if(background[r+block.getY()+1][c+block.getX()] != null) {
						return false;
					}
				}
			}
		}
		return true;
		
	}
	
	
	public void movementLeft() {
		
		if(checkLeft() == false) {
			return;
		}
		
		block.mouvementLeft();
		repaint();
	}
	
	public void movementRight() {
		if(checkRight() == false) {
			return;
		}
		block.mouvementRight();
		repaint();
	}
	
	public void rotationBlock() {
	
		block.rotation();
		repaint();
	}
	
	private boolean checkLeft() {
		if(block.getLeftEdge()==0) {
			return false;
		}
		
		int[][]shape = block.getPieceShape();
		
		for(int r = 0; r<block.getHeight(); r++) {
			for(int c = 0; c<block.getWidth(); c++) {
				if(shape[r][c]!=0) {
					if(background[r+block.getY()][c+block.getX()-1] != null) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	private boolean checkRight() {
		if(block.getRightEdge()==GRID_WIDTH) {
			return false;
		}
		
		int[][]shape = block.getPieceShape();
		int h = block.getHeight();
		int w = block.getWidth();
		
		for(int r = 0; r<h; r++) {
			for(int c = w-1; c>=0; c--) {
				if(shape[r][c]!=0) {
					if(background[r+block.getY()][c+block.getX()+1] != null) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public void removeLine() {
		
		boolean comp;
		int count = 0;
		
		for(int r = GRID_HEIGHT - 1; r>=0; r--) {
			comp = true;
			for(int c = 0; c < GRID_WIDTH; c++) {
				if (background[r][c] == null) {
					comp = false;
					break;
				}
			}
			
			if(comp == true) {
				//clear the line complete
				for(int i = 0; i < GRID_WIDTH; i++) {
					background[r][i] = null;
				}
				
				//shiftDown all the line
				for(int r1 = r; r1 > 0; r1--) {
					for(int c1 = 0; c1 < GRID_WIDTH; c1++) {
						background[r1][c1] = background[r1 - 1][c1];
					}
				}
				
				//clear the first line
				for(int i = 0; i < GRID_WIDTH; i++) {
					background[0][i] = null;
				}
				
				r++;
				count++;
			}
		}
		
		if(count <= 3) {
			score = score + count*100; 
		} else if (count >= 4) {
			score = score + 800;
		}
		
		repaint();
			
	}
	
	public void blockIntoBackground() {
		for (int r = 0; r < block.getHeight(); r++) {
			for (int c = 0; c < block.getWidth(); c++) {
				if(block.pieceShape[r][c] == 1) {
					background[r + block.getY()][c + block.getX()] = block.getPieceColor();
				}
			}
		}
	}
	
	public boolean isGameOver() {
		if(background[0][4] != null || background[0][5] != null) {
			return true;
			
		}
		return false;
	}
	
	public void resetBackground() {
		for (int r = 0; r < GRID_HEIGHT; r++) {
			for (int c = 0; c < GRID_WIDTH; c++) {
					background[r][c] = null;				
			}
		}
		repaint();
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			isBlockFalling();
			score++ ;
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			movementRight();
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			movementLeft();
		} else if(e.getKeyCode() == KeyEvent.VK_UP) {
			rotationBlock();
		} else if(e.getKeyCode() == KeyEvent.VK_P) {
			if(pause == true) {
				pause = false;
			} else if (pause == false) {
				pause = true;
			}
			repaint();
		} else if(e.getKeyCode() == KeyEvent.VK_R) {
			score = 0;
			resetBackground();
			selectRandomBlock();
		} else if(e.getKeyCode() == KeyEvent.VK_Q) {
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}	
}