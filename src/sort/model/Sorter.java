package sort.model;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sort.controller.SortController;
import sort.view.Chunk;

public class Sorter
{
	static final int ASCII_SIZE = 255;
	private ArrayList<String> valuesString;
	private ArrayList<Integer> valuesInt;
	private ArrayList<Double> valuesDouble;
	private ArrayList<Character> valuesChar;
	private GraphicsContext graphics;
	private Random random;
	private double canvasWidth;
	private double canvasHeight;

	public Sorter(Canvas canvas)
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Sorter(Canvas canvas, String type, String toSort)
	{
		super();
		random = new Random();
//		final GraphicsContext graphics = canvas.getGraphicsContext2D();
		graphics = canvas.getGraphicsContext2D();
		canvasWidth = canvas.getWidth();
		canvasHeight = canvas.getHeight();
		injectData(graphics, type, toSort);
	}
	
	
	/**
	 * Submits values for sorting
	 * ---------------
	 * integers are sorted by value
	 * doubles are sorted by value
	 * characters are sorted by frequency of occurrence (?)
	 * strings are sorted by 
	 * @param canvas The canvas on which to draw data representations
	 * @param type
	 * @param toSort
	 */
	public void injectData(GraphicsContext graphics, String type, String toSort)
	{
/*
 * BEGIN SEPARATING VALUES
 */
		String temp = toSort;
		while(temp.charAt(temp.length() - 1) == ',' || temp.charAt(temp.length() - 1) == ' ') {
			temp = temp.substring(0, temp.length() - 1);
		}
		while(temp.charAt(0) == ' ' || temp.charAt(0) == ',') {
			temp = temp.substring(1, temp.length());
		}
		temp = temp.replaceAll(" ", ""); // remove spaces
		System.out.println("temp: " + temp);
		temp = temp.toLowerCase(); // change to lowercase
		int amountOfValues = (temp.length() - temp.replace(",", "").length() + 1);
		System.out.println("values: " + amountOfValues);
		int maxInt = 0;
		double maxDouble = 0.0;
		
		switch(type)
		{
/*
		case "string":
	
			valuesString = new ArrayList<String>();		
			String tempString;
			for(int index = 0; index < amountOfValues; index ++)
			{
				if(temp.contains(","))
				{
					tempString = temp.substring(0, temp.indexOf(","));					
				}
				else
				{
					tempString = temp;
				}
				valuesString.add(tempString);
				temp = temp.replace(tempString + ",", "");
				System.out.println(temp);
			}
			buildGraphic(valuesString, graphics);
			break;
			
*/						
		case "double":
			
			valuesDouble = new ArrayList<Double>();
			double tempDouble;
			for(int index = 0; index < amountOfValues; index ++)
			{
				if(temp.contains(","))
				{
					tempDouble = Double.valueOf(temp.substring(0, temp.indexOf(",")));					
				}
				else
				{
					tempDouble = Double.valueOf(temp.substring(0));
				}
				valuesDouble.add(tempDouble);				
				temp = temp.replaceFirst(String.valueOf(tempDouble) + ",", "");
			} 
			for(double val : valuesDouble)
			{
				if(maxDouble < val)
				{
					maxDouble = val;
				}
			}
			buildGraphic(valuesDouble, graphics, maxDouble);
			break;
			
//////////			
		case "character":
			
			valuesChar = new ArrayList<Character>();
			char tempChar;
			amountOfValues = temp.length();
			for(int index = 0; index < amountOfValues; index ++)
			{
				tempChar = temp.charAt(index);
				valuesChar.add(tempChar);
			} 
			maxInt = mostCommonCharacterCount(valuesChar);
			
			buildGraphic(valuesChar, graphics, maxInt);
			break;
			
//////////
		case "integer":
			
			valuesInt = new ArrayList<Integer>();
			int tempInt;
			for(int index = 0; index < amountOfValues; index ++)
			{
				if(temp.contains(","))
				{
					tempInt = Integer.valueOf(temp.substring(0, temp.indexOf(",")));					
				}
				else
				{
					tempInt = Integer.valueOf(temp);
				}
				valuesInt.add(Integer.valueOf(tempInt));
				System.out.println("tempInt: " + tempInt);
				temp = temp.replaceFirst(String.valueOf(tempInt) + ",", "");
			}
			
			for(int val : valuesInt)
			{
				if(maxInt < val)
				{
					maxInt = val;
				}
			}
			
			buildGraphic(valuesInt, graphics, maxInt);
			break;	
		}
	}
	
