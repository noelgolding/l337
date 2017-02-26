package l337.game;

import l337.game.window.GFrame;

public class GameRunner extends Thread {
	public GameRunner(Game game) {
		super(game, game.getTitle());

		new GFrame(game.getTitle(), game.getCanvas(), game.isResizable());
	}
}