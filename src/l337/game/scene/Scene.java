package l337.game.scene;

import java.awt.Graphics;

public interface Scene {
	public void init();
	public void start();
	public void onEnter();
	public void end();
	public void onExit();
	public void onPause();
	public void onResume();
	public void update(float delta);
	public void draw(Graphics g);
	public Scene getNextScene();
	public void setNextScene(Scene scene);
}
