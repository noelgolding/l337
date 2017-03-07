package l337.game.sprite;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import l337.game.JfxGame;

public abstract class JfxSprite {
	protected JfxGame game;
	protected double x, y;
	protected int width, height;
	private List<JfxSprite> checkForCollision = new ArrayList<>();

	public JfxSprite(JfxGame game) {
		this.game = game;
	}
	
	public int getX() {
		return (int)x;
	}
	public int getY(){
		return (int)y;
	}
	
	public abstract int getWidth();
	public abstract int getHeight();
	
	
	public JfxSprite setX(double x) {
		this.x = x;
		return this;
	}
	public JfxSprite setY(double y){
		this.y = y;
		return this;
	}
	
	public JfxSprite setPosition(double x, double y){
		setX(x);
		setY(y);
		return this;
	}
	
	abstract public void update(float delta);
	abstract public void draw(GraphicsContext g);

	public boolean collidedWith(JfxSprite sprite) {
		// AABB collision test
		return getRect().intersects(sprite.getRect().getBoundsInLocal());
	}
	
	public JfxSprite canCollideWith(JfxSprite sprite) {
		checkForCollision.add(sprite);
		return this;
	}
	
	public JfxSprite canCollideWith(List<JfxSprite> sprites) {
		checkForCollision.addAll(sprites);
		return this;
	}
	
	public Rectangle getRect(){
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	public JfxSprite leftAlign(){
		setX(0);
		return this;
	}
	
	public JfxSprite centerAlign(){
		setX(game.getWidth()/2 - getWidth()/2);
		return this;
	}
	
	public JfxSprite rightAlign(){
		setX(game.getWidth() - 1 - getWidth());
		return this;
	}
	
	public JfxSprite topAlign(){
		setY(0);
		return this;
	}
	
	public JfxSprite middleAlign(){
		setY(game.getHeight()/2 - getHeight()/2);
		return this;
	}

	public JfxSprite bottomAlign(){
		setY(game.getHeight() - 1 - getHeight());
		return this;
	}

	public int getCenterX() {
		return (int)(x + getWidth()/2);
	}

	public int getCenterY() {
		return (int)(y + getHeight()/2);
	}

}
