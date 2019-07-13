package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneralTask implements Task {
    public final Date CURRENT_DATE = new Date();
    public final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String taskName;
    private Date dueDate;
    private boolean status;

    // MODIFIES: this
    // EFFECTS: construct a task object set taskName to given taskName,
    //          set dueDate to null, set overdue to false
    public GeneralTask(String taskName) {
        this.taskName = taskName;
        this.dueDate = null;
        this.status = false;
    }

    // MODIFIES: this
    // EFFECTS: construct a task object set taskName to given taskName,
    //          set dueDate to gicen dueDate, set overdue to false
    public GeneralTask(String taskName, String dueDate) throws ParseException {
        this.taskName = taskName;
        this.dueDate = sdf.parse(dueDate);
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
    public String getStatus() {
        if (this.status) {
            return ("Done");
        } else {
            return ("Not Done");
        }
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
        if (this.dueDate.before(CURRENT_DATE)) {
            return true;
        }
        return false;


    }
}
