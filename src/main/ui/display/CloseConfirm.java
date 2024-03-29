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
//public class CloseConfirm {
//    public static void display(String title, String message, ToDoMap map) {
//
//        Stage window = new Stage();
//
//
//        // block event to other window
//        setUpWindow(title, window);
//        VBox layout = new VBox(10);
//
//        tryToShowHistoryOrNoFileChoosed(map, layout);
//
//        Label label = new Label();
//        label.setText(message);
//
//
//
//        // create 1 buttons
//        Button yesButton = setYesButton(window);
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
//    private static void tryToShowHistoryOrNoFileChoosed(ToDoMap map, VBox layout) {
//        try {
//            FileReaderAndWriter.saveAllHistoryInMapToInput(map);
//            FileReaderAndWriter.copyInputToOutput();
//            addHistoryToLayout(layout);
//
//        } catch (IOException ioe) {
//            System.out.println("Caught unexpected IOException when closing");
//        } catch (Exception e) {
//            layout.getChildren().add(new Label("Did not choose any file"));
//        }
//    }
//
//    private static Button setYesButton(Stage window) {
//        Button yesButton = new Button("ok");
//
//        yesButton.setOnAction(e -> {
//            ToDoAppUsage.stopAndPlayButtonSound();
//            window.close();
//        });
//        return yesButton;
//    }
//
//    private static void addHistoryToLayout(VBox layout) {
//        try {
//            List<String> history = Files.readAllLines(Paths.get("outputfile.txt"));
//            for (String s: history) {
//                layout.getChildren().add(new Label(s));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void setUpWindow(String title, Stage window) {
//        window.initModality(Modality.APPLICATION_MODAL);
//
//        window.setTitle(title);
//        window.setMinWidth(250);
//    }
//}
//
