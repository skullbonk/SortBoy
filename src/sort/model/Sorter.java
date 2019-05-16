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
	
	/**
	 * Submits values for sorting
	 * @param canvas The canvas on which to draw data representations
	 * @param type
	 * @param toSort
	 */
	public void injectData(Canvas canvas, String type, String toSort)
	{
		double canvasWidth = canvas.getWidth();
		double canvasHeight = canvas.getHeight();
/*
 * BEGIN SEPARATING VALUES
 */
		String temp = toSort;
		temp = temp.replaceAll(" ", ""); // remove spaces
		temp = temp.replaceAll("[A-Z]", "[a-z]"); // change to lowercase
		int amountOfValues = (temp.length() - (temp.replace("[a-z]", "").length())) + 1;
		System.out.println(amountOfValues + " values");
		
		String tempString;
		switch(type)
		{
//////////
		case "string":
		
			values = new ArrayList<String>();

			System.out.println(amountOfValues + " values");
			
			for(int index = 0; index < amountOfValues; index ++)
			{
				if(temp.contains(","))
				{
					tempString = temp.substring(0, temp.indexOf(","));					
				}
				else
				{
					tempString = temp.substring(0);
				}
				values.add(tempString);
				temp = temp.replaceFirst(tempString + ",", "");
				System.out.println(temp);
			}
			buildGraphic(values);
			break;
			
//////////						
		case "double":
			values = new ArrayList<Double>();
			double tempDouble;
			for(int index = 0; index < amountOfValues; index ++)
			{
				if(temp.contains(","))
				{
					tempString = temp.substring(0, temp.indexOf(","));					
				}
				else
				{
					tempString = temp.substring(0);
				}
				tempDouble = Double.valueOf(tempString);
				values.add(tempDouble);
				temp = temp.replaceFirst(tempString + ",", "");
				System.out.println(temp);
			}
			buildGraphic(values);
			break;
			
//////////			
		case "character":
			values = new ArrayList<Character>();
			char tempChar;
			temp.replaceAll(",", "");
			for(int index = 0; index < amountOfValues; index ++)
			{
				tempChar = temp.charAt(index);
				values.add(tempChar);
			}
			break;
			
//////////
		case "integer":
			values = new ArrayList<Integer>();
			int tempInt;
			for(int index = 0; index < amountOfValues; index ++)
			{
				if(temp.contains(","))
				{
					tempString = temp.substring(0, temp.indexOf(","));
				}
				else
				{
					tempString = temp.substring(0);
				}
				tempInt = Integer.valueOf(tempString);
				values.add(tempInt);
				temp = temp.replaceFirst(tempString + ",", "");
			}
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
	
	
	public void sortData(String algorithm)
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
