package model;

import java.text.ParseException;
import java.util.Date;

public interface Task {
    // EFFECTS: return taskName
    String getTaskName();

    // EFFECTS: return dayUntilDue
    Date getDueDate();

    // EFFECTS: return status of this task
    boolean getStatus();

    // MODIFIES: this
    // EFFECTS: set this taskName to given taskName
    void setTaskName(String taskName);

    // MODIFIES: this
    // EFFECTS: set this dueDate to given year, month, date
    void setDueDate(String dueDate) throws ParseException;

    // MODIFIES: this
    // EFFECTS: set this overdue to given overdue
    void setStatus(boolean overdue);

    // EFFECTS: return true if this task is due, false otherwise
    boolean isOverdue();
}
