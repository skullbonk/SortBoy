package sort.view;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.*;


public class SortStage extends Application implements EventHandler<ActionEvent>
{
	Button saveButton;
	Button loadButton;
	public SortStage()
	{
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		setupStage(primaryStage);
	}
	
	public void setupStage(Stage primaryStage)
	{
		saveButton = new Button();
		loadButton = new Button();
		
		saveButton.setText("Save");
		loadButton.setText("Load");
		
		saveButton.setOnAction(this);
		loadButton.setOnAction(this);
		
		StackPane layout = new StackPane();
		layout.getChildren().add(saveButton);
		layout.getChildren().add(loadButton);
		
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
	
	public static void main(String[] args)
	{
		launch(args);
	}

}
