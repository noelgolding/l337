package l337.game.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import l337.game.JfxGame;

public abstract class JfxRectSprite extends JfxSprite {
	public static final int DEFAULT_START_X = 0;
	public static final int DEFAULT_START_Y = 0;
	public static final int DEFAULT_WIDTH = 32;
	public static final int DEFAULT_HEIGHT = 32;
	public static final Color DEFAULT_COLOR = Color.SIENNA;
	public static final boolean DEFAULT_FILLED = true;
	
	private Color color;
	private boolean filled;

	/**
	 * 
	 * @param game
	 */
	public JfxRectSprite(JfxGame game) {
		this(game, DEFAULT_START_X, DEFAULT_START_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR, DEFAULT_FILLED);
	}
	
	/**
	 * 
	 * @param game
	 * @param startX
	 * @param startY
	 */
	public JfxRectSprite(JfxGame game, int startX, int startY) {
		this(game, startX, startY, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR, DEFAULT_FILLED);
	}
	
	/**
	 * 
	 * @param game
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 */
	public JfxRectSprite(JfxGame game, int startX, int startY, int width, int height) {
		this(game, startX, startY, width, height, DEFAULT_COLOR, DEFAULT_FILLED);
	}
	
	/**
	 * 
	 * @param game
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 * @param color
	 */
	public JfxRectSprite(JfxGame game, int startX, int startY, int width, int height, Color color) {
		this(game, startX, startY, width, height, color, DEFAULT_FILLED);
	}
	
	/**
	 * 
	 * @param game
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 * @param color
	 * @param filled
	 */
	public JfxRectSprite(JfxGame game, int startX, int startY, int width, int height, Color color, boolean filled) {
		super(game);
		setPosition(startX, startY);
		this.width = width;
		this.height = height;
		this.color = color;
		this.filled = filled;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public void draw(GraphicsContext g) {		
		if (filled) {
			g.setFill(color);
			g.fillRect(getX(), getY(), getWidth(), getHeight());
		} else {
			g.setStroke(color);
			g.strokeRect(getX(), getY(), getWidth(), getHeight());
		}
	}
	
	public JfxRectSprite setColor(Color color){
		this.color = color;
		return this;
	}

	public JfxRectSprite setFilled(boolean filled) {
		this.filled = filled;
		return this;
	}
}
