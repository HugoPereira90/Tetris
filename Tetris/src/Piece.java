import java.awt.Color;
import java.awt.Graphics;

public class Piece {
	
	public static final int GRID_WIDTH = 10;
	public static final int GRID_HEIGHT = 20;
	
	int[][] pieceShape;
	int[][][] shapes;
	Color pieceColor;
	int x , y;
	int currentRotation=0;
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	Piece(int[][] shape, Color color){
		pieceShape = shape;
		pieceColor = color;
		
		initShapes();
		
		x = 4;
		y = 0;
	}
	
	Piece(){
		pieceShape = null;
		pieceColor = null;
		
		initShapes();
		
		x = 4;
		y = 0;
	}

	
	public int[][] getPieceShape() {
		return pieceShape;
	}

	public Color getPieceColor() {
		return pieceColor;
	}
	
	int getHeight() {
		return pieceShape.length;
	}
	
	int getWidth() {
		return pieceShape[0].length;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	int mouvementRight(){
		if(x >= 0 || x < 10) {
			x++;
		}
		return x;
	}
	
	int mouvementLeft(){
		if(x >= 0 && x < 10) {
			x--;
		}
		return x;
	}
	
	int pieceFalling(){
		if(y >= 0 && y <= 20) {
			y++;
		}
		return y;
	}
	
	private void initShapes() {
		shapes = new int[4][][];
		
		for(int i = 0; i < 4; i++) {
			int r = pieceShape[0].length;
			int c = pieceShape.length;
			
			shapes[i] = new int[r][c];
			
			for(int j = 0; j<r ; j++) {
				for(int k = 0; k<c ; k++) {
					shapes[i][j][k]= pieceShape[c-k-1][j];
				}
			}
			
			pieceShape = shapes[i];
		}
	}
	
	void rotation() {
		
		currentRotation++;
		if(currentRotation > 3) {
			currentRotation=0;
		}
		pieceShape = shapes[currentRotation];
		
		if((x + pieceShape[0].length > GRID_WIDTH) || (y + pieceShape.length > 20)) {
			currentRotation--;
			pieceShape = shapes[currentRotation];
		}
	}
	
	public void render(Graphics g, int blockSize, int x , int y) {
		//draw the shape
		for(int row = 0; row < pieceShape.length; row++) {
			for(int col = 0; col < pieceShape[0].length; col++) {
				if(pieceShape[row][col] != 0) {
							g.setColor(pieceColor);
							g.fillRect(col * blockSize + x * blockSize, row * blockSize + y * blockSize, blockSize, blockSize);
				}
			}
		}
	}
	
	public int getLeftEdge() {
		return x;
	}
	
	public int getRightEdge() {
		return x + getWidth();
	}
}
