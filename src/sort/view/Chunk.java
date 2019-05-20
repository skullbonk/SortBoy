package sort.view;


import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Chunk
{
	public Chunk(GraphicsContext graphics, Color color, double width, double height, String value)
	{
		graphics.setFill(color);
	}
}
