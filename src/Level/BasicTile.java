package Level;

import java.awt.Graphics;

import Core.Game;

public class BasicTile extends Tile {
	public BasicTile(Game game, int x, int y) {
		setGame(game);
		setInitialX(x);
		setInitialY(y);
		setTileID(0);
		setWorldXPos(getInitialX() + getGame().getxOffset());
		setWorldYPos(getInitialY() + getGame().getyOffset());
	}

	void tick() {
		setWorldXPos(getInitialX() + getGame().getxOffset());
		setWorldYPos(getInitialY() + getGame().getyOffset());
		checkVisibility();
	}

	void render(Graphics g) {
		g.drawImage(getGame().getResMan().getSpecificTile(0), getWorldXPos(), getWorldYPos(), getGame());
	}
}