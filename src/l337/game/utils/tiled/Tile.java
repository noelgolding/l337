package l337.game.utils.tiled;

import java.awt.image.BufferedImage;

public class Tile {

	private int gid;
	private int row;
	private int col;
	private int x;
	private int y;
	private int width;
	private int height;
	private String sourceImage;
	private int xOffset;
	private int yOffset;

	public Tile(String sourceImage, int gid, int xOffset, int yOffset, int width, int height, int row, int col) {
		this.sourceImage = sourceImage;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.gid = gid;
		this.row = row;
		this.col = col;
		this.width = width;
		this.height = height;
		this.x = col * width;
		this.y = row * height;
	}

	public int getGid() {
		return gid;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getSourceImage() {
		return sourceImage;
	}

	public int getxOffset() {
		return xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}
}
