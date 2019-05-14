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
		double canvasWidth = canvas.getWidth();
		double canvasHeight = canvas.getHeight();
		String type;
		
		if(toSort.contains("\"")) {
			type = "string"; }
		else if(toSort.contains(".")) {
			type = "double"; }
		else if(!containsNumbers(toSort)) {
			type = "char";
		}
		else { type = "int"; }
/*
 * BEGIN SEPARATING VALUES
 */
		String temp;
		switch(type)
		{
//////////
		case "string":
			temp = toSort;
			values = new ArrayList<String>();
			
			int amountOfValues = 1;
			for(int index = 0; index < toSort.length(); index ++) {
				if(toSort.charAt(index) == ',') {
					amountOfValues++;
				}
			}
			
			int startIndex, endIndex;
			String tempValue;
			for(int index = 0; index <= amountOfValues; index ++)
			{
				tempValue = temp.substring(temp.indexOf("\"") + 1);
				tempValue = temp.substring(0, temp.indexOf("\""));
				values.add(tempValue);
				temp = temp.replaceFirst(tempValue, "");
			}
			buildGraphic(values, type);
			break;
			
//////////						
		case "double":
			temp = toSort;
			values = new ArrayList<Double>();
			
			break;
			
//////////			
		case "char":
			temp = toSort;
			values = new ArrayList<Character>();
			
			break;
			
//////////
		case "int":
			temp = toSort;
			values = new ArrayList<Integer>();
			
			break;	
		}
		
	}
	
	
	public void buildGraphic(ArrayList list, String type)
	{
		
	}
	
	
	public void updateCanvas()
	{
		
	}
	
	public boolean containsNumbers(String text)
	{
		boolean contains = false;
		String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		for(int index = 0; index < 10; index ++)
		{
			if(text.contains(String.valueOf(index)))
			{
				contains = true;
				break;
			}
		}
		return contains;
	}
}
