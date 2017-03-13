package l337.game.sprite;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import l337.game.Game;

public abstract class Sprite {
	public static final int DEFAULT_START_X = 0;
	public static final int DEFAULT_START_Y = 0;
	
	protected Game game;
	protected double x, y;
	protected int width, height;
	private List<Sprite> checkForCollision = new ArrayList<>();

	public Sprite(Game game) {
		this(game, DEFAULT_START_X, DEFAULT_START_Y);
	}
	
	public Sprite(Game game, int startX, int startY) {
		this.game = game;
		this.x = startX;
		this.y = startY;
	}
	
	public int getX() {
		return (int)x;
	}
	public int getY(){
		return (int)y;
	}
	
	public int getRight(){
		return getX() + getWidth();
	}
	
	public int getBottom(){
		return getY() + getHeight();
	}
	
	public abstract int getWidth();
	public abstract int getHeight();
	
	
	public Sprite setX(double x) {
		this.x = x;
		return this;
	}
	public Sprite setY(double y){
		this.y = y;
		return this;
	}
	
	public Sprite setPosition(double x, double y){
		setX(x);
		setY(y);
		return this;
	}
	
	abstract public void update(float delta);
	abstract public void draw(Graphics g);

	public boolean collidedWith(Sprite sprite) {
		// AABB collision test
		return getRect().intersects(sprite.getRect());
	}
	
	public Sprite canCollideWith(Sprite sprite) {
		checkForCollision.add(sprite);
		return this;
	}
	
	public Sprite canCollideWith(List<Sprite> sprites) {
		checkForCollision.addAll(sprites);
		return this;
	}
	
	public Rectangle getRect(){
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	public Sprite leftAlign(){
		setX(0);
		return this;
	}
	
	public Sprite centerAlign(){
		setX(game.getWidth()/2 - getWidth()/2);
		return this;
	}
	
	public Sprite rightAlign(){
		setX(game.getWidth() - 1 - getWidth());
		return this;
	}
	
	public Sprite topAlign(){
		setY(0);
		return this;
	}
	
	public Sprite middleAlign(){
		setY(game.getHeight()/2 - getHeight()/2);
		return this;
	}

	public Sprite bottomAlign(){
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
