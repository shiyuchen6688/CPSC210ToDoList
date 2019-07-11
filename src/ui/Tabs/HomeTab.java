package ui.Tabs;

import ui.ButtonNames;
import ui.ToDoListUsage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeTab extends Tab{
    private static final String INIT_GREETING = "Welcome to your ToDo List";
    private JLabel greeting;

    //EFFECTS: constructs a home tab for console with buttons and a greeting
    public HomeTab(ToDoListUsage controller) {
        super(controller);

        setLayout(new GridLayout(1, 1));

        placeGreeting();
        placeHomeButtons();
//        placeStatusButton();
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting(){
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    //EFFECTS: creates Arrive and Leave buttons that change greeting message when clicked
    private void placeHomeButtons(){
        JButton b1 = new JButton(ButtonNames.OVERDUETASK.getValue());
        JButton b2 = new JButton(ButtonNames.ALLTASK.getValue());

        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.add(b2);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if(buttonPressed.equals(ButtonNames.OVERDUETASK.getValue())) {
                    greeting.setText("TODO: NEED TO PRINT OVERDUES");
                }

            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if(buttonPressed.equals(ButtonNames.ALLTASK.getValue())) {
                    getController().getToDoList().printAllTask();
                    greeting.setText("TODO: NEED TO PRINT ALL TASKS");
                }
            }
        });

        this.add(buttonRow);
    }


//    //EFFECTS: constructs a status button that switches to the report tab on the console
//    private void placeStatusButton(){
//        JPanel statusBlock = new JPanel();
//        JButton statusButton = new JButton(ButtonNames.GO_TO_REPORT.getValue());
//        statusBlock.add(formatButtonRow(statusButton));
//
//        statusButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String buttonPressed = e.getActionCommand();
//                if(buttonPressed.equals(ButtonNames.GO_TO_REPORT.getValue())){
//                    getController().getTabbedPane().setSelectedIndex(SmartHomeUI.REPORT_TAB_INDEX);
//                }
//            }
//        });
//
//        this.add(statusBlock);
//    }
}
