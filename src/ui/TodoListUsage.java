package ui;

import model.ToDoList;

public class TodoListUsage {

    private static ToDoList toDoList;
    private static Tool tool;


    public static void main(String[] args) {
        toDoList = new ToDoList();
        tool = new Tool(toDoList);


        tool.handleUserInput();
        System.out.println("Have a good day!");


    }

}
