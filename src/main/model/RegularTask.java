package model;

import ui.ToDoAppUsage;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RegularTask extends GeneralTask {

    // constructor
    // MODIFIES: this
    // EFFECTS: construct a task object set name to given name,
    //          set dueDate to null, set overdue to false
    public RegularTask(String taskName) {

        this.name = taskName;
        this.dueDate = null;
        this.status = false;
    }


    // MODIFIES: this
    // EFFECTS: construct a task object set name to given name,
    //          set dueDate to gicen dueDate, set overdue to false
    public RegularTask(String taskName, String dueDate) throws ParseException {
        this.name = taskName;
        this.dueDate = ToDoAppUsage.sdf.parse(dueDate);
        this.status = false;
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
        int day = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if (day < 3) {
            return true;
        }
        return false;
    }
}
