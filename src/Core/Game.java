package Core;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

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

	public void tick() {
		frame.setTitle(TITLE + " FPS: " + FPS);
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

		g.dispose();
		bs.show();
	}
}