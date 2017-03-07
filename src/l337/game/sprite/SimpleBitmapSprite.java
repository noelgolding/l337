package l337.game.sprite;

import java.awt.Image;

import l337.game.Game;

public class SimpleBitmapSprite extends BitmapSprite {

	public SimpleBitmapSprite(Game game, Image img) {
		super(game, img);
	}

	public SimpleBitmapSprite(Game game, Image img, int startX, int startY) {
		super(game, img, startX, startY);
	}

	@Override
	public void update(float delta) {
		// do nothing.
	}

}
