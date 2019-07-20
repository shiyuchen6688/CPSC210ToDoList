package ui;

import exceptions.NotFoundException;
import exceptions.TaskNotFoundException;
import model.RegularTask;
import model.Task;
import model.ToDoList;
import model.UrgentTask;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
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
    private static final String CHANGE_NAME_COMMAND = "1";
    private static final String CHANGE_DUEDATE_COMMAND = "2";
    private static final String DELETE_COMMAND = "3";

    private static Scanner input;
    private boolean isRunning;
    private ToDoList toDoList;
    private List<String> historyFiles;

    public Tool(ToDoList toDoList) {
        this.toDoList = toDoList;
        input = new Scanner(System.in);
        isRunning = true;
        this.historyFiles = new ArrayList<>();
        historyFiles.add("inputfile.txt");
        historyFiles.add("schoolfile.txt");
    }

    // Choose Format
    public void userChooseFormat() {
        printFormatOptions();
        String formatChoice = input.nextLine();
        handleFormatOptions(formatChoice);
    }

    public void printFormatOptions() {
        System.out.println("Welcome to your TODO list!");
        System.out.println("You can choose your default date format here:");
        System.out.println("\nEnter " + 1 + " to use yyyy-MM-dd");
        System.out.println("\nEnter " + 2 + " to use dd-MM-yyyy");
        System.out.println("\nEnter " + 3 + " to use MM-dd-yyyy");
        System.out.println("\nEnter " + 0 + " if you don't care and we will use our default yyyy-MM-dd");
    }

    public void handleFormatOptions(String formatChoice) {
        switch (formatChoice) {
            case "1":
                dateFormat = "yyyy-MM-dd";
                sdf = new SimpleDateFormat(dateFormat);
                break;
            case "2":
                dateFormat = "dd-MM-yyyy";
                sdf = new SimpleDateFormat(dateFormat);
                break;
            case "3":
                dateFormat = "MM-dd-yyyy";
                sdf = new SimpleDateFormat(dateFormat);
                break;
            default:
                dateFormat = "yyyy-MM-dd";
                System.out.println("seted to default yyyy-MM-dd");
                sdf = new SimpleDateFormat(dateFormat);
                break;
        }
    }


    // Entrance to tool
    public void handleUserInput() {
        System.out.println("How can I help you today?");
        printInstruction();
        String str;
        Boolean foundException = true;

        while (isRunning) {
            if (input.hasNext()) {
                str = input.nextLine();
                do {
                    try {
                        processInput(str);
                        foundException = false;
                    } catch (TaskNotFoundException e) {
                        System.out.println("\nTask can not be found");
                        System.out.println("Back to enter task name");
                        printInstruction();
                    } catch (ParseException p) {
                        System.out.println("\nDate added is not in appropriate form");
                        System.out.println("please follow: " + dateFormat);
                        System.out.println("Back to enter task name");
                    }
                } while (foundException);
            }
        }


    }

    public void processInput(String str) throws ParseException, TaskNotFoundException {
        switch (str) {
            case ALLTASKS_COMMAND:
                System.out.println("\n------Here is all of your tasks------");
                toDoList.printAllTasks();
                break;
            case ALLOVERDUES_COMMAND:
                System.out.println("\n------Here is all of your overdue tasks------");
                toDoList.printAllOverdueTasks();
                break;
            case ADD_TASK_COMMAND:
                handleAddTask();
                break;
            case EDIT_TASK_COMMAND:
                handleEditTask();
                break;
            case QUIT_COMMAND:
                isRunning = false;
                break;
            default:
                System.out.println("Wrong command, try again");
        }
    }

    // TODO QUESTION: CAN I DO THIS?
    // TODO: Let them add new files
    public String chooseFileToSaveHistory() throws FileNotFoundException {
        System.out.println("Here is all files you currently have");
        for (String file : historyFiles) {
            System.out.println(file);
        }

        System.out.println("\nPlease enter the name of the file you want to work with");
        String fileName = input.nextLine();
        Boolean canFindFile = false;
        for (String file : historyFiles) {
            if (file == fileName) {
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
        System.out.println("Enter " + QUIT_COMMAND + " to quit");
    }





    // TODO : seperate into 3 helpers
    public void handleAddTask() throws ParseException {
        // name
        System.out.println("\nPlease enter the name of your task");
        String name = input.nextLine();
        System.out.println("Name of the test is: " + name);
        // due date or not
        System.out.println("\nEnter dueDate in format " + dateFormat + " or Enter skip if no dueDate");
        String date = input.nextLine();
        if (date.equals("skip")) {
            date = null;
            System.out.println("Due date not set");
        } else {
            System.out.println("Due date set at: " + date);
        }
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

    // handle edit a Task, change name, delete, change due date
    public void handleEditTask() throws ParseException, TaskNotFoundException {
        // name
        System.out.println("\n------Here is all of your task------");
        toDoList.printAllTasks();
        System.out.println("Please enter the name of the task you want to edit");
        String taskName = input.nextLine();
        Task editTask = toDoList.findTask(taskName);
        System.out.println("Found task: " + taskName);
        printEditOptions();
        String option = input.nextLine();
        handleEditOption(option, editTask);

    }


    // print edit options
    public void printEditOptions() {
        System.out.println("\nEnter " + CHANGE_NAME_COMMAND + " to change name of this task");
        System.out.println("Enter " + CHANGE_DUEDATE_COMMAND + " to change due date of this task");
        System.out.println("Enter " + DELETE_COMMAND + " to delete this task");
    }

    public void handleEditOption(String option, Task editTask) throws ParseException, TaskNotFoundException {
        switch (option) {
            case "1":
                handleChangeName(editTask);
                break;
            case "2":
                handleChangeDueDate(editTask);
                break;
            case "3":
                handleDeleteTask(editTask);
                break;
        }
    }

    // TODO file does not exist
// ui Change Task Name
    public void handleChangeName(Task editTask) {
        System.out.println("Enter the new name you want to change to");
        String newName = input.nextLine();
        String oldName = editTask.getTaskName();
        editTask.setTaskName(newName);
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
    public void handleDeleteTask(Task editTask) throws TaskNotFoundException {
        String taskName = editTask.getTaskName();
        Boolean isDeleted = toDoList.deleteTask(taskName);
        if (isDeleted) {
            System.out.println("Task " + taskName + " has been deleted");
        }

    }


}



