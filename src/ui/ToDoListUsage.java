package ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Birthday;
import model.ToDoList;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ToDoListUsage extends Application {
    public static final int SCENE_WIDTH = 300;
    public static final int SCENE_HEIGHT = 250;
    public static final String BUTTON_NAME_ADDTASKBUTTON = "New Task";
    public static final String BUTTON_NAME_PRINTALLTASKSBUTTON = "All Tasks";
    public static final String BUTTON_NAME_PRINTALLOVERDUETASKSBUTTON = "Overdue Tasks";
    public static final String MESSAGE_END_OUTPUT = "Welcome, these are tasks you added before";


    // object from model
    private static ToDoList toDoList;
    private static Tool tool;
    private static Birthday birthdayOfShiyu = new Birthday("Shiyu");
    /// Stage and scenes
    Stage window;
    Scene scene1, scene2;
    // Buttons
    Button addTaskButton;
    Button printAllTasksButton;
    Button printAllOverdueTasksButton;

    public static void main(String[] args) throws ParseException, IOException {
        // setups
        toDoList = new ToDoList();
        toDoList.addTask(birthdayOfShiyu);

        // interactions inside intellij
        tool = new Tool(toDoList);
        tool.handleUserInput();    // Comment this out to able to use GUI

        // Load and Save
        List<String> lines = Files.readAllLines(Paths.get("inputfile.txt"));;
        PrintWriter writer = new PrintWriter("outputfile.txt","UTF-8");
        lines.add(MESSAGE_END_OUTPUT);
        for (int i = 0; i < lines.size(); i++){
            String line = lines.get(i);
            if (line.equals(MESSAGE_END_OUTPUT)) {
                System.out.println(MESSAGE_END_OUTPUT);
            } else {
                ArrayList<String> partsOfLine = splitOnSpace(line);
                System.out.print("Task: " + partsOfLine.get(0) + " ");
                System.out.println("DueDate: " + partsOfLine.get(1));
                writer.println(line);
            }
        }
        writer.close();

        // This part is for GUI TODO need to finish
        // launch(args);
    }

    // helper to split words in load and save. File download from CPSC-210 EDX.
    public static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }


    // This method is for GUI, TODO not finished yet
    @Override
    public void start(Stage primaryStage) throws Exception {
        // let window reference primaryStage
        window = primaryStage;

        // setup initialText message
        Label initialText = new Label("Welcom to your ToDoList");

        // Button to print all tasks
        printAllTasksButton = new Button(BUTTON_NAME_PRINTALLTASKSBUTTON);
        printAllTasksButton.setOnAction(e -> toDoList.printAllTasks());

        // Button to add task
        addTaskButton = new Button(BUTTON_NAME_ADDTASKBUTTON);
        addTaskButton.setOnAction(e -> {
            window.setScene(scene2);
            PopupAd.display("Advertisement", "Nikdas, 50% cheaper than them");
        });   // switch scene from scene1 to scene2 and pop up ad

        // Button to print all overdue tasks
        printAllOverdueTasksButton = new Button(BUTTON_NAME_PRINTALLOVERDUETASKSBUTTON);
        printAllOverdueTasksButton.setOnAction(e -> toDoList.printAllOverdueTasks());


        // Layout1 - vertically in scene1
        VBox layoutScene1 = new VBox(20);   // 5 is the spacing between elements inside the box
        layoutScene1.getChildren().addAll(initialText, printAllTasksButton, addTaskButton, printAllOverdueTasksButton);
        layoutScene1.setAlignment(Pos.CENTER);

        // Set scene 1
        scene1 = new Scene(layoutScene1, SCENE_WIDTH, SCENE_HEIGHT);

        // addTaskText - add task text in scene 2
        Label addTaskText = new Label("What is the name of your task?");
        // button2 - back to scene1
        Button button2 = new Button("Back to home");  // TODO: change scene1 to home
        button2.setOnAction(e -> window.setScene(scene1));

        // Layout2 - vertically in scene 2
        VBox layoutScene2 = new VBox(20);
        layoutScene2.getChildren().addAll(addTaskText, button2);

        // set scene2
        scene2 = new Scene(layoutScene2, SCENE_WIDTH, SCENE_HEIGHT);

        window.setScene(scene1);
        window.setTitle("TODO List");
        window.show();


        //        printAllOverdueTasksButton = new Button(BUTTON_NAME_PRINTALLOVERDUETASKSBUTTON);
        //        layout.getChildren().add(addTaskButton);
        //        layout.getChildren().add(printAllOverdueTasksButton);

    }


    // EFFECTS: return this toDoList
    public ToDoList getToDoList() {
        return toDoList;
    }

}
