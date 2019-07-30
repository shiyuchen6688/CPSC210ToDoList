package model;

import model.exceptions.NoDueDateException;
import model.exceptions.OverDueException;
import ui.ToDoListUsage;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

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
        this.dueDate = ToDoListUsage.sdf.parse(dueDate);
    }

    // EFFECTS: if given task has no due date, throw NoDueDateException
    //          if given task is already due, throw OverDueException
    //          otherwise, produce true if given task is due in 10 days
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
