package l337.game;

import java.awt.Graphics;
import java.util.EmptyStackException;
import java.util.Stack;

import l337.game.scene.Scene;

public abstract class SceneBasedGame extends AbstractGame{

	private Stack<Scene> scenes = new Stack<>();
	private Scene currentScene;
	
	public SceneBasedGame(String title, int width, int height) {
		super(title, width, height, DEFAULT_BG_COLOR);
	}
	
	public void pushScene(Scene scene){
		scenes.push(scene);
	}
	
	public void nextScene(){
		try {
			currentScene = scenes.pop();
			// trigger onEnter
			currentScene.start();
		} catch (EmptyStackException e) {
			// TODO : possibly load Game Completed Cut Scene?
		}
	}

	@Override
	public void update(float delta) {
		if (currentScene != null) {
			currentScene.update(delta);
		}
	}

	@Override
	public void draw(Graphics g) {
		if (currentScene != null) {
			currentScene.draw(g);
		}
	}
	
	public Scene getCurrentScene(){
		return this.currentScene;
	}

}
