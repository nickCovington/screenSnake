import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

//this class uses Apple and BodyParts to bring our snake to life

public class GamePanel extends JPanel implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 500, HEIGHT = 500;							//establishes size of our game window
	
	private Thread thread;
	
	private boolean running;
	
	private boolean right = true, left = false, up = false, down = false;		//snake's initial position is moving rightward
	
	private BodyParts b;
	private ArrayList<BodyParts> snake;
	
	private Apple apple;
	private ArrayList<Apple> apples;
	
	private Random r;
	
	private int xCoor = 10, yCoor = 10, size = 5;								//these will be the starting coordinates of our snake
	private int ticks = 0;
	
	
	
	public GamePanel() {
		setFocusable(true);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		
		snake = new ArrayList<BodyParts>();
		apples = new ArrayList<Apple>();
		
		r = new Random();
		
		start();
		
	}
	
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	public void tick() {														//establishes the contents and operations carried out during one in-game "tick"
		if (snake.size() == 0) {
			b = new BodyParts(xCoor, yCoor, 10);
			snake.add(b);
		}
		ticks++;
		if (ticks > 500000) {													//controls duration of each tick (- = faster)(+ = slower)
			if (right) xCoor++;
			if (left) xCoor--;
			if (up) yCoor++;
			if (down) yCoor--;
			
			ticks = 0;
			b = new BodyParts(xCoor, yCoor, 10);
			snake.add(b);
			
			if (snake.size() > size) {
				snake.remove(0);
			}
		}
		if (apples.size() == 0) {												//creates random apples all over the board
			int xCoor = r.nextInt(49);
			int yCoor = r.nextInt(49);
			
			apple = new Apple(xCoor, yCoor, 10);
			apples.add(apple);
		}
		
		for (int i = 0; i < apples.size(); i++) {								//deletes apple once snake passes over it and grows snake by 1 block
			if (xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()) {
				size++;
				apples.remove(i);
				i++;
			}
		}
		for (int i = 0; i < snake.size(); i ++) {								//causes a game over if snake collides with itself
			if (xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()) {
				if (i != snake.size() - 1) {
					System.out.println("HOLY COW, YOU TRIED TO EAT YOURSELF!!!");
				}
			}
		}
		
		
		
		if (xCoor < 0 || xCoor > 49 || yCoor < 0 || yCoor > 49) {				//causes a game over is snake collides with window boundary
			System.out.println("NNNNNNNOOOOOOOO, YOU HIT A WALL!!!");
			stop();
		}
		
	}
	public void paint(Graphics g) {												//paints the board and fills in snake/apple squares where applicable
		g.clearRect(0,  0,  WIDTH,  HEIGHT);
		
		g.setColor(Color.BLACK);
		g.fillRect(0,  0,  WIDTH,  HEIGHT);
		
		for (int i = 0; i < WIDTH / 10; i++) {
			g.drawLine(i * 10, 0, i * 10, HEIGHT);
		}
		for (int i = 0; i < HEIGHT / 10; i++) {
			g.drawLine(0, i * 10, HEIGHT, i * 10);
		}
		for (int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}
		for (int i = 0; i < apples.size(); i++) {
			apples.get(i).draw(g);
		}
	}

	@Override
	public void run() {															//ensures the board/snake/apples stay painted and game ticks keep performing their duties
		while (running) {
			tick();
			repaint();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {										//establishes keyboard control using arrow keys
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			up = false;
			down = false;
		}
		if (key == KeyEvent.VK_LEFT && !right) {
			left = true;
			up = false;
			down = false;
		}
		if (key == KeyEvent.VK_DOWN && !down) {
			up = true;
			left = false;
			right = false;
		}
		if (key == KeyEvent.VK_UP && !up) {
			down = true;
			left = false;
			right = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
