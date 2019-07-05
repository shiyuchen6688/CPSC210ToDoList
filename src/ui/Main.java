package ui;

import model.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final String ALLTASK_COMMAND = "all task";
    private static final String ALLOVERDUE_COMMAND = "all overdue";

    private static ArrayList<Task> tasks;
    private static Task taskCPSC210;
    private static Task taskWRDS150;
    private static Task task1DayLeft;
    private static Task task0DayLeft;
    private static Task task1DayOverdue;
    private static Task task5DayOverdue;
    private static Scanner input;
    private static boolean isRunning;



    public static void main(String[] args) {
        tasks = new ArrayList<>();
        taskCPSC210 = new Task("Assignment one for CPSC210");
        taskWRDS150 = new Task("Paragraph for WRDS150");
        task1DayLeft = new Task("This task should have one day left, not overdue");
        task1DayLeft.setDayUntilDue(1);
        task0DayLeft = new Task("This task should have zero day left, not overdue");
        task0DayLeft.setDayUntilDue(0);
        task1DayOverdue = new Task("This task should OVERDUE 1 day");
        task1DayOverdue.setDayUntilDue(-1);
        task5DayOverdue = new Task("This task should OVERDUE 10 day");
        task5DayOverdue.setDayUntilDue(-5);
        isRunning = true;
        input = new Scanner(System.in);

        addTask(taskCPSC210);
        addTask(taskWRDS150);
        addTask(task1DayLeft);
        addTask(task0DayLeft);
        addTask(task1DayOverdue);
        addTask(task5DayOverdue);
        printAllTask();
        printAllOverdueTask();




        handleUserInput();
        System.out.println("Have a good day!");


    }

    public static void addTask(Task task) {
        System.out.println("New task created: " + task.getTaskName());
        tasks.add(task);
    }

    public static void printAllTask() {
        System.out.println();
        System.out.println("----------Here is all of your tasks----------");
        int num = 1;
        for (Task t : tasks) {
            System.out.println(num + " : " + t.getTaskName());
            num = num + 1;
        }
        int count = num - 1;
        System.out.println("Done, you have " + count + " task in total");
        System.out.println();
    }

    public static void printAllOverdueTask() {
        System.out.println();
        System.out.println("---!!!Here is all of your OVERDUE tasks!!----");
        int num = 1;
        for (Task t : tasks) {
            if (t.getDayUntilDue() < 0) {
                System.out.println("OVERDUE Task " + num + " : " + t.getTaskName());
                num = num + 1;
            }
        }
        int count = num - 1;
        System.out.println("Done, you have " + count + " OVERDUE task in total!!!");
        System.out.println();
    }

    public static void handleUserInput() {
        System.out.println("Welcome to your TODO list, how can I help you?");
        printInstruction();
        String str;

        while(isRunning) {
            if (input.hasNext()) {
                str = input.nextLine();
                processInput(str);
            }
        }


    }

    public static void printInstruction() {
        System.out.println("\nEnter " + "name of your task" + " to add new task in to your TODO list");
        System.out.println("Enter " + ALLTASK_COMMAND + " to see all of your tasks.");
        System.out.println("Enter " + ALLOVERDUE_COMMAND + " to see all of your OVERDUE tasks.");
    }




    public static void processInput(String str) {
        if(str.length() > 0) {
            switch(str) {
                case ALLTASK_COMMAND:
                    printAllTask();
                    break;
                case ALLOVERDUE_COMMAND:
                    printAllOverdueTask();
                    break;
                default:
                    addTask(new Task(str));
                    break;


            }
        }
    }

}
