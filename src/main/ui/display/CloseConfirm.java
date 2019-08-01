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
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.List;
//
//public class CloseConfirm {
//    public static void display(String title, String message, ToDoMap map) {
//
//        Stage window = new Stage();
//
//
//        // block event to other window
//        window.initModality(Modality.APPLICATION_MODAL);
//
//        window.setTitle(title);
//        window.setMinWidth(250);
//        VBox layout = new VBox(10);
//
//        try {
//            FileReaderAndWriter.saveAllHistoryInMapToInput(map);
//            FileReaderAndWriter.copyInputToOutput();
//
//        } catch (IOException ioe) {
//            System.out.println("Caught unexpected IOException when closing");
//        }
//        Label label = new Label();
//        label.setText(message);
//
//        try {
//            List<String> history = Files.readAllLines(Paths.get("outputfile.txt"));
//            for (String s: history) {
//                layout.getChildren().add(new  Label(s));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // create 2 buttons
//        Button yesButton = new Button("ok");
//
//        yesButton.setOnAction(e -> {
//            window.close();
//
//        });
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
//}
//
