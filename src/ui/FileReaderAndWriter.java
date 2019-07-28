package ui;

import exceptions.TaskAlreadyExistException;
import model.RegularTask;
import model.Task;
import model.ToDoList;
import model.ToDoMap;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FileReaderAndWriter {

    public static final String MESSAGE_END_OUTPUT = "Goodbye, your tasks have been saved";



    private FileWriter outputFileWriter;
    private FileWriter inputFileWriter;
    private String chosenFile;

//    public FileReaderAndWriter(ToDoList toDoList) throws IOException {
//        outputFileWriter = new FileWriter("outputfile.txt", false);
//        inputFileWriter = new FileWriter("inputfile.txt", true);
//        this.toDoList = toDoList;
//    }

    public FileReaderAndWriter(String fileName) throws IOException {
        outputFileWriter = new FileWriter("outputfile.txt", false);
        inputFileWriter = new FileWriter(fileName, true);
        this.chosenFile = fileName;
    }


    // TODO let user decide where to save/load

    public void load(ToDoMap map) throws IOException, ParseException {
        System.out.println("\n---------- Here is all the task you added Before ----------");
        List<String> lines = Files.readAllLines((Paths.get(chosenFile)));
        for (String line : lines) {
            // separate the line into 3 parts
            ArrayList<String> partsOfLine = splitOnSpace(line);

            // assign each part of string
            String listName = partsOfLine.get(0);
            String taskName = partsOfLine.get(1);
            String dueDate = partsOfLine.get(2);
            if (dueDate.equals("None")) {
                dueDate = null;
            }

            // first two is print, the third one is line
            System.out.print("List: " + listName + " ");
            System.out.print("| Task: " + taskName + " ");
            System.out.println("| Duedate: " + dueDate);

            // get the the list from the map
            ToDoList curList = map.getList(listName);

            // if this list is not already in, create and add it
            if (curList == null) {
                curList = new ToDoList(listName);
                map.addToDoList(curList);
            }

            List<Task> allOriginalTask = curList.getTasks();
            Task newTask = new RegularTask(taskName,dueDate);

            // add this task into this list

                curList.addTask(newTask);


        }
    }

    // TODO !!! save map history to input, multiple list!!!!
    // save history of a map of list
    public void saveAllHistoryInMapToInput(ToDoMap toDoMap) throws IOException {
        Map<String, ToDoList> map = toDoMap.getMap();
        for(String name: map.keySet()) {
            saveAllHistoryInListToInput(name, map.get(name));
        }

        inputFileWriter.close();
    }

    // save history of a single list
    // MODIFIES: inputfile.txt
    // EFFECTS: save all tasks in s single list to inputfile.txt
    public void saveAllHistoryInListToInput(String listName, ToDoList toDoList) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(chosenFile));

        List<Task> tasks = toDoList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String name = t.getName();

            String date = "";
            if (t.getDueDate() != null) {
                date = ToDoListUsage.sdf.format(t.getDueDate());
            } else {
                date = "None";
            }

            // toAdd is the actual line that get added
            String toAdd = listName + "  " + name + "  " + date;
            if (!(lines.contains(toAdd))) {
                inputFileWriter.write(toAdd + "\n");
            }
        }

    }

    // MODIFIES: outputfile.txt
    // EFFECTS: add things in inputfile.txt to outputfile.txt
    public void copyInputToOutput() throws IOException {
        List<String> linesOutput = Files.readAllLines(Paths.get(chosenFile));
        linesOutput.add(MESSAGE_END_OUTPUT);
        for (int i = 0; i < linesOutput.size(); i++) {
            String line = linesOutput.get(i);
            if (line.equals(MESSAGE_END_OUTPUT)) {
                System.out.println(MESSAGE_END_OUTPUT);
                outputFileWriter.write(line + "\n");
            } else {
                ArrayList<String> partsOfLine = splitOnSpace(line);

                // first two is print, the third one is line
                System.out.print("List: " + partsOfLine.get(0) + " ");
                System.out.print("Task: " + partsOfLine.get(1) + " ");
                System.out.println("DueDate: " + partsOfLine.get(2));
                outputFileWriter.write(line + "\n");
            }
        }
        outputFileWriter.close();

    }

    // helper to split words in load and save. File download from CPSC-210 EDX.
    // TODO return List instead of ArrayList
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split("  ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    // return this inputFileWriter
    public FileWriter getInputFileWriter() {
        return this.inputFileWriter;
    }


}
