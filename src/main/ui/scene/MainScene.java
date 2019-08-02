//package ui.scene;
//
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import model.ToDoMap;
//import ui.ToDoAppUsage;
//import ui.Tool;
//import ui.display.PopupAd;
//
//import java.util.List;
//import java.util.Observable;
//import java.util.Observer;
//
//public class MainScene implements Observer {
//
//    public static final String BUTTON_NAME_ADDTASKBUTTON = "New Task";
//    public static final String BUTTON_NAME_PRINTALLTASKSBUTTON = "All Tasks";
//    public static final String BUTTON_NAME_PRINTALLOVERDUETASKSBUTTON = "Overdue Tasks";
//    public static final String CHOOSE_DATE_FORMABUTTONT = "Choose Date Format";
//    public static final String BUTTON_NAME_CLOSE_PROGRAM = "Close Program";
//
//    private static Scene sceneMain;
//    private static Label initialText;
//    private static Label dateFormatText;
//    private static VBox layout;
//    private static Button dateFormatChoiceButton;
//    private static Button printAllTaskButton;
//    private static Button addTasksButton;
//    private static Button overDueTasksButton;
//    private static Button quitButton;
//    private static ToDoMap toDoMap;
//    private static Tool tool;
//    private static Stage window;
//
//
//    public MainScene(ToDoMap toDoMap, Tool tool, Stage window) {
//        this.dateFormatText = new Label("Current date format is: " + ToDoAppUsage.dateFormat);
//        this.toDoMap = toDoMap;
//        this.tool = tool;
//        this.window = window;
//        initialText = new Label("Welcome to your ToDoList");
//        this.layout = new VBox(ToDoAppUsage.VBOC_SPACING);
//        initializeButtons();
//        setLayout();
//        this.sceneMain = new Scene(layout, ToDoAppUsage.LARGE_SCENE_WIDTH, ToDoAppUsage.LARGE_SCENE_HEIGHT * 2);
//    }
//
//
//    private static void setLayout() {
//        layout.getChildren().addAll(initialText, dateFormatText, dateFormatChoiceButton, printAllTaskButton,
//                addTasksButton, overDueTasksButton, quitButton);
//        layout.setAlignment(Pos.CENTER);
//    }
//
//    private void initializeButtons() {
//        dateFormatChoiceButton = setUpChooseDateFormatbutton();
//        addTasksButton = setUpAddTaskButton();
//        printAllTaskButton = setUpPrintAllTaskButton();
//        overDueTasksButton = setUpOverdueButton();
//        quitButton = setUpQuitButton();
//
//    }
//
//    public Scene getScene() {
//        return sceneMain;
//    }
//
//
//    public Button setUpChooseDateFormatbutton() {
//        Button chooseDateFormateButton = new Button(CHOOSE_DATE_FORMABUTTONT);
//        chooseDateFormateButton.setOnAction(e ->
//                window.setScene(ToDoAppUsage.chooseDateFormatScene.getChooseScene()));
//        return chooseDateFormateButton;
//    }
//
//    public Button setUpPrintAllTaskButton() {
//        Button printAllTaskButton = new Button(BUTTON_NAME_PRINTALLTASKSBUTTON);
//        printAllTaskButton.setOnAction(e -> {
//            List<String> tasks = toDoMap.returnAllMapTasks();
//            displayListMessageButtonToMain(tasks, "Back to main");
//        });
//        return printAllTaskButton;
//    }
//
//    public Button setUpAddTaskButton() {
//        Button addTaskButton = new Button(BUTTON_NAME_ADDTASKBUTTON);
//        addTaskButton.setOnAction(e -> {
//            window.setScene(ToDoAppUsage.sceneAddTask);
//            PopupAd.display("Advertisement", "Nikdas, 50% cheaper than them");
//        });   // switch scene from sceneMain to sceneAddTAsk and pop up ad
//        return addTaskButton;
//
//    }
//
//    public Button setUpOverdueButton() {
//        Button overDueButton = new Button(BUTTON_NAME_PRINTALLOVERDUETASKSBUTTON);
//        overDueButton.setOnAction(e -> {
//            List<String> overDueTasks = toDoMap.returnMapAllOverdueTasks();
//            displayListMessageButtonToMain(overDueTasks, "Back to main");
//        });
//
//        return overDueButton;
//    }
//
//    public Button setUpQuitButton() {
//        Button quitButton = new Button(BUTTON_NAME_CLOSE_PROGRAM);
//        quitButton.setOnAction(e -> ToDoAppUsage.closeProgram());
//        return quitButton;
//    }
//
//
////    public Scene setUpMainScene() {
////        // Main scene components
////        // setup initialText message
////        layout.getChildren().addAll(initialText, dateFormatChoiceButton, printAllTaskButton,
////                addTasksButton, overDueTasksButton, quitButton);
////        layout.setAlignment(Pos.CENTER);
////        return new Scene(layout, ToDoAppUsage.LARGE_SCENE_WIDTH, 100 + ToDoAppUsage.LARGE_SCENE_HEIGHT);
////    }
//
//    // EFFECTS: set stage to scene which diplay given list of msg, ok button to return to main menu
//    private void displayListMessageButtonToMain(List<String> msgList, String buttonName) {
//        VBox layout = new VBox(ToDoAppUsage.VBOC_SPACING);
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
//    private Button buttonToMain(String name) {
//        Button backToMainButton = new Button(name);
//        backToMainButton.setOnAction(e -> window.setScene(sceneMain));
//        return backToMainButton;
//    }
//
//    private void displayListOfString(List<String> lines, VBox layout) {
//        for (String msg : lines) {
//            Label msgLabel = new Label(msg);
//            layout.getChildren().add(msgLabel);
//        }
//    }
//
//
//    /**
//     * This method is called whenever the observed object is changed. An
//     * application calls an <tt>Observable</tt> object's
//     * <code>notifyObservers</code> method to have all the object's
//     * observers notified of the change.
//     *
//     * @param o   the observable object.
//     * @param arg an argument passed to the <code>notifyObservers</code>
//     */
//    @Override
//    public void update(Observable o, Object arg) {
//        this.dateFormatText = new Label("Current date format is: " + arg);
//        this.layout = new VBox(ToDoAppUsage.VBOC_SPACING);
//        setLayout();
//        this.sceneMain = new Scene(layout, ToDoAppUsage.LARGE_SCENE_WIDTH, ToDoAppUsage.LARGE_SCENE_HEIGHT * 2);
//    }
//
//}
