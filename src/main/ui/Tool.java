//package ui;
//
//import model.*;
//import model.exceptions.TaskAlreadyExistException;
//import model.exceptions.TaskNotFoundException;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import static ui.ToDoAppUsage.dateFormat;
//import static ui.ToDoAppUsage.sceneMain;
//
//public class Tool {
//    private static final String CHANGE_NAME_COMMAND = "1";
//    private static final String CHANGE_DUEDATE_COMMAND = "2";
//    private static final String DELETE_COMMAND = "3";
//
//
//    private static Scanner input;
//    public static boolean isRunning;
//    public List<String> historyFiles;
//
//
//    public Tool() {
//        input = new Scanner(System.in);
//        isRunning = true;
//        this.historyFiles = new ArrayList<>();
//        historyFiles.add("inputfile.txt");
//        // TODO you can add more files here to save history
//    }
//
//
//    public List<String> getHistoryFiles() {
//        return historyFiles;
//    }
//
//    public void printFormatOptions() {
////        System.out.println("Welcome to your TODO list!");
//        System.out.println("You can choose your default date format here:");
//        System.out.println("\nEnter " + 1 + " to use yyyy-MM-dd");
//        System.out.println("\nEnter " + 2 + " to use dd-MM-yyyy");
//        System.out.println("\nEnter " + 3 + " to use MM-dd-yyyy");
//        System.out.println("\nEnter " + 0 + " if you don't care and we will use our default yyyy-MM-dd");
//    }
//
//    public List<String> returnFormatOptions() {
//        List<String> options = new ArrayList<>();
//        options.add("You can choose your default date format here:");
//        options.add("\nEnter " + 1 + " to use yyyy-MM-dd");
//        options.add("\nEnter " + 2 + " to use dd-MM-yyyy");
//        options.add("\nEnter " + 3 + " to use MM-dd-yyyy");
//        options.add("\nEnter " + 0 + " if you don't care and we will use our default yyyy-MM-dd");
//        return options;
//    }
//
//    public String handleFormatOptions(String formatChoice) {
//        switch (formatChoice) {
//            case "yyyy-MM-dd":
//                dateFormat = "yyyy-MM-dd";
//                GeneralTask.sdf = new SimpleDateFormat(dateFormat);
//                sceneMain.updateDateFormatLabel(dateFormat);
//                return "choosed yyyy-MM-dd";
//            case "dd-MM-yyyy":
//                dateFormat = "dd-MM-yyyy";
//                GeneralTask.sdf = new SimpleDateFormat(dateFormat);
//                sceneMain.updateDateFormatLabel(dateFormat);
//                return "choosed dd-MM-yyyy";
//            case "MM-dd-yyyy":
//                dateFormat = "MM-dd-yyyy";
//                GeneralTask.sdf = new SimpleDateFormat(dateFormat);
//                sceneMain.updateDateFormatLabel(dateFormat);
//                return "choosed MM-dd-yyyy";
//            default:
//                dateFormat = "yyyy-MM-dd";
//                GeneralTask.sdf = new SimpleDateFormat(dateFormat);
//                sceneMain.updateDateFormatLabel(dateFormat);
//                return "option is incorrect, set to default yyyy-MM-dd";
//        }
//    }
//
//
//    public ToDoList chooseListFromMapOrCreateList(String s, ToDoMap map) {
//        ToDoList newList = map.getList(s);
//        if (newList == null) {
//            newList = new ToDoList(s);
//            map.addToDoList(newList);
//        }
//        return newList;
//    }
//
//
//
//    // EFFECTS: ask for task name and add the task to given toDoList
//    public void handleAddTaskToList(ToDoList toDoList) throws ParseException {
//        // name
//        Boolean nameSuccessed = false;
//        String name = null;
//
//        do {
//            try {
//                name = handleName(toDoList);
//                nameSuccessed = true;
//            } catch (TaskAlreadyExistException e) {
//                System.out.println(e.getMessage());
//            }
//        } while (!nameSuccessed);
//
//        // due date or not
//        String date = handleDuedate();
//
//        // create and add task
//        createAndAddSelectedTypeOfTask(name, date, toDoList);
//
//
//    }
//
//    public void handleAddTaskToList(ToDoList toDoList, String name, String dueDate, Boolean isUrgents)
//            throws TaskAlreadyExistException {
//        // check if task already exist
//        if (toDoList.contains(name)) {
//            throw new TaskAlreadyExistException();
//        }
//        Task newTask = null;
//        if (isUrgents) {
//            newTask = createNewUrgentTask(name, dueDate);
//        } else {
//            newTask = createNewRegularTask(name, dueDate);
//        }
//
//
//        if (newTask != null) {
//            toDoList.addTask(newTask);
//        } else {
//            System.out.println("When creating task new task is null");
//        }
//
//
//    }
//
//    private Task createNewRegularTask(String name, String dueDate) {
//        Task newTask = null;
//        try {
//            if (dueDate != "skip") {
//                newTask = new RegularTask(name, dueDate);
//            } else {
//                newTask = new RegularTask(name);
//            }
//
//        } catch (ParseException e) {
//            System.out.println("date of new regular task is incorrect");
//        }
//        return newTask;
//    }
//
//    private Task createNewUrgentTask(String name, String dueDate) {
//        Task newTask = null;
//        try {
//            if (dueDate != "skip") {
//                newTask = new UrgentTask(name, dueDate);
//            } else {
//                newTask = new UrgentTask(name);
//            }
//
//        } catch (ParseException e) {
//            System.out.println("date of new urgent task is incorrect");
//        }
//        return newTask;
//    }
//
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
//
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
//    private String handleName(ToDoList toDoList) throws TaskAlreadyExistException {
//        System.out.println("\nPlease enter the name of your task");
//        String name = input.nextLine();
//        System.out.println("Name of the tests is: " + name);
//        if (toDoList.contains(name)) {
//            throw new TaskAlreadyExistException();
//        }
//        return name;
//    }
//
////    // handle edit a Task, change name, delete, change due date
////    public void handleEditTask(ToDoMap map) throws ParseException, TaskNotFoundException {
////        // name
////        System.out.println("\n------Here is all of your task------");
////        map.printAllTasks();
////        System.out.println("Please enter the list name of the to do list you want to change");
////        String listName = input.nextLine();
////        System.out.println("Please enter the name of the task you want to edit");
////        String taskName = input.nextLine();
////        ToDoList editList = map.getList(listName);
////        Task editTask = editList.findTask(taskName);
////        System.out.println("Found task: " + taskName);
////        printEditOptions();
////        String option = input.nextLine();
////        handleEditOption(option, editTask, editList);
////
////    }
//
//
//    // print edit options
//    public void printEditOptions() {
//        System.out.println("\nEnter " + CHANGE_NAME_COMMAND + " to change name of this task");
//        System.out.println("Enter " + CHANGE_DUEDATE_COMMAND + " to change due date of this task");
//        System.out.println("Enter " + DELETE_COMMAND + " to delete this task");
//    }
//
////    public void handleEditOption(String option, Task editTask, ToDoList toDoList)
////            throws ParseException, TaskNotFoundException {
////        switch (option) {
////            case "1":
////                handleChangeName(editTask);
////                break;
////            case "2":
////                handleChangeDueDate(editTask);
////                break;
////            case "3":
////                handleDeleteTask(editTask, toDoList);
////                break;
////        }
////    }
//
//    // TODO file does not exist
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
//
//
//}
//
//
//
