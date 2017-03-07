package l337.game;

import java.util.Map;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import l337.game.camera.Viewport;
import l337.game.utils.AssetManager;
import l337.game.utils.KeyState;

public interface JfxGame {
	public String getTitle();
	public Canvas getCanvas();
	public Viewport getViewport();
	public void update(float delta);
	public void draw(GraphicsContext g);
	public boolean isDebug();
	public JfxGame setDebug(boolean debug);
	public int getWidth();
	public int getHeight();
	public boolean isResizable();
	public AssetManager getAssetManager();
	public Map<KeyCode, Boolean> getKeysDown();
	public Map<KeyCode, KeyState> getKeyState();
}