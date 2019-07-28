package model;

import exceptions.NoDueDateException;
import exceptions.OverDueException;
import ui.ToDoListUsage;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Birthday extends GeneralTask implements Holiday{

    private String greeting;
    private String gift;


    // MODIFIES: this
    // EFFECTS: construct a task object set name to given name,
    //          set dueDate to null, set overdue to false
    public Birthday(String name, String date) throws ParseException {
        super();
        this.name = "Birthday of " + name;
        this.dueDate = ToDoListUsage.sdf.parse(date);
        this.status = false;
        this.greeting = "Happy Birthday " + name + " !";
        this.gift = "Birthday card for " + name;
    }


    // setters

    // MODIFIES: this
    // EFFECTS: set greeting
    @Override
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }


    // MODIFIES: this
    // EFFECTS: set birthday gift
    @Override
    public void setGift(String gift) {
        this.gift = gift;
    }


    // getters

    // EFFECTS: return birthday greeting
    @Override
    public String getGreeting() {
        return this.greeting;
    }

    // EFFECTS: return birthday gift
    @Override
    public String getGift() {
        return this.gift;
    }



    // EFFECTS: if given task has no due date, throw NoDueDateException
    //          if given task is already due, throw OverDueException
    //          otherwise, produce true if given task is due in 3 days
    @Override
    public boolean closeToDue() throws OverDueException, NoDueDateException {
        LocalDate currentDate = LocalDate.now();
        if (!(this.dueDate == null)) {
            throw new NoDueDateException("Due date for task: " + name + " has not been set yet");
        }
        if (isOverdue()) {
            throw new OverDueException("Due date for task: " + name + " has passed");
        }
        Period period = Period.between(this.dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                currentDate);
        int days = period.getDays();
        if (days < 3) {
            return true;
        }
        return false;
    }




}
