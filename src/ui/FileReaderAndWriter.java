package ui;

import model.Task;
import model.ToDoList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReaderAndWriter {

    public static final String MESSAGE_END_OUTPUT = "Goodbye, your tasks have been saved";
    public final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    private FileWriter outputFileWriter;
    private FileWriter inputFileWriter;
    private ToDoList toDoList;

    public FileReaderAndWriter(ToDoList toDoList) throws IOException {
        outputFileWriter = new FileWriter("outputfile.txt", false);
        inputFileWriter = new FileWriter("inputfile.txt", true);
        this.toDoList = toDoList;
    }


    public void load() throws IOException, ParseException {
        System.out.println("---------- Here is all the task you added Before ----------");
        List<String> lines = Files.readAllLines((Paths.get("inputfile.txt")));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            String taskName = partsOfLine.get(0);
            String dueDate = partsOfLine.get(1);
            if (dueDate.equals("None")) {
                dueDate = null;
            }
            toDoList.addTask(taskName, dueDate);

        }
    }
//    // EFFECTS: print all history tasks
//    public void printHistory() throws IOException {
//        List<String> linesOutput = Files.readAllLines(Paths.get("inputfile.txt"));
//        for (String line : linesOutput) {
//            ArrayList<String> partsOfLine = splitOnSpace(line);
//            System.out.print("Task: " + partsOfLine.get(0) + "    ");
//            System.out.println("DueDate: " + partsOfLine.get(1));
//        }
//    }

    // MODIFIES: inputfile.txt
    // EFFECTS: save all tasks to inputfile.txt
    public void saveAllHistoryToInput() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("inputfile.txt"));
        List<Task> tasks = toDoList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String name = t.getTaskName();
            String date = "";
            if (t.getDueDate() != null) {
                date = sdf.format(t.getDueDate());
            } else {
                date = "None";
            }

            String toAdd = name + "  " + date;
            if (!(lines.contains(toAdd))) {
                inputFileWriter.write(toAdd + "\n");
            }
        }
        inputFileWriter.close();
    }

    // MODIFIES: outputfile.txt
    // EFFECTS: add things in inputfile.txt to outputfile.txt
    public void copyInputToOutput() throws IOException {
        List<String> linesOutput = Files.readAllLines(Paths.get("inputfile.txt"));
        linesOutput.add(MESSAGE_END_OUTPUT);
        for (int i = 0; i < linesOutput.size(); i++) {
            String line = linesOutput.get(i);
            if (line.equals(MESSAGE_END_OUTPUT)) {
                System.out.println(MESSAGE_END_OUTPUT);
                outputFileWriter.write(line + "\n");
            } else {
                ArrayList<String> partsOfLine = splitOnSpace(line);
                System.out.print("Task: " + partsOfLine.get(0) + " ");
                System.out.println("DueDate: " + partsOfLine.get(1));
                outputFileWriter.write(line + "\n");
            }
        }
        outputFileWriter.close();

    }

    // helper to split words in load and save. File download from CPSC-210 EDX.
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split("  ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    // return this inputFileWriter
    public FileWriter getInputFileWriter() {
        return this.inputFileWriter;
    }
}
