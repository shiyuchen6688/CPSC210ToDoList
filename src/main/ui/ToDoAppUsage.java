package ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import ui.display.CloseConfirm;
import ui.display.ConfirmBox;
import ui.scene.ChooseDateFormatScene;
import ui.scene.MainScene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

// extends Application to use display
public class ToDoAppUsage extends Application {
    public static final String INDENTATION = "     ";
    public static final int LARGE_SCENE_WIDTH = 600;
    public static final int LARGE_SCENE_HEIGHT = 500;
    public static final int SMALL_SCENE_WIDTH = 200;
    public static final int SMALL_SCENE_HEIGHT = 80;

    public static String dateFormat = "yyyy-MM-dd";
    public static int VBOC_SPACING = 20;


    // object from model
    private static ToDoMap toDoMap;
    private static ToDoList curList;
    private static Tool tool;
    private static FileReaderAndWriter fileReaderAndWriter;


    /// Stage and scenes
    static Stage window;
    static MainScene mainScene;
    public static ChooseDateFormatScene chooseDateFormatScene;
    public static Scene sceneAddTask;

    public static void main(String[] args) throws IOException {
        // TODO LAB10: Composite pattern is here
        compositePattern();
        dataFromWeb();


        // TODO LAB10: DID NOT PARSE JSON
//        String apikey = "c94e3798ff3727faefc4963184add64d";
//        String londonweatherquery = "https://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=";
//        String theURL=londonweatherquery+apikey;


        // setups
        toDoMap = new ToDoMap();
        launch(args);


    }

