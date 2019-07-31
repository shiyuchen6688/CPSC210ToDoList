package model;

import model.exceptions.*;
import ui.ToDoAppUsage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class ToDoList {

    private String name;
    private List<Task> tasks;
    private String type;
    private ToDoMap mapBelonged;


    // constructor

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


    // getters

    // EFFECTS: return ToDoMap this list belonged
    public ToDoMap getMap() {
        return this.mapBelonged;
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


    // setters

    // MODIFIES: this
    // EFFECTS: record that this list belonged to given map
    public void setMapBelonged(ToDoMap map) {
        if (this.mapBelonged != map) {
            this.mapBelonged = map;
            map.addToDoList(this.getName());
        }

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setType(String type) {
        this.type = type;
    }


    // EFFECTS: return size of todoList
    public int size() {
        return tasks.size();
    }


    // add task

    // MODIFIES: this
    // EFFECTS:  if task already exist, throw TaskAlreadyExistException
    //           otherwise, add a task with given name in to ToDoList
    public void addTask(String taskName) throws TaskAlreadyExistException {
        if (!contains(taskName)) {
            Task newTask = new RegularTask(taskName);
            tasks.add(newTask);
            newTask.setListBelonged(this);
        }
    }

    // MODIFIES: this
    // EFFECTS:  if task already exist, throw TaskAlreadyExistException
    //           otherwise, add a task with given name and given due date in to ToDoList
    public void addTask(String taskName, String dueDate) throws ParseException, TaskAlreadyExistException {
        if (!contains(taskName)) {
            Task newTask = new RegularTask(taskName, dueDate);
            tasks.add(newTask);
            newTask.setListBelonged(this);
        }

        throw new TaskAlreadyExistException("Trying to make task: " + taskName + " but it already exist");
    }

    // MODIFIES: this
    // EFFECTS: if task already exist, throw TaskAlreadyExistException
    //           otherwise, add given task in to ToDoList
    public void addTask(Task t) {
        if (!tasks.contains(t)) {
            tasks.add(t);
            t.setListBelonged(this);
        }

    }


    // delete task
    // MODIFIES: this
    // EFFECTS: if given task is already in the ToDoList, delete it and return true
    public boolean deleteTask(String taskName) throws TaskNotFoundException {
        if (contains(taskName)) {
            Task task = findTask(taskName);
            tasks.remove(task);
            task.removeListBelonged(this);
            System.out.println("Task deleted: " + taskName);
            return true;
        }
        return false;

    }


    // EFFECTS: if can find, return a specific task with given name,
    //          if cannot find, throw TaskNotFound exception
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
            String result = num + " : " + t.getName();
            if (t.getDueDate() != null) {
                result = result + ", Due Date is " + ToDoAppUsage.sdf.format(t.getDueDate());
            }
            System.out.println(result);
            num = num + 1;
        }
        System.out.println("Done, you have " + tasks.size() + " task in " + name);
        System.out.println();
    }

    public List<String> returnAllListTasks() {
        List<String> result = new ArrayList<>();
        result.add("Here is all of your task in list: " + name);
        for (Task t : tasks) {
            if (t.getDueDate() != null) {
                result.add(ToDoAppUsage.INDENTATION + "Task: " + t.getName()
                        + " Due Date:" + ToDoAppUsage.sdf.format(t.getDueDate()));
            } else {
                result.add(ToDoAppUsage.INDENTATION + "Task: " + t.getName()
                        + " with no due date");
            }
        }
        result.add("\nlist: " + name + " is done");
        return result;
    }


    // EFFECTS: print all overdue tasks inside todolist in format:
    //          OVERDUE Task num : task name
    // TODO old version don't need anymore
    public void printAllOverdueTasks() {
        System.out.println();
        int num = 1;
        for (Task t : tasks) {
            if (t.isOverdue()) {
                System.out.println("OVERDUE Task " + num + " : " + t.getName());
                num = num + 1;
            }
        }
        System.out.println("Done, you have " + tasks.size() + " OVERDUE task in total!!!");
        System.out.println();
    }


    public List<String> returnListAllOverdueTasks() {
        List<String> overDueTasks = new ArrayList<>();
        overDueTasks.add("Here is all of your overdue task in list: " + name);
        for (Task t : tasks) {
            if (t.isOverdue()) {
                overDueTasks.add(ToDoAppUsage.INDENTATION + "Overdue Task: " + t.getName()
                        + " Due Date:" + ToDoAppUsage.sdf.format(t.getDueDate()));
            }
            overDueTasks.add("\nlist: " + name + " is done");
            return overDueTasks;
        }
        return overDueTasks;
    }


    // EFFECTS: Return names of all tasks in the ToDOList in a String
    public String getAllTaskAsString() {
        return tasks.stream().map(Task::getName).collect(Collectors.joining("\n"));
    }


    // EFFECTS: return list of string names of tasks in the list
    public List<String> getAllTaskAsListOfString() {
        List<String> ls = new ArrayList<String>();
        for (Task t : tasks) {
            ls.add(t.getName());
        }
        return ls;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ToDoList toDoList = (ToDoList) o;
        return name.equals(toDoList.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
