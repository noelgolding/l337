package l337.game.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import l337.game.SceneBasedGame;
import l337.game.sprite.Sprite;
import l337.game.utils.AssetManager;

public abstract class AbstractScene implements Scene {
	private static final Color DEFAULT_BG_COLOR = Color.black;
	
	protected List<Sprite> sprites;
	protected SceneBasedGame game;
	protected AssetManager assetMgr;
	private boolean paused;
	private Scene nextScene;
	protected Color bgColor;
	private int width, height;
	
	public AbstractScene(SceneBasedGame game) {
		this(game, game.getWidth(), game.getHeight(), DEFAULT_BG_COLOR);
	}
	
	public AbstractScene(SceneBasedGame game, Color bgColor) {
		this(game, game.getWidth(), game.getHeight(), bgColor);
	}
	
	public AbstractScene(SceneBasedGame game, int width, int height) {
		this(game, width, height, DEFAULT_BG_COLOR);
	}
	
	public AbstractScene(SceneBasedGame game, int width, int height, Color bgColor) {
		this.game = game;
		this.assetMgr = game.getAssetManager();
		this.width = width;
		this.height = height;
		this.bgColor = bgColor;
	}
	
	@Override
	public void start() {
		sprites = new ArrayList<>();
		game.getViewport().setPosition(0,0);
		game.getCanvas().setBackground(bgColor);

		onEnter();
	}
	
	@Override
	public void end() {
		onExit();
		// TODO call cleanup?
		game.nextScene();
	}
	
	@Override
	public void onEnter() {
		init();
		game.getViewport().setMaxDimension(width, height);
	}
	
	@Override
	public void onExit() {
		game.pushScene(getNextScene());
	}
	
	@Override
	public Scene getNextScene() {
		return nextScene;
	}

	@Override
	public void setNextScene(Scene scene) {
		this.nextScene = scene;
	}
	
	@Override
	public void onPause() {
		paused = true;
	}

	@Override
	public void onResume() {
		paused = false;
	}

	@Override
	public void update(float delta) {
		if (!paused) {
			sprites.stream().filter(s -> s != null).forEach(s -> s.update(delta));
		}
	}

	@Override
	public void draw(Graphics g) {
		if (!paused){
			sprites.stream().filter(s -> s != null).forEach(s -> s.draw(g));
		}
	}
	
	// TODO : not sure if this is a good idea.
	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}

	// TODO : not sure if this is a good idea.
	public void removeSprite(Sprite sprite) {
		if (sprites != null) {
			sprites.remove(sprite);
		}
	}
	
	protected void updateWorldDimensions(int width, int height) {
		this.width = width;
		this.height = height;
		
	}

	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Rectangle getRect() {
		return new Rectangle(0, 0, width, height);
	}

	protected void clampToScene(Sprite sprite) {
		clampToRect(sprite, getRect());
	}
	
	protected void clampToRect(Sprite sprite, Rectangle rect) {
		if (sprite == null)
			return;
		
		if (sprite.getX() < 0) {
			sprite.setX(0);
		} else if (sprite.getX() + sprite.getWidth() > rect.getWidth() - 1) {
			sprite.setX(rect.getWidth() - 1 - sprite.getWidth());
		}
		
		if (sprite.getY() < 0) {
			sprite.setY(0);
		} else if (sprite.getY() + sprite.getHeight() > rect.getHeight() - 1) {
			sprite.setY(rect.getHeight() - 1 - sprite.getHeight());
		}
	}
	

}
