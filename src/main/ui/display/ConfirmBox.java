//package ui.display;
//
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.VBox;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import ui.ToDoAppUsage;
//
//public class ConfirmBox {
//
//    private static boolean answer = false;
//
//    public static boolean display(String title, String message) {
//
//        Stage window = new Stage();
//
//
//        // block event to other window
//        setUpWindow(title, window);
//
//        Label label = new Label();
//        label.setText(message);
//
//        // create 2 buttons
//        Button yesButton = setUpYesButton(window);
//
//        Button noButton = setUpNoButton(window);
//
//        VBox layout = new VBox(10);
//        layout.getChildren().addAll(label, yesButton, noButton);
//        layout.setAlignment(Pos.CENTER);
//
//        // Display window and wait for it to be closed before returning
//        Scene scene = new Scene(layout);
//        window.setScene(scene);
//        window.showAndWait();
//
//        return answer;
//
//    }
//
//    private static Button setUpNoButton(Stage window) {
//        Button noButton = new Button("no");
//
//
//        noButton.setOnAction(e -> {
//            ToDoAppUsage.stopAndPlayButtonSound();
//            answer = false;
//            window.close();
//        });
//        return noButton;
//    }
//
//    private static Button setUpYesButton(Stage window) {
//        Button yesButton = new Button("yes");
//        yesButton.setOnAction(e -> {
//            ToDoAppUsage.stopAndPlayButtonSound();
//            answer = true;
//            window.close();
//        });
//        return yesButton;
//    }
//
//    private static void setUpWindow(String title, Stage window) {
//        window.initModality(Modality.APPLICATION_MODAL);
//
//        window.setTitle(title);
//        window.setMinWidth(250);
//    }
//}
