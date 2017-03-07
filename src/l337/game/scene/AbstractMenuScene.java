package l337.game.scene;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import l337.game.SceneBasedGame;

public abstract class AbstractMenuScene extends AbstractScene {
	protected List<AbstractMenuSceneItem> menuItems = new ArrayList<>();
	protected String menuTitle;
	protected Color fgColor;
	
	public AbstractMenuScene(SceneBasedGame game, String menuTitle, Color fgColor, Color bgColor) {
		super(game, bgColor);
		this.menuTitle = menuTitle;
		this.fgColor = fgColor;
	}
	
	
	
	// TODO i don't know what default behavior I want.
	// TODO should this be removed, or moved to gameengine?
}
