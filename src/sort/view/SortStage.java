package sort.view;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;


public class SortStage extends Application implements EventHandler<ActionEvent>
{
	FileChooser files = new FileChooser();
	
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
		
		saveButton.setOnAction(e -> files.showSaveDialog(primaryStage));
		loadButton.setOnAction(e -> files.showOpenDialog(primaryStage));
		sortButton.setOnAction(e -> sort(primaryStage, getChoice(algoBox)));
		
//		data entry
		typeBox = new ChoiceBox<String>(); // where you select the type of data
		sortByBox = new ChoiceBox<String>(); // where you select how you want it sorted
		entryField = new TextField(); // where you enter the data
		algoBox = new ChoiceBox<String>(); // where you choose the sorting algorithm to use
		
		typeBox.getItems().addAll("Integer", "String", "Double");
		typeBox.getSelectionModel().select(0);
		
		sortByBox.getItems().addAll(getSortBy(getChoice(typeBox)));
		
		algoBox.getItems().addAll("Quicksort", "Merge sort", "Insertion sort", "Heapsort");
		algoBox.getSelectionModel().select(0);
		
//		utility stuff
		VBox layout = new VBox();
		layout.getChildren().add(saveButton);
		layout.getChildren().add(loadButton);
		layout.getChildren().add(typeBox);
		layout.getChildren().add(entryField);
		layout.getChildren().add(algoBox);
		layout.getChildren().add(sortButton);
		
		Scene scene1 = new Scene(layout, 1244, 720);
		primaryStage.setScene(scene1); // assigns this scene to the stage (window)
		primaryStage.show(); // displays to user	
		

	}
	
	
	@Override
	public void handle(ActionEvent event)
	{
		String source = event.getSource().toString();
	}
	
	
	private String getChoice(ChoiceBox<String> box)
	{
		String selection = box.getValue();
		return selection;
	}
	
	
	private String[] getSortBy(String type)
	{
		String[] stringSorts = {"Alphabetical", "Length"};
		String[] intSorts = {"Greatest -> Least", "Least -> Greatest"};
		String[] doubleSorts = {"Greatest -> Least", "Least -> Greatest"};
		String[] sortBy = {""};

		switch(type)
		{
		case "String":
			sortBy = stringSorts;
			break;
		case "Integer":
			sortBy = intSorts;
			break;
		case "Double":
			sortBy = doubleSorts;
			break;
		}
		return sortBy;
	}
	
	
	private void sort(Stage primaryStage, String algorithm)
	{
		VBox layout = new VBox();
		
		Scene scene2 = new Scene(layout, 1244, 720);
		primaryStage.setScene(scene2);
	}
	
	private void load()
	{
		
	}
	
	
	private void save()
	{
		
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}

}
