package sort.view;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.*;


public class SortStage extends Application implements EventHandler<ActionEvent>
{
	Button saveButton;
	Button loadButton;
	Button sortButton;
	
	TextField entryField;
	ChoiceBox<String> typeBox;
	ChoiceBox<String> sortByBox;
	ChoiceBox<String> algoBox;
	
	public SortStage()
	{
		super();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		setupStage(primaryStage);
	}
	
	public void setupStage(Stage primaryStage)
	{
//		buttons
		saveButton = new Button();
		loadButton = new Button();
		sortButton = new Button();
		
		saveButton.setText("Save");
		loadButton.setText("Load");
		sortButton.setText("Sort");
		
		saveButton.setOnAction(this);
		loadButton.setOnAction(this);
		sortButton.setOnAction(this);
		
//		data entry
		typeBox = new ChoiceBox<String>();
		sortByBox = new ChoiceBox<String>();
		entryField = new TextField();
		algoBox = new ChoiceBox<String>();
		
		typeBox.getItems().addAll("Integer", "String", "Double");
		typeBox.getSelectionModel().select(0);
		
		
		
		
//		utility stuff
		StackPane layout = new StackPane();
		layout.getChildren().add(saveButton);
		layout.getChildren().add(loadButton);
		layout.getChildren().add(typeBox);
		layout.getChildren().add(entryField);
		layout.getChildren().add(algoBox);
		
		Scene scene = new Scene(layout, 1244, 720);
		primaryStage.setScene(scene); // assigns this scene to the stage (window)
		primaryStage.show(); // displays to user	
	}
	
	
	@Override
	public void handle(ActionEvent event)
	{
		String source = event.getSource().toString();

		switch(source)
		{
		case "saveButton":
			
			break;
		case "loadButton":
			
			break;
		}
	}
	
	
	private String getChoice(ChoiceBox<String> box)
	{
		String selection = box.getValue();
		return selection;
	}
	
	
	private String[] getSortBy(String type)
	{
		switch(type)
		{
		case "String":
			String[] sortBy = {"Alphabetical", "Length"};
			return sortBy;
			break;
		case "Integer":
			
			break;
		case "Double":
			
			break;
		}
	}
	
	
	public static void main(String[] args)
	{
		launch(args);
	}

}
