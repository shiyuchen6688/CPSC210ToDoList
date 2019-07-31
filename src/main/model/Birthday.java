package model;

import model.exceptions.NoDueDateException;
import model.exceptions.OverDueException;
import ui.ToDoAppUsage;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Birthday extends GeneralTask implements Holiday {

    private String greeting;
    private String gift;


    // MODIFIES: this
    // EFFECTS: construct a task object set name to given name,
    //          set dueDate to null, set overdue to false
    public Birthday(String name, String date) throws ParseException {
        super();
        this.name = "Birthday of " + name;
        this.dueDate = ToDoAppUsage.sdf.parse(date);
        this.status = false;
        this.greeting = "Happy Birthday " + name + " !";
        this.gift = "Birthday card for " + name;
    }

    // MODIFIES: this
    // EFFECTS: construct a task object set name to given name,
    //          set dueDate to null, set overdue to false
    public Birthday(String name)  {
        super();
        this.name = "Birthday of " + name;
        this.dueDate = null;
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
        if (this.dueDate == null) {
            throw new NoDueDateException("Due date for task: " + name + " has not been set yet");
        }
        if (isOverdue()) {
            throw new OverDueException("Due date for task: " + name + " has passed");
        }
        Date now = java.sql.Date.valueOf(currentDate);
        long diff = this.dueDate.getTime() - now.getTime();
        TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if (diff < 3) {
            return true;
        }
        return false;
    }




}
