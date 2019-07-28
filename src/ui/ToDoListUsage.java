package ui;

import exceptions.TaskAlreadyExistException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import ui.display.PopupAd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static javafx.application.Application.launch;

// extends Application to use display
public class ToDoListUsage extends Application {
    public static final int LARGE_SCENE_WIDTH = 300;
    public static final int LARGE_SCENE_HEIGHT = 250;
    public static final int SMALL_SCENE_WIDTH = 200;
    public static final int SMALL_SCENE_HEIGHT = 80;
    public static final String BUTTON_NAME_ADDTASKBUTTON = "New Task";
    public static final String BUTTON_NAME_PRINTALLTASKSBUTTON = "All Tasks";
    public static final String BUTTON_NAME_PRINTALLOVERDUETASKSBUTTON = "Overdue Tasks";
    public static final String CHOOSE_DATE_FORMABUTTONT = "Choose Date Format";
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static String dateFormat = "yyyy-MM-dd";


    // object from model
    private static ToDoMap toDoMap;
    private static ToDoList curList;
    private static Tool tool;
    private static FileReaderAndWriter fileReaderAndWriter;


    /// Stage and scenes
    Stage window;
    Scene sceneMain, sceneAddTAsk, sceneChooseDateFormat;
    // Buttons
    Button addTaskButton;
    Button printAllTasksButton;
    Button printAllOverdueTasksButton;
    Button chooseDateFormateButton;

    public static void main(String[] args) {
        // setups
        toDoMap = new ToDoMap();
        toDoMap.addToDoList("school");
        launch(args);


        // Choose file
        String fileName = null;

        try {
            fileName = tool.chooseFileToSaveHistory();
        } catch (FileNotFoundException e) {
            System.out.println("File Not found");
        } finally {
            if (fileName == null) {
                System.out.println("Since file can not be found, we set the file to default file: inputfile.txt");
                fileName = "inputfile.txt";
            } else {
                System.out.println("file found, everything works fine");
            }
        }

        try {
            fileReaderAndWriter = new FileReaderAndWriter(fileName);
        } catch (IOException e) {
            System.out.println("\nSomething is wrong with this file");
            System.out.println("Use default inputfile.txt instead");
            try {
                fileReaderAndWriter = new FileReaderAndWriter("inputfile.txt");
            } catch (IOException ex) {
                ex.printStackTrace(); // probably not gonna happen
            }

        }

        // load and print all history
        try {
            fileReaderAndWriter.load(toDoMap);
        } catch (IOException e) {
            System.out.println("Found IOException");
        } catch (ParseException e) {
            System.out.println("WARNING: Some of old tasks might not show up");
        }


        // interactions inside intellij
        // TODO: RECOVER THIS To RUN TOOL
         tool.handleUserInput(toDoMap);    // Comment this out to able to use display


        // Load and Save
        try {
            fileReaderAndWriter.saveAllHistoryInMapToInput(toDoMap);
            fileReaderAndWriter.copyInputToOutput();
        } catch (IOException e) {
            System.out.println("WARNING: some of your tasks might not be saved");
        } finally {
            System.out.println("Thank for using this app, have a good day!");
        }

    }


