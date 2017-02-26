package l337.game.sprite;

import java.awt.Color;
import java.awt.Graphics;

import l337.game.Game;

public class FontSprite extends Sprite {
	private String string;
	private Color color;
	
	public FontSprite(Game game, String string, Color color) {
		super(game);
		this.string = string;
		this.color = color;
	}

	@Override
	public void update(float delta) {
		// do nothing by default.
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Font-metric stuff, size, family, etc.
		g.setColor(color);
		g.drawString(string, getX(), getY());
	}
}
