import java.awt.Color;
import java.awt.Graphics;

//this class will append new segments to our snake whenever it munches an apple

public class BodyParts {
	
	private int xCoor, yCoor, width, height;
	
	public BodyParts(int xCoor, int yCoor, int tileSize) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = tileSize;												//the width and height are set at 1x1 blocks. these blocks are the components of the snake
		height = tileSize;
	}
	
	public void tick() {
		
	}
	public void draw(Graphics g) {										//paints the snake
		g.setColor(Color.CYAN);
		g.fillRect(xCoor * width, yCoor * height, width, height);
	}

	public int getxCoor() {												//all this keeps track of the snake's location on the board
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}
	

}
