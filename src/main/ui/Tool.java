package ui;

import model.*;
import model.exceptions.TaskNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ui.ToDoAppUsage.dateFormat;

public class Tool {
    private static final String CHANGE_NAME_COMMAND = "1";
    private static final String CHANGE_DUEDATE_COMMAND = "2";
    private static final String DELETE_COMMAND = "3";


    private static Scanner input;
    public static boolean isRunning;
    public List<String> historyFiles;


    public Tool() {
        input = new Scanner(System.in);
        isRunning = true;
        this.historyFiles = new ArrayList<>();
        try {
            List<String> allFiles = Files.readAllLines(Paths.get("filenames.txt"));
            historyFiles.addAll(allFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public List<String> getHistoryFiles() {
        return historyFiles;
    }



    public String handleFormatOptions(String formatChoice) {
        switch (formatChoice) {
            case "yyyy-MM-dd":
                return handleOneFormatOption("yyyy-MM-dd");
            case "dd-MM-yyyy":
                return handleOneFormatOption("dd-MM-yyyy");
            case "MM-dd-yyyy":
                return handleOneFormatOption("MM-dd-yyyy");
            default:
                dateFormat = "yyyy-MM-dd";
                GeneralTask.sdf = new SimpleDateFormat(dateFormat);
                return "option is incorrect, set to default yyyy-MM-dd";
        }
    }


    private String handleOneFormatOption(String format) {
        dateFormat = format;
        GeneralTask.sdf = new SimpleDateFormat(format);
//        mainScene.updateDateFormatLabel(format);
        return "choosed " + format;
    }


    public ToDoList chooseListFromMapOrCreateList(String s, ToDoMap map) {
        ToDoList newList = map.getList(s);
        if (newList == null) {
            newList = new ToDoList(s);
            map.addToDoList(newList);
        }
        return newList;
    }



    public void handleAddTaskToList(ToDoList toDoList, String name, String dueDate, Boolean isUrgents) {
        // check if task already exist
        Task newTask = null;
        if (!toDoList.contains(name)) {

            if (isUrgents) {

                newTask = createNewUrgentTask(name, dueDate);
            } else {

                newTask = createNewRegularTask(name, dueDate);
            }
        }

        if (newTask != null) {
            toDoList.addTask(newTask);
        } else {
            System.out.println("When creating task new task is null");
        }


    }

    private Task createNewRegularTask(String name, String dueDate) {
        Task newTask = null;
        try {

            if (!dueDate.equals("skip")) {
                newTask = new RegularTask(name, dueDate);
            } else {
                newTask = new RegularTask(name);
            }

        } catch (ParseException e) {
            System.out.println("date of new regular task is incorrect");
        }
        return newTask;
    }

    private Task createNewUrgentTask(String name, String dueDate) {
        Task newTask = null;
        try {
            if (!dueDate.equals("skip")) {
                newTask = new UrgentTask(name, dueDate);
            } else {
                newTask = new UrgentTask(name);
            }

        } catch (ParseException e) {
            System.out.println("date of new urgent task is incorrect");
        }
        return newTask;
    }

//
//    private void createAndAddSelectedTypeOfTask(String name, String date, ToDoList toDoList) throws ParseException {
//        System.out.println("\nDo you want to set this task as an urgent task? Enter yes or no");
//        String isUrgent = input.nextLine();
//
//        if (isUrgent.equals("yes")) {
//            toDoList.addTask(new UrgentTask(name, date));
//            if (date == null) {
//                System.out.println("New urgent task created: " + name + " Due date not set");
//            } else {
//                System.out.println("New urgent task created: " + name + " Due date set at: " + date);
//            }
//
//        } else {
//            toDoList.addTask(new RegularTask(name, date));
//            if (date == null) {
//                System.out.println("New task created: " + name + " Due date not set");
//            } else {
//                System.out.println("New task created: " + name + " Due date set at: " + date);
//            }
//
//        }
//
//    }

//    private void printAllCurrentList(ToDoMap map) {
//        System.out.println("Here is all to do list you have");
//        for (String name : map.getMap().keySet()) {
//            System.out.println(name);
//        }
//    }
//
//    private String handleDuedate() {
//        System.out.println("\nEnter dueDate in format " + dateFormat + " or Enter skip if no dueDate");
//        String date = input.nextLine();
//        if (date.equals("skip")) {
//            date = null;
//            System.out.println("Due date not set");
//        } else {
//            System.out.println("Due date set at: " + date);
//        }
//        return date;
//    }
//
//    private String handleName(ToDoList toDoList) {
//        System.out.println("\nPlease enter the name of your task");
//        String name = input.nextLine();
//        System.out.println("Name of the tests is: " + name);
//        if (toDoList.contains(name)) {
//            name = null;
//        }
//        return name;
//    }



//// ui Change Task Name
//    public void handleChangeName(Task editTask) {
//        System.out.println("Enter the new name you want to change to");
//        String newName = input.nextLine();
//        String oldName = editTask.getName();
//        editTask.setName(newName);
//        System.out.println(String.format("Your task's name changed from %1s to %2s", oldName, newName));
//    }
//
//    // ui Change Task date
//    public void handleChangeDueDate(Task editTask) throws ParseException {
//        System.out.println("Enter the new due date you want to change to");
//        String oldDate = GeneralTask.sdf.format(editTask.getDueDate());
//        String newDate = input.nextLine();
//        editTask.setDueDate(newDate);
//        System.out.println(String.format("Your task's due date changed from %1s to %2s", oldDate, newDate));
//    }
//
//    // ui Delete task
//    public void handleDeleteTask(Task editTask, ToDoList toDoList) throws TaskNotFoundException {
//        String taskName = editTask.getName();
//        Boolean isDeleted = toDoList.deleteTask(taskName);
//        if (isDeleted) {
//            System.out.println("Task " + taskName + " has been deleted");
//        }
//
//    }


}



