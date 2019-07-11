package model;

public class Task {
    private String taskName;
    private int dayUntilDue;
    private boolean overdue;

    // MODIFIES: this
    // EFFECTS: construct a task object set taskName to given taskName,
    //          set dayUntilDue to 0, set overdue to false
    public Task(String taskName) {
        this.taskName = taskName;
        this.dayUntilDue = 0;
        this.overdue = false;
    }

    // EFFECTS: return taskName
    public String getTaskName() {
        return taskName;
    }

    // EFFECTS: return dayUntilDue
    public int getDayUntilDue() {
        return dayUntilDue;
    }

    // EFFECTS: return overdue
    public boolean getOverdue() {return overdue;}

    // MODIFIES: this
    // EFFECTS: set this taskName to given taskName
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    // MODIFIES: this
    // EFFECTS: set this dayUntilDue to given dayUntilDue
    public void setDayUntilDue(int dayUntilDue) {
        this.dayUntilDue = dayUntilDue;
    }

    // MODIFIES: this
    // EFFECTS: set this overdue to given overdue
    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

}
