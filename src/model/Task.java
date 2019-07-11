package model;

public class Task {
    private String taskName;
    private int dayUntilDue;

    // EFFECTS: construct a task object set taskName to given taskName, set dayUntilDue to 0
    public Task(String taskName) {
        this.taskName = taskName;
        this.dayUntilDue = 0;
    }

    // getters
    public String getTaskName() {
        return taskName;
    }

    public int getDayUntilDue() {
        return dayUntilDue;
    }

    // setters
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDayUntilDue(int dayUntilDue) {
        this.dayUntilDue = dayUntilDue;
    }

}
