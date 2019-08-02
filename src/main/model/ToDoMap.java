package model;

import java.util.*;

public class ToDoMap extends Observable {
    private Map<String, ToDoList> toDoMap;

    // MODIFIES: this
    // EFFECTS: construct a ToDoMap with only general ToDoList
    public ToDoMap() {
        toDoMap = new HashMap<>();
        String generalName = "general";
        ToDoList generalList = new ToDoList("general");
        toDoMap.put(generalName, generalList);
    }


    public void addToDoList(String name) {
        if (!toDoMap.keySet().contains(name)) {
            ToDoList newList = new ToDoList(name);
            toDoMap.put(name, newList);
            newList.setMapBelonged(this);
        }


    }


    public void addToDoList(ToDoList newList) {
        String name = newList.getName();
        if (!toDoMap.keySet().contains(name)) {
            toDoMap.put(name, newList);
            newList.setMapBelonged(this);
        }


    }

//    // EFFECTS: rint all task in map
//    public void printAllTasks() {
//        for (String name : toDoMap.keySet()) {
//            System.out.println("Here is all your task in list: " + name);
//            toDoMap.get(name).printAllTasks();
//        }
//    }

    public List<String> returnAllMapTasks() {
        List<String> result = new ArrayList<>();
        for (String name : toDoMap.keySet()) {
            result.addAll(toDoMap.get(name).returnAllListTasks());
        }
        return result;
    }

    public List<String> returnMapAllOverdueTasks() {
        List<String> overdueTasks = new ArrayList<>();
        for (String name : toDoMap.keySet()) {
            overdueTasks.addAll(toDoMap.get(name).returnListAllOverdueTasks());
        }
        return overdueTasks;
    }

//    // EFFECTS: rint all task in map
//    public void printAllOverdueTasks() {
//        for (String name : toDoMap.keySet()) {
//            System.out.println("Here is all your overdue task in list: " + name);
//            toDoMap.get(name).printAllOverdueTasks();
//        }
//    }

    public Boolean contains(String toDoListName) {
        return  toDoMap.keySet().contains(toDoListName);
//        if (toDoMap.get(toDoListName) != null) {
//            return true;
//        }
//        return false;
    }

    public int size() {
        return toDoMap.size();
    }

    public Map<String, ToDoList> getMap() {
        return toDoMap;
    }


    public ToDoList getList(String name) {
        return toDoMap.get(name);
    }


}
