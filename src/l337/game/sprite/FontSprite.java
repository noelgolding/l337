package l337.game.sprite;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import l337.game.Game;

public class FontSprite extends Sprite {
	public static final Font DEFAULT_FONT = new Font ("Helvetica", Font.BOLD, 36);
	
	private String string;
	private Color color;
	private Font font;
	private FontMetrics fontMetrics;
	
	
	public FontSprite(Game game, String string, Color color) {
		this(game, string, color, DEFAULT_FONT);
	}
	public FontSprite(Game game, String string, Color color, Font font) {
		super(game);
		this.string = string;
		this.color = color;
		this.font = font; //Initializes the font
		this.fontMetrics = game.getCanvas().getFontMetrics(font);
	}

	@Override
	public void update(float delta) {
		// do nothing by default.
	}
	
	@Override
	public void draw(Graphics g) {
		g.setFont(font);
		g.setColor(color);
		g.drawString(string, getX(), getY());
	}
	
	@Override
	public int getWidth() {
		return fontMetrics.stringWidth(string);
	}
	
	@Override
	public int getHeight() {
		return fontMetrics.getAscent() + fontMetrics.getDescent();
	}
}
