package sort.view;


import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class Chunk
{
	public Chunk(GraphicsContext graphics, Color color, double width, double height, String value, double startX)
	{
		graphics.setFill(color);
		graphics.setStroke(Color.BLACK);
		graphics.setLineWidth(1);
		graphics.fillRect(startX, 0.0, width, height);
		graphics.strokeRect(startX, 0.0, width, height);
		graphics.setFill(Color.BLACK);
		graphics.setTextAlign(TextAlignment.CENTER);
		graphics.fillText(value, startX + (width / 2), height + 15, width);
//		graphics.strokeText(value, startX, height + 15, width);
	}
}
