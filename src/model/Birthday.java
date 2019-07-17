package model;

import ui.ToDoListUsage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Birthday extends GeneralTask implements Holiday{
    private String greeting;
    private String gift;

    // MODIFIES: this
    // EFFECTS: construct a task object set taskName to given taskName,
    //          set dueDate to null, set overdue to false
    public Birthday(String name, String date) throws ParseException {
        super();
        this.taskName = "Birthday of " + name;
        this.dueDate = ToDoListUsage.sdf.parse(date);
        this.status = false;
        this.greeting = "Happy Birthday " + name + " !";
        this.gift = "Birthday card for " + name;
    }

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

    // EFFECTS: return taskName
    @Override
    public String getTaskName() {
        return taskName;
    }

    // EFFECTS: return dueDate
    @Override
    public Date getDueDate() {
        return dueDate;
    }

    // EFFECTS: return status of this task
    @Override
    public boolean getStatus() {
        return this.status;
    }

    // MODIFIES: this
    // EFFECTS: set this taskName to given taskName
    @Override
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    // MODIFIES: this
    // EFFECTS: set this dueDate to given dueDate in format yyyy-MM-dd
    @Override
    public void setDueDate(String dueDate) throws ParseException {
        this.dueDate = ToDoListUsage.sdf.parse(dueDate);
    }

    // MODIFIES: this
    // EFFECTS: set this status to given status
    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }

    // EFFECTS: return true if this task is due, false otherwise
    @Override
    public boolean isOverdue() {
        LocalDate currentDate = LocalDate.now();
        if (!(this.dueDate == null) && this.dueDate.before(java.sql.Date.valueOf(currentDate))) {
            return true;
        }
        return false;
    }

    @Override
    public boolean closeToDue() {
        LocalDate currentDate = LocalDate.now();
        if (!(this.dueDate == null) && this.dueDate.before(java.sql.Date.valueOf(currentDate))) {
            return false;
        }
        Period period = Period.between(this.dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                currentDate);
        int days = period.getDays();
        if (days < 3) {
            return true;
        }
        return false;
    }

//    public static void main(String[] args) throws ParseException {
//        Birthday a = new Birthday("Shiyu", "2019-10-08");
//        Birthday b = new Birthday("Shiyu", "2019-10-08");
//        String c = "shiyu";
//        String d = "shiyu";
//        System.out.println(c == d);
//        System.out.println(c.equals(d));
//    }

}
