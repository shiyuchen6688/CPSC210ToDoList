package ui;

import model.ToDoList;

import javax.swing.*;

public class ToDoListUsage extends JFrame{
    public static final int HOME_TAB_INDEX = 0;
    public static final int SETTINGS_TAB_INDEX = 1;
    public static final int REPORT_TAB_INDEX = 2;

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

    }

    private void loadTabs() {
        JPanel homeTab = new HomeTab(this);
        JPanel settingsTab = new SettingsTab(this);
        JPanel reportTab = new ReportTab(this);

        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");
        sidebar.add(settingsTab, SETTINGS_TAB_INDEX);
        sidebar.setTitleAt(SETTINGS_TAB_INDEX, "Settings");
        sidebar.add(reportTab, REPORT_TAB_INDEX);
        sidebar.setTitleAt(REPORT_TAB_INDEX, "Report");
    }

}
