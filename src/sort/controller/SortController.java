package sort.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
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
	private Scene scene;
	private TabPane tabs;
	private Sorter sorter;
//	private SingleSelectionModel<Tab> selectionModel;
	@FXML private Tab setupTab;
	@FXML private Tab sortTab;
	@FXML private Label enterDataLabel;
	@FXML private TextField entryField;
	@FXML private Button sortButton;
	@FXML private Button loadButton;
	@FXML private ChoiceBox<String> algoBox;
	@FXML public Canvas canvas;
	
	public SortController()
	{
		super();
		tabs = new TabPane();
		sorter = new Sorter(canvas);
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
		scene = new Scene(tabs, 800, 600);
		stage.setScene(scene);
		tabs.getSelectionModel().select(setupTab);
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
		
		sortButton.setOnAction((event) -> {
			submitSort();
			// change to sort tab
		});
		
	}
	
	
	private void submitSort()
	{
		if(sorter == null)
		{
			sorter = new Sorter(canvas, entryField.getText());
		}
		else
		{
			sorter.injectData(canvas, entryField.getText());
			System.out.println(entryField.getText());
		}
	}
	
	
	private void loadData()
	{
		try 
		{
			String text = "";
			FileChooser fileChooser = new FileChooser();
			File textFile = fileChooser.showOpenDialog(stage);
			String extension = textFile.getPath();
			extension = extension.substring(extension.lastIndexOf("."));
			System.out.println("File extension: " + extension);
			assert extension.equalsIgnoreCase(".txt");
//			FileReader reader = new FileReader(textFile.getPath());
			text = new String(Files.readAllBytes(Paths.get(textFile.getPath())));
			entryField.setText(text);
		}
		
		catch(FileNotFoundException exception)
		{
			entryField.setText("error loading file: file not found");
		}
		catch(IOException ioError)
		{
			entryField.setText("error loading file");
		}
		catch(AssertionError assertionError)
		{
			entryField.setText("error loading file: not a text file");
		}
	}
	
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}
}
