package l337.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import l337.game.utils.AssetManager;

// TODO java doc everything.
// TODO fix stalling/delay bug
public abstract class AbstractGame extends KeyAdapter implements Game {
	public static final Color DEFAULT_BG_COLOR = Color.black;
	
	private String title;
	private Canvas canvas;
	private AssetManager assetLoader;
	private Graphics g;
	private BufferStrategy bufferStrategy;
	private int width;
	private int height;
	protected boolean debug;
	protected int score;
	
	public Map<Integer, Boolean> keysdown = new HashMap<>();

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
		g = bufferStrategy.getDrawGraphics();		
	}
	
	@Override
	public void run() {
		initInternal();

//		while(true) {
//			updateInternal();
//			drawInternal();
//		}
		new Timer().scheduleAtFixedRate(new TimerTask(){
		    @Override
		    public void run(){
		    	updateInternal();
				drawInternal();
		    }
		},0,16);
	}
	
	void initInternal(){
		initCanvas();
		// TODO load splash screen while the assets get loaded.
		init();
	}

	private void drawInternal(){
		g.setColor(canvas.getBackground());
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		// delegate to implementing class
		draw(g);
		
		bufferStrategy.show();
	}
	
	private void updateInternal(){
		if (lastUpdate == 0) {
			lastUpdate = System.currentTimeMillis();
		}
		long now = System.currentTimeMillis();
		delta = (now - lastUpdate) / ONE_SECOND;
		lastUpdate = now;

		update(Math.min(delta, MAX_DELTA));
	}
	
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
	public Map<Integer, Boolean> getKeysDown() {
		return keysdown;
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
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keysdown.put(e.getKeyCode(), true);
		e.consume();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		keysdown.put(e.getKeyCode(), false);
		e.consume();
	}
}
