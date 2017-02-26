package l337.game.sprite;

import java.awt.Graphics;
import java.awt.Image;

import l337.game.Game;

public abstract class BitmapSprite extends Sprite {
	private Image img;
	
	public BitmapSprite(Game game, Image img) {
		super(game);
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
}
