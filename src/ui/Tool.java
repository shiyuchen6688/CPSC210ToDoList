package ui;

import model.ToDoList;

import java.util.Scanner;

public class Tool {
    private static final String ALLTASK_COMMAND = "all";
    private static final String ALLOVERDUE_COMMAND = "overdue";



    private Scanner input;
    private boolean isRunning;
    private ToDoList toDoList;

    public Tool(ToDoList toDoList) {
        this.toDoList = toDoList;
        input = new Scanner(System.in);
        isRunning = true;
    }

    public  void handleUserInput() {
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
        System.out.println("\nEnter " + "name of your task" + " to add new task in to your TODO list");
        System.out.println("Enter " + ALLTASK_COMMAND + " to see all of your tasks.");
        System.out.println("Enter " + ALLOVERDUE_COMMAND + " to see all of your OVERDUE tasks.");
    }


    public void processInput(String str) {
        if (str.length() > 0) {
            switch (str) {
                case ALLTASK_COMMAND:
                    toDoList.printAllTask();
                    break;
                case ALLOVERDUE_COMMAND:
                    toDoList.printAllOverdueTask();
                    break;
                default:
                    toDoList.addTask(str);
                    break;


            }
        }
    }


}
