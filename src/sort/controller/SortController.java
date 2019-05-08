package sort.controller;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sort.view.*;
import sort.model.*;


public class SortController
{
	private static final int INTEGER = 1;
	private static final int DOUBLE = 2;
	private static final int STRING = 3;
	
	private SortStage stage;
	private Sorter sorter;
	@FXML private TextField entryField;
	
	public SortController()
	{
		stage = new SortStage();
		sorter = new Sorter();
	}

	
	@FXML
	private void handleSortButtonAction(ActionEvent event)
	{
		submitForSort(entryField.getText());
	}
	
	private void submitForSort(String data)
	{
		int dataType;
		if(data.contains("\""))
		{
			dataType = STRING;
		}
		else if(data.contains("."))
		{
			dataType = DOUBLE;
		}
		else
		{
			dataType = INTEGER;
		}
		
		
		switch(dataType)
		{
		case STRING:
			
			break;
		case DOUBLE:
			
			break;
		case INTEGER:
			
			break;
		}
	}
	
	
	public static void main(String[] args)
	{
		SortStage.main(args);
	}
}
