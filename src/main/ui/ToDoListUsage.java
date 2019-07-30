package ui;

import model.exceptions.TaskAlreadyExistException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import ui.display.ConfirmBox;
import ui.display.PopupAd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.launch;

// extends Application to use display
public class ToDoListUsage extends Application {
    public static final int LARGE_SCENE_WIDTH = 300;
    public static final int LARGE_SCENE_HEIGHT = 250;
    public static final int SMALL_SCENE_WIDTH = 200;
    public static final int SMALL_SCENE_HEIGHT = 80;
    public static final String INDENTATION = "     ";
    public static final String BUTTON_NAME_ADDTASKBUTTON = "New Task";
    public static final String BUTTON_NAME_PRINTALLTASKSBUTTON = "All Tasks";
    public static final String BUTTON_NAME_PRINTALLOVERDUETASKSBUTTON = "Overdue Tasks";
    public static final String CHOOSE_DATE_FORMABUTTONT = "Choose Date Format";
    public static final String BUTTON_NAME_CLOSE_PROGRAM = "Close Program";
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static String dateFormat = "yyyy-MM-dd";


    // object from model
    private static ToDoMap toDoMap;
    private static ToDoList curList;
    private static Tool tool;
    private static FileReaderAndWriter fileReaderAndWriter;


    /// Stage and scenes
    Stage window;
    Scene sceneMain;
    Scene sceneAddTAsk;
    Scene sceneChooseDateFormat;
    // Buttons
    Button addTaskButton;
    Button printAllTasksButton;
    Button printAllOverdueTasksButton;
    Button chooseDateFormateButton;
    Button quitButton;

    public static void main(String[] args) {
        // setups
        toDoMap = new ToDoMap();
        toDoMap.addToDoList("school");
        launch(args);


    }


    // This method is for javaFX
    @Override
    public void start(Stage primaryStage) throws Exception {
        tool = new Tool();

        // let window reference primaryStage
        initializeWindow(primaryStage);

        // choose file scene
        Scene chooseFileScene = chooseFileScene();


        // Button to choose date Format
        chooseDateFormateButton = setUpChooseDateFormatbutton();

        // Button to print all tasks
        printAllTasksButton = setUpPrintAllTaskButton();


        // Button to add task
        addTaskButton = setUpAddTaskButton();

        // Button to print all overdue tasks
        printAllOverdueTasksButton = setUpOverdueButton();


        // Button to quit
        quitButton = setUpQuitButton();

        // Set sceneMain
        sceneMain = setUpMainScene();


        sceneChooseDateFormat = chooseDateFormateScene();


        // scene to add task
        sceneAddTAsk = setUpAddTaskScene();


        window.setScene(chooseFileScene);
        window.setTitle("TODO List");
        window.show();


    }

    private Scene chooseDateFormateScene() {
        // scene to choose date format

        Label choseDateFormatText = new Label("You can choose your default date format here:");

        Button buttonForDate1 = setUpDateFormatChoiceButton("yyyy-MM-dd");
        Button buttonForDate2 = setUpDateFormatChoiceButton("dd-MM-yyyy");
        Button buttonForDate3 = setUpDateFormatChoiceButton("MM-dd-yyyy");

        VBox layoutChooseDateFormat = new VBox(20);
        layoutChooseDateFormat.getChildren().addAll(choseDateFormatText, buttonForDate1,
                buttonForDate2, buttonForDate3);
        sceneChooseDateFormat = new Scene(layoutChooseDateFormat, LARGE_SCENE_WIDTH, LARGE_SCENE_HEIGHT);

        return sceneChooseDateFormat;
    }

    private  Button setUpDateFormatChoiceButton(String dateFormat) {
        Button button = new Button(dateFormat);
        button.setOnAction(e -> {
            String msg = tool.handleFormatOptions(dateFormat);
            displayMessageButtonToMain(msg, "ok");
        });

        return button;
    }

    public Button setUpChooseDateFormatbutton() {
        Button chooseDateFormateButton = new Button(CHOOSE_DATE_FORMABUTTONT);
        chooseDateFormateButton.setOnAction(e ->
                window.setScene(sceneChooseDateFormat));
        return chooseDateFormateButton;
    }

