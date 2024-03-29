package model;

import model.exceptions.NoDueDateException;
import model.exceptions.OverDueException;
import model.exceptions.TaskNotFoundException;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public abstract class GeneralTask extends Element implements Task {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static final String INDENTATION = "     ";

    protected String name;
    protected Date dueDate;
    protected boolean status;
    protected ToDoList listBelonged;
    protected List<Element> elements;

    // constructor
    public GeneralTask() {
        this.status = false;
        this.dueDate = null;
        this.elements = new ArrayList<>();
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

    public List<Element> getElements() {
        return this.elements;
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
        this.dueDate = sdf.parse(dueDate);
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
        Date now = java.sql.Date.valueOf(currentDate);
        long diff = this.dueDate.getTime() - now.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

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

    @Override
    public void display(String indentLevel) {
        System.out.println(indentLevel + name);
        for (Element element : elements) {
            element.display(indentLevel + indentLevel);
        }
    }

    public void addElement(Element e) {
        elements.add(e);
    }

    public abstract boolean closeToDue() throws OverDueException, NoDueDateException;
}
