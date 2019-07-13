package ui;

import model.ToDoList;

import java.text.ParseException;
import java.util.Scanner;

public class Tool {
    private static final String ALLTASKS_COMMAND = "all";
    private static final String ALLOVERDUES_COMMAND = "overdue";
    private static final String ADDTASK_COMMAND = "add";
    private static final String QUIT_COMMAND = "quit";



    private Scanner input;
    private boolean isRunning;
    private ToDoList toDoList;

    public Tool(ToDoList toDoList) {
        this.toDoList = toDoList;
        input = new Scanner(System.in);
        isRunning = true;
    }

    public void handleUserInput() throws ParseException {
        System.out.println("Welcome to your TODO list, how can I help you?");
        printInstruction();
        String str;

        while (isRunning) {
            if (input.hasNext()) {
                str = input.nextLine();
                processInput(str);
            }
        }


    }

    public void printInstruction() {
        System.out.println("\nEnter " + ADDTASK_COMMAND + " to add new task in to your TODO list");
        System.out.println("Enter " + ALLTASKS_COMMAND + " to see all of your tasks.");
        System.out.println("Enter " + ALLOVERDUES_COMMAND + " to see all of your OVERDUE tasks.");
        System.out.println("Enter " + QUIT_COMMAND + " to quit");
    }


    public void processInput(String str) throws ParseException {
        switch (str) {
            case ALLTASKS_COMMAND:
                toDoList.printAllTasks();
                break;
            case ALLOVERDUES_COMMAND:
                toDoList.printAllOverdueTasks();
                break;
            case ADDTASK_COMMAND:
                handleAddTask();
                break;
            case QUIT_COMMAND:
                isRunning = false;
                break;
            default:
                System.out.println("Wrong command, try again");
        }
    }


    public void handleAddTask() throws ParseException {
        // name
        System.out.println("Please enter the name of your task");
        String name = input.nextLine();
        // due date ot not
        System.out.println("Enter dueDate in format yyyy-mm-dd or Enter skip if no dueDate");
        String date = input.nextLine();
        if (date.equals("skip")) {
            toDoList.addTask(name);
        } else {
            toDoList.addTask(name, date);
        }
    }


}
