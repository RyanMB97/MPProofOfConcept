package Level;

import java.awt.Color;
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
	}

	void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(getWorldXPos(), getWorldYPos(), 32, 32);
	}
}