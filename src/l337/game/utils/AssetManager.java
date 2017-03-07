package l337.game.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public final class AssetManager {
	public static final String IMAGES_FOLDER = "assets/images";
	private Map<String, BufferedImage> imagesCache = new HashMap<>();
	
	public AssetManager() {
		try {
			loadImages(getPathToImages());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load image assets");
		}
	}
	
	public BufferedImage getImage(String assetname) {
		BufferedImage img = imagesCache.get(assetname);
		if (img == null) {
			try {
				img = loadImageInternal(assetname);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Failed to get image asset: " + assetname);
			}
		}
		return img;
	}
	
	private void loadImages(File file) throws IOException, URISyntaxException {
		// we know this is a directory
		for (File f : file.listFiles()) {
			loadImages(f, f.getName());
		}
	}
	
	private void loadImages(File file, String assetname) throws IOException, URISyntaxException{		
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				loadImages(f, assetname + "/" + f.getName());
			}
		} else {
			loadImageInternal(assetname);
		}
	}
	
	private BufferedImage loadImageInternal(String assetname) throws IOException, URISyntaxException {
		File pathToFile = new File(getPathToImages(), assetname);
	    BufferedImage image = ImageIO.read(pathToFile);
	    imagesCache.put(assetname, image);
		return image;
	}
	
	private File getPathToImages() throws URISyntaxException {
		ClassLoader classLoader = AssetManager.class.getClassLoader();
		URL resource = classLoader.getResource(IMAGES_FOLDER);
		URI uri = resource.toURI();
		return new File(uri);
	}

}
