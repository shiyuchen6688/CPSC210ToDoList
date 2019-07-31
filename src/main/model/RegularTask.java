package model;

import model.exceptions.NoDueDateException;
import model.exceptions.OverDueException;
import ui.ToDoAppUsage;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

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
        if (dueDate == null) {
            this.dueDate = null;
        } else {
            this.dueDate = ToDoAppUsage.sdf.parse(dueDate);
        }
        this.status = false;
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
