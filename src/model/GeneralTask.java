package model;

import ui.ToDoListUsage;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public abstract class GeneralTask implements Task {
    protected String taskName;
    protected Date dueDate;
    protected boolean status;

    public GeneralTask() {
        this.status = false;
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

    // EFFECTS: return true if dueDate is set and task is due, false otherwise
    @Override
    public boolean isOverdue() {
        Date currentDate = java.sql.Date.valueOf(LocalDate.now());
        if (!(this.dueDate == null) && this.dueDate.before(currentDate)) {
            return true;
        }
        return false;

    }
    @Override
    public int getDayUntilDue() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(currentDate,this.dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return period.getDays();
    }

    public abstract boolean closeToDue();
}
