package ui;

import model.exceptions.TaskAlreadyExistException;
import model.exceptions.TaskNotFoundException;
import model.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ui.ToDoListUsage.dateFormat;
import static ui.ToDoListUsage.sdf;

public class Tool {
    private static final String ALLTASKS_COMMAND = "all";
    private static final String ALLOVERDUES_COMMAND = "overdue";
    private static final String ADD_TASK_COMMAND = "add";
    private static final String EDIT_TASK_COMMAND = "edit";
    private static final String QUIT_COMMAND = "quit";
    private static final String REMINDER_COMMAND = "reminder";
    private static final String SWITCH_LIST_COMMAND = "change";
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
        historyFiles.add("inputfile.txt");
        // TODO you can add more files here to save history
    }


    public List<String> getHistoryFiles() {
        return historyFiles;
    }

    public void printFormatOptions() {
//        System.out.println("Welcome to your TODO list!");
        System.out.println("You can choose your default date format here:");
        System.out.println("\nEnter " + 1 + " to use yyyy-MM-dd");
        System.out.println("\nEnter " + 2 + " to use dd-MM-yyyy");
        System.out.println("\nEnter " + 3 + " to use MM-dd-yyyy");
        System.out.println("\nEnter " + 0 + " if you don't care and we will use our default yyyy-MM-dd");
    }

    public List<String> returnFormatOptions() {
        List<String> options = new ArrayList<>();
        options.add("You can choose your default date format here:");
        options.add("\nEnter " + 1 + " to use yyyy-MM-dd");
        options.add("\nEnter " + 2 + " to use dd-MM-yyyy");
        options.add("\nEnter " + 3 + " to use MM-dd-yyyy");
        options.add("\nEnter " + 0 + " if you don't care and we will use our default yyyy-MM-dd");
        return options;
    }

    public String handleFormatOptions(String formatChoice) {
        switch (formatChoice) {
            case "yyyy-MM-dd":
                dateFormat = "yyyy-MM-dd";
                sdf = new SimpleDateFormat(dateFormat);
                return "choosed yyyy-MM-dd";
            case "dd-MM-yyyy":
                dateFormat = "dd-MM-yyyy";
                sdf = new SimpleDateFormat(dateFormat);
                return "choosed dd-MM-yyyy";
            case "MM-dd-yyyy":
                dateFormat = "MM-dd-yyyy";
                sdf = new SimpleDateFormat(dateFormat);
                return "choosed MM-dd-yyyy";
            default:
                dateFormat = "yyyy-MM-dd";
                sdf = new SimpleDateFormat(dateFormat);
                return "option is incorrect, set to default yyyy-MM-dd";
        }
    }


//    // Entrance to tool
//    public void handleUserInput(ToDoMap toDoMap) {
//        System.out.println("How can I help you today?");
//        printInstruction();
//        String str;
//        Boolean foundException = true;
//
//        while (isRunning) {
//            if (input.hasNext()) {
//                str = input.nextLine();
//                do {
//                    try {
//                        processInput(str, toDoMap);
//                        foundException = false;
//                    } catch (TaskNotFoundException e) {
//                        System.out.println("\nTask can not be found");
//                        System.out.println("Back to enter task name");
//                        printInstruction();
//                    } catch (ParseException p) {
//                        System.out.println("\nDate added is not in appropriate form");
//                        System.out.println("please follow: " + dateFormat);
//                        System.out.println("Back to enter task name");
//                    }
//                } while (foundException);
//            }
//        }
//
//
//    }

//    public void processInput(String str, ToDoMap toDoMap) throws ParseException, TaskNotFoundException {
//
//        switch (str) {
//            case ALLTASKS_COMMAND:
//                System.out.println("\n------Here is all of your tasks------");
//                toDoMap.printAllTasks();
//                break;
//            case ALLOVERDUES_COMMAND:
//                System.out.println("\n------Here is all of your overdue tasks------");
//                toDoMap.printAllOverdueTasks();
//                break;
//            case ADD_TASK_COMMAND:
//                handleAddTaskToMap(toDoMap);
//                break;
//            case EDIT_TASK_COMMAND:
//                handleEditTask(toDoMap);
//                break;
////            case REMINDER_COMMAND:
////                System.out.println("Here is all your tasks that are close to due");
////                printReminder(toDoMap);
////                break;
//            case QUIT_COMMAND:
//                isRunning = false;
//                break;
//            default:
//                System.out.println("Wrong command, try again");
//        }
//    }


