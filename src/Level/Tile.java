package Level;

import java.awt.Graphics;

import Core.Game;

public abstract class Tile {
	private Game game;
	private int initialX, initialY, worldXPos, worldYPos;
	private int tileID;
	private boolean isVisible;

	abstract void tick();

	abstract void render(Graphics g);

	protected void checkVisibility() {
		if (getWorldXPos() >= -32 && getWorldXPos() <= getGame().getWidth() && getWorldYPos() >= -32 && getWorldYPos() <= getGame().getHeight()) {
			setVisible(true);
		} else {
			setVisible(false);
		}
	}

	// Getters and Setters

	public int getWorldXPos() {
		return worldXPos;
	}

	public void setWorldXPos(int worldXPos) {
		this.worldXPos = worldXPos;
	}

	public int getWorldYPos() {
		return worldYPos;
	}

	public void setWorldYPos(int worldYPos) {
		this.worldYPos = worldYPos;
	}

	public int getTileID() {
		return tileID;
	}

	public void setTileID(int tileID) {
		this.tileID = tileID;
	}

	public int getInitialX() {
		return initialX;
	}

	public void setInitialX(int initialX) {
		this.initialX = initialX;
	}

	public int getInitialY() {
		return initialY;
	}

	public void setInitialY(int initialY) {
		this.initialY = initialY;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

}