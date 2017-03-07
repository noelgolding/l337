package l337.game.utils.tiled;

/**
 * Immutable
 * 
 * @author ngolding
 *
 */
public final class TileSet {
	final private int firstgid;
	final private int lastgid;
	final private String name;
	final private int tileWidth;
	final private String source;
	final private int tileHeight;
	final private int imageWidth;
	final private int imageHeight;
	final private int numCols, numRows;

	public TileSet(int firstgid, String name, int tileWidth, int tileHeight, String source, int imageWidth, int imageHeight, int columnCount, int numTiles) {
		this.firstgid = firstgid;
		this.name = name;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.source = source;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.numCols = columnCount;
		this.numRows = numTiles / numCols;
		this.lastgid = firstgid + numTiles - 1;
	}
	
	@Override
	public String toString() {
		return String.format("%s: %s", name, source);
	}
	
	public Tile getTile(int gid, int row, int col){
		int index = gid - firstgid;
		
		int xOffset = ((index % numCols) * tileWidth); //column
		int yOffset = (index / numCols) * tileHeight; //row
		
		return new Tile(source, gid, xOffset, yOffset, tileWidth, tileHeight, row, col);
	}

	public int getFirstgid() {
		return firstgid;
	}

	public int getLastgid() {
		return lastgid;
	}

	public String getName() {
		return name;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public String getSource() {
		return source;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}
}
