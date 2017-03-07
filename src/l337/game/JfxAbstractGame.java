package l337.game;


import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import l337.game.camera.Viewport;
import l337.game.utils.AssetManager;
import l337.game.utils.KeyState;

public abstract class JfxAbstractGame extends Application implements JfxGame{
	public static final Color DEFAULT_BG_COLOR = Color.BLACK;
	
	private String title;
	private Canvas canvas;
	private Viewport viewport;
	private AssetManager assetLoader;
	private int width;
	private int height;
	private Color bgColor;
	protected boolean debug;
	protected int score;
	
	public Map<KeyCode, Boolean> keysdown = new HashMap<>();
	public Map<KeyCode, KeyState> keystate = new HashMap<>();

	private static final long TARGET_FPS = 60; // TODO : possibly make this tweakable
	private static final float ONE_SECOND = 1000f;
	private static final float TARGET_MS_PER_FRAME = ONE_SECOND/TARGET_FPS;
	private static final float MAX_DELTA = TARGET_MS_PER_FRAME/ONE_SECOND;
	
	float delta = 0f; // fraction of a second since the last update
	long lastUpdate;

	public JfxAbstractGame(String title, int width, int height, Color bgColor) {
		this.title = title;
		this.canvas = new Canvas();
		this.width = width;
		this.height = height;
		this.bgColor = bgColor;
		this.viewport = new Viewport(width, height);
		this.assetLoader = new AssetManager();
		canvas.setWidth(width);
		canvas.setHeight(height);
		
		canvas.setOnKeyPressed(e -> {
			KeyCode k = e.getCode();
			KeyState currentState = keysdown.getOrDefault(k, false) ? KeyState.Pressed : KeyState.JustPressed;
			keystate.put(k, currentState);
			keysdown.put(k, true);
		});
		canvas.setOnKeyReleased(e -> {
			KeyCode k = e.getCode();
			KeyState currentState = keysdown.getOrDefault(k, false) ? KeyState.Released : KeyState.JustReleased;
			keystate.put(k, currentState);
			keysdown.put(k, false);
		});
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox vbox = new VBox();		
		vbox.getChildren().add(canvas);
		
		primaryStage.setTitle(title);
		primaryStage.setScene(new Scene(vbox));
		primaryStage.setResizable(false);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
		
		canvas.requestFocus();
		
		new Timer().scheduleAtFixedRate(new TimerTask(){
		    @Override
		    public void run(){
		    	updateInternal();
				drawInternal();
		    }
		},0,(int)TARGET_MS_PER_FRAME);
	}
	
	private void drawInternal(){
		GraphicsContext g = canvas.getGraphicsContext2D();
		
		g.setFill(bgColor);
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		g.translate(-viewport.getX(), -viewport.getY());
		
		// delegate to implementing class
		draw(g);
	}
	
	private void updateInternal(){
		if (lastUpdate == 0) {
			lastUpdate = System.currentTimeMillis();
		}
		long now = System.currentTimeMillis();
		delta = (now - lastUpdate) / ONE_SECOND;
		lastUpdate = now;

		// delegate to implementing class
		update(Math.min(delta, MAX_DELTA));
		
		viewport.update();
	}
	
	/** Getters and Setters */
	@Override
	public String getTitle() {
		return title;
	}	
	
	@Override
	public Canvas getCanvas() {
		return canvas;
	}
	
	@Override
	public AssetManager getAssetManager() {
		return assetLoader;
	}
	
	@Override
	public Viewport getViewport() {
		return viewport;
	}
	
	@Override
	public Map<KeyCode, Boolean> getKeysDown() {
		return keysdown;
	}
	
	@Override
	public Map<KeyCode, KeyState> getKeyState() {
		return keystate;
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
	public boolean isResizable() {
		return false;
	}
	
	@Override
	public boolean isDebug() {
		return debug;
	}
	
	@Override
	public JfxGame setDebug(boolean debug) {
		this.debug = debug;
		return this;
	}

}
