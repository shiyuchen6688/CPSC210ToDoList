package model;

public class GeneralTask implements Task {
    private String taskName;
    private int dayUntilDue;
    private boolean overdue;

    // MODIFIES: this
    // EFFECTS: construct a task object set taskName to given taskName,
    //          set dayUntilDue to 0, set overdue to false
    public GeneralTask(String taskName) {
        this.taskName = taskName;
        this.dayUntilDue = 0;
        this.overdue = false;
    }

    // EFFECTS: return taskName
    @Override
    public String getTaskName() {
        return taskName;
    }

    // EFFECTS: return dayUntilDue
    @Override
    public int getDayUntilDue() {
        return dayUntilDue;
    }

    // EFFECTS: return overdue
    @Override
    public boolean getOverdue() {return overdue;}

    // MODIFIES: this
    // EFFECTS: set this taskName to given taskName
    @Override
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    // MODIFIES: this
    // EFFECTS: set this dayUntilDue to given dayUntilDue
    @Override
    public void setDayUntilDue(int dayUntilDue) {
        this.dayUntilDue = dayUntilDue;
    }

    // MODIFIES: this
    // EFFECTS: set this overdue to given overdue
    @Override
    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

}
