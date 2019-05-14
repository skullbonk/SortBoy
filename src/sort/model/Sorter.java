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
	
	public Sorter(Canvas canvas, String type, String toSort)
	{
		super();
		injectData(canvas, type, toSort);
	}
	
	public void injectData(Canvas canvas, String type, String toSort)
	{
		double canvasWidth = canvas.getWidth();
		double canvasHeight = canvas.getHeight();
/*
 * BEGIN SEPARATING VALUES
 */
		String temp = toSort;
		temp = temp.replaceAll(" ", "");
		temp = temp.replaceAll("[A-Z]", "[a-z]");
		int amountOfValues = temp.length() - (temp.replace("[a-z]", "").length());
		
		switch(type)
		{
//////////
		case "string":
			values = new ArrayList<String>();

			System.out.println(amountOfValues + " values");
			
			String tempValue;
			temp = temp.replaceAll("\"", "");
			temp = temp.replaceAll(" ", "");
			for(int index = 0; index < amountOfValues; index ++)
			{
				tempValue = temp.substring(0, temp.indexOf(","));
				values.add(tempValue);
				temp = temp.replaceFirst(tempValue + ",", "");
				System.out.println(temp);
			}
			buildGraphic(values);
			break;
			
//////////						
		case "double":
			values = new ArrayList<Double>();
			
			break;
			
//////////			
		case "char":
			values = new ArrayList<Character>();
			
			break;
			
//////////
		case "int":
			values = new ArrayList<Integer>();
			
			break;	
		}
		
	}
	
	
	public void buildGraphic(ArrayList list)
	{
		for(int index = 0; index < list.size(); index ++)
		{
			System.out.println(list.get(index));
		}
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
