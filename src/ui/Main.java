package ui;

import model.Task;

import java.util.ArrayList;

public class Main {
    private static ArrayList<Task> tasks;
    private static Task taskCPSC210;
    private static Task taskWRDS150;
    private static Task task1DayLeft;
    private static Task task0DayLeft;
    private static Task task1DayOverdue;
    private static Task task5DayOverdue;


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


        System.out.println("Welcome! Here is your TODO list");
        addTask(taskCPSC210);
        addTask(taskWRDS150);
        addTask(task1DayLeft);
        addTask(task0DayLeft);
        addTask(task1DayOverdue);
        addTask(task5DayOverdue);
        printAllTask();
        printAllOverdueTask();


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
        System.out.println("Done, you have " + num + " task in total");
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
        System.out.println("Done, you have " + num + " OVERDUE task in total!!!");
        System.out.println();
    }

}
