package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Birthday implements Holiday, Task{
    public final Date CURRENT_DATE = new Date();
    public final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String taskName;
    private Date dueDate;
    private boolean status;
    private String greeting;
    private String gift;

    // MODIFIES: this
    // EFFECTS: construct a task object set taskName to given taskName,
    //          set dueDate to null, set overdue to false
    public Birthday(String name) {
        this.taskName = "Birthday of " + name;
        this.dueDate = null;
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
        this.dueDate = sdf.parse(dueDate);
    }

    // MODIFIES: this
    // EFFECTS: set this status to given status
    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }

    // EFFECTS: return true if this task is due, false otherwise
    public boolean isOverdue() {
        if (!(this.dueDate == null) && this.dueDate.before(CURRENT_DATE)) {
                return true;
        }
        return false;
    }
}
