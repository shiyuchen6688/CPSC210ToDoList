package ui;

import exceptions.NotFoundException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import sun.java2d.loops.FillRect;

import java.awt.image.ImagingOpException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// extends Application to use GUI
public class ToDoListUsage {
    public static final int SCENE_WIDTH = 300;
    public static final int SCENE_HEIGHT = 250;
    public static final String BUTTON_NAME_ADDTASKBUTTON = "New Task";
    public static final String BUTTON_NAME_PRINTALLTASKSBUTTON = "All Tasks";
    public static final String BUTTON_NAME_PRINTALLOVERDUETASKSBUTTON = "Overdue Tasks";
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static String dateFormat = "yyyy-MM-dd";


    // object from model
    private static ToDoList toDoList;
    private static Tool tool;
    private static FileReaderAndWriter fileReaderAndWriter;

    /// Stage and scenes
    Stage window;
    Scene scene1, scene2;
    // Buttons
    Button addTaskButton;
    Button printAllTasksButton;
    Button printAllOverdueTasksButton;

    public static void main(String[] args) throws ParseException, IOException{
        // setups
        toDoList = new ToDoList();
        tool = new Tool(toDoList);
//        } catch (NotFoundException e) {
//            System.out.println("Can not be found, please try again");
//        } catch (IOException e) {
//            System.out.println("This is a bigger problem, please try again");
//        }
        tool.userChooseFormat();

        // Choose file
        String fileName = null;
        try {
            fileName = tool.chooseFileToSaveHistory();
        } catch (FileNotFoundException e) {
            System.out.println("File Not found");
        } finally{
            if (fileName == null) {
                System.out.println("Since file can not be found, we set the file to default file: inputfile.txt");
                fileName = "inputfile.txt";
            } else {
                System.out.println("file found, everything works fine");
            }
        }
        fileReaderAndWriter = new FileReaderAndWriter(toDoList, fileName);

        // load and print all history
        fileReaderAndWriter.load();

        // Reminders
        System.out.println("\nHere is a reminder of all the task that are close to due!!");
        toDoList.printAllCloseToDue();

        // interactions inside intellij
        tool.handleUserInput();    // Comment this out to able to use GUI

        // Load and Save
        fileReaderAndWriter.saveAllHistoryToInput();
        fileReaderAndWriter.copyInputToOutput();


        // TODO need to finish This part is for GUI
        // launch(args);
    }

    // helper to split words in load and save. File download from CPSC-210 EDX.
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split("  ");
        return new ArrayList<>(Arrays.asList(splits));
    }


//    // This method is for GUI, TODO not finished yet
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        // let window reference primaryStage
//        window = primaryStage;
//
//        // setup initialText message
//        Label initialText = new Label("Welcome to your ToDoList");
//
//        // Button to print all tasks
//        printAllTasksButton = new Button(BUTTON_NAME_PRINTALLTASKSBUTTON);
//        printAllTasksButton.setOnAction(e -> toDoList.printAllTasks());
//
//        // Button to add task
//        addTaskButton = new Button(BUTTON_NAME_ADDTASKBUTTON);
//        addTaskButton.setOnAction(e -> {
//            window.setScene(scene2);
//            PopupAd.display("Advertisement", "Nikdas, 50% cheaper than them");
//        });   // switch scene from scene1 to scene2 and pop up ad
//
//        // Button to print all overdue tasks
//        printAllOverdueTasksButton = new Button(BUTTON_NAME_PRINTALLOVERDUETASKSBUTTON);
//        printAllOverdueTasksButton.setOnAction(e -> toDoList.printAllOverdueTasks());
//
//
//        // Layout1 - vertically in scene1
//        VBox layoutScene1 = new VBox(20);   // 5 is the spacing between elements inside the box
//        layoutScene1.getChildren().addAll(initialText, printAllTasksButton, addTaskButton, printAllOverdueTasksButton);
//        layoutScene1.setAlignment(Pos.CENTER);
//
//        // Set scene 1
//        scene1 = new Scene(layoutScene1, SCENE_WIDTH, SCENE_HEIGHT);
//
//        // addTaskText - add task text in scene 2
//        Label addTaskText = new Label("What is the name of your task?");
//        // button2 - back to scene1
//        Button button2 = new Button("Back to home");  // TODO: change scene1 to home
//        button2.setOnAction(e -> window.setScene(scene1));
//
//        // Layout2 - vertically in scene 2
//        VBox layoutScene2 = new VBox(20);
//        layoutScene2.getChildren().addAll(addTaskText, button2);
//
//        // set scene2
//        scene2 = new Scene(layoutScene2, SCENE_WIDTH, SCENE_HEIGHT);
//
//        window.setScene(scene1);
//        window.setTitle("TODO List");
//        window.show();
//
//
//        //        printAllOverdueTasksButton = new Button(BUTTON_NAME_PRINTALLOVERDUETASKSBUTTON);
//        //        layout.getChildren().add(addTaskButton);
//        //        layout.getChildren().add(printAllOverdueTasksButton);
//
//    }


    // EFFECTS: return this toDoList
    public ToDoList getToDoList() {
        return toDoList;
    }


}
