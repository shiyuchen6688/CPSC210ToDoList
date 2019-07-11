package ui;

import model.ToDoList;
import ui.tabs.HomeTab;

import javax.swing.*;

public class ToDoListUsage extends JFrame{
    public static final int HOME_TAB_INDEX = 0;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private JTabbedPane sidebar;

    private static ToDoList toDoList;
    private static Tool tool;


    public static void main(String[] args) {
        new ToDoListUsage();


        tool.handleUserInput();
        System.out.println("Have a good day!");


    }


    // Used some part from SmartHomeUI
    public ToDoListUsage() {
        super("Welcome, ToDo List console");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        toDoList = new ToDoList();
        tool = new Tool(toDoList);

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadTabs();
        add(sidebar);

        setVisible(true);
    }

    private void loadTabs() {
        JPanel homeTab = new HomeTab(this);

        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "ToDo Home");
    }

    // EFFECTS: return this toDoList
    public ToDoList getToDoList() {
        return toDoList;
    }

}
