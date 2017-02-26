package l337.game.scene;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import l337.game.SceneBasedGame;
import l337.game.sprite.Sprite;

public abstract class AbstractScene implements Scene {
	
	private List<Sprite> sprites;
	protected SceneBasedGame game;
	private boolean paused;
	private Scene nextScene;
	
	public AbstractScene(SceneBasedGame game) {
		this.game = game;
	}
	
	@Override
	public void start() {
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
		sprites = new ArrayList<>();
		init();
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
			sprites.stream().forEach(s -> s.update(delta));
		}
	}

	@Override
	public void draw(Graphics g) {
		if (!paused){
			sprites.stream().forEach(s -> s.draw(g));
		}
	}
	
	// TODO : not sure if this is a good idea.
	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}

}
