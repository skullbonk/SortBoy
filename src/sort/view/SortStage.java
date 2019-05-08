package sort.view;

import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;

import sort.controller.SortController;


public class SortStage extends Application implements EventHandler<ActionEvent>
{
	Stage window;
	FileChooser files = new FileChooser();
	
//	AnchorPane layout1;
//	AnchorPane layout2;
	
	TabPane tabs;
	
	Scene scene;
	
	Canvas sortCanvas;
	
	AnchorPane layout1;
	AnchorPane layout2;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("SortBot.fxml"));
		window = primaryStage;
		window.setTitle("SortBoy");
		window.setScene(new Scene(root, 800, 600));
		window.show();
	}
	
	
	public SortStage()
	{
		super();
	}
	
	
	// SETUP
	public Tab setupTab1(AnchorPane layout1) // for setting up sort
	{
		Tab tab1 = new Tab("Setup");
		Button sortSceneButton = new Button("To sort page");
		sortSceneButton.setOnAction(e -> {/*set tab to tab2 (sort)*/;});
		
		ChoiceBox<String> dataTypeBox = new ChoiceBox<String>();
		dataTypeBox.setItems(FXCollections.observableArrayList(
				"String", "Integer", "Double"));
		
		Label enterDataLabel = new Label("Enter values here, separated by commas");
		TextField entryField = new TextField();
		
		layout1.getChildren().addAll(sortSceneButton, enterDataLabel, entryField);
		
		return tab1;
	}
	
	// SORT
	public Tab setupTab2(AnchorPane layout2)
	{
		Tab tab2 = new Tab("Sort");
		Button setupSceneButton = new Button("To setup page");
		setupSceneButton.setOnAction(e -> {/*set tab to tab1 (setup)*/;});
		
		sortCanvas = new Canvas(800, 600);		
		layout2.getChildren().addAll(setupSceneButton, sortCanvas);
		
		return tab2;
	}
	
	
	@Override
	public void handle(ActionEvent event)
	{
		String source = event.getSource().toString();
	}
	
	
	private String getSelection(ChoiceBox<String> box)
	{
		String selection = box.getValue();
		return selection;
	}
	
	
	

	public static void main(String[] args)
	{
		launch(args);
	}

}
