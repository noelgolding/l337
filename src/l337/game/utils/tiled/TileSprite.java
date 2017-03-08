package l337.game.utils.tiled;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import l337.game.Game;
import l337.game.sprite.SimpleBitmapSprite;

public class TileSprite extends SimpleBitmapSprite{
//	private Tile tile;
	private BufferedImage img;
	private int worldX1;
	private int worldY1;
	private int worldX2;
	private int worldY2;
	private int srcX1;
	private int srcY1;
	private int srcX2;
	private int srcY2;
	
	
	public TileSprite(Game game, Tile tile) {
		super(game, game.getAssetManager().getImage(tile.getSourceImage()), tile.getX(), tile.getY());
//		this.tile = tile;
		this.img = game.getAssetManager().getImage(tile.getSourceImage());
		this.width = tile.getWidth();
		this.height = tile.getHeight();
		this.worldX1 = getX();
		this.worldY1 = getY();
		this.worldX2 = worldX1 + tile.getWidth();
		this.worldY2 = worldY1 + tile.getWidth();
		this.srcX1 = tile.getxOffset();
		this.srcY1 = tile.getyOffset();
		this.srcX2 = srcX1 + tile.getWidth();
		this.srcY2 = srcY1 + tile.getWidth();
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
	public void update(float delta) {
		// do nothing
	}


	@Override
	public void draw(Graphics g) {
		// only draw if in the viewport
		if (!getRect().intersects(game.getViewport().getRect())){
			return;
		}
		
		g.drawImage(img, 
				worldX1, worldY1, 
				worldX2, worldY2, 
				srcX1,srcY1, 
				srcX2, srcY2, 
				null);
	}
	
}
