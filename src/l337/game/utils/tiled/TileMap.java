package l337.game.utils.tiled;

import java.util.List;
import java.util.Map;

/**
 * Immutable
 * 
 * @author ngolding
 *
 */
public final class TileMap {
	final private String source;
	final private int numCols, numRows;
	final private int tileWidth, tileHeight;
	final private List<TileSet> tilesets; // TODO should ensure this is an immutable List
	final private Map<String, List<Tile>> layers; // TODO should ensure this is an immutable Map
	final private int pixelWidth, pixelHeight;
	
	/**
	 * 
	 * @param numCols
	 * @param numRows
	 * @param tileWidth
	 * @param tileHeight
	 * @param tilesets - cannot be null
	 * @throws TileSetListNullPointerException
	 */
	public TileMap(String source, int numCols, int numRows, int tileWidth, int tileHeight, List<TileSet> tilesets, Map<String, List<Tile>> layers) throws TileSetListNullPointerException{
		if (tilesets == null) {
			throw new TileSetListNullPointerException(new NullPointerException("@param tilesets - cannot be null"));
		}
		this.source = source;
		this.numCols = numCols;
		this.numRows = numRows;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.tilesets = tilesets;
		this.layers = layers;
		this.pixelWidth = numCols * tileWidth;
		this.pixelHeight = numRows * tileHeight;
	}
	
	@Override
	public String toString() {
		return String.format("[TileMap: %s]\n Cols: %d, Rows: %d\n TileWidth: %d, TileHeight: %d\n PixelWidth: %d, PixelHeight: %d\n TileSets: %s\n Layers: %s"
				, source, numCols, numRows, tileWidth, tileHeight, pixelWidth, pixelHeight, tilesets, layers);
	}
	
	public int getPixelWidth() {
		return pixelWidth;
	}
	
	public int getPixelHeight() {
		return pixelHeight;
	}
	
	// TODO: maybe, get layer by index
//	public List<Tile> getLayer(int index) {
//		assert(index > 0);
//		return layers.get(index);
//	}
	
	public List<Tile> getLayer(String name) {
		return layers.get(name);
	}
	
	public Map<String, List<Tile>> getLayers(){
		return layers;
	}
	
	public static class TileSetListNullPointerException extends Exception{
		private static final long serialVersionUID = 1L;
		public TileSetListNullPointerException(Throwable e) {
			super(e);
		}
	}
}
