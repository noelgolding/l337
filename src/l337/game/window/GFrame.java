package l337.game.window;

import java.awt.Canvas;

import javax.swing.JFrame;

public class GFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public GFrame(String title, Canvas canvas, boolean resizable) {
		setTitle(title);
		
		add(canvas);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		canvas.requestFocus();

		setResizable(resizable);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
}