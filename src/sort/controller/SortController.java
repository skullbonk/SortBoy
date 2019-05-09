package sort.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sort.view.*;
import sort.model.*;


public class SortController extends Application
{
	private Stage stage;
	private TabPane tabs;
	private Sorter sorter;
	@FXML private Tab setupTab;
	@FXML private Tab sortTab;
	@FXML private Label enterDataLabel;
	@FXML private TextField entryField;
	@FXML private Button sortButton;
	@FXML private Button loadButton;
	@FXML private ChoiceBox algoBox;
	
	public SortController()
	{
		super();
		tabs = new TabPane();
		sorter = new Sorter();
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		FXMLLoader fxml = new FXMLLoader();
		FileInputStream fxmlStream = new FileInputStream("src/sort/view/SortBoy.fxml");
		fxml.setRoot(tabs);
		tabs = (TabPane) fxml.load(fxmlStream);
		stage = primaryStage;
		stage.setTitle("SortBoy");
		stage.setScene(new Scene(tabs, 800, 600));
		stage.show();
	}
	
	
//	@FXML
//	private void handleLoadButtonAction(ActionEvent event)
//	{
//		loadData();
//	}

	@FXML
	private void initialize()
	{
		loadButton.setOnAction((event) -> {
			loadData();
		});
		
	}
	
	
	
	private void loadData()
	{
		try 
		{
			String text = "";
			FileChooser fileChooser = new FileChooser();
			File textFile = fileChooser.showOpenDialog(stage);
			Scanner fileScanner = new Scanner(textFile);
			text = fileScanner.toString();
			entryField.setText(text);
		}
		
		catch(FileNotFoundException exception)
		{
			entryField.setText("error loading file");
		}
	}
	
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}
}
