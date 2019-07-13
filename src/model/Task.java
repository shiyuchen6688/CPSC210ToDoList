package model;

public interface Task {
    // EFFECTS: return taskName
    String getTaskName();

    // EFFECTS: return dayUntilDue
    int getDayUntilDue();

    // EFFECTS: return overdue
    boolean getOverdue();

    // MODIFIES: this
    // EFFECTS: set this taskName to given taskName
    void setTaskName(String taskName);

    // MODIFIES: this
    // EFFECTS: set this dayUntilDue to given dayUntilDue
    void setDayUntilDue(int dayUntilDue);

    // MODIFIES: this
    // EFFECTS: set this overdue to given overdue
    void setOverdue(boolean overdue);
}
