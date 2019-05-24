package sort.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sort.view.*;
import sort.model.*;

// JDK 10.0.1
public class SortController extends Application
{
	private Stage stage;
	private Scene scene;
	private TabPane tabs;
	private Sorter sorter;
	
	@FXML private SingleSelectionModel<Tab> selectionModel;
	@FXML private Tab setupTab;
	@FXML private Tab sortTab;
	@FXML private Label enterDataLabel;
	@FXML private TextField entryField;
	@FXML private Button submitButton;
	@FXML private Button sortButton;
	@FXML private Button saveButton;
	@FXML private Button loadButton;
	@FXML private ChoiceBox<String> typeBox;
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
	

	@FXML
	/**
	 * Sets up button events, choice box items
	 */
	private void initialize()
	{
		selectionModel = tabs.getSelectionModel();
		typeBox.setItems(FXCollections.observableArrayList(
			/*"string", */"integer", "double"/*, "character"*/));
		typeBox.setValue("integer");
		typeBox.setTooltip(new Tooltip("The type of data to sort"));
		
		algoBox.setItems(FXCollections.observableArrayList(
				"insertion", "merge", "quick"));
		algoBox.setValue("quick");
		algoBox.setTooltip(new Tooltip("The sorting algorithm to be used"));
		
		saveButton.setOnAction((event) -> {
			saveData();
		});
		
		loadButton.setOnAction((event) -> {
			loadData();
		});
		
		submitButton.setOnAction((event) -> {
			submitSort();
			// change to sort tab
		});
		
		sortButton.setOnAction((event) -> {	
			tabs.getSelectionModel().select(sortTab);
			sortData(typeBox.getSelectionModel().getSelectedItem(), algoBox.getSelectionModel().getSelectedItem());
		});
		
	}
	
	private String autoFill(String type)
	{
		String data = "";
		String temp = "";
		if(type.equals("integer"))
		{
			for(int i = 0; i < sorter.getR(100) + 1; i ++)
			{
				data.concat(String.valueOf(sorter.getR(100)) + ",");
			}
			
		}
		else if(type.equals("double"))
		{
			for(int i = 0; i < sorter.getR(100) + 1; i ++)
			{
				temp = String.valueOf(sorter.getR(100)) + "." + String.valueOf(sorter.getR(100));
				data.concat(temp + ",");
			}
		}
		data = data.substring(0, data.length() - 1);
		return data;
	}
	
	/**
	 * Sends data to Sorter
	 */
	private void submitSort()
	{
		try
		{		
			String type = typeBox.getSelectionModel().getSelectedItem();
			String data;
			data = entryField.getText();
			if(!type.isEmpty())
			{		
				sorter = new Sorter(canvas, type, data);
			}
			else
			{
				Alert noType = new Alert(AlertType.ERROR);
				noType.setTitle("Error");
				noType.setHeaderText("No data type specified");
				noType.setContentText("Select a data type to continue");
				noType.showAndWait();
			}
		}
		catch(NumberFormatException numFormatException)
		{
			createErrorMessage(numFormatException, "Ensure entered data matches the selected type");
		}
	}
	
	
	private void createErrorMessage(Exception exception, String content)
	{
		Alert errorMessage = new Alert(AlertType.ERROR);
		errorMessage.setTitle("Error");
		String message = String.valueOf(exception);
		message = message.replace("java.lang.", "");
		errorMessage.setHeaderText(message);
		errorMessage.setContentText(content);
		errorMessage.showAndWait();
	}
	
	private void createErrorMessage(Error error, String content)
	{
		Alert errorMessage = new Alert(AlertType.ERROR);
		errorMessage.setTitle("Error");
		String message = String.valueOf(error);
		message = message.replace("java.lang.", "");
		errorMessage.setHeaderText(message);
		errorMessage.setContentText(content);
		errorMessage.showAndWait();
	}
	
	private void sortData(String algorithm, String type)
	{
		sorter.sortData(algorithm, type);
	}
	
	
	private void saveData()
	{
		String text = entryField.getText();
		try
		{
			FileChooser fileChooser = new FileChooser();
			File saveFile = fileChooser.showSaveDialog(stage);
			Scanner textScanner = new Scanner(text);
			PrintWriter saveText = new PrintWriter(saveFile);
			while(textScanner.hasNext())
			{
				String currentLine = textScanner.nextLine();
				saveText.println(currentLine);
			}
			textScanner.close();
			saveText.close();
		}
		catch(Error error)
		{
			createErrorMessage(error, "broken");
		}
		catch(FileNotFoundException notFound) {
			createErrorMessage(notFound, "yell at reagnan");
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
