package l337.game.sprite;

import java.awt.Image;

import l337.game.Game;

public abstract class AnimatedBitmapSprite extends BitmapSprite {
	private Image[] frames;
	protected float fps;
	
	protected int currentKeyFrame = 0;
	
	public AnimatedBitmapSprite(Game game, Image[] frames, float fps) {
		super(game, frames[0]);
		this.frames = frames;
		this.fps = fps;
	}

	@Override
	public Image getImg() {
		return frames[currentKeyFrame];
	}
	
	public void setFrames(Image[] frames) {
		this.frames = frames;
	}
}
