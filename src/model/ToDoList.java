package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class ToDoList {
    public final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private List<Task> tasks;


    // MODIFIES: this
    // EFFECTS: create a ToDoList object with empty tasks list
    public ToDoList() {
        tasks = new ArrayList<>();
    }



    // EFFECTS: return tasks of this list
    public List<Task> getTasks() {
        return this.tasks;
    }

    // MODIFIES: this
    // EFFECTS:  add a task with given name in to ToDoList
    public void addTask(String taskName) {
        System.out.println("New task created: " + taskName);
        tasks.add(new GeneralTask(taskName));
    }

    // MODIFIES: this
    // EFFECTS:  add a task with given name and given due date in to ToDoList
    public void addTask(String taskName, String dueDate) throws ParseException {
        System.out.println("New task created: " + taskName);
        tasks.add(new GeneralTask(taskName, dueDate));
    }

    // MODIFIES: this
    // EFFECTS: add given task in to ToDoList
    public void addTask(Task t) {
        System.out.println("New task created: " + t.getTaskName());
        tasks.add(t);
    }



    // MODIFIES: this
    // EFFECTS: delete given task from the ToDoList, return true if deleted
    //          return false if can not find
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


    // EFFECTS: return a specific task with given name,
    //          return null if can not find
    public Task findTask(String taskName) {
        for (Task t: tasks) {
            if(t.getTaskName().equals(taskName)) {
                System.out.println("Found task: " + taskName);
                return t;
            }
        }
        System.out.println("Task: " + taskName + " can not be found");
        return null;
    }



    // EFFECTS: print all task inside todolist in format: Number : task name
    public void printAllTasks() {
        System.out.println();
        System.out.println("----------Here is all of your tasks----------");
        int num = 1;
        for (Task t : tasks) {
            String result = num + " : " + t.getTaskName();
            if (t.getDueDate() != null) {
                result = result + ", Due Date is " + sdf.format(t.getDueDate());
            }
            System.out.println(result);
            num = num + 1;
        }
        System.out.println("Done, you have " + tasks.size() + " task in total");
        System.out.println();
    }

    // EFFECTS: Return names of all tasks in the ToDOList in a String
    public String getAllTaskAsString() {
        return tasks.stream().map(Task::getTaskName).collect(Collectors.joining("\n"));
    }

    // EFFECTS: return list of string names of tasks in the list
    public List<String> getAllTaskAsListOfString() {
        List<String> ls = new ArrayList<String>();
        for (Task t: tasks) {
            ls.add(t.getTaskName());
        }
        return ls;
    }



    // EFFECTS: print all overdue tasks inside todolist in format:
    //          OVERDUE Task num : task name
    public void printAllOverdueTasks() {
        System.out.println();
        System.out.println("---!!!Here is all of your OVERDUE tasks!!----");
        int num = 1;
        for (Task t : tasks) {
            if (t.isOverdue()) {
                System.out.println("OVERDUE Task " + num + " : " + t.getTaskName());
                num = num + 1;
            }
        }
        System.out.println("Done, you have " + tasks.size() + " OVERDUE task in total!!!");
        System.out.println();
    }



    // EFFECTS: return true if todoList contain task with given name, false otherwise
    public boolean contains(String taskName) {
        for (Task t: tasks) {
            if (t.getTaskName().equals(taskName)) {
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
