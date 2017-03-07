package l337.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import l337.game.sprite.JfxSprite;

public abstract class JfxSimpleGame extends JfxAbstractGame {

	Map<Integer, List<JfxSprite>> spriteRenderLayers = new HashMap<>();

	public JfxSimpleGame(String title, int width, int height, Color bgColor) {
		super(title, width, height, bgColor);
	}

	@Override
	public void update(float delta) {
		// TODO Better update algorithm
		for (List<JfxSprite> layer : spriteRenderLayers.values().stream().collect(Collectors.toList())){
			layer.stream().forEach(s -> s.update(delta));
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		for (List<JfxSprite> layer : spriteRenderLayers.values().stream().collect(Collectors.toList())){
			layer.stream().forEach(s -> s.draw(g));
		}
	}
	
	public void addSpriteToLayer(int layerIndex, JfxSprite sprite) {
		List<JfxSprite> layer = spriteRenderLayers.get(layerIndex);
		if (layer == null) {
			layer = new ArrayList<>();
			spriteRenderLayers.put(layerIndex, layer);
		}
		layer.add(sprite);
	}
	
	public void removeSpriteFromLayer(int layerIndex, JfxSprite sprite) {
		List<JfxSprite> layer = spriteRenderLayers.get(layerIndex);
		if (layer == null) 
			return;
		
		layer.remove(sprite);
	}

}