    // This method is for display, TODO not finished yet
    @Override
    public void start(Stage primaryStage) throws Exception {
        tool = new Tool();
        // let window reference primaryStage
        window = primaryStage;


        // main scene

        // setup initialText message
        Label initialText = new Label("Welcome to your ToDoList");


        // options on mainScene

        // Button to choose date Format
        chooseDateFormateButton = new Button(CHOOSE_DATE_FORMABUTTONT);
        chooseDateFormateButton.setOnAction(e -> {
            window.setScene(sceneChooseDateFormat);
        });

        // Button to print all tasks
        printAllTasksButton = new Button(BUTTON_NAME_PRINTALLTASKSBUTTON);
        printAllTasksButton.setOnAction(e -> {
            List<String> tasks = toDoMap.returnAllMapTasks();
            displayListMessage(tasks);
        });

        // Button to add task
        addTaskButton = new Button(BUTTON_NAME_ADDTASKBUTTON);
        addTaskButton.setOnAction(e -> {
            window.setScene(sceneAddTAsk);
            PopupAd.display("Advertisement", "Nikdas, 50% cheaper than them");
        });   // switch scene from sceneMain to sceneAddTAsk and pop up ad

        // Button to print all overdue tasks
        printAllOverdueTasksButton = new Button(BUTTON_NAME_PRINTALLOVERDUETASKSBUTTON);
        printAllOverdueTasksButton.setOnAction(e -> toDoMap.printAllOverdueTasks());


        // Layout1 - vertically in sceneMain
        VBox layoutScene1 = new VBox(20);   // 5 is the spacing between elements inside the box
        layoutScene1.getChildren().addAll(initialText, chooseDateFormateButton, printAllTasksButton, addTaskButton, printAllOverdueTasksButton);
        layoutScene1.setAlignment(Pos.CENTER);

        // Set sceneMain
        sceneMain = new Scene(layoutScene1, LARGE_SCENE_WIDTH, LARGE_SCENE_HEIGHT);




        // scene to choose date format
        Label choseDateFormatText = new Label("You can choose your default date format here:");
        Button buttonForDate1 = new Button("yyyy-MM-dd");
        Button buttonForDate2 = new Button("dd-MM-yyyy");
        Button buttonForDate3 = new Button("MM-dd-yyyy");
        VBox layoutChooseDateFormat = new VBox(20);
        layoutChooseDateFormat.getChildren().addAll(choseDateFormatText, buttonForDate1, buttonForDate2, buttonForDate3);
        sceneChooseDateFormat = new Scene(layoutChooseDateFormat, LARGE_SCENE_WIDTH, LARGE_SCENE_HEIGHT);

        // action of each date button
        buttonForDate1.setOnAction(e -> {
            String msg = tool.handleFormatOptions("yyyy-MM-dd");
            displayMessage(msg);
        });
        buttonForDate2.setOnAction(e -> {
            String msg = tool.handleFormatOptions("dd-MM-yyyy");
            displayMessage(msg);
        });
        buttonForDate3.setOnAction(e -> {
            String msg = tool.handleFormatOptions("MM-dd-yyyy");
            displayMessage(msg);
        });





        // scene to add task

        // Layout2 - vertically in scene 2
        VBox layoutSceneAddTask = new VBox(20);
        layoutSceneAddTask.setAlignment(Pos.CENTER);


        // which list to add it to
        Label askListText = new Label("What list do you want to add your task? " +
                "\n-------------------Or------------------" +
                "\nenter name of new list you want to create");

        // TODO let user create their own list

        TextField listInput = new TextField();
        listInput.setPromptText("Enter name of the list here");

        // name of task adding
        Label askNameText = new Label("What is the name of your task?");

        TextField nameInput = new TextField();
        nameInput.setPromptText("Enter your name here");

        // dueDate of task adding
        Label askDueDateText = new Label("What is the due date of your task? Enter skip if no due date");

        TextField dueDateInput = new TextField();
        dueDateInput.setPromptText("Enter your due date here");

        // urgent or not?
        Label askUrgentText = new Label("Do you want to set it as urgent task? Enter yes or no");

        TextField askUrgentInput = new TextField();
        askUrgentInput.setPromptText("Enter yes or no");

        // add button to add this task
        Button buttonAdd = new Button("Add");
        buttonAdd.setOnAction(e -> {
            curList = tool.chooseListFromMapOrCreateList(listInput.getText(), toDoMap);
            Boolean isUrgent = false;
            if (askUrgentInput.getText() == "true") {
                isUrgent = true;
            }
            try {
                tool.handleAddTaskToList(curList, nameInput.getText(), dueDateInput.getText(), isUrgent);
            } catch (TaskAlreadyExistException ex) {
                 errorPage();
            }

            String confirmMsg = String.format("Are you sure you want to add task: %s with due date: %s in list: %s"
                    , nameInput.getText(), dueDateInput.getText(), listInput.getText());
            confirmPage(confirmMsg);
        });
        // back to sceneMain
        Button buttonHome = buttonToMain("Back to home");


        // add everything to layout
        layoutSceneAddTask.getChildren().addAll(askListText, listInput
                , askNameText, nameInput
                , askDueDateText, dueDateInput
                , askUrgentText, askUrgentInput
                , buttonAdd, buttonHome);
        // set sceneAddTAsk
        sceneAddTAsk = new Scene(layoutSceneAddTask, LARGE_SCENE_WIDTH * 2, LARGE_SCENE_HEIGHT * 2);

        window.setScene(sceneMain);
        window.setTitle("TODO List");
        window.show();


        //        printAllOverdueTasksButton = new Button(BUTTON_NAME_PRINTALLOVERDUETASKSBUTTON);
        //        layout.getChildren().add(addTaskButton);
        //        layout.getChildren().add(printAllOverdueTasksButton);

    }

    private void confirmPage(String msg) {
        Scene confirmScene;
        Label confirmLabel = new Label(msg);
        Button buttonToMain = buttonToMain("Yes");
        VBox layout = new VBox(20);
        layout.getChildren().addAll(confirmLabel, buttonToMain);
        layout.setAlignment(Pos.CENTER);

        confirmScene = new Scene(layout, 3 * LARGE_SCENE_WIDTH, SMALL_SCENE_HEIGHT);
        window.setScene(confirmScene);
    }

    private void errorPage() {
        Scene errorScene;
        Label errorLabel = new Label("Input is illegal, do you want to go back to main?");
        Button buttonToMain = buttonToMain("Yes");
        VBox layout = new VBox(20);
        layout.getChildren().addAll(errorLabel, buttonToMain);
        layout.setAlignment(Pos.CENTER);

        errorScene = new Scene(layout, LARGE_SCENE_WIDTH, SMALL_SCENE_HEIGHT);
        window.setScene(errorScene);

    }

    public void displayMessage(String msg) {
        Scene displayScene;
        Label msgLabel = new Label(msg);
        // click ok and back to main
        Button ok = buttonToMain("ok");
        VBox layout = new VBox(20);
        layout.getChildren().addAll(msgLabel, ok);

        displayScene = new Scene(layout, SMALL_SCENE_WIDTH, SMALL_SCENE_HEIGHT);
        window.setScene(displayScene);
    }

    private Button buttonToMain(String name) {
        Button backToMainButton = new Button(name);
        backToMainButton.setOnAction(e -> window.setScene(sceneMain));
        return backToMainButton;
    }

    public void displayListMessage(List<String> msgList) {
        VBox layout = new VBox(20);


        // display all msgs
        for (String msg: msgList) {
            Label msgLabel = new Label(msg);
            layout.getChildren().add(msgLabel);
        }

        // ok button to get back to main
        Button ok = buttonToMain("Back to main");
        layout.getChildren().add(ok);
        layout.setAlignment(Pos.CENTER);


        Scene displayScene = new Scene(layout, LARGE_SCENE_WIDTH, LARGE_SCENE_HEIGHT);
        window.setScene(displayScene);


    }


}