    public Button setUpPrintAllTaskButton() {
        Button printAllTaskButton = new Button(BUTTON_NAME_PRINTALLTASKSBUTTON);
        printAllTaskButton.setOnAction(e -> {
            List<String> tasks = toDoMap.returnAllMapTasks();
            displayListMessageButtonToMain(tasks, "Back to main");
        });
        return printAllTaskButton;
    }

    public Button setUpAddTaskButton() {
        Button addTaskButton = new Button(BUTTON_NAME_ADDTASKBUTTON);
        addTaskButton.setOnAction(e -> {
            window.setScene(sceneAddTAsk);
            PopupAd.display("Advertisement", "Nikdas, 50% cheaper than them");
        });   // switch scene from sceneMain to sceneAddTAsk and pop up ad
        return addTaskButton;

    }

    public Button setUpOverdueButton() {
        Button overDueButton = new Button(BUTTON_NAME_PRINTALLOVERDUETASKSBUTTON);
        overDueButton.setOnAction(e -> {
            List<String> overDueTasks = toDoMap.returnMapAllOverdueTasks();
            displayListMessageButtonToMain(overDueTasks, "Back to main");
        });

        return overDueButton;
    }

    public Button setUpQuitButton() {
        Button quitButton = new Button(BUTTON_NAME_CLOSE_PROGRAM);
        quitButton.setOnAction(e -> closeProgram());
        return quitButton;
    }

    public Scene setUpMainScene() {
        // Main scene components
        // setup initialText message
        Label initialText = new Label("Welcome to your ToDoList");
        VBox layoutScene1 = new VBox(20);   // 20 is the spacing between elements inside the box
        layoutScene1.getChildren().addAll(initialText, chooseDateFormateButton, printAllTasksButton,
                addTaskButton, printAllOverdueTasksButton, quitButton);
        layoutScene1.setAlignment(Pos.CENTER);
        return new Scene(layoutScene1, LARGE_SCENE_WIDTH, 100 + LARGE_SCENE_HEIGHT);
    }

    public Scene setUpAddTaskScene() {
        VBox layoutSceneAddTask = new VBox(20);
        layoutSceneAddTask.setAlignment(Pos.CENTER);


        // which list to add it to
        Label askListText = new Label("What list do you want to add your task? "
                + "\n-------------------Or------------------"
                + "\nenter name of new list you want to create");


        TextField listInput = setUpInputTextFieldAndPromptText("Enter name of the list here");


        // name of task adding
        Label askNameText = new Label("What is the name of your task?");

        TextField nameInput = setUpInputTextFieldAndPromptText("Enter your name here");

        // dueDate of task adding
        Label askDueDateText = new Label("What is the due date of your task? Enter skip if no due date");

        TextField dueDateInput = setUpInputTextFieldAndPromptText("Enter your due date here");

        // urgent or not?
        Label askUrgentText = new Label("Do you want to set it as urgent task? Enter yes or no");

        TextField askUrgentInput = setUpInputTextFieldAndPromptText("Enter yes or no");

        // add button to add this task
        Button buttonAdd = setUpAddButton(listInput, nameInput, dueDateInput, askUrgentInput);


        // back to sceneMain
        Button buttonHome = buttonToMain("Back to home");


        // add everything to layout
        layoutSceneAddTask.getChildren().addAll(askListText, listInput,
                askNameText, nameInput, askDueDateText, dueDateInput,
                askUrgentText, askUrgentInput, buttonAdd, buttonHome);

        // create scene and return it
        return new Scene(layoutSceneAddTask, LARGE_SCENE_WIDTH * 2, LARGE_SCENE_HEIGHT * 2);
    }

