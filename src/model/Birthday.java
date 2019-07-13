package model;

public class Birthday implements Holiday, Task {
    // TODO: not single point control, could have used an abstract class, just to have two interface
    private String taskName;
    private int dayUntilDue;
    private boolean overdue;
    private String greeting;
    private String gift;

    public Birthday(String name) {
        this.taskName = "Birthday of " + name;
        this.dayUntilDue = 0;
        this.overdue = false;
        this.greeting = "HappyBirthday! " + name;
        this.gift = "Birthday card with " + name + "'s name on it";
    }

    @Override
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @Override
    public void setGift(String gift) {
        this.gift = gift;
    }

    @Override
    public String getGreeting() {
        return this.greeting;
    }

    @Override
    public String getGift() {
        return this.gift;
    }

    @Override
    public String getTaskName() {
        return this.taskName;

    }

    @Override
    public int getDayUntilDue() {
        return this.dayUntilDue;
    }

    @Override
    public boolean getOverdue() {
        return this.overdue;
    }

    @Override
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void setDayUntilDue(int dayUntilDue) {
        this.dayUntilDue = dayUntilDue;
    }

    @Override
    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }
}
