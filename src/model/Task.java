package model;

public class Task {
    private String taskName;
    private int dayUntilDue;

    public Task(String taskName) {
        this.taskName = taskName;
        this.dayUntilDue = 0;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getDayUntilDue() {
        return dayUntilDue;
    }

    public void changeTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDayUntilDue(int dayUntilDue) {
        this.dayUntilDue = dayUntilDue;
    }

}
