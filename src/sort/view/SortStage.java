package sort.view;

import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;


public class SortStage extends Application implements EventHandler<ActionEvent>
{
	Stage window;
	FileChooser files = new FileChooser();
	
	VBox layout1;
	VBox layout2;
	
	Scene scene1;
	Scene scene2;
	
	Canvas sortCanvas;
	
	
	public SortStage()
	{
		super();
		layout1 = new VBox();
		layout2 = new VBox();
		scene1 = new Scene(layout1, 1244, 720);
		scene2 = new Scene(layout2, 1244, 720);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		window = primaryStage;
		setupStage(window);
	}
	
	
	public void setupStage(Stage window)
	{
		setupScene1(layout1);
		setupScene2(layout2);
		window.setScene(scene1);
		window.setTitle("Setup");
		window.show();
	}
	
	
	public void setupScene1(VBox layout1) // for setting up sort
	{
		Button sortSceneButton = new Button("To sort page");
		sortSceneButton.setOnAction(e -> {window.setScene(scene2); window.setTitle("Sort");});
		
		ChoiceBox<String> dataTypeBox = new ChoiceBox<String>();
		dataTypeBox.setItems(FXCollections.observableArrayList(
				"String", "Integer", "Double"));
		
		Label enterDataLabel = new Label("Enter values here, separated by commas");
		TextField entryField = new TextField();
		
		layout1.getChildren().addAll(sortSceneButton, enterDataLabel, entryField);
		
	}
	
	
	public void setupScene2(VBox layout2)
	{
		Button setupSceneButton = new Button("To setup page");
		setupSceneButton.setOnAction(e -> {window.setScene(scene1); window.setTitle("Setup");});
		
		sortCanvas = new Canvas(800, 600);		
		layout2.getChildren().addAll(setupSceneButton, sortCanvas);
		
		
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
	
	

	public static void main(String[] args)
	{
		launch(args);
	}

}
