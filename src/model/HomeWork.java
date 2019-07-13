//package model;
//
//// TODO check and fix this class
//public class HomeWork implements Task {
//    private String taskName;
//    private int dayUntilDue;
//    private boolean overdue;
//
//    // MODIFIES: this
//    // EFFECTS: construct a task object set taskName to given taskName,
//    //          set dayUntilDue to 0, set overdue to false
//    public HomeWork(String taskName) {
//        this.taskName = taskName;
//        this.dayUntilDue = 0;
//        this.overdue = false;
//    }
//
//    // EFFECTS: return taskName
//    @Override
//    public String getTaskName() {
//        return taskName;
//    }
//
//    // EFFECTS: return dayUntilDue
//    @Override
//    public int getDueDate() {
//        return dayUntilDue;
//    }
//
//    // EFFECTS: return overdue
//    @Override
//    public boolean getOverdue() {return overdue;}
//
//    // MODIFIES: this
//    // EFFECTS: set this taskName to given taskName
//    @Override
//    public void setTaskName(String taskName) {
//        this.taskName = taskName;
//    }
//
//    // MODIFIES: this
//    // EFFECTS: set this dueDate to given dueDate
//    @Override
//    public void setDueDate(int dueDate) {
//        this.dayUntilDue = dueDate;
//    }
//
//    // MODIFIES: this
//    // EFFECTS: set this overdue to given overdue
//    @Override
//    public void setOverdue(boolean overdue) {
//        this.overdue = overdue;
//    }
//}
