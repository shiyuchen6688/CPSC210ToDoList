//package ui;
//
//import javafx.application.Application;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import model.ToDoList;
//import model.ToDoMap;
//import model.exceptions.TaskAlreadyExistException;
//import ui.display.CloseConfirm;
//import ui.display.ConfirmBox;
//import ui.scene.MainScene;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//// extends Application to use display
//public class ToDoAppUsage extends Application {
//    public static final String INDENTATION = "     ";
//    public static final int LARGE_SCENE_WIDTH = 300;
//    public static final int LARGE_SCENE_HEIGHT = 250;
//    public static final int SMALL_SCENE_WIDTH = 200;
//    public static final int SMALL_SCENE_HEIGHT = 80;
//
//    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    public static String dateFormat = "yyyy-MM-dd";
//    // TODO: LAB 9 VBox spacing - Create a constant for it
//    public static int VBOC_SPACING = 20;
//
//
//    // object from model
//    private static ToDoMap toDoMap;
//    private static ToDoList curList;
//    private static Tool tool;
//    private static FileReaderAndWriter fileReaderAndWriter;
//
//
//    /// Stage and scenes
//    static Stage window;
//    static MainScene sceneMain;
//    Scene sceneAddTAsk;
//    public static Scene sceneChooseDateFormat;
//    public static Scene sceneAddTask;
//
//    // TODO : LAB9 LOW COHESION, todo app usage class display a lot of different scenes
//    public static void main(String[] args) {
//        // setups
//        toDoMap = new ToDoMap();
//        toDoMap.addToDoList("school");
//        launch(args);
//
//
//    }
//
//
//    // This method is for javaFX
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        tool = new Tool();
//
//        // TODO LAB9 extract method create a lot of helper
//        // let window reference primaryStage
//        initializeWindow(primaryStage);
//
//        // choose file scene
//        Scene chooseFileScene = chooseFileScene();
//
//        // Set sceneMain
//        sceneMain = new MainScene(toDoMap, tool, window);
//
//
//        sceneChooseDateFormat = chooseDateFormateScene();
//
//
//        // scene to add task
//        sceneAddTask = setUpAddTaskScene();
//
//
//        window.setScene(chooseFileScene);
//        window.setTitle("TODO List");
//        window.show();
//
//
//    }
//
//    private void initializeWindow(Stage primaryStage) {
//        window = primaryStage;
//        window.setMinWidth(250);
//        window.setMinHeight(250);
//        window.setOnCloseRequest(e -> closeProgramNoBack());
//    }
//
//    private Scene chooseDateFormateScene() {
//        // scene to choose date format
//
//        Label choseDateFormatText = new Label("You can choose your default date format here:");
//
//        // TODO LAB9 REDUCED DUPLICATION
//        Button buttonForDate1 = setUpDateFormatChoiceButton("yyyy-MM-dd");
//        Button buttonForDate2 = setUpDateFormatChoiceButton("dd-MM-yyyy");
//        Button buttonForDate3 = setUpDateFormatChoiceButton("MM-dd-yyyy");
//
//        VBox layoutChooseDateFormat = new VBox(VBOC_SPACING);
//        layoutChooseDateFormat.getChildren().addAll(choseDateFormatText, buttonForDate1,
//                buttonForDate2, buttonForDate3);
//        sceneChooseDateFormat = new Scene(layoutChooseDateFormat, LARGE_SCENE_WIDTH, LARGE_SCENE_HEIGHT);
//
//        return sceneChooseDateFormat;
//    }
//
//    private Button setUpDateFormatChoiceButton(String dateFormat) {
//        Button button = new Button(dateFormat);
//        button.setOnAction(e -> {
//            String msg = tool.handleFormatOptions(dateFormat);
//            ToDoAppUsage.displayMessageButtonToMain(msg, "ok");
//        });
//
//        return button;
//    }
//
//    public Scene setUpAddTaskScene() {
//        VBox layoutSceneAddTask = new VBox(VBOC_SPACING);
//        layoutSceneAddTask.setAlignment(Pos.CENTER);
//
//
//        // which list to add it to
//        Label askListText = new Label("What list do you want to add your task? "
//                + "\n-------------------Or------------------"
//                + "\nenter name of new list you want to create");
//
//
//        TextField listInput = setUpInputTextFieldAndPromptText("Enter name of the list here");
//
//
//        // name of task adding
//        Label askNameText = new Label("What is the name of your task?");
//
//        TextField nameInput = setUpInputTextFieldAndPromptText("Enter your name here");
//
//        // dueDate of task adding
//        Label askDueDateText = new Label("What is the due date of your task? Enter skip if no due date");
//
//        TextField dueDateInput = setUpInputTextFieldAndPromptText("Enter your due date here");
//
//        // urgent or not?
//        Label askUrgentText = new Label("Do you want to set it as urgent task? Enter yes or no");
//
//        TextField askUrgentInput = setUpInputTextFieldAndPromptText("Enter yes or no");
//
//        // add button to add this task
//        Button buttonAdd = setUpAddButton(listInput, nameInput, dueDateInput, askUrgentInput);
//
//
//        // back to sceneMain
//        Button buttonHome = buttonToMain("Back to home");
//
//
//        // add everything to layout
//        layoutSceneAddTask.getChildren().addAll(askListText, listInput,
//                askNameText, nameInput, askDueDateText, dueDateInput,
//                askUrgentText, askUrgentInput, buttonAdd, buttonHome);
//
//        // create scene and return it
//        return new Scene(layoutSceneAddTask, LARGE_SCENE_WIDTH * 2, LARGE_SCENE_HEIGHT * 2);
//    }
//
//    private Button setUpAddButton(TextField listInput, TextField nameInput,
//                                  TextField dueDateInput, TextField askUrgentInput) {
//        Button buttonAdd = new Button("Add");
//        buttonAdd.setOnAction(e -> {
//            curList = tool.chooseListFromMapOrCreateList(listInput.getText(), toDoMap);
//            Boolean isUrgent = false;
//            if (askUrgentInput.getText() == "true") {
//                isUrgent = true;
//            }
//            try {
//                tool.handleAddTaskToList(curList, nameInput.getText(), dueDateInput.getText(), isUrgent);
//            } catch (TaskAlreadyExistException ex) {
//                errorPage();
//            }
//
//            String confirmMsg =
//                    String.format("Are you sure you want to add task: %s \nwith due date: %s \nin list: %s",
//                    nameInput.getText(), dueDateInput.getText(), listInput.getText());
//            confirmPage(confirmMsg);
//        });
//        return buttonAdd;
//    }
//
//    private TextField setUpInputTextFieldAndPromptText(String promptText) {
//        TextField inputField = new TextField();
//        inputField.setPromptText(promptText);
//        return inputField;
//    }
//
//    private Scene chooseFileScene() {
//        Scene chooseFileScene;
//        VBox chooseFileLayout = new VBox(VBOC_SPACING);
//        Label displayFileText = new Label("Here is all of your current files you can choose");
//        chooseFileLayout.getChildren().add(displayFileText);
//        for (String s : tool.historyFiles) {
//            Label l = new Label(INDENTATION + s);
//            chooseFileLayout.getChildren().add(l);
//        }
//        Label chooseFileText = new Label("Please Enter the name of the file you want to use");
//        TextField chooseFileInput = new TextField();
//        chooseFileInput.setPromptText("Name of the file");
//
//        Button finishedChooseFileButton = new Button("ok");
//
//        setFuncitonOfFinishedChooseFilesButton(chooseFileInput, finishedChooseFileButton);
//
//
//        chooseFileLayout.getChildren().addAll(chooseFileText, chooseFileInput, finishedChooseFileButton);
//        chooseFileLayout.setAlignment(Pos.CENTER);
//
//        chooseFileScene = new Scene(chooseFileLayout);
//        return chooseFileScene;
//    }
//
//    private void setFuncitonOfFinishedChooseFilesButton(TextField chooseFileInput, Button finishedChooseFileButton) {
//        finishedChooseFileButton.setOnAction(e -> {
//            try {
//                String fileName = chooseFileInput.getText();
//                fileReaderAndWriter = new FileReaderAndWriter(fileName, "outputfile.txt");
//            } catch (IOException exception) {
//                System.out.println("\nSomething is wrong with this file");
//                System.out.println("Use default inputfile.txt instead");
//                try {
//                    fileReaderAndWriter = new FileReaderAndWriter("inputfile.txt", "outputfile.txt");
//                } catch (IOException ex) {
//                    ex.printStackTrace(); // probably not gonna happen
//                }
//
//            }
//
//            List<String> historyAsListOfString = loadHistoryIAndReturnHistoryAsListOfString();
//            displayListMessageButtonToMain(historyAsListOfString, "ok");
//        });
//    }
//
//    private void addHistoryFileLabelToChooseFileScene(VBox chooseFileLayout) {
//        for (String s : tool.getHistoryFiles()) {
//            Label l = new Label(INDENTATION + s);
//            chooseFileLayout.getChildren().add(l);
//        }
//    }
//
//
//    private List<String> loadHistoryIAndReturnHistoryAsListOfString() {
//        List<String> history = null;
//
//        history = tryAddHistoryAndReturnLoad();
//
//
//        return history;
//
//    }
//
//    private List<String> tryAddHistoryAndReturnLoad() {
//        List<String> history = new ArrayList<>();
//        try {
//            history = fileReaderAndWriter.addHistryIntoMapAndReturnLoad(toDoMap);
//        } catch (IOException e) {
//            System.out.println("Found IOException");
//            Scene errorScene = setUpErrorScene();
//            window.setScene(errorScene);
//            history = null;
//        } catch (ParseException e) {
//            System.out.println("WARNING: Some of old tasks might not show up");
//            Scene errorScene = setUpErrorScene();
//            window.setScene(errorScene);
//
//            history = null;
//        }
//        return history;
//
//    }
//
//    private Scene setUpErrorScene() {
//        VBox errorSceneLayout = new VBox(VBOC_SPACING);
//        Label errorMessage = new Label("History files contain error, you might lost all of your history.");
//        Button startOverButton = buttonToMain("start over");
//
//        errorSceneLayout.getChildren().addAll(errorMessage, startOverButton);
//        errorSceneLayout.setAlignment(Pos.CENTER);
//
//        return new Scene(errorSceneLayout);
//    }
//
//    private void confirmPage(String msg) {
//        Scene confirmScene;
//        Label confirmLabel = new Label(msg);
//        Button buttonToMain = buttonToMain("Yes");
//        VBox layout = new VBox(VBOC_SPACING);
//        layout.getChildren().addAll(confirmLabel, buttonToMain);
//        layout.setAlignment(Pos.CENTER);
//
//        confirmScene = new Scene(layout);
//        window.setScene(confirmScene);
//    }
//
//    private void errorPage() {
//        Scene errorScene;
//        Label errorLabel = new Label("Input is illegal, do you want to go back to main?");
//        Button buttonToMain = buttonToMain("Yes");
//        VBox layout = new VBox(VBOC_SPACING);
//        layout.getChildren().addAll(errorLabel, buttonToMain);
//        layout.setAlignment(Pos.CENTER);
//
//        errorScene = new Scene(layout, LARGE_SCENE_WIDTH, SMALL_SCENE_HEIGHT);
//        window.setScene(errorScene);
//
//    }
//
//
//    // EFFECTS: set stage to scene which diplay given msg, ok button to return to main menu
//    public static void displayMessageButtonToMain(String msg, String buttonName) {
//        Scene displayScene;
//        Label msgLabel = new Label(msg);
//        // click ok and back to main
//        Button ok = buttonToMain(buttonName);
//        VBox layout = new VBox(VBOC_SPACING);
//        layout.getChildren().addAll(msgLabel, ok);
//
//        displayScene = new Scene(layout, SMALL_SCENE_WIDTH, SMALL_SCENE_HEIGHT);
//        window.setScene(displayScene);
//    }
//
//    // EFFECTS: set stage to scene which diplay given list of msg, ok button to return to main menu
//    private static void displayListMessageButtonToMain(List<String> msgList, String buttonName) {
//        VBox layout = new VBox(VBOC_SPACING);
//
//
//        // display all msgs
//        displayListOfString(msgList, layout);
//
//        // ok button to get back to main
//        Button ok = buttonToMain(buttonName);
//        layout.getChildren().add(ok);
//        layout.setAlignment(Pos.CENTER);
//
//
//        Scene displayScene = new Scene(layout);
//        window.setScene(displayScene);
//
//    }
//
//    // EFFECTS: return a button that can be used to return to main
//    private static Button buttonToMain(String name) {
//        Button backToMainButton = new Button(name);
//        backToMainButton.setOnAction(e -> window.setScene(sceneMain.getScene()));
//        return backToMainButton;
//    }
//
//
//    public static void closeProgramNoBack() {
//        CloseConfirm.display("Bye", "Thank you for using our app", toDoMap);
//    }
//
//    public static void printSavedReportAndButtonToClose() throws IOException {
//        List<String> lines = Files.readAllLines(Paths.get("outputfile.txt"));
//        displayListMessageButtonToMain(lines, "ok");
//
//        VBox layout = new VBox(VBOC_SPACING);
//
//        // display all lines in output
//        displayListOfString(lines, layout);
//
//
//        // close button to quit program
//        Button closeButton = new Button("close");
//        closeButton.setOnAction(e -> {
//            stopRunningAndCloseWindow();
//        });
//
//        layout.getChildren().addAll(closeButton);
//        layout.setAlignment(Pos.CENTER);
//
//
//        Scene displayScene = new Scene(layout);
//        window.setScene(displayScene);
//    }
//
//    public static void closeProgram() {
//        Boolean confirm = ConfirmBox.display("Confirm", "Are you sure you want to quit?");
//        if (fileReaderAndWriter == null) {
//            // when they did not even enter fileName
//            stopRunningAndCloseWindow();
//        } else {
//            if (confirm == true) {
//                // Load and Save
//                try {
//                    fileReaderAndWriter.saveAllHistoryInMapToInput(toDoMap);
//                    fileReaderAndWriter.copyInputToOutput();
//                    printSavedReportAndButtonToCloseOrToMain();
//                } catch (IOException e) {
//                    System.out.println("WARNING: some of your tasks might not be saved");
//                } finally {
//                    System.out.println("Thank for using this app, have a good day!");
//                }
//
//            } else {
//                window.setScene(sceneMain.getScene());
//            }
//        }
//    }
//
//    private static void stopRunningAndCloseWindow() {
//        Tool.isRunning = false;
//        window.close();
//    }
//
//
//    public static void printSavedReportAndButtonToCloseOrToMain() throws IOException {
//        List<String> lines = Files.readAllLines(Paths.get("outputfile.txt"));
//        displayListMessageButtonToMain(lines, "ok");
//
//        VBox layout = new VBox(VBOC_SPACING);
//
//        // display all lines in output
//        displayListOfString(lines, layout);
//
//        // keepWorking button to get back to main
//        Button keppWorkingButton = buttonToMain("keep working");
//
//        // close button to quit program
//        Button closeButton = new Button("close");
//        closeButton.setOnAction(e -> {
//            stopRunningAndCloseWindow();
//        });
//
//        layout.getChildren().addAll(keppWorkingButton, closeButton);
//        layout.setAlignment(Pos.CENTER);
//
//
//        Scene displayScene = new Scene(layout);
//        window.setScene(displayScene);
//    }
//
//    // TODO LAB9 REDUCED DUPLICATION
//    private static void displayListOfString(List<String> lines, VBox layout) {
//        for (String msg : lines) {
//            Label msgLabel = new Label(msg);
//            layout.getChildren().add(msgLabel);
//        }
//    }
//
//
//}
