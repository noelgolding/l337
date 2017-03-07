package l337.game.sprite;

import java.awt.Graphics;
import java.awt.Image;

import l337.game.Game;

public abstract class BitmapSprite extends Sprite {
	private Image img;
	
	public BitmapSprite(Game game, Image img) {
		this(game, img, DEFAULT_START_X, DEFAULT_START_Y);
	}

	public BitmapSprite(Game game, Image img, int startX, int startY) {
		super(game, startX, startY);
		this.setImg(img);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getImg(), getX(), getY(), null);
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	
	@Override
	public int getWidth() {
		return this.img.getWidth(null);
	}
	
	@Override
	public int getHeight() {
		return this.img.getHeight(null);
	}
}
