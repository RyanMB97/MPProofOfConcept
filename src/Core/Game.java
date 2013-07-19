package Core;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import Entities.Player;
import Level.World;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	// Other classes
	private Player player;
	private InputHandler inputHandler;
	private World world;

	// Frame related variables
	JFrame frame;
	private final String TITLE = "Multiplayer Proof Of Concept";
	private final int WIDTH = 640;
	private final int HEIGHT = 480;
	private final Dimension gameDim = new Dimension(WIDTH, HEIGHT);

	// Game Related
	Thread gameThread;
	private boolean running = false;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	// Offsets for scrolling during movement
	private int xOffset = 0, yOffset = 0;

	// FPS and UPS
	int frames = 0;
	int ticks = 0;
	int FPS = 0;
	int UPS = 0;
	double delta = 0D;

	public void run() {
		long currentTime = System.currentTimeMillis();
		while (running) {
			tick();
			render();
			long finalTime = System.currentTimeMillis();
			frames++;
			if (finalTime - currentTime >= 1000) {
				currentTime = finalTime;
				FPS = frames;
				frames = 0;
			}

			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void start() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public synchronized void stop() {
		running = false;
		System.exit(0);
	}

	public Game() {
		init();
		initClasses();
	}

	public void init() {
		frame = new JFrame(TITLE + " FPS: " + FPS);
		setMinimumSize(gameDim);
		setMaximumSize(gameDim);
		setPreferredSize(gameDim);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		requestFocus();
	}

	public void initClasses() {
		player = new Player(this);
		inputHandler = new InputHandler(this);
		world = new World(this);
	}

	public void tick() {
		frame.setTitle(TITLE + " FPS: " + FPS);

		getWorld().tick();
		getPlayer().tick();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

		getWorld().render(g);
		getPlayer().render(g);

		g.dispose();
		bs.show();
	}

	// Getters and Setters

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public InputHandler getInputHandler() {
		return inputHandler;
	}

	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
}