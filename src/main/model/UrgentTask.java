package model;

import model.exceptions.NoDueDateException;
import model.exceptions.OverDueException;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UrgentTask extends GeneralTask {

    // constructor

    // MODIFIES: this
    // EFFECTS: construct a urgent task with given taskName
    public UrgentTask(String taskName) {
        this.name = taskName;
    }

    // MODIFIES: this
    // EFFECTS: construct a urgent task with given taskName and dueDate
    public UrgentTask(String taskName, String dueDate) throws ParseException {
        this.name = taskName;
        this.dueDate = GeneralTask.sdf.parse(dueDate);
    }

    // EFFECTS: if given task has no due date, throw NoDueDateException
    //          if given task is already due, throw OverDueException
    //          otherwise, produce true if given task is due in 10 days
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
        int  day =  (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if (day < 10) {
            return true;
        }
        return false;
    }
}
