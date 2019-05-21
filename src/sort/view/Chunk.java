package sort.view;


import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Chunk
{
	public Chunk(GraphicsContext graphics, Color color, double bottomLeft, double topLeft, double topRight, double bottomRight,  String value)
	{
		graphics.setFill(color);
		
	}
}
