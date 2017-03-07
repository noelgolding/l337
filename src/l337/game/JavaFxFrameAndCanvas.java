package l337.game;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static l337.game.jfx.utils.JfxStyle.*;

public class JavaFxFrameAndCanvas extends javafx.application.Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Canvas canvas = new Canvas(800, 600);
		VBox vbox = new VBox();
		vbox.setStyle(jfxStyle(FX_BACKGROUND_COLOR, FX_WHITE));
		
		vbox.getChildren().add(getHBox1(canvas));
		vbox.getChildren().add(getHBox2(canvas));
		
		primaryStage.setTitle("My Title");
		primaryStage.setScene(new Scene(vbox, canvas.getWidth() - 10, canvas.getHeight() + 30));
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	private Node getHBox2(Canvas canvas) {
		GraphicsContext g = canvas.getGraphicsContext2D();
		g.setLineWidth(5);
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			g.beginPath();
			g.moveTo(e.getX(), e.getY());
			g.stroke();
			g.closePath();
		});
		canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
			g.lineTo(e.getX(), e.getY());
			g.stroke();
		});
		HBox hbox = new HBox();
		hbox.getChildren().add(canvas);
		hbox.setStyle(jfxStyle(FX_BORDER_COLOR, FX_BLACK));
		return hbox;
	}

	private Node getHBox1(Canvas canvas) {
		GraphicsContext g = canvas.getGraphicsContext2D();
		ColorPicker colorPicker = new ColorPicker(Color.BLACK);
		Button button = new Button("Erase");
		
		colorPicker.setOnAction(e -> g.setStroke(colorPicker.getValue()));
		button.setOnAction(e -> {
			if (button.getText().equals("Erase")){
				g.setStroke(Color.WHITE);
				g.setLineWidth(30);
				canvas.setCursor(Cursor.OPEN_HAND);
				colorPicker.setVisible(false);
				button.setText("Sketch");
			} else {
				g.setStroke(colorPicker.getValue());
				g.setLineWidth(5);
				canvas.setCursor(Cursor.DEFAULT);
				colorPicker.setVisible(true);
				button.setText("Erase");				
			}
		});
		
		HBox hbox = new HBox();
		hbox.setSpacing(5);
		hbox.getChildren().add(button);
		hbox.getChildren().add(colorPicker);
		return hbox;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