//    // EFFECTS: print all close to due tasks
//    public void printReminder(ToDoMap map) {
//        for (String name : map.getMap().keySet()) {
//            map.getList(name).printAllCloseToDueTasks();
//        }
//    }

    public ToDoList chooseListFromMapOrCreateList(String s, ToDoMap map) {
        ToDoList newList = map.getList(s);
        if (newList == null) {
            newList = new ToDoList(s);
            map.addToDoList(newList);
        }
        return newList;
    }

    public String chooseFileToSaveHistory() throws FileNotFoundException {
        System.out.println("Here is all files you currently have");
        for (String file : historyFiles) {
            System.out.println(file);
        }

        System.out.println("\nPlease enter the name of the file you want to work with");
        String fileName = input.nextLine();
        Boolean canFindFile = false;
        for (String file : historyFiles) {
            if (file.equals(fileName)) {
                canFindFile = true;
                break;
            }
        }
        if (!canFindFile) {
            throw new FileNotFoundException();
        }
        return fileName;
    }

// GET another method that process input

    public void printInstruction() {
        System.out.println("\nEnter " + ADD_TASK_COMMAND + " to add new task in to your TODO list");
        System.out.println("Enter " + ALLTASKS_COMMAND + " to see all of your tasks.");
        System.out.println("Enter " + ALLOVERDUES_COMMAND + " to see all of your OVERDUE tasks.");
//        System.out.println("Enter " + SWITCH_LIST_COMMAND + " to work on a different list or create a new one");
        System.out.println("Enter " + QUIT_COMMAND + " to quit");
    }


    public void handleAddTaskToMap(ToDoMap map) throws ParseException {
        printAllCurrentList(map);
        System.out.println("Please choose which list to work on or enter name of the new list");
        String listName = input.nextLine();
        ToDoList curList = chooseListFromMapOrCreateList(listName, map);
        System.out.println("List you are working on is " + curList.getName());
        handleAddTaskToList(curList);
    }

    // EFFECTS: ask for task name and add the task to given toDoList
    public void handleAddTaskToList(ToDoList toDoList) throws ParseException {
        // name
        Boolean nameSuccessed = false;
        String name = null;

        do {
            try {
                name = handleName(toDoList);
                nameSuccessed = true;
            } catch (TaskAlreadyExistException e) {
                System.out.println(e.getMessage());
            }
        } while (!nameSuccessed);

        // due date or not
        String date = handleDuedate();

        // create and add task
        createAndAddSelectedTypeOfTask(name, date, toDoList);


    }

    public void handleAddTaskToList(ToDoList toDoList, String name, String dueDate, Boolean isUrgents)
            throws TaskAlreadyExistException {
        // check if task already exist
        if (toDoList.contains(name)) {
            throw new TaskAlreadyExistException("Trying to add a duplicate task");
        }
        Task newTask = null;
        if (isUrgents) {
            newTask = createNewUrgentTask(name, dueDate, newTask);
        } else {
            newTask = createNewRegularTask(name, dueDate, newTask);
        }


        if (newTask != null) {
            toDoList.addTask(newTask);
        } else {
            System.out.println("When creating task new tasl is null");
        }


    }

    private Task createNewRegularTask(String name, String dueDate, Task newTask) {
        try {
            if (dueDate != "skip") {
                newTask = new RegularTask(name, dueDate);
            } else {
                newTask = new RegularTask(name);
            }

        } catch (ParseException e) {
            System.out.println("date of new regular task is incorrect");
        }
        return newTask;
    }

    private Task createNewUrgentTask(String name, String dueDate, Task newTask) {
        try {
            if (dueDate != "skip") {
                newTask = new UrgentTask(name, dueDate);
            } else {
                newTask = new UrgentTask(name);
            }

        } catch (ParseException e) {
            System.out.println("date of new urgent task is incorrect");
        }
        return newTask;
    }


    private void createAndAddSelectedTypeOfTask(String name, String date, ToDoList toDoList) throws ParseException {
        System.out.println("\nDo you want to set this task as an urgent task? Enter yes or no");
        String isUrgent = input.nextLine();

        if (isUrgent.equals("yes")) {
            toDoList.addTask(new UrgentTask(name, date));
            if (date == null) {
                System.out.println("New urgent task created: " + name + " Due date not set");
            } else {
                System.out.println("New urgent task created: " + name + " Due date set at: " + date);
            }

        } else {
            toDoList.addTask(new RegularTask(name, date));
            if (date == null) {
                System.out.println("New task created: " + name + " Due date not set");
            } else {
                System.out.println("New task created: " + name + " Due date set at: " + date);
            }

        }

    }

    private void printAllCurrentList(ToDoMap map) {
        System.out.println("Here is all to do list you have");
        for (String name : map.getMap().keySet()) {
            System.out.println(name);
        }
    }

    private String handleDuedate() {
        System.out.println("\nEnter dueDate in format " + dateFormat + " or Enter skip if no dueDate");
        String date = input.nextLine();
        if (date.equals("skip")) {
            date = null;
            System.out.println("Due date not set");
        } else {
            System.out.println("Due date set at: " + date);
        }
        return date;
    }

    private String handleName(ToDoList toDoList) throws TaskAlreadyExistException {
        System.out.println("\nPlease enter the name of your task");
        String name = input.nextLine();
        System.out.println("Name of the test is: " + name);
        if (toDoList.contains(name)) {
            throw new TaskAlreadyExistException("Task: " + name + " already exist");
        }
        return name;
    }

