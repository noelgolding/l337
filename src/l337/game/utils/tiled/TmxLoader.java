package l337.game.utils.tiled;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import l337.game.utils.AssetManager;
import l337.game.utils.tiled.TileMap.TileSetListNullPointerException;

public final class TmxLoader {
	public static final String TILEMAPS_FOLDER = "assets/tilemaps";
	
	public static TileMap loadMap(AssetManager assetManager, String tileMapName) throws TileSetListNullPointerException {
		final ClassLoader classLoader = TmxLoader.class.getClassLoader();
		final URL resource = classLoader.getResource(TILEMAPS_FOLDER + "/" + tileMapName);
		
		// CREATE DOM DOC
		Document doc = null;
		final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        try(InputStream inputStream = new FileInputStream(new File(resource.toURI()))) {
        	final DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        	doc = docBuilder.parse(inputStream);
        	doc.getDocumentElement().normalize();
		} catch (IOException | ParserConfigurationException | SAXException | URISyntaxException e) {
			throw new RuntimeException(String.format("Failed to load tilemap: %s", resource.toString()), e);
		}
        
        // if we got here we can assume doc is not null;
		final List<TileSet> tileSets = new ArrayList<>();

        // Get TileMap Attributes
        final Element map = doc.getDocumentElement();
        final int numCols = Integer.parseInt(map.getAttribute("width"));
		final int numRows = Integer.parseInt(map.getAttribute("height"));
		final int tileWidth = Integer.parseInt(map.getAttribute("tilewidth"));
		final int tileHeight = Integer.parseInt(map.getAttribute("tileheight"));

		// Get TileSets Attributes
		final NodeList tilesetElements = map.getElementsByTagName("tileset");
		for (int i = 0; i < tilesetElements.getLength(); i++) {			
			final Element tilesetElement = (Element)tilesetElements.item(i);
			final Node imageNode = tilesetElement.getElementsByTagName("image").item(0);
			final NamedNodeMap tilesetImgNodeAttributes = imageNode.getAttributes();
			final NamedNodeMap tilesetElementAttributes = tilesetElement.getAttributes();

			final int imageWidth = Integer.parseInt(tilesetImgNodeAttributes.getNamedItem("width").getNodeValue());
			final int imageHeight = Integer.parseInt(tilesetImgNodeAttributes.getNamedItem("height").getNodeValue());
			final int firstgid = Integer.parseInt(tilesetElementAttributes.getNamedItem("firstgid").getNodeValue());
			final String name = tilesetElementAttributes.getNamedItem("name").getNodeValue();
			final int tilesetTileWidth = Integer.parseInt(tilesetElementAttributes.getNamedItem("tilewidth").getNodeValue());
			final int tilesetTileHeight = Integer.parseInt(tilesetElementAttributes.getNamedItem("tileheight").getNodeValue());
			final int columnCount = Integer.parseInt(tilesetElementAttributes.getNamedItem("columns").getNodeValue());
			final int numTiles = Integer.parseInt(tilesetElementAttributes.getNamedItem("tilecount").getNodeValue());

			final String source = tilesetImgNodeAttributes.getNamedItem("source").getNodeValue();

			tileSets.add(new TileSet(firstgid, name, tilesetTileWidth, tilesetTileHeight, source, imageWidth, imageHeight, columnCount, numTiles));
		}
		
		// Get each Layers tile layout
		final Map<String, List<Tile>> layers = new LinkedHashMap<>();
		final NodeList layerElements = map.getElementsByTagName("layer");
		for (int i = 0; i < layerElements.getLength(); i++) {
			List<Tile> tiles = new ArrayList<>();
			final Element layerElement = (Element)layerElements.item(i);
			final String name = layerElement.getAttribute("name");
			
			final Node dataNode = layerElement.getElementsByTagName("data").item(0);
			String[] gids = dataNode.getTextContent().split("\\s*,\\s*");
						
			for (int g = 0; g < gids.length; g++) {
				String trimmedGid = gids[g].trim();
				if (trimmedGid.equals("0")) {
					continue;
				}
				
				int row = g / numCols;
				int col = g % numCols;
				
				int gid = Integer.parseInt(trimmedGid);
				
				Tile tile = tileSets.stream()
					.filter(t -> (t.getFirstgid() <= gid && t.getLastgid() >= gid))
					.findFirst().get().getTile(gid, row, col);
				
				tiles.add(tile);
			}
						
			layers.put(name, tiles);
		}
        return new TileMap(tileMapName, numCols, numRows, tileWidth, tileHeight, tileSets, layers);
	}		
}
