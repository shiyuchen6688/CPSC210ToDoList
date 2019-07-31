package model;

import model.exceptions.NoDueDateException;
import model.exceptions.OverDueException;
import model.exceptions.TaskNotFoundException;
import ui.ToDoAppUsage;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public abstract class GeneralTask implements Task {

    protected String name;
    protected Date dueDate;
    protected boolean status;
    protected ToDoList listBelonged;

    // constructor
    public GeneralTask() {
        this.status = false;
        this.dueDate = null;
    }


    // getters

    // EFFECTS: return name
    @Override
    public String getName() {
        return name;
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

    // EFFECTS: return the list this class belongded to
    @Override
    public ToDoList getListBelonged() {
        return this.listBelonged;
    }


    // setters

    // MODIFIES: this
    // EFFECTS: set this name to given name
    @Override
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: set this dueDate to given dueDate in format yyyy-MM-dd
    @Override
    public void setDueDate(String dueDate) throws ParseException {
        this.dueDate = ToDoAppUsage.sdf.parse(dueDate);
    }

    // MODIFIES: this
    // EFFECTS: set this status to given status
    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }

    // MODIFIES: this
    // EFFECTS: set list belonged to given toDoList
    @Override
    public void setListBelonged(ToDoList toDoList) {
        if (this.listBelonged != toDoList) {
            this.listBelonged = toDoList;
            toDoList.addTask(this);

        }
    }


    // EFFECTS: return true if dueDate is set and task is due, false otherwise
    @Override
    public boolean isOverdue() {
        LocalDate currentDate = LocalDate.now();
        if (dueDate != null) {
            return this.dueDate.before(java.sql.Date.valueOf(currentDate));
        }
        return false;
    }


    // EFFECTS: return number of day until this task is due
    @Override
    public int getDayUntilDue() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(currentDate,
                this.dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return period.getDays();
    }


    // MODIFIES: this
    // EFFECTS: remove listBelonged of this task
    @Override
    public void removeListBelonged(ToDoList list) throws TaskNotFoundException {
        if (this.listBelonged == list) {
            this.listBelonged = null;
            list.deleteTask(this.getName());
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeneralTask)) {
            return false;
        }
        GeneralTask that = (GeneralTask) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public abstract boolean closeToDue() throws OverDueException, NoDueDateException;
}
