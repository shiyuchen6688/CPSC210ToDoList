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
//public class PopupAd {
//    public static void display(String title, String message) {
//        Stage window = new Stage();
//
//        window.initModality(Modality.APPLICATION_MODAL);  // Block other user interaction
//        window.setTitle(title);
//        window.setMinWidth(250);
//
//        // Ad label 1
//        Label label1 = new Label();
//        label1.setText(message);
//
//        // Close button
//        Button closeButton = new Button("I Don't Care");
//        closeButton.setOnAction(e -> {
//            ToDoAppUsage.stopAndPlayButtonSound();
//            window.close();
//        });
//
//        // layout
//        VBox layout = new VBox(10);
//        layout.getChildren().addAll(label1, closeButton);
//        layout.setAlignment(Pos.CENTER);
//
//        // set scene up
//        Scene scene = new Scene(layout);
//        window.setScene(scene);
//
//        window.showAndWait();  // have to close this window first
//
//
//    }
//}
