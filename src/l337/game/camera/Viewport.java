package l337.game.camera;

import java.awt.Rectangle;

import l337.game.sprite.Sprite;

public class Viewport {
	private Sprite focusObject;
	private float x, y, width, height;
	private double max_x, max_y;
	private double marginX, marginY;
	
	public Viewport(int width, int height) {
		this(width, height, width/3, height/3);
	}
	
	public Viewport(int width, int height, float marginX, float marginY) {
		this.x = 0;
		this.y = 0;
		this.marginX = marginX;
		this.marginY = marginY;
		this.width = width;
		this.height = height;
		setMaxDimension(width, height);		
	}
	
	public void setFocus(Sprite object){
		this.focusObject = object;
	}

	public void setMaxDimension(int x, int y) {
		this.max_x = x - width;
		this.max_y = y - height;
		
//		System.out.println(String.format("MaxX, MaxY: %f, %f", this.max_x, this.max_y));
//		System.out.println(String.format("Width, Height: %f, %f", this.width, this.height));
//		System.out.println(String.format("Margin: %f", this.margin));
	}
	
	public void setMargins(float marginX, float marginY) {
		this.marginX = marginX;
		this.marginY = marginY;
	}
	
	public void update(){
		if(focusObject == null) {
			return;
		}
		
		int center_x = (int)focusObject.getCenterX();
		int center_y = (int)focusObject.getCenterY();
		
		// if scroll left/right
		if (center_x < x + marginX) {
			x = (int) Math.max(0, center_x - marginX);
		} else if (center_x > x + width - marginX){
			x = (int) Math.min(max_x, center_x + marginX - width);
		}
		
		// if scroll up/down
		if (center_y < y + marginY) {
			y = (int) Math.max(0, center_y - marginY);
		} else if (center_y > y + height - marginY) {
			y = (int) Math.min(max_y, center_y + marginY - height);
		}
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Rectangle getRect(){
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}
}
