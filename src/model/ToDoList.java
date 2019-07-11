package model;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    List<Task> tasks;


    // EFFECTSL create
    public ToDoList() {
        tasks = new ArrayList<>();
    }



    // MODIFIES: this
    // EFFECTS: add a task in to ToDoList
    public void addTask(String taskName) {
        System.out.println("New task created: " + taskName);
        tasks.add(new Task(taskName));
    }



    // MODIFIES: this
    // EFFECTS: delete a task from the ToDoList
    public boolean deleteTask(String taskName) {
        Task t = findTask(taskName);
        if (t != null) {
            tasks.remove(t);
            System.out.println("Task deleted: " + taskName);
            return true;
        } else {
            System.out.println("This task does not exist");
            return false;
        }
    }


    // EFFECTS: find a specific task with givn name
    public Task findTask(String taskName) {
        for (Task t: tasks) {
            if(t.getTaskName() == taskName) {
                System.out.println("Found task: " + taskName);
                return t;
            }
        }
        System.out.println("Task: " + taskName + " can not be found");
        return null;
    }



    // EFFECTS: print all task inside todolist in format: Number : task name
    public void printAllTask() {
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

    // EFFECTS: Return names of all tasks in the ToDOList in a String
    public String getAllTaskString() {
        String result = "";
        for (Task t: tasks) {
            result = result + "\n" + t.getTaskName();
        }
        return result;
    }



    // EFFECTS: print all overdue tasks inside todolist in format:
    //          OVERDUE Task num : task name
    public void printAllOverdueTask() {
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



    // EFFECTS: return true if todoList contain task with given name, false otherwise
    public boolean contains(String taskName) {
        for (Task t: tasks) {
            if (t.getTaskName() == taskName) {
                return true;
            }
        }

        return false;
    }



    // EFFECTS: return size of todoList
    public int size() {
        return tasks.size();
    }
}