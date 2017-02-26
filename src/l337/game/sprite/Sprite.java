package l337.game.sprite;

import java.awt.Graphics;

import l337.game.Game;

public abstract class Sprite {
	protected Game game;
	protected float x, y;
	protected int width, height;

	public Sprite(Game game) {
		this.game = game;
	}
	
	public int getX() {
		return (int)x;
	}
	public int getY(){
		return (int)y;
	}
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	abstract public void update(float delta);
	abstract public void draw(Graphics g);
}
