package Level;

import java.awt.Graphics;

import Core.Game;

public class World {
	private Game game;
	private Tile[] tiles = new Tile[25 * 25];
	private int worldPixelSize = 25 * 32;

	public World(Game game) {
		setGame(game);
		generateWorld();
	}

	public void generateWorld() {
		int x = 0;
		int y = 0;
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new BasicTile(getGame(), x, y);
			x += 32;
			if (x == 25 * 32) {
				x = 0;
				y += 32;
			}
		}
	}

	public void tick() {
		for (Tile t : tiles) {
			t.tick();
		}
	}

	public void render(Graphics g) {
		for (Tile t : tiles) {
			if (t.isVisible())
				t.render(g);
		}
	}

	// Getters and Setters

	public Tile[] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[] tiles) {
		this.tiles = tiles;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getWorldPixelSize() {
		return worldPixelSize;
	}

	public void setWorldPixelSize(int worldPixelSize) {
		this.worldPixelSize = worldPixelSize;
	}

}