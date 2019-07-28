package model;

import java.util.HashMap;
import java.util.Map;

public class ToDoMap {
    private Map<String, ToDoList> toDoMap;

    // MODIFIES: this
    // EFFECTS: construct a ToDoMap with only general ToDoList
    public ToDoMap() {
        toDoMap = new HashMap<>();
        String generalName = "general";
        ToDoList generalList = new ToDoList("General");
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

    // EFFECTS: rint all task in map
    public void printAllTasks() {
        for(String name: toDoMap.keySet()) {
            System.out.println("Here is all your task in list: " + name);
            toDoMap.get(name).printAllTasks();
        }
    }

    // EFFECTS: rint all task in map
    public void printAllOverdueTasks() {
        for(String name: toDoMap.keySet()) {
            System.out.println("Here is all your overdue task in list: " + name);
            toDoMap.get(name).printAllOverdueTasks();
        }
    }

    public Boolean contains(String toDoListName) {
        if(toDoMap.get(toDoListName) != null) {
            return true;
        }
        return false;
    }

    // TODO need test
    public int size() {
        return toDoMap.size();
    }

    // TODO return num list inside the map
    public Map<String, ToDoList> getMap() {
        return toDoMap;
    }


    public ToDoList getList(String name) {
        return toDoMap.get(name);
    }



}