    private Button setUpAddButton(TextField listInput, TextField askUrgentInput,
                                  TextField nameInput, TextField dueDateInput) {
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

            String confirmMsg = String.format("Are you sure you want to add task: %s \nwith due date: %s \nin list: %s",
                    nameInput.getText(), dueDateInput.getText(), listInput.getText());
            confirmPage(confirmMsg);
        });
        return buttonAdd;
    }

    private TextField setUpInputTextFieldAndPromptText(String promptText) {
        TextField inputField = new TextField();
        inputField.setPromptText(promptText);
        return inputField;
    }

    private Scene chooseFileScene() {
        Scene chooseFileScene;
        VBox chooseFileLayout = new VBox(20);
        Label displayFileText = new Label("Here is all of your current files you can choose");
        chooseFileLayout.getChildren().add(displayFileText);
        for (String s : tool.historyFiles) {
            Label l = new Label(INDENTATION + s);
            chooseFileLayout.getChildren().add(l);
        }
        Label chooseFileText = new Label("Please Enter the name of the file you want to use");
        TextField chooseFileInput = new TextField();
        chooseFileInput.setPromptText("Name of the file");

        Button finishedChooseFileButton = new Button("ok");

        setFuncitonOfFinishedChooseFilesButton(chooseFileInput, finishedChooseFileButton);


        chooseFileLayout.getChildren().addAll(chooseFileText, chooseFileInput, finishedChooseFileButton);
        chooseFileLayout.setAlignment(Pos.CENTER);

        chooseFileScene = new Scene(chooseFileLayout);
        return chooseFileScene;
    }

    private void setFuncitonOfFinishedChooseFilesButton(TextField chooseFileInput, Button finishedChooseFileButton) {
        finishedChooseFileButton.setOnAction(e -> {
            try {
                String fileName = chooseFileInput.getText();
                fileReaderAndWriter = new FileReaderAndWriter(fileName);
            } catch (IOException exception) {
                System.out.println("\nSomething is wrong with this file");
                System.out.println("Use default inputfile.txt instead");
                try {
                    fileReaderAndWriter = new FileReaderAndWriter("inputfile.txt");
                } catch (IOException ex) {
                    ex.printStackTrace(); // probably not gonna happen
                }

            }

            List<String> historyAsListOfString = loadHistoryIAndReturnHistoryAsListOfString();
            displayListMessageButtonToMain(historyAsListOfString, "ok");
        });
    }

    private void addHistoryFileLabelToChooseFileScene(VBox chooseFileLayout) {
        for (String s : tool.getHistoryFiles()) {
            Label l = new Label(INDENTATION + s);
            chooseFileLayout.getChildren().add(l);
        }
    }

    private void initializeWindow(Stage primaryStage) {
        window = primaryStage;
        window.setMinWidth(250);
        window.setMinHeight(250);
        window.setOnCloseRequest(e -> closeProgram());
    }

    private List<String> loadHistoryIAndReturnHistoryAsListOfString() {
        List<String> history = null;

        history = tryAddHistoryAndReturnLoad();

//        try {
//            history = fileReaderAndWriter.addHistryIntoMapAndReturnLoad(toDoMap);
////            finishedChooseFileButton.setOnAction(e -> displayListMessageButtonToMain(history, "ok"));
//        } catch (IOException e) {
//            System.out.println("Found IOException");
//        } catch (ParseException e) {
//            System.out.println("WARNING: Some of old tasks might not show up");
//        } catch (TaskAlreadyExistException exception) {
//
//            System.out.println(exception.getMessage());
//
//            Scene errorScene;
//            VBox errorSceneLayout = new VBox(20);
//            Label errorMessage = new Label("History files contain error, you might lost all of your history.");
//            Button startOverButton = buttonToMain("start over");
//
//            errorSceneLayout.getChildren().addAll(errorMessage, startOverButton);
//            errorSceneLayout.setAlignment(Pos.CENTER);
//
//            errorScene = new Scene(errorSceneLayout);
//            history = null;
////            finishedChooseFileButton.setOnAction( e ->  window.setScene(errorScene));
//        }

        return history;

    }

    private List<String> tryAddHistoryAndReturnLoad() {
        List<String> history = new ArrayList<>();
        try {
            history = fileReaderAndWriter.addHistryIntoMapAndReturnLoad(toDoMap);
//            finishedChooseFileButton.setOnAction(e -> displayListMessageButtonToMain(history, "ok"));
        } catch (IOException e) {
            System.out.println("Found IOException");
        } catch (ParseException e) {
            System.out.println("WARNING: Some of old tasks might not show up");
        } catch (TaskAlreadyExistException exception) {

            System.out.println(exception.getMessage());

            Scene errorScene = setUpErrorScene();

            history = null;
        }

        return history;

    }

    private Scene setUpErrorScene() {
        VBox errorSceneLayout = new VBox(20);
        Label errorMessage = new Label("History files contain error, you might lost all of your history.");
        Button startOverButton = buttonToMain("start over");

        errorSceneLayout.getChildren().addAll(errorMessage, startOverButton);
        errorSceneLayout.setAlignment(Pos.CENTER);

        return new Scene(errorSceneLayout);
    }

    private void confirmPage(String msg) {
        Scene confirmScene;
        Label confirmLabel = new Label(msg);
        Button buttonToMain = buttonToMain("Yes");
        VBox layout = new VBox(20);
        layout.getChildren().addAll(confirmLabel, buttonToMain);
        layout.setAlignment(Pos.CENTER);

        confirmScene = new Scene(layout);
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


    // EFFECTS: set stage to scene which diplay given msg, ok button to return to main menu
    public void displayMessageButtonToMain(String msg, String buttonName) {
        Scene displayScene;
        Label msgLabel = new Label(msg);
        // click ok and back to main
        Button ok = buttonToMain(buttonName);
        VBox layout = new VBox(20);
        layout.getChildren().addAll(msgLabel, ok);

        displayScene = new Scene(layout, SMALL_SCENE_WIDTH, SMALL_SCENE_HEIGHT);
        window.setScene(displayScene);
    }

    // EFFECTS: set stage to scene which diplay given list of msg, ok button to return to main menu
    private void displayListMessageButtonToMain(List<String> msgList, String buttonName) {
        VBox layout = new VBox(20);


        // display all msgs
        displayListOfString(msgList, layout);

        // ok button to get back to main
        Button ok = buttonToMain(buttonName);
        layout.getChildren().add(ok);
        layout.setAlignment(Pos.CENTER);


        Scene displayScene = new Scene(layout);
        window.setScene(displayScene);

    }

    // EFFECTS: return a button that can be used to return to main
    private Button buttonToMain(String name) {
        Button backToMainButton = new Button(name);
        backToMainButton.setOnAction(e -> window.setScene(sceneMain));
        return backToMainButton;
    }


    private void closeProgram() {
        Boolean confirm = ConfirmBox.display("Confirm", "Are you sure you want to quit?");
        if (fileReaderAndWriter == null) {
            // when they did not even enter fileName
            stopRunningAndCloseWindow();
        } else {
            if (confirm == true) {
                // Load and Save
                try {
                    fileReaderAndWriter.saveAllHistoryInMapToInput(toDoMap);
                    fileReaderAndWriter.copyInputToOutput();
                    printSavedReportAndButtonToCloseOrToMain();
                } catch (IOException e) {
                    System.out.println("WARNING: some of your tasks might not be saved");
                } finally {
                    System.out.println("Thank for using this app, have a good day!");
                }

            } else {
                window.setScene(sceneMain);
            }
        }
    }

    private void stopRunningAndCloseWindow() {
        Tool.isRunning = false;
        window.close();
    }


    public void printSavedReportAndButtonToCloseOrToMain() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("outputfile.txt"));
        displayListMessageButtonToMain(lines, "ok");

        VBox layout = new VBox(20);


        // display all lines in output
        displayListOfString(lines, layout);

        // keepWorking button to get back to main
        Button keppWorkingButton = buttonToMain("keep working");

        // close button to quit program
        Button closeButton = new Button("close");
        closeButton.setOnAction(e -> {
            stopRunningAndCloseWindow();
        });

        layout.getChildren().addAll(keppWorkingButton, closeButton);
        layout.setAlignment(Pos.CENTER);


        Scene displayScene = new Scene(layout);
        window.setScene(displayScene);
    }

    private void displayListOfString(List<String> lines, VBox layout) {
        for (String msg : lines) {
            Label msgLabel = new Label(msg);
            layout.getChildren().add(msgLabel);
        }
    }


}
