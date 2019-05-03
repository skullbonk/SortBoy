package sort.controller;

import javafx.application.*;
import sort.view.*;
import sort.model.*;


public class SortController
{
	private SortStage stage;
	private Sorter sorter;
	
	public SortController()
	{
		stage = new SortStage();
		sorter = new Sorter();
	}

	
	public static void main(String[] args)
	{
		SortStage.main(args);
	}
}
