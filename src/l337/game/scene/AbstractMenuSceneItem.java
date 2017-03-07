package l337.game.scene;

public abstract class AbstractMenuSceneItem {
	protected String text;
	protected GameConfigCommand command;
	
	public AbstractMenuSceneItem(String text, GameConfigCommand command) {
		this.text = text;
		this.command = command;
	}

	public String getText() {
		return text;
	}

	public GameConfigCommand getCommand() {
		return command;
	}

}
