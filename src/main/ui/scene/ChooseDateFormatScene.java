//package ui.scene;
//
//
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.VBox;
//import ui.ToDoAppUsage;
//import ui.Tool;
//
//import java.util.Observable;
//
//public class ChooseDateFormatScene extends Observable {
//    private static Scene chooseScene;
//    private Tool tool;
//
//
//    public ChooseDateFormatScene(Tool tool, MainScene observer) {
//        // scene to choose date format
//        this.tool = tool;
//        Label choseDateFormatText = new Label("You can choose your default date format here:");
//
//        Button buttonForDate1 = setUpDateFormatChoiceButton("yyyy-MM-dd");
//        Button buttonForDate2 = setUpDateFormatChoiceButton("dd-MM-yyyy");
//        Button buttonForDate3 = setUpDateFormatChoiceButton("MM-dd-yyyy");
//
//        VBox layoutChooseDateFormat = new VBox(ToDoAppUsage.VBOC_SPACING);
//        layoutChooseDateFormat.getChildren().addAll(choseDateFormatText, buttonForDate1,
//                buttonForDate2, buttonForDate3);
//        chooseScene = new Scene(layoutChooseDateFormat);
//        addObserver(observer);
//
//    }
//
//
//    public Scene getChooseScene() {
//        return chooseScene;
//    }
//
//
//    private Button setUpDateFormatChoiceButton(String dateFormat) {
//        Button button = new Button(dateFormat);
//        button.setOnAction(e -> {
//            ToDoAppUsage.stopAndPlayButtonSound();
//            setChanged();
//            notifyObservers(dateFormat);
//            String msg = tool.handleFormatOptions(dateFormat);
//            ToDoAppUsage.displayMessageButtonToMain(msg, "ok");
//        });
//
//        return button;
//    }
//
//
//}
