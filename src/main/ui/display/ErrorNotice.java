//package ui.display;
//
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.VBox;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import model.ToDoMap;
//import ui.FileReaderAndWriter;
//import ui.ToDoAppUsage;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.List;
//
//public class ErrorNotice {
//
//    public static void display(String title, String message, Scene lastScene) {
//
//        Stage window = new Stage();
//
//
//        // block event to other window
//        setUpWindow(title, window);
//        VBox layout = new VBox(10);
//
//
//        Label label = new Label();
//        label.setText(message);
//
//
//
//        // create 1 buttons
//        Button yesButton = setYesButton(window, lastScene);
//
//
//        layout.getChildren().addAll(label, yesButton);
//        layout.setAlignment(Pos.CENTER);
//
//        // Display window and wait for it to be closed before returning
//        Scene scene = new Scene(layout);
//        window.setScene(scene);
//        window.showAndWait();
//
//
//    }
//
//
//    private static Button setYesButton(Stage window, Scene lastScene) {
//        Button yesButton = new Button("ok");
//
//        yesButton.setOnAction(e -> {
//            ToDoAppUsage.stopAndPlayButtonSound();
//            window.close();
//        });
//        return yesButton;
//    }
//
//
//    private static void setUpWindow(String title, Stage window) {
//        window.initModality(Modality.APPLICATION_MODAL);
//
//        window.setTitle(title);
//        window.setMinWidth(250);
//    }
//}