    // TODO: lab10 date from web
    private static void dataFromWeb() throws IOException {
        BufferedReader br = null;

        try {
            String theUrL = "https://www.ugrad.cs.ubc.ca/~cs210/2018w1/welcomemsg.html"; //this can point to any URL
            URL url = new URL(theUrL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            System.out.println(sb);
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }

    private static void compositePattern() {
        GeneralTask outerTest = new RegularTask("The outer task");
        GeneralTask innerTask = new RegularTask("inner task");
        GeneralTask innerTask2 = new RegularTask("inner task2");
        GeneralTask innerInnerTask = new RegularTask("inner inner task");
        innerTask.addElement(new Note("Note 1 for inner test"));
        innerTask.addElement(new Note("Note 2 for inner test"));
        innerTask.addElement(innerInnerTask);
        innerTask2.addElement(new Note("Note 1 for inner test"));
        innerTask2.addElement(new Note("Note 2 for inner test"));
        outerTest.addElement(new Note("Note 1 for outer task"));
        outerTest.addElement(innerTask);
        outerTest.addElement(innerTask2);
        outerTest.addElement(new Note("Note 2 for outer task"));

        outerTest.display("     ");
    }


    // This method is for javaFX
    @Override
    public void start(Stage primaryStage) {
        tool = new Tool();
        // let window reference primaryStage
        initializeWindow(primaryStage);

        // choose file scene
        Scene chooseFileScene = chooseFileScene();

        // Set mainScene
        mainScene = new MainScene(toDoMap, tool, window);
        chooseDateFormatScene = new ChooseDateFormatScene(tool, mainScene);


        // scene to add task
        sceneAddTask = setUpAddTaskScene();


        window.setScene(chooseFileScene);
        window.setTitle("TODO List");
        window.show();


    }

    private void initializeWindow(Stage primaryStage) {
        window = primaryStage;
        window.setMinWidth(250);
        window.setMinHeight(250);
        window.setOnCloseRequest(e -> closeProgramNoBack());
    }


    public Scene setUpAddTaskScene() {
        VBox layoutSceneAddTask = new VBox(VBOC_SPACING);
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


        // back to mainScene
        Button buttonHome = buttonToMain("Back to home");


        // add everything to layout
        layoutSceneAddTask.getChildren().addAll(askListText, listInput,
                askNameText, nameInput, askDueDateText, dueDateInput,
                askUrgentText, askUrgentInput, buttonAdd, buttonHome);

        // create scene and return it
        return new Scene(layoutSceneAddTask, LARGE_SCENE_WIDTH * 2, LARGE_SCENE_HEIGHT * 2);
    }

    private Button setUpAddButton(TextField listInput, TextField nameInput,
                                  TextField dueDateInput, TextField askUrgentInput) {
        Button buttonAdd = new Button("Add");
        buttonAdd.setOnAction(e -> {
            curList = tool.chooseListFromMapOrCreateList(listInput.getText(), toDoMap);
            boolean isUrgent = false;
            if (askUrgentInput.getText() == "true") {
                isUrgent = true;
            }
            if (curList.contains(nameInput.getText())) {
                errorPage();
            } else {
                tool.handleAddTaskToList(curList, nameInput.getText(), dueDateInput.getText(), isUrgent);
                String confirmMsg =
                        String.format("Are you sure you want to add task: %s \nwith due date: %s \nin list: %s",
                                nameInput.getText(), dueDateInput.getText(), listInput.getText());
                confirmPage(confirmMsg);
            }



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
        VBox chooseFileLayout = new VBox(VBOC_SPACING);
        Label displayFileText = new Label("Here is all of your current files you can choose \n "
                + "you can also enter name of new file you want to add and use");
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
                fileReaderAndWriter = new FileReaderAndWriter(fileName, "outputfile.txt");
                ifCreateANewFileAddToToolHistoryFiles(fileName);
            } catch (IOException exception) {
                System.out.println("\nSomething is wrong with this file");
                System.out.println("Use default inputfile.txt instead");
                try {
                    fileReaderAndWriter = new FileReaderAndWriter("inputfile.txt", "outputfile.txt");
                } catch (IOException ex) {
                    ex.printStackTrace(); // probably not gonna happen
                }

            }

            List<String> historyAsListOfString = loadHistoryIAndReturnHistoryAsListOfString();
            displayListMessageButtonToMain(historyAsListOfString, "ok");
        });
    }

    private void ifCreateANewFileAddToToolHistoryFiles(String fileName) {
        if (!tool.historyFiles.contains(fileName)) {
            fileReaderAndWriter.addNewFileNameToFileNames(fileName);
            tool.historyFiles.add(fileName);
        }
    }

    private void addHistoryFileLabelToChooseFileScene(VBox chooseFileLayout) {
        for (String s : tool.getHistoryFiles()) {
            Label l = new Label(INDENTATION + s);
            chooseFileLayout.getChildren().add(l);
        }
    }


    private List<String> loadHistoryIAndReturnHistoryAsListOfString() {
        List<String> history = null;

        history = tryAddHistoryAndReturnLoad();


        return history;

    }

    private List<String> tryAddHistoryAndReturnLoad() {
        List<String> history = new ArrayList<>();
        try {
            history = fileReaderAndWriter.addHistryIntoMapAndReturnLoad(toDoMap);
        } catch (IOException e) {
            System.out.println("Found IOException");
            Scene errorScene = setUpErrorScene();
            window.setScene(errorScene);
            history = null;
        } catch (ParseException e) {
            System.out.println("WARNING: Some of old tasks might not show up");
            Scene errorScene = setUpErrorScene();
            window.setScene(errorScene);

            history = null;
        }
        return history;

    }

    private Scene setUpErrorScene() {
        VBox errorSceneLayout = new VBox(VBOC_SPACING);
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
        VBox layout = new VBox(VBOC_SPACING);
        layout.getChildren().addAll(confirmLabel, buttonToMain);
        layout.setAlignment(Pos.CENTER);

        confirmScene = new Scene(layout);
        window.setScene(confirmScene);
    }

    private void errorPage() {
        Scene errorScene;
        Label errorLabel = new Label("Input is illegal, do you want to go back to main?");
        Button buttonToMain = buttonToMain("Yes");
        VBox layout = new VBox(VBOC_SPACING);
        layout.getChildren().addAll(errorLabel, buttonToMain);
        layout.setAlignment(Pos.CENTER);

        errorScene = new Scene(layout, LARGE_SCENE_WIDTH, SMALL_SCENE_HEIGHT);
        window.setScene(errorScene);

    }


    // EFFECTS: set stage to scene which diplay given msg, ok button to return to main menu
    public static void displayMessageButtonToMain(String msg, String buttonName) {
        Scene displayScene;
        Label msgLabel = new Label(msg);
        // click ok and back to main
        Button ok = buttonToMain(buttonName);
        VBox layout = new VBox(VBOC_SPACING);
        layout.getChildren().addAll(msgLabel, ok);

        displayScene = new Scene(layout, SMALL_SCENE_WIDTH, SMALL_SCENE_HEIGHT);
        window.setScene(displayScene);
    }


    // EFFECTS: set stage to scene which diplay given list of msg, ok button to return to main menu
    private static void displayListMessageButtonToMain(List<String> msgList, String buttonName) {
        VBox layout = new VBox(VBOC_SPACING);


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
    private static Button buttonToMain(String name) {
        Button backToMainButton = new Button(name);
        backToMainButton.setOnAction(e -> window.setScene(mainScene.getScene()));
        return backToMainButton;
    }


    public static void closeProgramNoBack() {
        CloseConfirm.display("Bye", "Thank you for using our app", toDoMap);
    }

    public static void printSavedReportAndButtonToClose() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("outputfile.txt"));
        displayListMessageButtonToMain(lines, "ok");

        VBox layout = new VBox(VBOC_SPACING);

        // display all lines in output
        displayListOfString(lines, layout);


        // close button to quit program
        Button closeButton = new Button("close");
        closeButton.setOnAction(e -> {
            stopRunningAndCloseWindow();
        });

        layout.getChildren().addAll(closeButton);
        layout.setAlignment(Pos.CENTER);


        Scene displayScene = new Scene(layout);
        window.setScene(displayScene);
    }

    public static void closeProgram() {
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
                window.setScene(mainScene.getScene());
            }
        }
    }

    private static void stopRunningAndCloseWindow() {
        Tool.isRunning = false;
        window.close();
    }


    public static void printSavedReportAndButtonToCloseOrToMain() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("outputfile.txt"));
        displayListMessageButtonToMain(lines, "ok");

        VBox layout = new VBox(VBOC_SPACING);

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

    private static void displayListOfString(List<String> lines, VBox layout) {
        for (String msg : lines) {
            Label msgLabel = new Label(msg);
            layout.getChildren().add(msgLabel);
        }
    }


}