	/**
	 * Builds a graphic where values are integers
	 * @param list The ArrayList containing the values to be visualized
	 * @param graphics The GraphicsContext of the canvas
	 * @param maxValue The maximum value that will need to be represented
	 */
	public void buildGraphic(ArrayList list, GraphicsContext graphics, double maxValue)
	{
		ArrayList<Color> colors = new ArrayList<Color>();
		int amountOfValues = list.size();
		double value;
		double chunkHeight;
		double chunkWidth = canvasWidth / list.size();
		double maxChunkHeight = canvasHeight * .9;
		double chunkHeightScale = maxChunkHeight / maxValue; 
		
		System.out.println("chunk width: " + chunkWidth);
		System.out.println("max chunk height: " + maxChunkHeight);
		System.out.println("chunk height scale: " + chunkHeightScale);
		System.out.println("----------------");
		Chunk chunk;
		double startX;
		for(int index = 0; index < amountOfValues; index ++)
		{	
			colors.add(generateColor());
			value = Double.valueOf(String.valueOf(list.get(index)));
			startX = chunkWidth * index;
			chunkHeight = chunkHeightScale * Double.valueOf(String.valueOf(list.get(index)));
			chunk = new Chunk(graphics, colors.get(index), chunkWidth, chunkHeight, String.valueOf(list.get(index)), startX);
			try {
			graphics.wait(100);
			}
			catch(InterruptedException interrupted) {
				System.out.println("interrupted");
			}
		}
	}
	
	
	public void sortData(String type, String algorithm)
	{
		ArrayList values = getList(type);
		System.out.println("algorithm: " + algorithm);
		double max = 0.0;
		for(int index = 0; index < values.size(); index ++) {
			if(max < Double.valueOf(String.valueOf(values.get(index)))) {
				max = Double.valueOf(String.valueOf(values.get(index)));
			}
		}
		int low = 0, high = values.size() - 1;
		
//		ArrayList sorted;
		switch(algorithm) {
		case "quick":
			values = quickSort(values, low, high);
			break;
		case "merge":
//			ArrayList sorted = mergeSort();
			break;
		case "insertion":
			// insertion sort
			break;
		}
		graphics.clearRect(0.0, 0.0, canvasWidth, canvasHeight);
		buildGraphic(values, graphics, max);
	}
		
	/* low =  start index, high = end index */
	private ArrayList quickSort(ArrayList values, int low, int high) 
	{
		ArrayList sorted = values;
		if(low < high)
		{

			int part = partition(sorted, high, low);
			
			sorted = quickSort(sorted, low, part);
			sorted = quickSort(sorted, part + 1, high);
		}
		
		return sorted;
	}
	
	
	private int partition(ArrayList sorted, int high, int low)
	{
		double pivot = Double.valueOf(String.valueOf(sorted.get(low + (high - low) / 2)));
		int index = low - 1;
		int subIndex = high + 1;
		while(true)
		{
			do 
			{
				index++;
			}
			while(Double.valueOf(String.valueOf(sorted.get(index))) < pivot);
			do
			{
				subIndex--;
			}
			while(Double.valueOf(String.valueOf(sorted.get(subIndex))) > pivot);
			if(index >= subIndex)
			{
				return subIndex;
			}
			double temp = Double.valueOf(String.valueOf(sorted.get(index)));
			sorted.set(index, sorted.get(subIndex));
			sorted.set(subIndex, temp);
		}
	}
	
	
	public ArrayList getList(String type)
	{
		ArrayList list = new ArrayList();
		switch(type) {
		case "integer":
			list = valuesInt;
			break;
		case "double":
			list = valuesDouble;
			break;
		case "character":
			list = valuesChar;
			break;
		case "string":
			list = valuesString;
			break;
		}
		return list;
	}
	
	
	/**
	 * @return a number from 0 to 255
	 */
	public double rVal()
	{
		return random.nextDouble();
	}
	
	/** 
	 * @param max The maximum int, exclusive
	 * @return A random integer between 0 and the specified maximum
	 */
	public int getR(int max)
	{
		return random.nextInt(max);
	}
	
	
	public Color generateColor()
	{
		int selector = random.nextInt(3);
		double red = rVal(), green = rVal(), blue = rVal();
		double[] rgb = {red, green, blue};
		if(rgb[selector] > .85) {
			rgb[selector] = rgb[selector] * .75;
		}
		red = rgb[0]; green = rgb[1]; blue = rgb[2];
		return new Color(red, green, blue, .9);
	}
	
	public int mostCommonCharacterCount(ArrayList<Character> list)
	{
		int count[] = new int[ASCII_SIZE];
		String temp = "";
		int max = 0;
		
		for(int i = 0; i < list.size(); i ++)
		{
			count[list.get(i)]++;
		}
		
		char[] characters = new char[list.size()];
		for(int i = 0; i < list.size(); i ++)
		{
			characters[i] = list.get(i);
			for(int j = 0; j <= i; j ++)
			{				
				if(list.get(i).equals(characters[j]))
				{
					max ++;
				}
			}			
		}
		
		
		return max;
	}
}
