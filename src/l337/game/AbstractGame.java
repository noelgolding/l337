package l337.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import l337.game.camera.Viewport;
import l337.game.utils.AssetManager;
import l337.game.utils.KeyState;

// TODO java doc everything.
// TODO fix stalling/delay bug
public abstract class AbstractGame extends KeyAdapter implements Game {
	public static final Color DEFAULT_BG_COLOR = Color.black;
	
	private String title;
	private Canvas canvas;
	private Viewport viewport;
	private AssetManager assetLoader;
	private BufferStrategy bufferStrategy;
	private int width;
	private int height;
	protected boolean debug;
	protected int score;
	
	public Map<Integer, Boolean> keysdown = new HashMap<>();
	public Map<Integer, KeyState> keystate = new HashMap<>();

	private static final long TARGET_FPS = 60; // TODO : possibly make this tweakable
	private static final float ONE_SECOND = 1000f;
	private static final float TARGET_MS_PER_FRAME = ONE_SECOND/TARGET_FPS;
	private static final float MAX_DELTA = TARGET_MS_PER_FRAME/ONE_SECOND;
	
	float delta = 0f; // fraction of a second since the last update
	long lastUpdate;
	
	public AbstractGame(String title, int width, int height, Color bgColor) {
		this.title = title;
		this.canvas = new Canvas();
		this.width = width;
		this.height = height;
		this.viewport = new Viewport(width, height);
		this.assetLoader = new AssetManager();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setBackground(bgColor);
		canvas.addKeyListener(this);
	}
	
	private void initCanvas(){
		bufferStrategy = canvas.getBufferStrategy();
		if (bufferStrategy == null) {
			canvas.createBufferStrategy(3);
			bufferStrategy = canvas.getBufferStrategy();
		}
	}
	
	@Override
	public void run() {
		initInternal();

		new Timer().scheduleAtFixedRate(new TimerTask(){
		    @Override
		    public void run(){
		    	updateInternal();
				drawInternal();
		    }
		},0,(int)TARGET_MS_PER_FRAME);
	}
	
	void initInternal(){
		initCanvas();
		// TODO load splash screen while the assets get loaded.
		
		
		// delegate to implementing class
		init();
	}

	private void drawInternal(){
		Graphics g = bufferStrategy.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(canvas.getBackground());
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		g2d.translate(-viewport.getX(), -viewport.getY());
		
		// delegate to implementing class
		draw(g);
		
		g.dispose();
		g2d.dispose();
		bufferStrategy.show();
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
	
	/** Keyboard Input Adapter */
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		KeyState curentState = keysdown.getOrDefault(keyCode, false) ? KeyState.Pressed : KeyState.JustPressed;
		keysdown.put(e.getKeyCode(), true);
		keystate.put(e.getKeyCode(), curentState);
		e.consume();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		KeyState curentState = keysdown.getOrDefault(keyCode, false) ? KeyState.Released : KeyState.JustReleased;
		keysdown.put(e.getKeyCode(), false);
		keystate.put(e.getKeyCode(), curentState);
		e.consume();
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
	public Map<Integer, Boolean> getKeysDown() {
		return keysdown;
	}
	
	@Override
	public Map<Integer, KeyState> getKeyState() {
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
	public int getWorldWidth() {
		return this.viewport.getMaxX();
	}
	
	@Override
	public int getWorldHeight() {
		return this.viewport.getMaxY();
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
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
}
