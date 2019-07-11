package ui.tabs;

import ui.ToDoListUsage;

import javax.swing.*;
import java.awt.*;

public abstract class Tab extends JPanel {
    private ToDoListUsage controller;

    //REQUIRES: ToDoListUsage controller that holds this tab
    public Tab(ToDoListUsage controller){
        this.controller = controller;
    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b){
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }

    //EFFECTS: returns the SmartHomeUI controller for this tab
    public ToDoListUsage getController(){
        return controller;
    }




}
