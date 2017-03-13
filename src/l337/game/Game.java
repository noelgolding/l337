package l337.game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.Map;

import l337.game.camera.Viewport;
import l337.game.utils.AssetManager;
import l337.game.utils.KeyState;

public interface Game extends Runnable {
	public String getTitle();
	public Canvas getCanvas();
	public Viewport getViewport();
	public void init();
	public void update(float delta);
	public void draw(Graphics g);
	public boolean isDebug();
	public void setDebug(boolean debug);
	public int getWidth();
	public int getHeight();
	public int getWorldWidth();
	public int getWorldHeight();
	public boolean isResizable();
	public AssetManager getAssetManager();
	public Map<Integer, Boolean> getKeysDown();
	public Map<Integer, KeyState> getKeyState();

}