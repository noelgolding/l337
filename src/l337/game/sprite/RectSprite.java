package l337.game.sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import l337.game.Game;

public class RectSprite extends Sprite {
	public static final int DEFAULT_WIDTH = 32;
	public static final int DEFAULT_HEIGHT = 32;
	public static final Color DEFAULT_COLOR = new Color(10506797);
	private Rectangle rect;
	private Color color;
	
	public RectSprite(Game game) {
		this(game, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_START_X, DEFAULT_START_Y, DEFAULT_COLOR);
	}
	
	public RectSprite(Game game, int width, int height) {
		this(game, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_START_X, DEFAULT_START_Y, DEFAULT_COLOR);
	}

	public RectSprite(Game game, int width, int height, int startX, int startY, Color color) {
		super(game, startX, startY);
		this.rect = new Rectangle(startX, startY, width, height);
		this.color = color;
	}

	@Override
	public int getWidth() {
		return (int)this.rect.getWidth();
	}

	@Override
	public int getHeight() {
		return (int)this.rect.getHeight();
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

}
