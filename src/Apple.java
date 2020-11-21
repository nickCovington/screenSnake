import java.awt.Color;
import java.awt.Graphics;

//this class will populate our game board with apples for snake to eat

public class Apple {
	
	private int xCoor, yCoor, width, height;
	
	public Apple(int xCoor, int yCoor, int tileSize) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = tileSize;
		height = tileSize;
	}
	
	public void tick() {
		
	}
	public void draw(Graphics g) {										//paints the apples
		g.setColor(Color.RED);
		g.fillRect(xCoor * width, yCoor * height, width, height);
	}

	public int getxCoor() {												//keeps track of apple's location on the board
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