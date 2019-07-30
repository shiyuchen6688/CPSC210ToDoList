package ui;

import model.exceptions.TaskAlreadyExistException;
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


    public FileReaderAndWriter(String fileName) throws IOException {
        outputFileWriter = new FileWriter("outputfile.txt", false);
        inputFileWriter = new FileWriter(fileName, true);
        this.chosenFile = fileName;
    }


    // TODO let user decide where to save/addHistryIntoMapAndReturnLoad

    public List<String> addHistryIntoMapAndReturnLoad(ToDoMap map)
            throws IOException, ParseException, TaskAlreadyExistException {
        List<String> msgList = new ArrayList<>();
        msgList.add("\n---------- Here is all the task you added Before ----------");
        List<String> lines = Files.readAllLines((Paths.get(chosenFile)));

        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            String listName = partsOfLine.get(0);
            String taskName = partsOfLine.get(1);
            String dueDate = partsOfLine.get(2);
            if (dueDate.equals("None")) {
                dueDate = null;
            }
            msgList.add("List: " + listName + " " + "| Task: " + taskName + " " + "| Duedate: " + dueDate);
            // get the the list from the map
            ToDoList curList = map.getList(listName);

            // if this list is not already in, create and add it
            curList = ifCurLIstNullCreateNew(map, listName, curList);

            addTaskNoDuplicate(taskName, dueDate, curList);


        }
        msgList.add("All history above has already been saved");
        return msgList;
    }

    private void addTaskNoDuplicate(String taskName, String dueDate, ToDoList curList)
            throws ParseException, TaskAlreadyExistException {
        List<Task> allOriginalTask = curList.getTasks();
        Task newTask = new RegularTask(taskName, dueDate);

        // add this task into this list
        if (!allOriginalTask.contains(newTask)) {
            curList.addTask(newTask);
        } else {
            throw new TaskAlreadyExistException("load trying to add task: "
                    + newTask.getName() + " but it alreadt exist");
        }
    }

    private ToDoList ifCurLIstNullCreateNew(ToDoMap map, String listName, ToDoList curList) {
        if (curList == null) {
            curList = new ToDoList(listName);
            map.addToDoList(curList);
        }
        return curList;
    }

    // TODO !!! save map history to input, multiple list!!!!
    // save history of a map of list
    public void saveAllHistoryInMapToInput(ToDoMap toDoMap) throws IOException {
        Map<String, ToDoList> map = toDoMap.getMap();
        for (String name : map.keySet()) {
            saveAllHistoryInListToInput(name, map.get(name));
        }

        inputFileWriter.close();
    }


    // save history of a single list
    // MODIFIES: inputfile.txt
    // EFFECTS: save all tasks in s single list to inputfile.txt
    public void saveAllHistoryInListToInput(String listName, ToDoList toDoList) throws IOException {
        PrintWriter writer = new PrintWriter(chosenFile);
        writer.print("");
        writer.close();

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

            // stringToAdd is the actual line that get added
            String stringToAdd = listName + "  " + name + "  " + date + "\n";
            inputFileWriter.write(stringToAdd);
        }

    }

    // TODO delete this later
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


    // helper to split words in addHistryIntoMapAndReturnLoad and save. File download from CPSC-210 EDX.
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split("  ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    // return this inputFileWriter
    public FileWriter getInputFileWriter() {
        return this.inputFileWriter;
    }


}