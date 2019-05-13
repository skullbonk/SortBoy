package sort.model;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import sort.controller.SortController;

public class Sorter
{
	private ArrayList values;

	public Sorter(Canvas canvas)
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Sorter(Canvas canvas, String toSort)
	{
		super();
		injectData(canvas, toSort);
	}
	
	public void injectData(Canvas canvas, String toSort)
	{
		String temp;
		double canvasWidth = canvas.getWidth();
		double canvasHeight = canvas.getHeight();
		String type;
		if(toSort.contains("\"")) {
			type = "string"; }
		else if(toSort.contains(".")) {
			type = "double"; }
		else { type = "int"; }
		
		switch(type)
		{
		case "string":
			temp = toSort;
			values = new ArrayList<String>();
			
			break;
			
			
		case "double":
			temp = toSort;
			values = new ArrayList<Double>();
			
			break;
			
			
		case "int":
			temp = toSort;
			values = new ArrayList<Integer>();
			
			break;
			
		}
		
	}
	
	public void updateCanvas()
	{
		
	}
}