//    // handle edit a Task, change name, delete, change due date
//    public void handleEditTask(ToDoMap map) throws ParseException, TaskNotFoundException {
//        // name
//        System.out.println("\n------Here is all of your task------");
//        map.printAllTasks();
//        System.out.println("Please enter the list name of the to do list you want to change");
//        String listName = input.nextLine();
//        System.out.println("Please enter the name of the task you want to edit");
//        String taskName = input.nextLine();
//        ToDoList editList = map.getList(listName);
//        Task editTask = editList.findTask(taskName);
//        System.out.println("Found task: " + taskName);
//        printEditOptions();
//        String option = input.nextLine();
//        handleEditOption(option, editTask, editList);
//
//    }


    // print edit options
    public void printEditOptions() {
        System.out.println("\nEnter " + CHANGE_NAME_COMMAND + " to change name of this task");
        System.out.println("Enter " + CHANGE_DUEDATE_COMMAND + " to change due date of this task");
        System.out.println("Enter " + DELETE_COMMAND + " to delete this task");
    }

//    public void handleEditOption(String option, Task editTask, ToDoList toDoList)
//            throws ParseException, TaskNotFoundException {
//        switch (option) {
//            case "1":
//                handleChangeName(editTask);
//                break;
//            case "2":
//                handleChangeDueDate(editTask);
//                break;
//            case "3":
//                handleDeleteTask(editTask, toDoList);
//                break;
//        }
//    }

    // TODO file does not exist
// ui Change Task Name
    public void handleChangeName(Task editTask) {
        System.out.println("Enter the new name you want to change to");
        String newName = input.nextLine();
        String oldName = editTask.getName();
        editTask.setName(newName);
        System.out.println(String.format("Your task's name changed from %1s to %2s", oldName, newName));
    }

    // ui Change Task date
    public void handleChangeDueDate(Task editTask) throws ParseException {
        System.out.println("Enter the new due date you want to change to");
        String oldDate = ToDoListUsage.sdf.format(editTask.getDueDate());
        String newDate = input.nextLine();
        editTask.setDueDate(newDate);
        System.out.println(String.format("Your task's due date changed from %1s to %2s", oldDate, newDate));
    }

    // ui Delete task
    public void handleDeleteTask(Task editTask, ToDoList toDoList) throws TaskNotFoundException {
        String taskName = editTask.getName();
        Boolean isDeleted = toDoList.deleteTask(taskName);
        if (isDeleted) {
            System.out.println("Task " + taskName + " has been deleted");
        }

    }


}


