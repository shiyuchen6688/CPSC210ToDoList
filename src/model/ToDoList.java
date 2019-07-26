package model;

import exceptions.FileNotFoundException;
import exceptions.NotFoundException;
import exceptions.TaskNotFoundException;
import ui.ToDoListUsage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sun.jmx.snmp.ThreadContext.contains;


public class ToDoList {

    // TODO: make use of this name
    private String name;
    private List<Task> tasks;
    private String type;


    // MODIFIES: this
    // EFFECTS: create a ToDoList object with empty tasks list
    public ToDoList(String name) {
        tasks = new ArrayList<>();
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: create a ToDoList object with empty list and given type
    public ToDoList(String name, String type) {
        this.type = type;
        tasks = new ArrayList<>();
        this.name = name;
    }


    // EFFECTS: return name of this list
    public String getName() {
        return this.name;
    }

    // EFFECTS: return type of this list
    public String getType() {
        return this.type;
    }

    // EFFECTS: return tasks of this list
    public List<Task> getTasks() {
        return this.tasks;
    }

    // MODIFIES: this
    // EFFECTS:  add a task with given name in to ToDoList
    public void addTask(String taskName) {
        if (!contains(taskName)) {
            Task newTask = new RegularTask(taskName);
            tasks.add(newTask);
            newTask.setListBelonged(this);
        }
    }

    // MODIFIES: this
    // EFFECTS:  add a task with given name and given due date in to ToDoList
    public void addTask(String taskName, String dueDate) throws ParseException {
        if (!contains(taskName)) {
            Task newTask = new RegularTask(taskName, dueDate);
            tasks.add(newTask);
            newTask.setListBelonged(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: add given task in to ToDoList
    public void addTask(Task t) {
        if (!tasks.contains(t)) {
            tasks.add(t);
            t.setListBelonged(this);
        }
    }


    // MODIFIES: this
    // EFFECTS: if given task is already in the ToDoList, delete it and return true
    //          Otherwise, return false
    public boolean deleteTask(String taskName) throws TaskNotFoundException {
        // TODO THIS IS SO WEIRED!!!!!!!!
        if (contains(taskName)) {
            Task task = findTask(taskName);
            tasks.remove(task);
            task.removeListBelonged(this);
            System.out.println("Task deleted: " + taskName);
            return true;
        }
        return false;

    }


    // EFFECTS: return a specific task with given name,
    //          return null if can not find
    public Task findTask(String taskName) throws TaskNotFoundException {
        Task taskRegular = new RegularTask(taskName);
        Task taskUrgent = new UrgentTask(taskName);
        for (Task t : tasks) {
            if (taskRegular.equals(t) || taskUrgent.equals(t)) {
                return t;
            }
        }
        throw new TaskNotFoundException();
    }

    // EFFECTS: return true if todoList contain task with given name, false otherwise
    public boolean contains(String taskName) {
        Task taskRegular = new RegularTask(taskName);
        Task taskUrgent = new UrgentTask(taskName);
        for (Task t : tasks) {
            if (taskRegular.equals(t) || taskUrgent.equals(t)) {
                return true;
            }
        }

        return false;
    }


    // EFFECTS: print all task inside todolist in format: Number : task name
    public void printAllTasks() {
        System.out.println();
        int num = 1;
        for (Task t : tasks) {
            String result = num + " : " + t.getTaskName();
            if (t.getDueDate() != null) {
                result = result + ", Due Date is " + ToDoListUsage.sdf.format(t.getDueDate());
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
        for (Task t : tasks) {
            ls.add(t.getTaskName());
        }
        return ls;
    }


    // EFFECTS: print all overdue tasks inside todolist in format:
    //          OVERDUE Task num : task name
    public void printAllOverdueTasks() {
        System.out.println();
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


    public void printAllCloseToDue() {
        for (Task t : tasks) {
            if (t.closeToDue()) {
                System.out.println(String.format("\nTask %s is due in %s days", t.getTaskName(), t.getDayUntilDue()));
            }
        }
    }

    // EFFECTS: return size of todoList
    public int size() {
        return tasks.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoList toDoList = (ToDoList) o;
        return name.equals(toDoList.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
